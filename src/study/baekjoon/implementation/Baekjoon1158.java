package study.baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1158
 * 요세푸스 문제
 */
public class Baekjoon1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}

		sb.append("<");

		List<Integer> list = new ArrayList<>();
		while (!q.isEmpty()) {
			for (int i = 0; i < K - 1; i++) {
				Integer n = q.poll();
				q.offer(n);
			}

			sb.append(q.poll()).append(", ");
		}

		int len = sb.length();
		sb.delete(len - 2, len);
		sb.append(">");

		System.out.println(sb);
	}
}
