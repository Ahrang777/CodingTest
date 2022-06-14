package graph.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
입력
7 12
1 2 3
1 3 2
3 2 1
2 5 2
3 4 4 
7 3 6
5 1 5
1 6 2
6 4 1
6 5 3
4 5 3
6 7 4

출력

 */
public class Ex8 {

    static class Edge implements Comparable<Edge> {

        private int distance;
        private int nodeA;
        private int nodeB;

        public Edge(int distance, int nodeA, int nodeB) {
            this.distance = distance;
            this.nodeA = nodeA;
            this.nodeB = nodeB;
        }

        public int getDistance() {
            return distance;
        }

        public int getNodeA() {
            return nodeA;
        }

        public int getNodeB() {
            return nodeB;
        }

        @Override
        public int compareTo(Edge o) {
            return (this.distance < o.distance ? -1 : (this.distance > o.distance ? 1 : 0));
        }
    }

    //N개의 집(노드), M개의 길(간선)
    public static int n, m;
    public static int[] parent = new int[100001];
    public static ArrayList<Edge> edges = new ArrayList<>();
    public static int result = 0;

    public static int findRoot(int x) {
        if(x == parent[x]) return x;
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
            //a - b : 유지비 c
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());  //유지비

            edges.add(new Edge(c, a, b));
        }

        Collections.sort(edges);

        int max = 0;
        for (int i = 0; i < edges.size(); i++) {
            int a = edges.get(i).getNodeA();
            int b = edges.get(i).getNodeB();
            int distance = edges.get(i).getDistance();

            if (findRoot(a) != findRoot(b)) {
                result += distance;
                max = Math.max(max, distance);
                union(a, b);
            }
        }
        result -= max;
        System.out.println(result);
    }
}
