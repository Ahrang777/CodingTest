package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/1339
 *
 * 단어 수학
 */
public class Baekjoon1339 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[26];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				arr[str.charAt(j) - 'A'] += Math.pow(10, str.length() - 1 - j);
			}
		}

		Arrays.sort(arr);

		int result = 0;
		int num = 9;

		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] == 0) {
				break;
			}

			result += (arr[i] * num);
			num--;
		}

		System.out.println(result);
	}
}
