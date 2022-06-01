package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 최소 신장 트리 >> 신장 트리 중 최소 비용으로 만들 수 있는 신장 트리 찾기
 */

class Edge implements Comparable<Edge> {

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
    public int compareTo(Edge o) {
        return Integer.compare(this.distance, o.distance);
    }
}
public class Kruskal {

    public static int v, e;
    public static int[] parent = new int[100001];

    public static ArrayList<Edge> edges = new ArrayList<>();
    public static int result = 0;

    public static int findRoot(int x) {
        if (x == parent[x]) {
            return x;
        }
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

        v = Integer.parseInt(stk.nextToken());
        e = Integer.parseInt(stk.nextToken());

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int distance = Integer.parseInt(stk.nextToken());

            edges.add(new Edge(a, b, distance));
        }

        Collections.sort(edges);

        for (Edge edge : edges) {
            int a = edge.getNodeA();
            int b = edge.getNodeB();
            int distance = edge.getDistance();

            if (findRoot(a) != findRoot(b)) {
                result += distance;
                union(a, b);
            }
        }

        System.out.println(result);
    }
}
