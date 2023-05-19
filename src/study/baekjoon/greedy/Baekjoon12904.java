package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/12904
 *
 * 문제
 */
public class Baekjoon12904 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder S = new StringBuilder(br.readLine());
		StringBuilder T = new StringBuilder(br.readLine());

		while (S.length() != T.length()) {
			int len = T.length();
			if (T.charAt(len - 1) == 'A') {
				T.deleteCharAt(len - 1);
				continue;
			}

			if (T.charAt(len - 1) == 'B') {
				T.deleteCharAt(len - 1);
				T.reverse();
			}
		}

		int result = S.toString().equals(T.toString()) ? 1 : 0;

		System.out.println(result);
	}
}
