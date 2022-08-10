package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/19238
 *
 * 스타트 택시
 * 삼성전자 공채
 * 
 * 다시 풀기
 */
public class Baekjoon19238 {

    static class Taxi {
        int move, x, y;

        public Taxi(int move, int x, int y) {
            this.move = move;
            this.x = x;
            this.y = y;
        }
    }

    static class Passenger implements Comparable<Passenger> {
        int id, sx, sy, dx, dy;

        public Passenger() {

        }

        @Override
        public int compareTo(Passenger other) {
            if (this.sx != other.sx) {
                return Integer.compare(this.sx, other.sx);
            }

            return Integer.compare(this.sy, other.sy);
        }
    }

    // f: 연료의 양
    public static int n, m, fuel;
    public static Taxi taxi;
    public static Passenger taken;

    public static int[][] map;
    public static Map<Integer, Passenger> passMap = new HashMap<>();

    public static final int BLANK = 0;
    public static final int WALL = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        // 1 ~ N 으로 입력되서 -1
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        taxi = new Taxi(0, x, y);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            // 1이 벽이라서 2부터 승객
            Passenger p = new Passenger();
            p.id = i + 2;
            p.sx = Integer.parseInt(st.nextToken()) - 1;
            p.sy = Integer.parseInt(st.nextToken()) - 1;
            p.dx = Integer.parseInt(st.nextToken()) - 1;
            p.dy = Integer.parseInt(st.nextToken()) - 1;

            passMap.put(p.id, p);

            map[p.sx][p.sy] = p.id;
        }
        
        solution();
        
    }

    public static void solution() {
        
        while (!passMap.isEmpty()) {
            // 손님 위치로 이동
            taken = null;
            int toStartFuel = bfs();
            fuel -= toStartFuel;

            if (fuel < 0) break;

            int toDestFuel = bfs();
            fuel -= toDestFuel;

            if (fuel < 0) break;

            fuel += toDestFuel * 2;
        }

        System.out.println(fuel < 0 ? -1 : fuel);
    }

    // 소모되는 연료의 양
    public static int bfs() {
        Queue<Taxi> q = new LinkedList<>();

        // 다음 손님 후보
        PriorityQueue<Passenger> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        int move = taxi.move;
        
        q.offer(taxi);
        visited[taxi.x][taxi.y] = true;
        

        while (!q.isEmpty()) {
            Taxi now = q.poll();

            // 이동 중 연료 부족한 경우 큰값 전달해서 solution에서 fuel < 0 되도록한다.
            if (fuel < now.move) {
                return Integer.MAX_VALUE;
            }

            // 같은 거리의 손님들만 비교하기 위해서
            // 큐에서 뽑은 택시의 거리가 이전과 달라지면 break
            // 이렇게 해야 같은 거리의 손님들끼리 행, 열로 비교 가능
            if (move != now.move && !pq.isEmpty()) {
                break;
            }

            move = now.move;
            
            // 손님 태운 경우
            if (taken != null) {
                // 현재 목적지 인 경우 
                if (now.x == taken.dx && now.y == taken.dy) {
                    passMap.remove(taken.id);
                    taken = null;
                    taxi = new Taxi(0, now.x, now.y);
                    return move;
                }
            }
            // 손님 없는 경우
            // 손님 찾기
            else {
                int id = map[now.x][now.y];

                if (id > 1) {
                    Passenger p = passMap.get(id);
                    pq.offer(p);
                }
            }

            // 위치 이동
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (map[nx][ny] != WALL && !visited[nx][ny]) {
                    q.offer(new Taxi(now.move + 1, nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        // while문이 끝났는데 pq가 비어있으면 벽에 막혀서 도달 못한것
        if (pq.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        // 손님 결정
        taken = pq.poll();
        
        // 택시를 손님 위치로 이동
        taxi = new Taxi(0, taken.sx, taken.sy);
        map[taken.sx][taken.sy] = BLANK;
        return move;
    }
}
