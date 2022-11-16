package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWvzGUKKPVwDFASy&categoryId=AWvzGUKKPVwDFASy&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=5
 *
 * 홀수 피라미드
 */
public class Swea8016 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            long n = Integer.parseInt(br.readLine());
            long left = (n-1) * (n-1) * 2 + 1;
            long right = (n*n*2) - 1;

            sb.append("#" + tc + " " + left + " " + right + "\n");
        }

        System.out.println(sb);
        br.close();
    }
}
