package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AW5jBWLq7jwDFATQ&categoryId=AW5jBWLq7jwDFATQ&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=6
 * 
 * 제로
 */
public class Swea8931 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int K = Integer.parseInt(br.readLine());
            Stack<Integer> s = new Stack<>();
            int result = 0;

            for (int i = 0; i < K; i++) {
                int n = Integer.parseInt(br.readLine());

                if (n == 0) {
                    s.pop();
                    continue;
                }

                s.push(n);
            }

            while (!s.isEmpty()) {
                result += s.pop();
            }

            sb.append("#" + tc + " " + result + "\n");
        }

        System.out.println(sb);
        br.close();
    }
}
