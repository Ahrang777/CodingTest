package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42840
 *
 * 모의고사
 */
public class Programmers42840 {

    public static int[] solution(int[] answers) {
        int[][] p = {
                {1,2,3,4,5},
                {2,1,2,3,2,4,2,5},
                {3,3,1,1,2,2,4,4,5,5}
        };

        int[] score = new int[3];

        for (int i = 0; i < answers.length; i++){
            if (answers[i] == p[0][i % p[0].length])
                score[0]++;
            if (answers[i] == p[1][i % p[1].length])
                score[1]++;
            if (answers[i] == p[2][i % p[2].length])
                score[2]++;
        }

        int max = Math.max(score[0], Math.max(score[1], score[2]));

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < score.length; i++){
            if (max == score[i]) list.add(i + 1);
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
