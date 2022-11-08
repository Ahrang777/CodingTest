package study.swexpertacademy.d2;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV189xUaI8UCFAZN&categoryId=AV189xUaI8UCFAZN&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=2
 *
 * 수도 요금 경쟁
 */
public class Swea1284 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            int A = P * W;
            int B = Q;
            if (W > R) {
                B += (W - R) * S;
            }

            System.out.printf("#%d %d\n", tc, Math.min(A, B));
        }
    }
}
