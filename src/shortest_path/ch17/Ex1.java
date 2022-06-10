package shortest_path.ch17;

import java.io.*;
import java.util.*;

/*
입력
5
14
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
3 5 10
3 1 8
1 4 2
5 1 7
3 4 2
5 2 4

출력
0 2 3 1 4
12 0 15 2 5
8 5 0 1 1
10 7 13 0 3
7 4 10 6 0
 */

/**
 * https://www.acmicpc.net/problem/11404
 *
 * 플로이드
 */
public class Ex1 {

    public static int[][] graph;
    public static int n, m;
    public static final int INF = (int) 1e9;

    public static void floydWarshall() {
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][i] + graph[i][b]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());

        graph = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 1; i <= n; i++) {
            graph[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            if (c < graph[a][b]) {
                graph[a][b] = c;
            }
        }

        floydWarshall();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == INF) {
                    System.out.print("0 ");
                } else {
                    System.out.print(graph[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
