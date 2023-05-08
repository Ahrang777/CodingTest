package study.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon2467 {
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

		int left = 0;
		int right = n - 1;
		int min = Integer.MAX_VALUE;
		int n1 = 0, n2 = 0;

		while (left < right) {
			int sum = arr[left] + arr[right];

			if (Math.abs(sum) < min) {
				n1 = arr[left];
				n2 = arr[right];
				min = Math.abs(sum);
			}

			if (sum > 0) {
				right--;
			} else {
				left++;
			}
		}

		System.out.println(n1 + " " + n2);
	}
}
