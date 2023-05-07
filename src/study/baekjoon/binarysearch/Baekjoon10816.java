package study.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10816
 *
 * 숫자카드2
 */
public class Baekjoon10816 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int target = Integer.parseInt(st.nextToken());
			sb.append(countByRange(arr, target, target)).append(" ");
		}

		System.out.println(sb);
	}

	private static int countByRange(int[] arr, int leftValue, int rightValue) {
		int rigthIndex = upperBound(arr, rightValue, 0, arr.length);
		int leftIndex = leftBound(arr, leftValue, 0, arr.length);
		return rigthIndex - leftIndex;
	}

	private static int upperBound(int[] arr, int target, int start, int end) {
		while (start < end) {
			int mid = (start + end) / 2;

			if (arr[mid] > target)	end = mid;
			else start = mid + 1;
		}

		return end;
	}

	private static int leftBound(int[] arr, int target, int start, int end) {
		while (start < end) {
			int mid = (start + end) / 2;
			if (arr[mid] >= target)	end = mid;
			else
				start = mid + 1;
		}
		return end;
	}
}
