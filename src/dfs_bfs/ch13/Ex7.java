package dfs_bfs.ch13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
==========
2 20 50
50 30
20 40

1
==========
2 40 50
50 30
20 40

0
==========
2 20 50
50 30
30 40

1
==========
3 5 10
10 15 20
20 30 25
40 22 10

2
==========
4 10 50
10 100 20 90
80 100 60 70
70 20 30 40
50 20 100 10

3
==========
 */
public class Ex7 {

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
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

    //nxn 국가, l <=  <= r
    public static int n, l, r;
    public static int totalCount = 0;

    public static int[][] map = new int[50][50];
    public static int[][] unions = new int[50][50];

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    //BFS
    //index는 연합 구분하기 위한 용도 1끼리 같은 연합, 2끼리 같은 연합
    public static void process(int x, int y, int index) {
        //연합한 나라가 어디인지 보관했다가 마지막에 인구분배
        ArrayList<Point> united = new ArrayList<>();
        united.add(new Point(x, y));

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        unions[x][y] = index;
        int sum = map[x][y];    //현재 연합의 전체 인구 수
        int count = 1;  //현재 연합의 국가 수

        while (!q.isEmpty()) {
            Point now = q.poll();
            x = now.getX();
            y = now.getY();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && unions[nx][ny] == -1) {
                    int gap = Math.abs(map[x][y] - map[nx][ny]);
                    if (l <= gap && gap <= r) {
                        q.offer(new Point(nx, ny));

                        // 연합에 추가하기
                        unions[nx][ny] = index;
                        sum += map[nx][ny];
                        count += 1;
                        united.add(new Point(nx, ny));
                    }
                }
            }
        }

        for (Point p : united) {
            x = p.getX();
            y = p.getY();
            map[x][y] = sum / count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        l = Integer.parseInt(stk.nextToken());
        r = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        while (true) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    unions[i][j] = -1;
                }
            }

            int index = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (unions[i][j] == -1) { // 해당 나라가 아직 처리되지 않았다면
                        process(i, j, index);
                        index += 1;
                    }
                }
            }
            // 모든 인구 이동이 끝난 경우
            // 더 이상 여러개로 묶이지 않고 그 자체로 하나의 union 이라서 index는 n*n 까지 커짐
            if (index == n * n) break;
            totalCount += 1;
        }

        System.out.println(totalCount);
    }
}
