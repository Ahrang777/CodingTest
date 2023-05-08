package study.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/1300
 *
 * K번째 수
 * 참고: https://st-lab.tistory.com/281
 */
public class Baekjoon1300 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		long start= 1;
		long end = K;

		while (start < end) {
			long mid = (start + end) / 2;
			long cnt = 0;

			for (int i = 1; i <= N; i++) {
				cnt += Math.min(mid / i, N);
			}

			if (K <= cnt) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}

		System.out.println(end);
	}
}
