package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1449
 *
 * 수리공 항승
 */
public class Baekjoon1449 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int start = arr[0];
		int end = start + L - 1;
		int result = 1;

		for (int pos : arr) {
			if (pos >= start && pos <= end) {
				continue;
			}

			start = pos;
			end = pos + L - 1;
			result++;
		}

		System.out.println(result);
	}
}
