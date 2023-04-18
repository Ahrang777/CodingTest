package study.programmers.challenges.stack_queue;

import java.util.ArrayList;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42584
 *
 * 주식가격
 */
public class Programmers42584 {

    /*public static int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                answer[i]++;
                if (prices[i] > prices[j]) break;
            }
        }

        return answer;
    }*/

    public static int[] solution(int[] prices) {
        Stack<Integer> beginIdxs = new Stack<>();
        int i=0;
        int[] terms = new int[prices.length];

        beginIdxs.push(i);
        for (i=1; i<prices.length; i++) {
            while (!beginIdxs.empty() && prices[i] < prices[beginIdxs.peek()]) {
                int beginIdx = beginIdxs.pop();
                terms[beginIdx] = i - beginIdx;
            }
            beginIdxs.push(i);
        }
        while (!beginIdxs.empty()) {
            int beginIdx = beginIdxs.pop();
            terms[beginIdx] = i - beginIdx - 1;
        }

        return terms;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        for (int s : solution(prices)) {
            System.out.print(s + " ");
        }
    }
}
