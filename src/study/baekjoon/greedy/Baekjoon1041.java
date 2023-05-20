package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1041
 * 주사위
 */
public class Baekjoon1041 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		long N = Integer.parseInt(br.readLine());
		long[] num = new long[4];
		num[1] = 5 * (N - 2) * (N - 2) + 4 * (N - 2);
		num[2] = 8 * (N - 2) + 4;
		num[3] = 4;

		st = new StringTokenizer(br.readLine());
		List<Integer> dice = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			dice.add(Integer.parseInt(st.nextToken()));
		}

		long result = 0;

		if (N == 1) {
			Collections.sort(dice);
			for (int i = 0; i < 5; i++) {
				result += dice.get(i);
			}
		} else {
			long min = Collections.min(dice);
			result += num[1] * min;

			min = Long.MAX_VALUE;
			for (int i = 0; i < 6; i++) {
				for (int j = i + 1; j < 6; j++) {
					if (i + j != 5) {
						min = Math.min(min, dice.get(i) + dice.get(j));
					}
				}
			}

			result += num[2] * min;

			int sum = 0;
			for (int i = 0; i < 3; i++) {
				sum += Math.min(dice.get(i), dice.get(5 - i));
			}

			result += num[3] * sum;
		}

		System.out.println(result);
	}
}
