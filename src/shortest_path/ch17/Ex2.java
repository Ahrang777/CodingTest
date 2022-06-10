package shortest_path.ch17;

import java.io.*;
import java.util.*;

/*
입력
6 6
1 5
3 4
4 2
4 6
5 2
5 4

출력
1
======
입력
6 7
1 5
3 4
4 2
4 6
5 2
5 4
4 5

출력
2
 */
public class Ex2 {

    public static int n, m;
    public static int[][] graph = new int[501][501];
    public static final int INF = (int) 1e9;
    
    public static void floydWashall() {
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
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 1; i <= n; i++) {
            graph[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            graph[a][b] = 1;
        }
        
        floydWashall();

        int result = 0;

        //0, INF 제외한 값들은 관계가 있는것
        for (int i = 1; i <= n; i++) {
            int cnt = 0;

            for (int j = 1; j <= n; j++) {
                if (graph[i][j] != INF || graph[j][i] != INF) {
                    cnt += 1;
                }
            }

            if (cnt == n) {
                result += 1;
            }
        }

        System.out.println(result);
    }
}
