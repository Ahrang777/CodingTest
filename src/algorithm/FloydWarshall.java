package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
입력
4
7
1 2 4
1 4 6
2 1 3
2 3 7
3 1 5
3 4 4
4 3 2

출력
0 4 8 6
3 0 7 9
5 9 0 4
7 11 2 0
 */

/**
 * 최단 경로
 * 모든 지점에서 다른 모든 지점까지 서로서로 최단 경로
 */
public class FloydWarshall {

    public static int n, m;
    public static int[][] graph;

    // Integer.MAX_VALUE 로 했을 경우 제대로 안돌아감, graph[a][i] + graph[i][b] 를 하면 int 범위를 넘어가서 음수가 나오고 이로 인해 min 에서 음수가 나온다.
    public static final int INF = (int) 1e9;

    //시간 복잡도: O(N³)
    //1 ~ N 번 노드
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
            //A에서 B로 가는 비용은 C
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            graph[a][b] = c;
        }

        floydWarshall();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(graph[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
