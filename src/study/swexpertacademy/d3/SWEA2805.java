package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV7GLXqKAWYDFAXB&categoryId=AV7GLXqKAWYDFAXB&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=1
 *
 * 농작물 수확하기
 */
public class SWEA2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int res = 0;

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    int n = str.charAt(j) - '0';
                    map[i][j] = n;
                }
            }


            for (int i = 0; i < N/2; i++) {
                for (int j = N/2 - i; j <= N/2 + i; j++) {
                    res += map[i][j];
                }
            }

            for (int i = N/2; i < N; i++) {
                for (int j = i - N/2; j <= N + N/2 -i -1; j++) {
                    res += map[i][j];
                }
            }

            System.out.printf("#%d %d\n", tc, res);
        }
    }
}
