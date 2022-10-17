package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV15Khn6AN0CFAYD&categoryId=AV15Khn6AN0CFAYD&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=1
 *
 * [S/W 문제해결 응용] 2일차 - 최대 상금
 */
public class SWEA1244 {
    static int max;
    static int chance;
    static String[] arr;


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            arr = str.split("");
            chance = Integer.parseInt(st.nextToken());
            int len = arr.length;
            chance = Math.min(len, chance);

            max = Integer.MIN_VALUE;
            dfs(0, 0);

            System.out.printf("#%d %d\n", tc, max);
        }
    }

    public static void dfs(int depth, int start) {
        if (depth == chance) {
            StringBuilder sb = new StringBuilder();
            for (String s : arr) sb.append(s);
            max = Math.max(max, Integer.parseInt(sb.toString()));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // 교환 i <-> j
                swap(i, j);
                dfs(depth + 1, i);

                // 교환, 원복
                swap(i, j);
            }
        }

    }

    public static void swap(int i, int j) {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
