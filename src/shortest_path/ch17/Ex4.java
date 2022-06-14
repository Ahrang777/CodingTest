package shortest_path.ch17;

import java.util.*;
import java.io.*;

/*
입력
6 7
3 6
4 3
3 2
1 3
1 2
2 4
5 2

출력
4 2 3
 */
public class Ex4 {

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
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int n, m;
    public static int start = 1;
    public static ArrayList<Node>[] graph;
    public static int[] d = new int[20001];
    public static final int INF = (int) 1e9;

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.getIndex();
            int dist = node.getDistance();

            if (d[now] < dist) continue;

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

        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            // 양방향 그래프
            // 한번 이동할때마다 1칸 늘어남
            graph[a].add(new Node(b, 1));
            graph[b].add(new Node(a, 1));
        }

        Arrays.fill(d, INF);

        dijkstra(start);

        /*int maxIndex = 1;
        for (int i = 2; i <= n; i++) {
            if (d[maxIndex] == d[i]) continue;

            if (d[maxIndex] < d[i]) {
                maxIndex = i;
            }
        }

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (d[maxIndex] == d[i]) {
                cnt += 1;
            }
        }

        System.out.print(maxIndex + " " + d[maxIndex] + " " + cnt);*/

        // 가장 최단 거리가 먼 노드 번호(동빈이가 숨을 헛간의 번호)
        int maxNode = 0;
        // 도달할 수 있는 노드 중에서, 가장 최단 거리가 먼 노드와의 최단 거리
        int maxDistance = 0;
        // 가장 최단 거리가 먼 노드와의 최단 거리와 동일한 최단 거리를 가지는 노드들의 리스트
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 1; i <= n; i++) {
            if (maxDistance < d[i]) {
                maxNode = i;
                maxDistance = d[i];
                result.clear();
                result.add(maxNode);
            }
            else if (maxDistance == d[i]) {
                result.add(i);
            }
        }

        System.out.println(maxNode + " " + maxDistance + " " + result.size());
    }
}
