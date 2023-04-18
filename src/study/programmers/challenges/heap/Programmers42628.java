package study.programmers.challenges.heap;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42628
 *
 * 이중우선순위큐
 */
public class Programmers42628 {
    /*
    public int[] solution(String[] operations) {
        int[] answer = new int[2];

		PriorityQueue<Integer> minQ = new PriorityQueue<>();
		PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());

		for (String operation : operations) {
			String[] arr = operation.split(" ");
			int n = Integer.parseInt(arr[1]);

			if (arr[0].equals("I")) {
				maxQ.offer(n);
				minQ.offer(n);
				continue;
			}

			if (maxQ.isEmpty() && minQ.isEmpty()) {
				continue;
			}

			if (n == 1) {
				Integer max = maxQ.poll();
				minQ.remove(max);
				continue;
			}

			if (n == -1) {
				Integer min = minQ.poll();
				maxQ.remove(min);
			}
		}

		if (maxQ.isEmpty() && minQ.isEmpty()) {
			answer[0] = 0;
			answer[1] = 0;
		} else {
			answer[0] = maxQ.poll();
			answer[1] = minQ.poll();
		}

		return answer;
    }
     */

    public static int[] solution(String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minQ = new PriorityQueue<>();

        for (String op : operations) {
            String[] s = op.split(" ");
            int n = Integer.parseInt(s[1]);

            if (s[0].equals("I")) { // 삽입
                maxQ.offer(n);
                minQ.offer(n);
            } else {
                if (n == -1) {  // 최솟값 삭제
                    if (minQ.isEmpty()) {
                        continue;
                    }
                    int min = minQ.poll();
                    maxQ.remove(min);
                } else if (n == 1) {    // 최댓값 삭제
                    if (maxQ.isEmpty()) {
                        continue;
                    }

                    int max = maxQ.poll();
                    minQ.remove(max);
                }
            }

        }

        if (maxQ.isEmpty() && minQ.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = maxQ.poll();
            answer[1] = minQ.poll();
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] operations = {
                {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"},
                {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}
        };

        for (int i = 0; i < operations.length; i++) {
            for (int s : solution(operations[i])) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
