package study.swexpertacademy.d2;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PjMgaALgDFAUq&categoryId=AV5PjMgaALgDFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=3
 *
 * 가랏! RC카!
 */
public class Swea1940 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int distance = 0;
            int prev = 0;
            int now = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                switch (c) {
                    case 0:
                        distance += prev;
                        break;
                    case 1:
                        now = Integer.parseInt(st.nextToken());
                        prev += now;
                        distance += prev;
                        break;
                    case 2:
                        now = Integer.parseInt(st.nextToken());
                        if (prev < now) prev = 0;
                        else prev -= now;
                        distance += prev;
                        break;
                }
            }

            System.out.printf("#%d %d\n", tc, distance);
        }
    }
}
