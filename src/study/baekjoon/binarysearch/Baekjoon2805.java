package study.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2805
 * 
 * 나무 자르기
 */
public class Baekjoon2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = 0;

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] trees = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = 1000000000;

		while (start <= end) {
			int mid = (start + end) / 2;
			long total = 0;	// 범위 주의!

			for (int tree : trees) {
				if (tree > mid) {
					total += (tree - mid);
				}
			}

			if (total >= m) {
				answer = Math.max(answer, mid);
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		System.out.println(answer);
	}
}
