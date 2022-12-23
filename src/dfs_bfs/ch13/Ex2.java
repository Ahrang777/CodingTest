package dfs_bfs.ch13;

import study.Study;

import java.io.*;
import java.util.*;

/*
7 7
2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0

27
=============
4 6
0 0 0 0 0 0
1 0 0 0 0 2
1 1 1 0 0 2
0 0 0 0 0 2

9
=============
8 8
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0

3
 */

/**
 * 1. 벽 세우기
 * 2. 바이러스 퍼뜨리기
 * 3. 안전지역 계산
 * 4. 안전지역 가장 큰 값 구하기
 *
 * https://www.acmicpc.net/problem/14502
 * 
 * 연구소
 */
public class Ex2 {

    /*
    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static int n, m;
    public static int[][] map = new int[8][8];
    public static int[][] copy = new int[8][8];
    public static ArrayList<Position> viruses = new ArrayList<>();
    public static int max = 0;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static final int BLANK = 0;
    public static final int WALL = 1;
    public static final int VIRUS = 2;

    public static void copyMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    //bfs
    public static void spreadVirus() {
        Queue<Position> q = new LinkedList<>();

        for (Position virus : viruses) {
            q.offer(new Position(virus.getX(), virus.getY()));
        }

        while (!q.isEmpty()) {
            Position now = q.poll();
            int x = now.getX();
            int y = now.getY();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (copy[nx][ny] == BLANK) {
                        copy[nx][ny] = VIRUS;
                        q.offer(new Position(nx, ny));
                    }
                }
            }
        }
    }

    public static int getArea() {
        int area = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == BLANK) {
                    area += 1;
                }
            }
        }

        return area;
    }

    public static void setWall(int cnt) {
        if (cnt == 3) {
            // map 복사
            copyMap();

            // 바이러스 전파
            spreadVirus();

            //안전 영역 계산
            max = Math.max(max, getArea());
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == BLANK) {
                    map[i][j] = WALL;
                    setWall(cnt + 1);
                    map[i][j] = BLANK;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                if (map[i][j] == VIRUS)
                    viruses.add(new Position(i, j));
            }
        }

        setWall(0);

        System.out.println(max);
    }
     */

    /*public static int n, m, result = 0;

    public static int[][] map = new int[8][8]; // 초기 맵 배열
    public static int[][] temp = new int[8][8]; // 벽을 설치한 뒤의 맵 배열

    //방향
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    static class Node{
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs() {
        Queue<Node> q = new LinkedList<>();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (temp[x][y] == 2) {
                    q.offer(new Node(x, y));
                }
            }
        }

        while (!q.isEmpty()) {
            Node now = q.poll();
            int x = now.x;
            int y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (temp[nx][ny] == 0) {
                        q.offer(new Node(nx, ny));
                        temp[nx][ny] = 2;
                    }
                }
            }
        }

        getSafeAreaBfs();
    }

    public static void getSafeAreaBfs(){
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) {
                    count += 1;
                }
            }
        }
        result = Math.max(result, count);
    }

    //dfs
    public static void spreadVirus(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 상, 하, 좌, 우 중에서 바이러스가 퍼질 수 있는 경우
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (temp[nx][ny] == 0) {
                    // 해당 위치에 바이러스 배치하고, 다시 재귀적으로 수행
                    temp[nx][ny] = 2;
                    spreadVirus(nx, ny);
                }
            }
        }
    }

    public static void copy() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = map[i][j];
            }
        }
    }

    //안전지역 크기
    public static int getSafeArea(){
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public static void setWall(int count) {
        //map을 유지하기 위해 copy
        if (count == 3) {
            copy();

            *//*
            //바이러스 전파
            //특정 위치별 바이러스 전파
            //DFS를 이용한 바이러스 전파
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (temp[i][j] == 2) {
                        spreadVirus(i, j);
                    }
                }
            }

            result = Math.max(result, getSafeArea());*//*

            //BFS를 이용한 바이러스 전파
            bfs();
            return;
        }

        //백트래킹
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    setWall(count + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        setWall(0);
        System.out.println(result);
    }*/

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;

    static int N, M;
    static int[][] map;
    static int[][] copy;
    static List<Position> viruses = new ArrayList<>();
    static int maxValue = Integer.MIN_VALUE;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == VIRUS) {
                    viruses.add(new Position(i, j));
                }
            }
        }

        wall(0);

        System.out.println(maxValue);
    }

    private static void wall(int depth) {
        if (depth == 3) {
            copy(); // map 원본 유지를 위한 복사본 생성
            spread();   // 바이러스 전파
            maxValue = Math.max(maxValue, safeArea());
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == BLANK) {
                    map[i][j] = WALL;
                    wall(depth + 1);
                    map[i][j] = BLANK;
                }
            }
        }
    }

    private static void spread() {
        Queue<Position> q = new LinkedList<>();
        for (Position virus : viruses) {
            q.offer(virus);
        }

        while (!q.isEmpty()) {
            Position now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny) && copy[nx][ny] == BLANK) {
                    copy[nx][ny] = VIRUS;
                    q.offer(new Position(nx, ny));
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static void copy() {
        copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    private static int safeArea() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == BLANK) {
                    total++;
                }
            }
        }

        return total;
    }
}
