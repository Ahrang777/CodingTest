package study.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1654
 *
 * 랜선 자르기
 */
public class Baekjoon1654 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] lines = new int[K];
		long end = 0;
		for (int i = 0; i < K; i++) {
			lines[i] = Integer.parseInt(br.readLine());
			if (end < lines[i])    end = lines[i];
		}

		long start = 1;
		long result = 0;

		while (start <= end) {
			long mid = (start + end) / 2;

			long cnt = 0;
			for (int line : lines) {
				cnt += (line / mid);
			}

			if (cnt >= N) {
				start = mid + 1;
				result = Math.max(result, mid);
			} else {
				end = mid - 1;
			}
		}

		System.out.println(result);
	}
}
