package study.swexpertacademy.d2;

import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PsIl6AXIDFAUq&categoryId=AV5PsIl6AXIDFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=2
 *
 * 쉬운 거스름돈
 */
public class Swea1970 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] m = {50000, 10000, 5000, 1000, 500, 100, 50, 10};

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] cnt = new int[m.length];

            for (int i = 0; i < m.length; i++) {
                if (N >= m[i]) {
                    cnt[i] = N / m[i];
                    N %= m[i];
                    if(N == 0)	break;
                }

            }

            System.out.println("#" + tc);
            for (int c : cnt) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
