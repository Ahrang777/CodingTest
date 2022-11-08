package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV7GOPPaAeMDFAXB&categoryId=AV7GOPPaAeMDFAXB&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=2
 *
 * 최장 경로
 */
public class Swea2814 {
    static int[][] graph;
    static boolean[] visited;
    static int N, max;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            max = Integer.MIN_VALUE;

            graph = new int[N+1][N+1];
            visited = new boolean[N+1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x][y] = graph[y][x] = 1;
            }

            for (int i = 1; i <= N; i++) {
                dfs(1, i);
                visited[i] = false;
            }

            sb.append("#" + tc + " " + max + "\n");
        }

        System.out.println(sb);
    }

    public static void dfs(int cnt, int start) {
        visited[start] = true;

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && graph[start][i] == 1) {
                dfs(cnt + 1, i);
                visited[i] = false;
            }
        }

        max = Math.max(max, cnt);
    }
}
