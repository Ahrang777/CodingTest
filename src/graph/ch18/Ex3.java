package graph.ch18;

import java.io.*;
import java.util.*;

/*
입력
7 11
0 1 7
0 3 5
1 2 8
1 3 9
1 4 7
2 4 5
3 4 15
3 5 6
4 5 8
4 6 9
5 6 11

출력
51
 */
public class Ex3 {

    public static class Edge implements Comparable<Edge> {
        private int nodeA;
        private int nodeB;
        private int distance;

        public Edge(int nodeA, int nodeB, int distance) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.distance = distance;
        }

        public int getNodeA() {
            return nodeA;
        }

        public int getNodeB() {
            return nodeB;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int n, m;
    public static int[] parent = new int[200001]; // 부모 테이블 초기화하기
    public static ArrayList<Edge> edges = new ArrayList<>();
    public static int result = 0;

    public static int findRoot(int x) {
        if (x == parent[x]) return x;
        return parent[x] = findRoot(parent[x]);
    }

    public static void union(int a, int b) {
        a = findRoot(a);
        b = findRoot(b);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());

            edges.add(new Edge(x, y, z));
            result += z;
        }

        Collections.sort(edges);

        for (Edge edge : edges) {
            int nodeA = edge.getNodeA();
            int nodeB = edge.getNodeB();
            int distance = edge.getDistance();

            if (findRoot(nodeA) != findRoot(nodeB)) {
                union(nodeA, nodeB);
                result -= distance;
            }
        }

        System.out.println(result);
    }
}
