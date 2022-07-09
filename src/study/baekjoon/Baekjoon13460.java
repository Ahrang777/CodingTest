package study.baekjoon;

/*
======================
입력1
5 5
#####
#..B#
#.#.#
#RO.#
#####

출력1
1
======================
입력2
7 7
#######
#...RB#
#.#####
#.....#
#####.#
#O....#
#######

출력2
5
======================
입력3
7 7
#######
#..R#B#
#.#####
#.....#
#####.#
#O....#
#######

출력3
5
======================
입력4
10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.#.#..#
#...#.O#.#
##########

출력4
-1
======================
입력5
3 7
#######
#R.O.B#
#######

출력5
1
======================
입력6
10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.##...#
#O..#....#
##########

출력6
7
======================
입력7
3 10
##########
#.O....RB#
##########

출력7
-1
======================
 */

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/13460
 *
 * 구슬 탈출2
 * 삼성전자 공채
 *
 * 다시 풀기
 */
public class Baekjoon13460 {

    static class Marble {
        int rx, ry, bx, by, cnt;

        public Marble(){}

        public Marble(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

    public static int n, m;
    public static char[][] board = new char[10][10];

    public static final int BLANK = '.';
    public static final int WALL = '#';
    public static final int HOLE = 'O';
    public static final int RED = 'R';
    public static final int BLUE = 'B';

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static void bfs(int rx, int ry, int bx, int by) {
        Queue<Marble> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][m][n][m];

        q.offer(new Marble(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!q.isEmpty()) {
            Marble now = q.poll();

            // 10번 이하로 움직일 수 없는 경우
            if (now.cnt > 10) {
                System.out.println(-1);
                return;
            }

            // 파란 공이 빠지는 경우 실패
            if (board[now.bx][now.by] == HOLE) {
                continue;
            }

            // 파란 공은 안 빠지고 빨간 공은 빠지는 경우 성공
            if (board[now.rx][now.ry] == HOLE && board[now.bx][now.by] != HOLE) {
                System.out.println(now.cnt);
                return;
            }

            // 공 이동
            for (int i = 0; i < 4; i++) {
                int rnx = now.rx;
                int rny = now.ry;
                int bnx = now.bx;
                int bny = now.by;

                // 빨간 공 이동
                while (true) {
                    rnx += dx[i];
                    rny += dy[i];

                    if (board[rnx][rny] == HOLE || board[rnx][rny] == WALL)
                        break;
                }
                if (board[rnx][rny] == WALL) {
                    rnx -= dx[i];
                    rny -= dy[i];
                }

                // 파란 공 이동
                while (true) {
                    bnx += dx[i];
                    bny += dy[i];

                    if (board[bnx][bny] == HOLE || board[bnx][bny] == WALL)
                        break;
                }
                if (board[bnx][bny] == WALL) {
                    bnx -= dx[i];
                    bny -= dy[i];
                }

                // 빨간 공, 파란 공 겹치는 경우
                if (rnx == bnx && rny == bny && board[rnx][rny] != HOLE) {
                    // 둘중 이동거리가 더 큰게 뒤에 있는 구슬이다.
                    int rDist = Math.abs(rnx - now.rx) + Math.abs(rny - now.ry);
                    int bDist = Math.abs(bnx - now.bx) + Math.abs(bny - now.by);

                    // 빨간 공이 파란공보다 뒤에 있는 경우
                    if (rDist > bDist) {
                        rnx -= dx[i];
                        rny -= dy[i];
                    } else {
                        bnx -= dx[i];
                        bny -= dy[i];
                    }
                }

                // 아직 방문하지 않았다면 큐에 넣기
                if (visited[rnx][rny][bnx][bny] == false) {
                    visited[rnx][rny][bnx][bny] = true;
                    q.offer(new Marble(rnx, rny, bnx, bny, now.cnt + 1));
                }
            }
        }
        
        // 빨파 같이 나와서 실패하는 경우
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;

        for (int i = 0; i < n; i++) {
            String str = bf.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);

                if (board[i][j] == RED) {
                    rx = i;
                    ry = j;
                }
                if (board[i][j] == BLUE) {
                    bx = i;
                    by = j;
                }
            }
        }

        bfs(rx, ry, bx, by);
    }
}
