package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV13_BWKACUCFAYh&categoryId=AV13_BWKACUCFAYh&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=1
 *
 * [S/W 문제해결 기본] 2일차 - Sum
 */
public class SWEA1209 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int sum = 0;

        for (int tc = 1; tc <= 10; tc++) {
            int T = Integer.parseInt(br.readLine());
            int[][] arr = new int[100][100];
            int max = 0;
            int sum1 = 0;
            int sum2 = 0;
            int sum3 = 0;
            int sum4 = 0;

            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i =0; i<100; i++){
                sum1 = 0;
                sum2 = 0;
                sum3 += arr[i][i];
                sum4 += arr[i][99-i];

                for(int j=0; j<100; j++){
                    sum1 += arr[i][j];
                    sum2 += arr[j][i];
                }
                max = Math.max(max,sum1);
                max = Math.max(max,sum2);
            }
            max = Math.max(max,sum3);
            max = Math.max(max,sum4);

            System.out.printf("#%d %d\n", T, max);
        }

    }
}
