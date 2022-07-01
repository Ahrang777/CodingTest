package study.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
입력1
3
0 0 0
0 0 0
0 9 0

출력1
0
================
입력2
3
0 0 1
0 0 0
0 9 0

출력2
3
================
입력3
4
4 3 2 1
0 0 0 0
0 0 9 0
1 2 3 4

출력3
14
================
입력4
6
5 4 3 2 3 4
4 3 2 3 4 5
3 2 9 5 6 6
2 1 2 3 4 5
3 2 1 6 5 4
6 6 6 6 6 6

출력4
60
================
입력5
6
6 0 6 0 6 1
0 0 0 0 0 2
2 3 4 5 6 6
0 0 0 0 0 2
0 2 0 0 0 0
3 9 3 0 0 1

출력5
48
================
입력6
6
1 1 1 1 1 1
2 2 6 2 2 3
2 2 5 2 2 3
2 2 2 4 6 3
0 0 0 0 0 6
0 0 0 0 0 9

출력6
39
 */

/**
 * https://www.acmicpc.net/problem/16236
 *
 * 아기 상어
 * 삼성전자 공채
 */
public class Baekjoon16236 {

    public static class Position implements Comparable<Position> {
        private int dist;   // 이동 시간
        private int x;
        private int y;

        public Position(int dist, int x, int y) {
            this.dist = dist;
            this.x = x;
            this.y = y;
        }

        public int getDist() {
            return dist;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public int compareTo(Position other) {
            if (this.dist != other.dist) {
                return Integer.compare(this.dist, other.dist);
            }
            if (this.x != other.x) {
                return Integer.compare(this.x, other.x);
            }
            return Integer.compare(this.y, other.y);
        }
    }

    public static int n;
    public static int[][] map = new int[20][20];

    public static final int BLANK = 0;
    public static final int SHARK = 9;

    // U, L
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static Position shark;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;

        n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(stk.nextToken());
                if (x == SHARK) {
                    x = BLANK;
                    shark = new Position(0, i, j);
                }
                map[i][j] = x;
            }
        }

        int eat = 0;    // 먹은 횟수
        int time = 0;   // 시간누적
        int size = 2;   // 상어 크기
        /*while (true) {
            // 다음 먹을 물고기
            PriorityQueue<Position> fishes = new PriorityQueue<>();
            int[][] distance = new int[n][n];

            // -1 인 경우는 방문안한 칸
            for (int i = 0; i < n; i++) {
                Arrays.fill(distance[i], -1);
            }

            Queue<Position> q = new LinkedList<>();
            q.offer(shark);

            int x = shark.getX();
            int y = shark.getY();

            distance[x][y] = 0;

            // 거리테이블 갱신, 다음 먹을수 있는 물고기들 찾기
            while (!q.isEmpty()) {
                Position now = q.poll();
                x = now.getX();
                y = now.getY();

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        // 아직 방문x, 이동 가능한 칸
                        if (distance[nx][ny] == -1 && map[nx][ny] <= size) {
                            distance[nx][ny] = distance[x][y] + 1;
                            q.offer(new Position(distance[nx][ny], nx, ny));
                            if (1 <= map[nx][ny] && map[nx][ny] <= 6 && map[nx][ny] < size) {
                                fishes.offer(new Position(distance[nx][ny], nx, ny));
                            }
                        }
                    }
                }
            }

            // 더이상 먹을 수 있는 물고기 없는 경우
            if (fishes.isEmpty()) {
                break;
            }

            // 먹을 수 있는 물고기 있는 경우
            // PriorityQueue 이고 Position 은 거리, 위, 왼쪽 우선순위
            // fish는 먹을 물고기
            Position fish = fishes.poll();
            eat += 1;   //먹은 횟수 증가
            time += fish.getDist(); // 시간 증가
            x = fish.getX();
            y = fish.getY();
            map[x][y] = BLANK;  // 먹은 처리
            if (eat == size) {  // 크기만큼 먹으면 크기 증가, 먹은 횟수 초기화
                size += 1;
                eat = 0;
            }
            shark = new Position(0, x, y); // 먹은 물고기 위치에서 다시 위 과정 반복
        }*/

        // 다음 먹을 물고기
        PriorityQueue<Position> fishes = new PriorityQueue<>();

        while (true) {
            boolean[][] visited = new boolean[n][n];

            Queue<Position> q = new LinkedList<>();
            q.offer(shark);

            int x = shark.getX();
            int y = shark.getY();

            visited[x][y] = true;

            // 거리테이블 갱신, 다음 먹을수 있는 물고기들 찾기
            while (!q.isEmpty()) {
                Position now = q.poll();
                x = now.getX();
                y = now.getY();

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        // 아직 방문x, 이동 가능한 칸
                        if (!visited[nx][ny] && map[nx][ny] <= size) {
                            visited[nx][ny] = true;
                            int dist = now.getDist() + 1;
                            q.offer(new Position(dist, nx, ny));
                            if (map[nx][ny] != BLANK && map[nx][ny] < size) {
                                fishes.offer(new Position(dist, nx, ny));
                            }
                        }
                    }
                }
            }

            // 더이상 먹을 수 있는 물고기 없는 경우
            if (fishes.isEmpty()) {
                break;
            }

            // 먹을 수 있는 물고기 있는 경우
            // PriorityQueue 이고 Position 은 거리, 위, 왼쪽 우선순위
            // fish는 먹을 물고기
            Position fish = fishes.poll();
            eat += 1;   //먹은 횟수 증가
            time += fish.getDist(); // 시간 증가
            x = fish.getX();
            y = fish.getY();
            map[x][y] = BLANK;  // 먹은 처리
            if (eat == size) {  // 크기만큼 먹으면 크기 증가, 먹은 횟수 초기화
                size += 1;
                eat = 0;
            }
            shark = new Position(0, x, y); // 먹은 물고기 위치에서 다시 위 과정 반복
            fishes.clear();
        }

        System.out.println(time);
    }
}
