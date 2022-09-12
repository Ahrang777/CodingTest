package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42861
 *
 * 섬 연결하기
 */
public class Programmers42861 {

    public static int[] parent;

    public static int findRoot(int x) {
        if (x == parent[x]) return x;
        return parent[x] = findRoot(parent[x]);
    }

    public static void union(int a, int b) {
        a = findRoot(a);
        b = findRoot(b);

        if (a < b)  parent[b] = a;
        else    parent[a] = b;
    }

    public static int solution(int n, int[][] costs) {
        int answer = 0;

        parent = new int[n];
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            int distance = cost[2];

            if (findRoot(a) != findRoot(b)) {
                answer += distance;
                union(a, b);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {
                {0, 1, 1}, {0, 2, 2}, {1, 2, 5},
                {1, 3, 1}, {2, 3, 8}
        };

        System.out.println(solution(n, costs));
    }
}
