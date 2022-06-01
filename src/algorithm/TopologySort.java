package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 위상 정렬
 * 방향 그래프의 모든 노드를 방향성을 거스르지 않고 순서대로 나열
 * ex) 선수과목
 */
public class TopologySort {

    public static int v, e;
    public static ArrayList<Integer>[] graph;
    public static int[] indegree = new int[100001]; //진입 차수

    public static void topologySort() {
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 1; i <= v; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);

            for (int next : graph[now]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        // 위상 정렬을 수행한 결과 출력
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        v = Integer.parseInt(stk.nextToken());
        e = Integer.parseInt(stk.nextToken());

        graph = new ArrayList[v + 1];
        for (int i = 0; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            //a -> b
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            graph[a].add(b);
            indegree[b]++;
        }

        topologySort();
    }
}
