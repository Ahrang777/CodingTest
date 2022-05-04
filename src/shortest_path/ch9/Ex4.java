package shortest_path.ch9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
입력1
5 7
1 2
1 3
1 4
2 4
3 4
3 5
4 5
4 5

출력1
3

===========

입력2
4 2
1 3
2 4
3 4

출력2
-1
 */
public class Ex4 {

    public static final int INF = (int) 1e9;
    
    //회사 개수, 도로 개수, 최종 지점, 중간 지점
    //1 -> k -> x
    public static int n, m, x, k;
    public static int[][] graph = new int[101][101];
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < 101; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 0; i <= n; i++) {
            graph[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        stk = new StringTokenizer(bf.readLine(), " ");
        x = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        //플로이드 워셜 알고리즘
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][i] + graph[i][b]);
                }
            }
        }

        //1 -> k -> x
        int distance = graph[1][k] + graph[k][x];

        if (distance >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(distance);
        }
    }
}
