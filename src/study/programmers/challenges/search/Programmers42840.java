package study.programmers.challenges.search;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42840
 *
 * 모의고사
 */
public class Programmers42840 {

    public static int[] solution(int[] answers) {
        int[][] submit = {
                {1,2,3,4,5},
                {2,1,2,3,2,4,2,5},
                {3,3,1,1,2,2,4,4,5,5}
        };

        int[] scores = new int[3];

        for (int i = 0; i < answers.length; i++){
            for (int j = 0; j < scores.length; j++) {
                if (answers[i] == submit[j][i % submit[j].length]) {
                    scores[j]++;
                }
            }
        }

        int max = Math.max(scores[0], Math.max(scores[1], scores[2]));

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < scores.length; i++){
            if (max == scores[i]) list.add(i + 1);
        }

        int answer[] = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }


        return answer;
//        return list.stream().mapToInt(i -> i.intValue()).toArray();
    }

    public static void main(String[] args) {
        int[][] answers = {
                {1, 2, 3, 4, 5},
                {1, 3, 2, 4, 2}
        };

        for (int i = 0; i < answers.length; i++) {
            int[] solution = solution(answers[i]);

            for (int s : solution) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
