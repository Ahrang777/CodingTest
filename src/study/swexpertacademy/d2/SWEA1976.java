package study.swexpertacademy.d2;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PttaaAZIDFAUq&categoryId=AV5PttaaAZIDFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=3
 *
 * 시각 덧셈
 */
public class SWEA1976 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int H1 = Integer.parseInt(st.nextToken());
            int M1 = Integer.parseInt(st.nextToken());
            int H2 = Integer.parseInt(st.nextToken());
            int M2 = Integer.parseInt(st.nextToken());

            int m = M1 + M2;
            int h = H1 + H2;

            h += m / 60;
            m%=60;
            if (h>=13)	h-=12;

            System.out.printf("#%d %d %d\n", tc, h, m);
        }
    }
}
