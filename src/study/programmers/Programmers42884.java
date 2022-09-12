package study.programmers;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42884
 *
 * 단속카메라
 * 다시풀기
 */
public class Programmers42884 {

    public static int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);

        int cam = Integer.MIN_VALUE;
        for (int[] route : routes) {
            if (cam < route[0]) {
                cam = route[1];
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] routes = {
                {-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}
        };
        
        System.out.println(solution(routes));
    }
}
