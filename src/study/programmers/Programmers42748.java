package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42748
 *
 * K번째수
 */
public class Programmers42748 {

    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int[] tmp = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            Arrays.sort(tmp);
            answer[i] = tmp[commands[i][2] - 1];
        }

        /*for (int a = 0; a < commands.length; a++){
            int[] command = commands[a];
            int i = command[0];
            int j = command[1];
            int k = command[2];

            ArrayList<Integer> arr = new ArrayList<>();

            for(int b = i - 1; b < j; b++){
                arr.add(array[b]);
            }

            Collections.sort(arr);
            answer[a] = arr.get(k - 1);
        }*/

        return answer;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {
                {2, 5, 3},
                {4, 4, 1},
                {1, 7, 3}
        };

        int[] solution = solution(array, commands);

        for (int n : solution) {
            System.out.print(n + " ");
        }
    }
}
