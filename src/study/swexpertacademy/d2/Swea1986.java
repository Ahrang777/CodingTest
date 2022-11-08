package study.swexpertacademy.d2;

import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PxmBqAe8DFAUq&categoryId=AV5PxmBqAe8DFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=2
 *
 * 지그재그 숫자
 */
public class Swea1986 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int res = 0;
            for (int i = 1; i <= N; i++) {
                if (i % 2 == 0)	res-=i;
                else res+=i;
            }

            System.out.printf("#%d %d\n", tc, res);
        }
    }
}
