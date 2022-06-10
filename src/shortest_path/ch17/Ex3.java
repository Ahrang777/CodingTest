package shortest_path.ch17;

import java.io.*;
import java.util.*;

/*
입력
3
3
5 5 4
3 9 1
3 2 7
5
3 7 2 0 1
2 8 0 9 1
1 2 1 8 1
9 8 9 2 0
3 6 5 1 5
7
9 0 5 1 1 5 3
4 1 2 1 6 5 3
0 7 6 1 6 8 5
1 1 7 8 3 2 3
9 4 0 7 6 4 1
5 8 3 2 4 8 3
7 4 8 4 8 3 4

출력

 */
public class Ex3 {

    public static class Node implements Comparable<Node>{
        private int x;
        private int y;
        private int distacne;

        public Node(int x, int y, int distacne) {
            this.x = x;
            this.y = y;
            this.distacne = distacne;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getDistacne() {
            return distacne;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distacne, other.distacne);
        }
    }

    public static int t, n;
    public static int[][] graph;
    public static int[][] d;
    public static final int INF = (int) 1e9;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    //시작 좌표 x, y
    public static void dijkstra(int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(x, y, graph[x][y]));
        d[x][y] = graph[x][y];

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            x = node.getX();
            y = node.getY();
            int dist = node.getDistacne();

            if (d[x][y] < dist) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 이런 맵과 같은 형태는 항상 범위 체크가 중요
                if (nx < 0 || nx >= n || ny < 0 || ny >= n)   continue;

                int cost = d[x][y] + graph[nx][ny];

                if (cost < d[nx][ny]) {
                    d[nx][ny] = cost;
                    pq.offer(new Node(nx, ny, cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;

        t = Integer.parseInt(bf.readLine());

        ArrayList<Integer> results = new ArrayList<>();

        for (int k = 0; k < t; k++) {
            n = Integer.parseInt(bf.readLine());
            graph = new int[n][n];
            d = new int[n][n];

            for (int i = 0; i < n; i++) {
                Arrays.fill(d[i], INF);
            }

            for (int i = 0; i < n; i++) {
                stk = new StringTokenizer(bf.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            dijkstra(0, 0);

            results.add(d[n - 1][n - 1]);
        }

        for (Integer result : results) {
            System.out.println(result);
        }
    }
}
