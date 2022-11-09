package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14w-rKAHACFAYD&categoryId=AV14w-rKAHACFAYD&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=4
 *
 * [S/W 문제해결 기본] 8일차 - 암호문1
 */
public class Swea1228 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine());
            List<String> cryptogram = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cryptogram.add(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                String command = st.nextToken();
                int pos = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());

                for (int j = 0; j < count; j++) {
                    cryptogram.add(pos + j, st.nextToken());
                }
            }

            sb.append("#" + tc + " ");
            for (int i = 0; i < 10; i++) {
                sb.append(cryptogram.get(i)).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
