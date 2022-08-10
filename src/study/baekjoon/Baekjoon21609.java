package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/21609
 *
 * 상어 중학교
 * 삼성전자 공채
 * 
 * 다시 풀기
 */
public class Baekjoon21609 {

    /*// 블록 그룹
    static class Block implements Comparable<Block> {
        // 블록 크기, 무지개 블록 개수, 기준 블록 x, y
        int size, r_size, x, y;

        public Block(int size, int r_size, int x, int y) {
            this.size = size;
            this.r_size = r_size;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Block other) {
            if (this.size != other.size) {
                return Integer.compare(this.size, other.size);
            } else if (this.r_size != other.r_size) {
                return Integer.compare(this.r_size, other.r_size);
            } else if (this.x != other.x) {
                return Integer.compare(this.x, other.x);
            }

            return Integer.compare(this.y, other.y);
        }
    }

    static int n, m, result;
    static int[][] map;
    static boolean[][] visited;

    static PriorityQueue<Block> pq = new PriorityQueue<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static final int BLANK = -2;
    static final int BLACK = -1;
    static final int RAINBOW = 0;

    static void solution() {
        while (true) {
            // group 만들기
            grouping();

            // 대상 그룹 찾기
            if (pq.isEmpty()) break;

            Block maxBlock = pq.poll();

            // 제거될 부분 확인하기 위해
            visited = new boolean[n][n];
            bfs(maxBlock.x, maxBlock.y);
            removeBlock();

            // 중력 적용
            gravity();

            // 반시계 90도
            map = rotate();

            // 중력 적용
            gravity();
            pq.clear();
        }

        System.out.println(result);
    }

    static int[][] rotate() {
        int[][] tmp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[n - j - 1][i] = map[i][j];
            }
        }
        return tmp;
    }

    static void gravity() {
        *//*for (int i = 0; i < n; i++) {   // 열
            for (int j = n - 1; j >= 0; j--) {  // 행

                if (map[j][i] == BLACK || map[j][i] == BLANK) continue;

                int k = j;
                while (true) {
                    k++;
                    if (map[k][i] != BLANK || !isRange(k, i)) break;

                    int tmp = map[k][i];
                    map[k][i] = map[k - 1][i];
                    map[k - 1][i] = tmp;
                }
            }
        }*//*
        for(int j = 0 ; j < n ; j++) {
            for(int i = n-1 ; i >= 0 ; i--) {
                for(int k = i ; k < n-1 ; k ++) {
                    if(map[k][j] == -1) continue;
                    if(map[k][j] != -2 && map[k+1][j] == -2) {
                        int temp = map[k][j];
                        map[k][j] = -2;
                        map[k+1][j] = temp;
                    }
                }
            }
        }
    }

    static void removeBlock() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    cnt++;
                    map[i][j] = BLANK;
                }
            }
        }

        result += (int) Math.pow(cnt, 2);
    }

    // bfs
    static void grouping() {
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 일반블록, 처음 확인
                if (map[i][j] > RAINBOW && !visited[i][j]) {
                    bfs(i, j);
                    setRainbow();
                }
            }
        }
    }

    // 무지개 블록은 방문 안한 처리?
    // 다른 그룹이랑 크기 비교할때 해당 무지개 블록이 A그룹에 속할때 B 그룹에 속할때 큰거 비교위해서
    static void setRainbow() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == RAINBOW)
                    visited[i][j] = false;
            }
        }
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        int index = map[x][y];
        int size = 1;
        int r_size = 0;
        visited[x][y] = true;

        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 넘는 경우, 방문한적 있는 경우
                if (!isRange(nx, ny) || visited[nx][ny]) continue;

                if (map[nx][ny] == RAINBOW || map[nx][ny] == index) {
                    if (map[nx][ny] == RAINBOW) r_size++;

                    size++;
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        // 행 작은것, 열 작은 것 순으로 찾기때문에 처음 x, y가 기준블록의 x, y
        if (size >= 2) pq.add(new Block(size, r_size, x, y));
    }
    
    // 경계 체크
    static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
    }*/

    static int N,M, answer;
    static int[][] map;
    static boolean[][] visited;
    static LinkedList<Block> list = new LinkedList<>();
    static Queue<int []> queue = new LinkedList<int[]>();
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static private class Block implements Comparable<Block>{
        int totalPoint, rainbowPoint, row, col;
        public Block(int totalPoint, int rainbowPoint, int row, int col) {
            this.totalPoint = totalPoint;
            this.rainbowPoint = rainbowPoint;
            this.row = row;
            this.col = col;
        }

        public int compareTo(Block o) {
            if(this.totalPoint == o.totalPoint) {
                if(this.rainbowPoint == o.rainbowPoint) {
                    if(this.row == o.row) {
                        return o.col - this.col;
                    }
                    return o.row - this.row;
                }
                return o.rainbowPoint - this.rainbowPoint;
            }

            return o.totalPoint - this.totalPoint;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            // 블록 그룹 찾기 2 이상 아니면 break;
            visited = new boolean[N][N];
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j< N ; j++) {
                    if(map[i][j] > 0 && visited[i][j] == false) {
                        bfs(i, j, true);
                    }
                }
            }
            if(list.isEmpty()) break;

            Collections.sort(list);
            // 찾은 블록 없애기
            visited = new boolean[N][N];
            bfs(list.get(0).row, list.get(0).col, false);
            removeBlock();

            // 중력
            gravity();
            // 반시계
            map = rotate();
            // 중력
            gravity();

            list.clear();
        }
        System.out.println(answer);
    }

    private static int[][] rotate() {
        int[][] temp = new int[N][N];

        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                temp[N-j-1][i]=map[i][j];
            }
        }

        return temp;
    }

    private static void gravity() {
        for(int j = 0 ; j < N ; j++) {
            for(int i = N-1 ; i >= 0 ; i--) {
                for(int k = i ; k < N-1 ; k ++) {
                    if(map[k][j] == -1) continue;
                    if(map[k][j] != -2 && map[k+1][j] == -2) {
                        int temp = map[k][j];
                        map[k][j] = -2;
                        map[k+1][j] = temp;
                    }
                }
            }
        }
    }

    private static void removeBlock() {
        int count = 0;
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                if(visited[i][j] == true) {
                    count++;
                    map[i][j] = -2;
                }
            }
        }

        answer += (int)Math.pow(count, 2);
    }

    private static void bfs(int x, int y, boolean flag) {
        int blockPoint = map[x][y];
        visited[x][y] = true;
        queue.offer(new int[]{x, y});
        int totalPoint = 1;
        int rainbowPoint = 0;
        while(! queue.isEmpty()) {
            int cur[] = queue.poll();
            for(int i  = 0 ; i < 4 ; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(0 > nx || nx >= N || 0 > ny || ny >= N || visited[nx][ny] == true) continue;
                if(map[nx][ny] == blockPoint || map[nx][ny] == 0) {
                    if(map[nx][ny] == 0) rainbowPoint += 1;
                    totalPoint += 1;
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }

        if(totalPoint >= 2) list.add(new Block(totalPoint, rainbowPoint, x, y));
        if(flag == true) {
            for(int i = 0; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    if(map[i][j] == 0) visited[i][j] = false;
                }
            }
        }

    }
}
