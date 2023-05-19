package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2212
 *
 * 센서
 */
public class Baekjoon2212 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int[] diff = new int[N - 1];
		for (int i = 0; i < N - 1; i++) {
			diff[i] = arr[i + 1] - arr[i];
		}

		Arrays.sort(diff);

		int total = 0;
		for (int i = 0; i < N - K; i++) {
			total += diff[i];
		}

		System.out.println(total);
	}
}
