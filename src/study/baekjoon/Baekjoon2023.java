package study.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2023
 * 신기한 소수
 */
public class Baekjoon2023 {
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		sb = new StringBuilder();

		for (int i = 1; i <= 9; i++) {
			if (isPrime(i)) {
				dfs(i + "");
			}
		}
		System.out.println(sb);
	}

	private static void dfs(String str) {
		if (str.length() == N) {
			sb.append(str).append("\n");
			return;
		}

		for (int i = 0; i <= 9; i++) {
			if (isPrime(Integer.parseInt(str + i))) {
				dfs(str + i);
			}
		}
	}

	private static boolean isPrime(int n) {
		if (n < 2)	return false;

		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
