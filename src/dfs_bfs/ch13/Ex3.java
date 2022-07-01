package dfs_bfs.ch13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
입력
3 3
1 0 2
0 0 0
3 0 0
2 3 2

출력
3
=====
입력
3 3
1 0 2
0 0 0
3 0 0
1 2 2

출력
0
 */

/**
 * https://www.acmicpc.net/problem/18405
 * 
 * 경쟁적 전염
 */
public class Ex3 {

    static class Virus implements Comparable<Virus>{
        private int x;
        private int y;
        private int index;
        private int second;

        public Virus(int x, int y, int index, int second) {
            this.x = x;
            this.y = y;
            this.index = index;
            this.second = second;
        }

        public int getIndex() {
            return index;
        }

        public int getSecond() {
            return second;
        }

        @Override
        public int compareTo(Virus other) {
            return Integer.compare(this.index, other.index);
        }
    }

    public static int n, k;

    //s초 후 (x, y) 에 바이러스 존재?
    public static int s, x, y;

    public static int[][] map = new int[201][201];
    public static ArrayList<Virus> viruses = new ArrayList<>();

    //방향
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    
    //bfs
    //s초 (x, y) 위치의 값 반환
    public static int find() {
        spread();

        return map[x][y];
    }

    public static void spread() {

        Queue<Virus> q = new LinkedList<>();

        //처음 위치한 바이러스들 모두 큐에 넣기
        for (Virus v : viruses) {
            q.offer(v);
        }

        while (!q.isEmpty()) {

            Virus now = q.poll();

            if (now.getSecond() == s) break;

            for (int i = 0; i < 4; i++) {

                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int second = now.getSecond();

                if (nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
                    if (map[nx][ny] == 0 ) {
                        map[nx][ny] = now.getIndex();
                        q.offer(new Virus(nx, ny, map[nx][ny], ++second));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                if (map[i][j] != 0) {
                    viruses.add(new Virus(i, j, map[i][j], 0));
                }
            }
        }

        Collections.sort(viruses);

        stk = new StringTokenizer(bf.readLine(), " ");
        s = Integer.parseInt(stk.nextToken());
        x = Integer.parseInt(stk.nextToken());
        y = Integer.parseInt(stk.nextToken());

        System.out.println(find());

        /*for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }*/
    }

    /*
    public static class Virus implements Comparable<Virus> {
        private int x;
        private int y;
        private int type;
        private int time;

        public Virus(int x, int y, int type, int time) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.time = time;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getType() {
            return type;
        }

        public int getTime() {
            return time;
        }

        @Override
        public int compareTo(Virus other) {
            return Integer.compare(this.type, other.type);
        }
    }


    public static int n, k, s, targetX, targetY;
    public static int[][] map = new int[200][200];
    public static ArrayList<Virus> viruses = new ArrayList<>();
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static final int BLANK = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                if (map[i][j] != BLANK) {
                    viruses.add(new Virus(i, j, map[i][j], 0));
                }
            }
        }

        stk = new StringTokenizer(bf.readLine(), " ");
        s = Integer.parseInt(stk.nextToken());
        targetX = Integer.parseInt(stk.nextToken());
        targetY = Integer.parseInt(stk.nextToken());

        Collections.sort(viruses);

        Queue<Virus> q = new LinkedList<>();
        for (Virus virus : viruses) {
            q.offer(virus);
        }

        while (!q.isEmpty()) {
            Virus now = q.poll();
            int time = now.getTime();

            if (time == s) break;

            int x = now.getX();
            int y = now.getY();
            int type = now.getType();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (map[nx][ny] == BLANK) {
                        map[nx][ny] = type;
                        q.offer(new Virus(nx, ny, type, time + 1));
                    }
                }
            }
        }

        System.out.println(map[targetX - 1][targetY - 1]);
    }
     */
}
