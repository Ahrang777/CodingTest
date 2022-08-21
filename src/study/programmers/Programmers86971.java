package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/86971
 *
 *
 */
public class Programmers86971 {

    public static int solution(int n, int[][] wires) {
        int answer = -1;

        return answer;
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
