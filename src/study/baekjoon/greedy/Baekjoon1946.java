package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1946
 *
 * 신입 사원
 */
public class Baekjoon1946 {
	static class Score implements Comparable<Score> {
		int document, interview;

		public Score(int document, int interview) {
			this.document = document;
			this.interview = interview;
		}

		@Override
		public int compareTo(Score o) {
			return Integer.compare(this.document, o.document);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			List<Score> scores = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int document = Integer.parseInt(st.nextToken());
				int interview = Integer.parseInt(st.nextToken());
				scores.add(new Score(document, interview));
			}

			Collections.sort(scores);

			int cnt = 1;	// 서류 1등은 무조건 통과
			int interviewMin = scores.get(0).interview;

			for (int i = 1; i < N; i++) {
				// 이전 면접 점수들 중 최소값
				if (interviewMin > scores.get(i).interview) {
					cnt++;
					interviewMin = scores.get(i).interview;
				}
			}

			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
