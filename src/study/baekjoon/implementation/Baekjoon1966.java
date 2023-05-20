package study.baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1966
 * 프린터 큐
 */
public class Baekjoon1966 {
	static class Document implements Comparable<Document> {
		int index, priority;

		public Document(int index, int priority) {
			this.index = index;
			this.priority = priority;
		}

		@Override
		public int compareTo(Document o) {
			return Integer.compare(o.priority, this.priority);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Document target = null;

			st = new StringTokenizer(br.readLine());
			Queue<Document> q = new LinkedList<>();
			PriorityQueue<Document> pq = new PriorityQueue<>();
			for (int i = 0; i < N; i++) {
				Document document = new Document(i, Integer.parseInt(st.nextToken()));
				q.offer(document);
				pq.offer(document);
				if (i == M) {
					target = document;
				}
			}

			int cnt = 0;
			while (true) {
				Document now = q.poll();
				Document p = pq.poll();

				if (now.priority == p.priority && now.index == M) {
					cnt++;
					break;
				}

				if (now.priority == p.priority) {
					cnt++;
					continue;
				}

				q.offer(now);
				pq.offer(p);
			}

			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
