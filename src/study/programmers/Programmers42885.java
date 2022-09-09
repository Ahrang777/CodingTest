package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42885
 *
 * 구명보트
 * 다시풀기
 */
public class Programmers42885 {

    public static int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);

        int min = 0;

        for (int max = people.length - 1; min <= max; max--){
            if (people[min] + people[max] <= limit) min++;
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] people = {
                {70, 50, 80, 50}, {70, 80, 50}
        };

        int[] limit = {100, 100};

        for (int i = 0; i < 2; i++) {
            System.out.println(solution(people[i], limit[i]));
        }
    }
}
