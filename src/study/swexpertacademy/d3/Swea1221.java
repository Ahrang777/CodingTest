package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14jJh6ACYCFAYD&categoryId=AV14jJh6ACYCFAYD&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=4
 *
 * [S/W 문제해결 기본] 5일차 - GNS
 */
public class Swea1221 {
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        Map<String, Integer> map1 = new HashMap<>();
        Map<Integer, String> map2 = new HashMap<>();

        init(map1, map2);

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            String testCase = st.nextToken();
            int N = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                String number = st.nextToken();
                list.add(map1.get(number));
            }

            Collections.sort(list);

            sb.append(testCase).append("\n");
            for (Integer n : list) {
                sb.append(map2.get(n)).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    // "ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"
    public static void init(Map<String, Integer> map1, Map<Integer, String> map2) {
        String[] arr = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};

        for (int i = 0; i <= 9; i++) {
            map1.put(arr[i], i);
            map2.put(i, arr[i]);
        }
    }
}
