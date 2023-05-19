package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11000
 *
 * 강의실 배정
 */
public class Baekjoon11000 {
	static class Lecture implements Comparable<Lecture> {
		int start, end;

		public Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Lecture o) {
			if (this.start != o.start) {
				return Integer.compare(this.start, o.start);
			}

			return Integer.compare(this.end, o.end);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		List<Lecture> lectures = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lectures.add(new Lecture(start, end));
		}

		Collections.sort(lectures);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(lectures.get(0).end);

		for (int i = 1; i < N; i++) {
			if (pq.peek() <= lectures.get(i).start) {
				pq.poll();
			}

			pq.offer(lectures.get(i).end);
		}

		System.out.println(pq.size());
	}
}
