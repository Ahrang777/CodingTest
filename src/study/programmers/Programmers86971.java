package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/86971
 *
 * 전력망을 둘로 나누기
 * 다시 풀기
 */
public class Programmers86971 {

    /*
    int N, min;
    int[][] map;
    int[] vst;
    int dfs(int n){
        vst[n] = 1;
        int child = 1;
        for(int i = 1; i <= N; i++) {
            if(vst[i] == 0 && map[n][i] == 1) {
                child += dfs(i);
            }
        }
        min = Math.min(min, Math.abs(child - (N - child)));
        return child;
    }
    public int solution(int n, int[][] wires) {
        N = n;
        min = n;
        map = new int[n+1][n+1];
        vst = new int[n+1];
        for(int[] wire : wires) {
            int a = wire[0], b = wire[1];
            map[a][b] = map[b][a] = 1;
        }
        dfs(1);
        return min;
    }
     */

    public static int[][] graph;

    public static int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        graph = new int[n+1][n+1];

        for (int[] wire : wires) {
            graph[wire[0]][wire[1]] = 1;
            graph[wire[1]][wire[0]] = 1;
        }

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            graph[a][b] = 0;
            graph[b][a] = 0;

            answer = Math.min(answer, bfs(n, b));

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        return answer;
    }

    public static int bfs(int n, int start) {
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 1; i <= n; i++){
                if (!visited[i] && graph[now][i] == 1){
                    cnt++;
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }

        return Math.abs(n - 2 * cnt);
    }

    public static void main(String[] args) {
        int[] n = {9, 4, 7};
        int[][][] wires = {
                {
                        {1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}
                },
                {
                        {1, 2}, {2, 3}, {3, 4}
                },
                {
                        {1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}
                }
        };

        for (int i = 0; i < 3; i++) {
            System.out.println(solution(n[i], wires[i]));
        }
    }
}
