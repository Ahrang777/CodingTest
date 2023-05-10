package study.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2343
 *
 * 기타 레슨
 */
public class Baekjoon2343 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		int start = 0;
		int end = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			start = Math.max(start, arr[i]);
			end += arr[i];
		}

		while (start < end) {
			int mid = (start + end) / 2;

			int cnt = 0;
			int sum = 0;

			for (int n : arr) {
				if (sum + n > mid) {
					sum = 0;
					cnt++;
				}

				sum += n;
			}

			if (sum > 0) {
				cnt++;
			}

			if (cnt <= M) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}

		System.out.println(start);



		/*while (start <= end) {
			int mid = (start + end) / 2;

			int cnt = 0;
			int sum = 0;
			for (int n : arr) {
				if (sum + n > mid) {
					sum = 0;
					cnt++;
				}

				sum += n;
			}

			if (sum > 0)
				cnt++;

			if (cnt > M) {
				start = mid + 1;
			} else {	//
				end = mid - 1;
			}
		}

		System.out.println(start);*/
	}
}
