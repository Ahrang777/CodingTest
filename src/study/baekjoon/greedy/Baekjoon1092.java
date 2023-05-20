package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1092
 *
 * 배
 */
public class Baekjoon1092 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());

		List<Integer> limits = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			limits.add(Integer.parseInt(st.nextToken()));
		}

		int M = Integer.parseInt(br.readLine());
		List<Integer> boxes = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			boxes.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(limits, Collections.reverseOrder());
		Collections.sort(boxes, Collections.reverseOrder());

		if (limits.get(0) < boxes.get(0)) {
			System.out.println(-1);
			return;
		}

		int cnt = 0;
		while (!boxes.isEmpty()) {
			int boxIndex = 0;
			int limitsIndex = 0;

			while (limitsIndex < N) {
				if (boxIndex >= boxes.size()) {
					break;
				}

				if (limits.get(limitsIndex) >= boxes.get(boxIndex)) {
					boxes.remove(boxIndex);
					limitsIndex++;
				} else {
					boxIndex++;
				}
			}
			cnt++;
		}

		System.out.println(cnt);
	}
}
