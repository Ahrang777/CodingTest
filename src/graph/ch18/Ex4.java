package graph.ch18;

import java.io.*;
import java.util.*;

/*
입력
5
11 -15 -15
14 -5 -15
-1 -1 -5
10 -4 -1
19 -4 19

출력
4
 */

/**
 * https://www.acmicpc.net/problem/2887
 * 
 * 행성 터널
 *
 * 1 <= N <= 100,000
 * 모든 행성을 연결해서 비교하는 경우 간선의 수가 n(n-1)/2 가 되기때문에 안됨
 *
 * 간선의 수를 줄여야 한다.
 * 아래 방법을 사용하면 간선의 수가 3(n-1) 이 된다.
 *
 * E = n(n-1)/2 vs 3(n-1), 크루스칼 시간 복잡도: (ElogE)
 */
public class Ex4 {

    public static class Position implements Comparable<Position> {
        private int pos;
        private int index;

        public Position(int pos, int index) {
            this.pos = pos;         //좌표 >> x좌표 or y좌표 or z좌표
            this.index = index;     //행성 구분용 번호 >> 1번 행성, 2번 행성
        }

        public int getPos() {
            return pos;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public int compareTo(Position other) {
            if (this.pos == other.pos) {
                return Integer.compare(this.index, other.index);
            }
            return Integer.compare(this.pos, other.pos);
        }
    }

    public static class Edge implements Comparable<Edge> {
        private int distance;
        private int nodeA;
        private int nodeB;

        public Edge(int distance, int nodeA, int nodeB) {
            this.distance = distance;
            this.nodeA = nodeA;
            this.nodeB = nodeB;
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

    public static int n;
    public static ArrayList<Edge> edges = new ArrayList<>();

    public static int[] parent = new int[100001];
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
        StringTokenizer stk = null;
        n = Integer.parseInt(bf.readLine());

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        ArrayList<Position> x = new ArrayList<>();
        ArrayList<Position> y = new ArrayList<>();
        ArrayList<Position> z = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            x.add(new Position(a, i));
            y.add(new Position(b, i));
            z.add(new Position(c, i));
        }

        // 각 축을 기준으로 인접한 정점간 간선 구하기 위해 정렬
        // 거리상 인접한 것들로 간선 만드는게 가장 작다
        // x좌표가 11, 14, -1, 10, 19 로 거리가 3, 15, 11, 9 되는것보다
        // 정렬해서 좌표가 -1, 10, 11, 14, 19 로 전체 거리는 20 이 되는게 최소다.
        Collections.sort(x);
        Collections.sort(y);
        Collections.sort(z);

        for (int i = 0; i < n - 1; i++) {
            edges.add(new Edge(x.get(i + 1).getPos() - x.get(i).getPos(), x.get(i).getIndex(), x.get(i + 1).getIndex()));
            edges.add(new Edge(y.get(i + 1).getPos() - y.get(i).getPos(), y.get(i).getIndex(), y.get(i + 1).getIndex()));
            edges.add(new Edge(z.get(i + 1).getPos() - z.get(i).getPos(), z.get(i).getIndex(), z.get(i + 1).getIndex()));
        }

        Collections.sort(edges);

        for (Edge edge : edges) {
            int cost = edge.getDistance();
            int a = edge.getNodeA();
            int b = edge.getNodeB();

            if (findRoot(a) != findRoot(b)) {
                union(a, b);
                result += cost;
            }
        }

        System.out.println(result);
    }
}
