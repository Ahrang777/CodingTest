package dfs_bfs.ch5;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * DFS, 인접리스트, 재귀함수
 */
public class Ex8DfsRecursiveFunction {
    //idx: 0 ~ 8    노드: 1 ~ 8
    public static boolean[] visited = new boolean[9];
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

    /*
    public static ArrayList<Integer>[] graph = new ArrayList[9];    //1 ~ 8
    public static void dfs(int start) {
        visited[start] = true;
        System.out.print(start + " ");

        for (int i = 0; i < graph[start].size(); i++) {
            int next = graph[start].get(i);
            if (visited[next] == false) {
                dfs(next);
            }
        }
    }*/

    public static void dfs(int x){
        visited[x] = true;
        System.out.print(x + " ");

        for (int i = 0; i < graph.get(x).size(); i++) {

            int y = graph.get(x).get(i);
            if(!visited[y])
                dfs(y);
        }
    }

    /*
    //Stack 이용
    public static void dfs(int start) {
        Stack<Integer> s = new Stack<>();
        s.push(start);
        visited[start] = true;
        System.out.print(start + " ");

        *//*while (!s.isEmpty()) {
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
        }*//*

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
    }*/

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 9; i++) {
            graph.add(new ArrayList<Integer>());
        }

        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(1).add(8);

        graph.get(2).add(1);
        graph.get(2).add(7);

        graph.get(3).add(1);
        graph.get(3).add(4);
        graph.get(3).add(5);

        graph.get(4).add(3);
        graph.get(4).add(5);

        graph.get(5).add(3);
        graph.get(5).add(4);

        graph.get(6).add(7);

        graph.get(7).add(2);
        graph.get(7).add(6);
        graph.get(7).add(8);

        graph.get(8).add(1);
        graph.get(8).add(7);

//        long start = System.currentTimeMillis();
        dfs(1);
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println(end-start);
    }
}
