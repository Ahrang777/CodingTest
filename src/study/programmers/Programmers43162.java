package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43162
 *
 * 네트워크
 */
public class Programmers43162 {

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] chk = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!chk[i]) {
                dfs(computers, chk, i);
                answer++;
            }
        }
        return answer;
    }
    public static void dfs(int[][] computers, boolean[] chk, int start) {
        chk[start] = true;
        for(int i = 0; i < computers.length; i++) {
            if(computers[start][i] == 1 && !chk[i]) {
                dfs(computers, chk, i);
            }
        }
    }

    /*static boolean[] visited;

    public static int solution(int n, int[][] computers) {
        int answer = 0;

        visited = new boolean[n];

        for (int i = 0; i < n; i++){
            if (!visited[i]) {
                bfs(computers, i, n);
                answer++;
            }
        }

        return answer;
    }

    public static void bfs(int[][] computers, int start, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int now = q.poll();

            for (int i =  0; i < n; i++) {

                if (!visited[i] && computers[now][i] == 1) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }

    }*/

    public static void main(String[] args) {
        int[] n = {3, 3};
        int[][][] computers = {
                {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}},
                {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}
        };

        for (int i = 0; i < 2; i++) {
            System.out.println(solution(n[i], computers[i]));
        }
    }
}
