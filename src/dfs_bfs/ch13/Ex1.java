package dfs_bfs.ch13;

import javax.xml.stream.events.StartDocument;
import java.io.*;
import java.util.*;

/*
4 4 2 1
1 2
1 3
2 3
2 4

4
=======
4 3 2 1
1 2
1 3
1 4

-1
=======
4 4 1 1
1 2
1 3
2 3
2 4

2
3
 */
public class Ex1 {

    //도시 개수, 도로 개수, 거리 정보, 출발 도시
    public static int n, m, k, x;
    public static ArrayList<Integer>[] graph;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        x = Integer.parseInt(stk.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        visited = new boolean[n+1];

        for(int i=0;i<m;i++){
            stk = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            graph[a].add(b);
        }


        /*
        // 모든 도시에 대한 최단 거리 초기화
        public static int[] d = new int[300001];

        // 출발 도시까지의 거리는 0으로 설정
        d[x] = 0;

        // 너비 우선 탐색(BFS) 수행
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(x);
        while (!q.isEmpty()) {
            int now = q.poll();
            // 현재 도시에서 이동할 수 있는 모든 도시를 확인
            for (int i = 0; i < graph.get(now).size(); i++) {
                int nextNode = graph.get(now).get(i);
                // 아직 방문하지 않은 도시라면
                if (d[nextNode] == -1) {
                    // 최단 거리 갱신
                    d[nextNode] = d[now] + 1;
                    q.offer(nextNode);
                }
            }
        }
         */

        //BFS
        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = -1;
        }
        q.offer(x);
        visited[x] = true;
        result[x] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                if (!visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                    result[next] = result[now] + 1;
                }
            }
        }

        boolean flag = false;
        for (int i = 1; i <= n; i++) {
            if (result[i] == k) {
                System.out.println(i);
                flag = true;
            }
        }

        if (!flag) {
            System.out.println(-1);
        }
    }
}
