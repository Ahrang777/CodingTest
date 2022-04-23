package dfs_bfs.ch5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Ex9BfsQueue {

    public static boolean[] visited = new boolean[9];
    public static ArrayList<Integer>[] graph = new ArrayList[9];

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int x = q.poll();
            System.out.print(x + " ");

            for (int i = 0; i < graph[x].size(); i++) {
                int y = graph[x].get(i);
                if(!visited[y]){
                    q.offer(y);
                    visited[y] = true;
                }
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Integer>();
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

        bfs(1);
    }
}
