package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/49189
 *
 * 가장 먼 노드
 */
public class Programmers49189 {

    static List<Integer>[] graph;
    static int[] count;
    static boolean[] visited;

    public static int solution(int n, int[][] edge) {
        graph = new ArrayList[n + 1];
        count = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        bfs(1);
        Arrays.sort(count);

        int max = count[count.length - 1];
        int answer = 0;

        for (int i = count.length - 1; count[i] == max; i--) {
            answer++;
        }

        return answer;
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        count[start] = 0;

        while(!q.isEmpty()) {
            int now = q.poll();
            int dist = count[now];

            for (int next : graph[now]) {
                if (!visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                    count[next] = dist + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] vertex = {
                {3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}
        };

        System.out.println(solution(n, vertex));
    }
}
