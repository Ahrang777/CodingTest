package study.programmers.challenges.greedy;

import java.util.Stack;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42883
 * 
 * 큰 수 만들기
 */
public class Programmers42883 {

    /*public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int max = 0;
        int index = 0;
        for (int i = 0; i < number.length() - k; i++) {
            max = 0;
            for (int j = index; j <= k + i; j++) {
                if (max < number.charAt(j) - '0') {
                    max = number.charAt(j) - '0';
                    index = j + 1;
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }*/

    public static String solution(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }

        for (int i=0; i<result.length; i++) {
            result[i] = stack.get(i);
        }

        return new String(result);
    }

    public static void main(String[] args) {
        String[] number = {"1924", "1231234", "4177252841"};
        int[] k = {2, 3, 4};

        for (int i = 0; i < 3; i++) {
            System.out.println(solution(number[i], k[i]));
        }
    }
}
