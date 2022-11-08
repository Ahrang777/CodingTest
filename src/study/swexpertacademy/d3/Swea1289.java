package study.swexpertacademy.d3;

import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV19AcoKI9sCFAZN&categoryId=AV19AcoKI9sCFAZN&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=1
 *
 * 원재의 메모리 복구하기
 */
public class Swea1289 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String str = br.readLine();
            int len = str.length();
            int cnt = 0;
            char now = '0';

            for (int i = 0; i < len; i++) {
                if (str.charAt(i) != now) {
                    now =  str.charAt(i);
                    cnt++;
                }
            }

            System.out.printf("#%d %d\n", tc, cnt);
        }
    }
}
