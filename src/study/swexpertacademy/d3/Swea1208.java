package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV139KOaABgCFAYh&categoryId=AV139KOaABgCFAYh&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=1
 *
 * [S/W 문제해결 기본] 1일차 - Flatten
 */
public class Swea1208 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] h = new int[100];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++)	h[i] = Integer.parseInt(st.nextToken());

            Arrays.sort(h);
            for (int i = 0; i < N; i++) {
                if (h[99] - h[0] <= 1) break;
                h[99] -= 1;
                h[0] += 1;
                Arrays.sort(h);
            }

            System.out.println("#" + tc + " " + (h[99] - h[0]));
        }
    }
}
