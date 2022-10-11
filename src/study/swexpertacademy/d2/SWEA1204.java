package study.swexpertacademy.d2;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV13zo1KAAACFAYh&categoryId=AV13zo1KAAACFAYh&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=1
 *
 * SW Expert Academy
 * 최빈수 구하기
 */
public class SWEA1204 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] score = new int[101];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 1000; i++) {
                score[Integer.parseInt(st.nextToken())]++;
            }
            int max = 0;
            for (int i = 1; i <= 100; i++) {
                if (score[i] >= score[max])	max = i;
            }

            System.out.printf("#%d %d\n", n, max);
        }
    }
}
