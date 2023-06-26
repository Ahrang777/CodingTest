package study.baekjoon.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14719
 * 빗물
 */
public class Baekjoon14719 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[] arr = new int[W];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int result = 0;
		/**
		 * 현재 블록의 높이보다 높은 블록이 왼쪽에 있어야 한다.
		 * 현재 블록의 높이보다 높은 블록이 오른쪽에 있어야 한다.
		 * 첫, 마지막 블록에는 빗물이 고일 수 없다. 1 ~ W - 2
		 */
		for (int i = 1; i < W - 1; i++) {
			int left = 0;
			int right = 0;

			for (int j = 0; j < i; j++) {
				left = Math.max(arr[j], left);
			}
			for (int j = i + 1; j < W; j++) {
				right = Math.max(arr[j], right);
			}

			if (arr[i] < left && arr[i] < right) {
				result += (Math.min(left, right) - arr[i]);
			}
		}

		System.out.println(result);
	}
}
