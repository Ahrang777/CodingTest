package graph.ch18;

import java.io.*;
import java.util.*;

/*
입력
3
5
5 4 3 2 1
2
2 4
3 4
3
2 3 1
0
4
1 2 3 4
3
1 2
3 4
2 3

출력
5 3 2 4 1
2 3 1
IMPOSSIBLE
 */

/**
 * https://www.acmicpc.net/problem/3665
 *
 * 최종 순위
 */
public class Ex5 {

    public static int test, n, m;
    // 모든 노드에 대한 진입차수는 0으로 초기화
    public static int[] indegree = new int[501];

    // 각 노드에 연결된 간선 정보를 담기 위한 배열 초기화
    public static boolean[][] graph = new boolean[501][501];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;

        test = Integer.parseInt(bf.readLine());

        for (int tc = 0; tc < test; tc++) {
            Arrays.fill(indegree, 0);

            for (int i = 0; i < 501; i++) {
                Arrays.fill(graph[i], false);
            }

            n = Integer.parseInt(bf.readLine());

            // 작년 순위 정보 입력
            stk = new StringTokenizer(bf.readLine(), " ");
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(stk.nextToken());
                arr.add(x);
            }

            // 방향 그래프의 간선 정보 초기화
            // 자신 보다 낮은 등수를 가진 팀을 가리키도록 한다.
            // graph[i][j] = true 이면 i -> j
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    graph[arr.get(i)][arr.get(j)] = true;
                    indegree[arr.get(j)] += 1;
                }
            }

            // 올해 변경된 순위 정보 입력
            m = Integer.parseInt(bf.readLine());
            for (int i = 0; i < m; i++) {
                stk = new StringTokenizer(bf.readLine(), " ");
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());

                // 간선의 방향 뒤집기
                // a -> b 에서 b -> a 로 변경
                if (graph[a][b]) {
                    graph[a][b] = false;
                    graph[b][a] = true;
                    indegree[a] += 1;
                    indegree[b] -= 1;
                }
                // b -> a 에서 a -> b 로 변경
                else {
                    graph[a][b] = true;
                    graph[b][a] = false;
                    indegree[a] -= 1;
                    indegree[b] += 1;
                }
            }

            // 위상 정렬(Topology Sort) 시작
            ArrayList<Integer> result = new ArrayList<>(); // 알고리즘 수행 결과를 담을 리스트
            Queue<Integer> q = new LinkedList<>(); // 큐 라이브러리 사용

            // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
            for (int i = 1; i <= arr.size(); i++) {
                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }


            boolean certain = true; // 위상 정렬 결과가 오직 하나인지의 여부 >> 결과가 여러개라서 확실한 순위가 없는지 체크
            boolean cycle = false; // 그래프 내 사이클이 존재하는지 여부 >> 일관성 체크

            // 정확히 노드의 개수만큼 반복
            for (int i = 0; i < n; i++) {
                // 큐가 비어 있다면 사이클이 발생했다는 의미
                if (q.size() == 0) {
                    cycle = true;
                    break;
                }
                // 큐의 원소가 2개 이상이라면 가능한 정렬 결과가 여러 개라는 의미
                if (q.size() >= 2) {
                    certain = false;
                    break;
                }
                // 큐에서 원소 꺼내기
                int now = q.poll();
                result.add(now);
                // 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
                for (int j = 1; j <= n; j++) {
                    if (graph[now][j]) {
                        indegree[j] -= 1;
                        // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                        if (indegree[j] == 0) {
                            q.offer(j);
                        }
                    }
                }
            }

            // 사이클이 발생하는 경우(일관성이 없는 경우)
            if (cycle) System.out.println("IMPOSSIBLE");

            // 위상 정렬 결과가 여러 개인 경우
            else if (!certain) System.out.println("?");

            // 위상 정렬을 수행한 결과 출력
            else {
                for (int i = 0; i < result.size(); i++) {
                    System.out.print(result.get(i) + " ");
                }
                System.out.println();
            }
        }
    }
}
