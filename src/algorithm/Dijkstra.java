package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
입력
6 11
1
1 2 2
1 3 5
1 4 1
2 3 3
2 4 2
3 2 3
3 6 5
4 3 3
4 5 1
5 3 1
5 6 2

출력
0
2
3
1
2
4
 */

/**
 * 최단 경로
 * 한 지점에서 다른 지점들로의 최단 경로
 */
public class Dijkstra {

    public static int n, m, start;
    public static ArrayList<Node>[] graph;
    public static int[] d = new int[100001];
    public static boolean[] visited = new boolean[100001];
    public static final int INF = Integer.MAX_VALUE;

    //O(V²), V: 노드 개수
    public static void dijkstra1(int start) {
        visited[start] = true;
        d[start] = 0;

        for (Node n : graph[start]) {
            d[n.getIndex()] = n.getDistance();
        }

        for (int i = 0; i < n - 1; i++) {
            //방문안하고 최단거리 가장 짧은 노드를 찾는 과정이 순차 탐색 : O(N)
            int now = getSmallestNode();
            visited[now] = true;

            for (Node node : graph[now]) {
                int cost = d[now] + node.getDistance();
                if (cost < d[node.getIndex()]) {
                    d[node.getIndex()] = cost;
                }
            }
        }
    }

    public static int getSmallestNode() {
        int min = INF;
        int index = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && d[i] < min) {
                min = d[i];
                index = i;
            }
        }
        return index;
    }

    public static class Node implements Comparable<Node> {

        private int index;
        private int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        public int getIndex() {
            return index;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    //O(ElogV), V: 노드 개수, E: 간선 개수
    //우선순위 큐를 이용하여 방문안하고 최단거리 가장 짧은 노드를 찾는 과정 >> O(logV)
    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.getIndex();
            int distance = node.getDistance();

            //최소라고 뽑은것보다 기존의 최소거리 테이블의 값이 더 작다면 이미 최소값으로 확정된것
            if(d[now] < distance) continue;

            for (Node next : graph[now]) {
                int cost = d[now] + next.getDistance();
                if (cost < d[next.getIndex()]) {
                    d[next.getIndex()] = cost;
                    pq.offer(new Node(next.getIndex(), cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        start = Integer.parseInt(bf.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            graph[a].add(new Node(b, c));
        }

        Arrays.fill(d, INF);

        dijkstra(start);

        for (int i = 1; i <= n; i++) {
            if (d[i] == INF) {
                System.out.println("INFINITY");
            } else {
                System.out.println(d[i]);
            }
        }
    }
}
