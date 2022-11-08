package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWcPjEuKAFgDFAU4&categoryId=AWcPjEuKAFgDFAU4&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=4
 *
 * 정곤이의 단조 증가하는 수
 */
public class Swea6190 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int max = -1;

            st = new StringTokenizer(br.readLine());
            for (int i = 0 ; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    String mul = String.valueOf(arr[i] * arr[j]);

                    if (isDan(mul)) {
                        max = Math.max(max, Integer.parseInt(mul));
                    }
                }
            }

            sb.append("#" + tc + " " + max + "\n");
        }

        System.out.println(sb);
        br.close();
    }

    public static boolean isDan(String mul) {
        char prev = mul.charAt(0);

        for (int i = 1; i < mul.length(); i++) {
            if (prev > mul.charAt(i)) {
                return false;
            }

            prev = mul.charAt(i);
        }

        return true;
    }
}
