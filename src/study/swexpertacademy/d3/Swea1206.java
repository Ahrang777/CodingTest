package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV134DPqAA8CFAYh&categoryId=AV134DPqAA8CFAYh&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=1
 *
 * [S/W 문제해결 기본] 1일차 - View
 */
public class Swea1206 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] b = new int[N];
            int cnt = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)	b[i] = Integer.parseInt(st.nextToken());

            for (int i = 2; i < N - 2; i++) {
                int max = Math.max(Math.max(b[i-1], b[i-2]),Math.max(b[i+1], b[i+2]));
                if (b[i] - max > 0)	cnt += (b[i] - max);
            }

            System.out.printf("#%d %d\n", tc, cnt);
        }
    }
}
