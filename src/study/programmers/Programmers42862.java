package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42862
 * 
 * 체육복
 */
public class Programmers42862 {

    /**
     * 잃어버린 사람: -1
     * 안잃어버리고 여벌없는 경우 or 잃어버렸지만 여벌가지면(자기 옷 있지만 빌려줄수 없는 경우): 0,
     * 빌려줄수 있는 경우: 1
     */
    public static int solution(int n, int[] lost, int[] reserve) {
        int[] people = new int[n + 2];
        int answer = n;

        for (int l : lost)
            people[l]--;
        for (int r : reserve)
            people[r]++;

        for (int i = 1; i <= n; i++) {
            if (people[i] == -1) {
                if (people[i - 1] == 1) {
                    people[i]++;
                    people[i - 1]--;
                } else if (people[i + 1] == 1) {
                    people[i]++;
                    people[i + 1]--;
                } else {
                    answer--;
                }
            }
        }

        return answer;


    }

    /*public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;

        Arrays.sort(lost);
        Arrays.sort(reserve);

        for (int i = 0; i < reserve.length; i++) {
            for (int j = 0; j < lost.length; j++) {
                if (reserve[i] == lost[j]) {
                    reserve[i] = -1;
                    lost[j] = -1;
                    answer++;
                    break;
                }
            }
        }

        for (int r : reserve) {
            for (int i = 0; i < lost.length; i++) {
                if (r == lost[i] - 1 || r == lost[i] + 1) {
                    answer++;
                    lost[i] = -1;
                    break;
                }
            }
        }

        return answer;
    }*/

    public static void main(String[] args) {
        int[] n = {5, 5, 3};
        int[][] lost = {
                {2, 4}, {2, 4}, {3}
        };
        int[][] reserve = {
                {1, 3, 5}, {3}, {1}
        };

        for (int i = 0; i < 3; i++) {
            System.out.println(solution(n[i], lost[i], reserve[i]));
        }
    }
}
