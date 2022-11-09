package study.swexpertacademy.d3;

import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AXaSUPYqPYMDFASQ&categoryId=AXaSUPYqPYMDFASQ&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=4
 *
 * 오목 판정
 */
public class Swea11315 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static char[][] map;
    static int N;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            String result = "";

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j);
                }
            }

            if (check()) {
                result = "YES";
            } else {
                result = "NO";
            }

            sb.append("#" + tc + " " + result + "\n");
        }

        System.out.println(sb);
        br.close();
    }

    public static boolean check() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] == 'o') {
                    for (int dir = 0; dir < 8; dir++) {
                        for (int k = 1; k < 5; k++) {
                            int nx = x + dx[dir] * k;
                            int ny = y + dy[dir] * k;

                            if (nx < 0 || nx >= N || ny < 0 || ny >= N)	break;
                            if (map[nx][ny] == '.')	break;
                            if (k == 4)	return true;
                        }

                    }
                }

            }
        }

        return false;
    }
}
