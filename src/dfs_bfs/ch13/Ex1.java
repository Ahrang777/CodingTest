package dfs_bfs.ch13;

import java.util.*;

public class Ex1 {

    public static ArrayList<Integer>[] graph = new ArrayList[9];
    public static boolean[] visited = new boolean[9];

    public static void dfs1(int start) {
        visited[start] = true;
        System.out.print(start + " ");

        for (int i = 0; i < graph[start].size(); i++) {
            int next = graph[start].get(i);
            if (visited[next] == false) {
                dfs1(next);
            }
        }
    }

    public static void dfs(int start) {
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

            if(!flag)
                s.pop();
        }
    }

    public static void DFS(int start){
        System.out.print("Depth First Traversal: ");
        boolean[] visited = new boolean[9];
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(start);
        while (!stack.isEmpty()){
            int v = stack.pop();
            if (!visited[v]){
                System.out.print(v + " ");
                visited[v] = true;
                for (int i = 0; i < graph[v].size(); i++){
                    int dest = graph[v].get(i);
                    if (!visited[dest])
                        stack.push(dest);
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[1].add(2);
        graph[1].add(3);
        graph[1].add(8);

        graph[2].add(1);
        graph[2].add(7);

        graph[3].add(1);
        graph[3].add(4);
        graph[3].add(5);

        graph[4].add(3);
        graph[4].add(5);

        // 노드 5에 연결된 노드 정보 저장
        graph[5].add(3);
        graph[5].add(4);

        graph[6].add(7);

        graph[7].add(2);
        graph[7].add(6);
        graph[7].add(8);

        graph[8].add(1);
        graph[8].add(7);

        for (int i = 1; i < 9; i++) {
            Collections.sort(graph[i]);
        }

        dfs1(1);    //1 2 7 6 8 3 4 5

        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        System.out.println();
        dfs(1); //1 8 7 6 3 5 4 2
    }
}
