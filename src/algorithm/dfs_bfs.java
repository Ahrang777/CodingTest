package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
입력
4 5 1
1 2
1 3
1 4
2 4
3 4

출력
1 2 4 3 
1 2 3 4 
 */
public class dfs_bfs {

    public static int n, m, v;
    public static ArrayList<Integer>[] graph;
    public static boolean[] visited = new boolean[1001];


    public static void dfs(int start) {
        visited[start] = true;
        System.out.print(start + " ");

        for (int next : graph[start]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    public static void dfs2(int start) {
        Stack<Integer> s = new Stack<>();
        s.push(start);
        visited[start] = true;
        System.out.print(start + " ");

        /*while (!s.isEmpty()) {
            int now = s.pop(); //더이상 방문하지 않은 인접노드 없으면 pop이고 인접노드 확인은 peek()로 스택에서 뽑지 않고 조회로만 확인
            for (int next : graph[now]) {

                //for (int i = 0; i < graph[now].size(); i++) {
                //int next = graph[now].get(i);

                if (!visited[next]) {
                    s.push(now);
                    s.push(next);
                    visited[next] = true;
                    System.out.print(s.peek() + " ");
                    break;
                }
            }
        }*/

        while (!s.isEmpty()) {
            int now = s.peek();
            boolean flag = false;

            for (int next : graph[now]) {
                if (!visited[next]) {
                    s.push(next);
                    visited[next] = true;
                    flag = true;
                    System.out.print(next + " ");
                    break;
                }
            }

            if (!flag)
                s.pop();
        }
    }

    public static void dfs3(int[][] graph, boolean[] visited, int start) {
        visited[start] = true;
        for (int i = 0; i < graph.length; i++) {
            if (graph[start][i] == 1 && !visited[i]) {
                dfs3(graph, visited, i);
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            System.out.print(now + " ");

            for (int next : graph[now]) {
                if (!visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        v = Integer.parseInt(stk.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }

        for (int i = 1; i < n + 1; i++) {
            Collections.sort(graph[i]);
        }

        dfs(v);
        for (int i = 1; i < n + 1; i++) {
            visited[i] = false;
        }
        System.out.println();
        bfs(v);
    }
}
