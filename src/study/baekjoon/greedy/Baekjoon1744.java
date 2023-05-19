package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/1744
 *
 * 수 묶기
 */
public class Baekjoon1744 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int result = 0;

		int i = 0;
		int j = N - 1;

		// 음수, 0 의 경우 곱하고 더한다
		while (i < N - 1) {
			if (arr[i] < 1 && arr[i + 1] < 1) {
				result += arr[i++] * arr[i++];
			} else {
				break;
			}
		}

		// 1보다 큰 양수의 경우 곱하고 더한다.
		while (j > 0) {
			if (arr[j] > 1 && arr[j - 1] > 1) {
				result += arr[j--] * arr[j--];
			} else {
				break;
			}
		}

		// 1같은 나머지 수들은 그냥 더한다
		// i, j에서 마지막 대상에서 하나씩 증가, 감소 시켰기에 1이 있을 경우 위치가 같을 수 있다.
		while (i <= j) {
			result += arr[i++];
		}

		/*
		int start = 0;
		int end = N - 1;

		// 0, 음수
		for (; start < end; start += 2) {
			if (arr[start] < 1 && arr[start + 1] < 1) {
				result += arr[start] * arr[start + 1];
			} else {
				break;
			}
		}

		// 양수
		for (; end > 0; end -= 2) {
			if (arr[end] > 1 && arr[end - 1] > 1) {
				result += arr[end] * arr[end - 1];
			} else {
				break;
			}
		}

		// 나머지 수 더해주기
		for (; end >= start; end--) {
			result += arr[end];
		}
		*/

		System.out.println(result);
	}
}
