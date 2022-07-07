package study.baekjoon;

import java.io.*;
import java.util.*;

/*
입력1
4 2 0 0 8
0 2
3 4
5 6
7 8
4 4 4 1 3 3 3 2

출력1
0
0
3
0
0
8
6
3
========================
입력2
3 3 1 1 9
1 2 3
4 0 5
6 7 8
1 3 2 2 4 4 1 1 3

출력2
0
0
0
3
0
1
0
6
0
========================
입력3
2 2 0 0 16
0 2
3 4
4 4 4 4 1 1 1 1 3 3 3 3 2 2 2 2

출력3
0
0
0
0
========================
입력4
3 3 0 0 16
0 1 2
3 4 5
6 7 8
4 4 1 1 3 3 2 2 4 4 1 1 3 3 2 2

출력4
0
0
0
6
0
8
0
2
0
8
0
2
0
8
0
2
========================
 */

/**
 * https://www.acmicpc.net/problem/14499
 * 
 * 주사위 굴리기
 * 삼성전자 공채
 */
public class Baekjoon14499 {

    public static int n, m, x, y, k;
    public static int[][] map = new int[20][20];

    // 명령
    public static ArrayList<Integer> command = new ArrayList<>();

    // 동, 서, 북, 남
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};

    /*
    주사위 전개도
    1: 윗면, 6: 밑면(지도랑 닿는 부분)
      2
    4 1 3
      5
      6
     */
    public static int[] dice = new int[7];

    public static void solution(int x, int y) {

        for (int dir : command) {

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 범위 넘어가는 경우 무시
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }

            int tmp;

            switch (dir) {
                case 0:
                    tmp = dice[6];
                    dice[6] = dice[3];
                    dice[3] = dice[1];
                    dice[1] = dice[4];
                    dice[4] = tmp;
                    break;

                case 1:
                    tmp = dice[6];
                    dice[6] = dice[4];
                    dice[4] = dice[1];
                    dice[1] = dice[3];
                    dice[3] = tmp;
                    break;
                case 2:
                    tmp = dice[2];
                    dice[2] = dice[1];
                    dice[1] = dice[5];
                    dice[5] = dice[6];
                    dice[6] = tmp;
                    break;
                case 3:
                    tmp = dice[6];
                    dice[6] = dice[5];
                    dice[5] = dice[1];
                    dice[1] = dice[2];
                    dice[2] = tmp;
                    break;
            }

            /*// 동
            if (dir == 0) {
                // 이동
                int tmp = dice[6];
                dice[6] = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[4];
                dice[4] = tmp;
            } 
            // 서
            else if (dir == 1) {
                // 이동
                int tmp = dice[6];
                dice[6] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[3];
                dice[3] = tmp;
            }
            // 북
            else if (dir == 2) {
                // 이동
                int tmp = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = tmp;
            }
            // 남
            else if (dir == 3) {
                // 이동
                int tmp = dice[6];
                dice[6] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[2];
                dice[2] = tmp;
            }*/

            // 해당 칸이 0인 경우
            // 주사위 -> 지도 칸
            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[6];
            }
            // 지도의 해당 칸이 0이 아닌 경우
            // 지도 칸 -> 주사위, 지도 칸 0으로 바꾸기
            else {     
                dice[6] = map[nx][ny];
                map[nx][ny] = 0;
            }

            x = nx;
            y = ny;
            
            // 주사위 윗면 출력
            System.out.println(dice[1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        x = Integer.parseInt(stk.nextToken());
        y = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < k; i++) {
            command.add(Integer.parseInt(stk.nextToken()) - 1);
        }

        solution(x, y);
    }
}
