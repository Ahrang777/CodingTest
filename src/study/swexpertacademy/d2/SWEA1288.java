package study.swexpertacademy.d2;

import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV18_yw6I9MCFAZN&categoryId=AV18_yw6I9MCFAZN&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=3
 *
 * 새로운 불면증 치료법
 */
public class SWEA1288 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int count = 0;
            int[] cnt = new int[10];
            int idx = 0;

            while (count < 10) {
                idx += N;
                int tmp = idx;
                while (true) {
                    int n = tmp % 10;
                    if (cnt[n] == 0)	count++;
                    cnt[n]++;
                    tmp/=10;
                    if (tmp == 0)	break;
                }

            }

            System.out.printf("#%d %d\n", tc, idx);
        }
    }
}
