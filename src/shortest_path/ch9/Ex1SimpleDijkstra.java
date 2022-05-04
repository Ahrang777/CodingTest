package shortest_path.ch9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 입력
 * 6 11
 * 1
 * 1 2 2
 * 1 3 5
 * 1 4 1
 * 2 3 3
 * 2 4 2
 * 3 2 3 
 * 3 6 5
 * 4 3 3
 * 4 5 1
 * 5 3 1
 * 5 6 2
 * 
 * 출력
 * 0
 * 2
 * 3
 * 1
 * 2
 * 4
 */
public class Ex1SimpleDijkstra {

    public static class Node{
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
    }

    public static final int INF = (int) 1e9;
    
    //노드 개수, 간선 개수, 시작 노드 번호
    public static int n, m, start;

    public static ArrayList<Node>[] graph;

    //방문 체크
    public static boolean[] visited = new boolean[100001];

    //최단 거리 테이블
    public static int[] d = new int[100001];

    private static void dijkstra(int start) {
        d[start] = 0;
        visited[start] = true;
        for (int i = 0; i < graph[start].size(); i++) {
            d[graph[start].get(i).getIndex()] = graph[start].get(i).getDistance();
        }

        //가장 작은거 뽑고, 갱신하고 한 세트 
        //처음에 start를 가장 작은걸로 뽑고 갱신했기에 n-1번 반복
        for (int i = 0; i < n - 1; i++) {
            int now = getSmallestNode();
            visited[now] = true;

            //now 에 연결된 노드들 최단거리 비교, 갱신
            for (int j = 0; j < graph[now].size(); j++) {
                int cost = d[now] + graph[now].get(j).getDistance();
                if (cost < d[graph[now].get(j).getIndex()]) {
                    d[graph[now].get(j).getIndex()] = cost;
                }
            }
        }
    }

    public static int getSmallestNode(){
        int minValue = INF;
        int index = 0;
        for (int i = 1; i <= n; i++) {
            if (d[i] < minValue && !visited[i]) {
                minValue = d[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        start = Integer.parseInt(bf.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            //a노드에서 b노드로 가는 비용이 c
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
