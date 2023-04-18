package study.programmers.challenges.heap;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42626
 *
 * 더 맵게
 */
public class Programmers42626 {

    public static int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.offer(s);
        }

        while (true) {
            if (pq.peek() >= K) {
                break;
            }

            if (pq.size() <= 1) {
                answer = -1;
                break;
            }


            answer++;
            int first = pq.poll();
            int second = pq.poll();

            int res = first + 2 * second;

            pq.offer(res);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;

        System.out.println(solution(scoville, K));
    }
}
