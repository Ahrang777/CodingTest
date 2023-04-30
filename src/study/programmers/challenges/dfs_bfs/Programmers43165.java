package study.programmers.challenges.dfs_bfs;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43165
 *
 * 타겟 넘버
 */
public class Programmers43165 {

    /*public static int answer = 0;
    public static int[] output;

    public static int solution(int[] numbers, int target) {

        output = new int[numbers.length];

        dfs(0, numbers, new int[]{-1, 1}, target);
        return answer;
    }

    public static void dfs(int depth, int[] numbers, int[] arr, int target) {
        if (depth == numbers.length) {

            int res = 0;
            for (int i = 0; i < output.length; i++){
                res += (output[i] * numbers[i]);
            }

            if (res == target)  answer++;

            return;
        }

        for (int i = 0; i < 2; i++){
            output[depth] = arr[i];
            dfs(depth + 1, numbers, arr, target);
        }
    }*/

    public static int solution(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(numbers, 0, 0, target);
        return answer;
    }

    public static int dfs(int[] numbers, int depth, int sum, int target) {
        if (depth == numbers.length) {
            if (sum == target) return 1;

            return 0;
        }

        // (+인 경우) + (-인 경우)
        return dfs(numbers, depth + 1, sum + numbers[depth], target) + dfs(numbers, depth + 1, sum - numbers[depth], target);
    }

    public static void main(String[] args) {
        int[][] numbers = {
                {1, 1, 1, 1, 1},
                {4, 1, 2, 1}
        };

        int[] target = {3, 4};

        for (int i = 0; i < 2; i++) {
//            answer = 0;
            System.out.println(solution(numbers[i], target[i]));
        }
    }
}
