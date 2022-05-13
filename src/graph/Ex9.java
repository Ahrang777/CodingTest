package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
입력
5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 -1

출력
10
20
14
18
17
 */
public class Ex9 {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static int[] indegree = new int[501];
    public static int[] times = new int[501];

    public static void topologySort() {
        int[] result = new int[501]; // 알고리즘 수행 결과를 담을 배열
        for (int i = 1; i <= n; i++) {
            result[i] = times[i];
        }

        Queue<Integer> q = new LinkedList<>(); // 큐 라이브러리 사용

        // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            // 큐에서 원소 꺼내기
            int now = q.poll();
            // 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
            for (int i = 0; i < graph[now].size(); i++) {
                result[graph[now].get(i)] = Math.max(result[graph[now].get(i)], result[now] + times[graph[now].get(i)]);
                indegree[graph[now].get(i)] -= 1;
                // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                if (indegree[graph[now].get(i)] == 0) {
                    q.offer(graph[now].get(i));
                }
            }
        }

        // 위상 정렬을 수행한 결과 출력
        for (int i = 1; i <= n; i++) {
            System.out.println(result[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
            //해당 강의 시간
            int x = Integer.parseInt(stk.nextToken());
            times[i] = x;
            while (true) {
                //선수과목 or 끝 표시
                x = Integer.parseInt(stk.nextToken());
                if (x == -1) {
                    break;
                }
                graph[x].add(i);    //x -> i
                indegree[i]++;
            }
        }

        topologySort();
    }
}
