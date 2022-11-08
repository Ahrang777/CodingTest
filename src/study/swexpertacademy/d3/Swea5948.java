package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWZ2IErKCwUDFAUQ&categoryId=AWZ2IErKCwUDFAUQ&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=3
 *
 * 새샘이의 7-3-5 게임
 */
public class Swea5948 {
    static Set<Integer> set;
    static int[] arr = new int[7];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            set = new HashSet<>();
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 7; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0, 0);

            List list = new ArrayList<>(set);

            Collections.sort(list, Collections.reverseOrder());

            sb.append("#" + tc + " " + list.get(4) + "\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static void dfs(int depth, int start, int sum) {
        if (depth == 3) {
            set.add(sum);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            dfs(depth + 1, i + 1, sum + arr[i]);
        }
    }
}
