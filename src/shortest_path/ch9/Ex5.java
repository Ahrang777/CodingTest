package shortest_path.ch9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
입력
3 2 1
1 2 4
1 3 2

출력
2 4
 */
public class Ex5 {

    static class Node implements Comparable<Node> {

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
        public int compareTo(Node other) {
            return (this.distance < other.getDistance() ? -1 : (this.distance > other.getDistance() ? 1 : 0));
        }
    }

    public static final int INF = (int) 1e9;

    //도시 개수(n), 통로 개수(m), 시작점(start),
    public static int n, m, start;

    public static ArrayList<Node>[] graph;

    public static int[] d = new int[30001];

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int dis = node.getDistance();
            int now = node.getIndex();

            if(d[now] < dis) continue;

            for (int i = 0; i < graph[now].size(); i++) {
                int cost = d[now] + graph[now].get(i).getDistance();
                if (cost < d[graph[now].get(i).getIndex()]) {
                    d[graph[now].get(i).getIndex()] = cost;
                    pq.offer(new Node(graph[now].get(i).getIndex(), cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        start = Integer.parseInt(stk.nextToken());

        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());
            graph[x].add(new Node(y, z));
        }

        Arrays.fill(d, INF);

        dijkstra(start);

        int count = 0;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (d[i] != INF) {
                count++;
                max = Math.max(max, d[i]);
            }
        }

        System.out.println((count - 1) + " " + max);
    }
}
