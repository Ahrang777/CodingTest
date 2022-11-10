package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWLv6mx6htoDFAVV&categoryId=AWLv6mx6htoDFAVV&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=4
 *
 * 태혁이의 사랑은 타이밍
 */
public class Swea4299 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int N = 11 * 24 * 60 + 11 * 60 + 11;

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            List<Integer> time = new ArrayList<>();
            int D = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int res = 0;

            int m = D * 24 * 60 + H * 60 + M;

            res = (m - N >= 0) ? (m - N) : -1;

            sb.append("#" + tc + " " + res + "\n");
        }

        System.out.println(sb);
        br.close();
    }
}
