package study.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2559
 * 수열
 */
public class Baekjoon2559 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0;
		for (int i = 1; i <= K; i++) {
			sum += arr[i];
		}

		int max = sum;

		int s = 1, e = K;

		while (e + 1 <= N) {
			int newSum = sum - arr[s] + arr[e + 1];
			if (newSum > max) {
				max = newSum;
			}

			sum = newSum;
			s++;
			e++;
		}

		System.out.println(max);
	}

	// public static void main(String[] args) throws Exception {
	// 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 	StringTokenizer st = new StringTokenizer(br.readLine());
	//
	// 	int N = Integer.parseInt(st.nextToken());
	// 	int K = Integer.parseInt(st.nextToken());
	//
	// 	int[] arr = new int[N + 1];
	// 	st = new StringTokenizer(br.readLine());
	// 	for (int i = 1; i <= N; i++) {
	// 		arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
	// 	}
	//
	// 	int max = arr[K];
	//
	// 	for (int i = K + 1; i <= N; i++) {
	// 		max = Math.max(max, arr[i] - arr[i - K]);
	// 	}
	//
	// 	System.out.println(max);
	// }
}
