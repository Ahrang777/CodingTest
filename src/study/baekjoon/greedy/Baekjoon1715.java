package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * https://www.acmicpc.net/problem/1715
 *
 * 카드 정렬하기
 */
public class Baekjoon1715 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int result = 0;

		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}

		while (pq.size() > 1) {
			Integer first = pq.poll();
			Integer second = pq.poll();
			int sum = first + second;

			result += sum;
			pq.offer(sum);
		}

		System.out.println(result);
	}
}
