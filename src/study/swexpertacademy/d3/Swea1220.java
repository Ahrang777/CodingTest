package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14hwZqABsCFAYD&categoryId=AV14hwZqABsCFAYD&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=1
 *
 * [S/W 문제해결 기본] 5일차 - Magnetic
 */
public class Swea1220 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int col = 0; col < 100; col++) {
                String str = "";
                for (int row = 0; row < 100; row++) {
                    if (map[row][col] >= 1)	str += map[row][col];
                }

                int sum = 0;
                for (int i = 0; i < str.length() - 1; i++) {
                    if(str.charAt(i) == '1') {
                        if(str.charAt(i + 1) == '2')	sum++;
                    }
                }
                cnt += sum;
            }

            System.out.printf("#%d %d\n", tc, cnt);
        }
    }
}
