package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1931
 *
 * 회의실 배정
 */
public class Baekjoon1931 {
	static class Meeting implements Comparable<Meeting> {
		int start, end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			if (this.end != o.end) {
				return Integer.compare(this.end, o.end);
			}

			return Integer.compare(this.start, o.start);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());

		List<Meeting> meetings = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			meetings.add(new Meeting(s, e));
		}

		Collections.sort(meetings);

		int prevEnd = 0;
		int cnt = 0;

		for (Meeting m : meetings) {
			if (prevEnd <= m.start) {
				cnt++;
				prevEnd = m.end;
			}
		}

		System.out.println(cnt);
	}
}
