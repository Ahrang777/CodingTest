package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWksRkI6AR0DFAVE&categoryId=AWksRkI6AR0DFAVE&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=3
 *
 * 승현이의 수학공부
 */
public class SWEA7193 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());	// N진법
            String X = st.nextToken();

            long sum = 0;
            for (int i = X.length() - 1; i >= 0; i--) {
                int n = X.charAt(i) - '0';
                sum += n;
            }

            sb.append("#" + tc + " " + (sum % (N - 1)) + "\n");
        }

        System.out.println(sb);
        br.close();
    }

    /*
    9진수 234
    (2 * 9^2 + 3 * 9 + 4 * 1) % 8
    ((2 * 9^2 % 8) + (3 * 9 % 8) + (4 * 1 % 8)) % 8
    1 % 8 = 1
    9 % 8 = 1
    9^2 % 8 = 1
    (2 + 3 + 4) % 8
     */
}
