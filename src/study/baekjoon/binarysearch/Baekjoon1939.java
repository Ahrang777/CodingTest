package study.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1939
 *
 * 중량제한
 */
public class Baekjoon1939 {
	/**
	 * 다익스트라
	 */
	static class Node implements Comparable<Node> {
		int index, dist;

		public Node(int index, int dist) {
			this.index = index;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(o.dist, this.dist);
		}
	}

	static int N, M, A, B;
	static int[] d;
	static List<Node>[] graph;
	static final int INF = (int)1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		d = new int[N + 1];
		Arrays.fill(d, -1);	// 최소가 아닌 최대값으로 갱신해야 하므로

		graph = new List[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(to, dist));
			graph[to].add(new Node(from, dist));
		}

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		dijkstra();
		System.out.println(d[B]);
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(A, INF));
		d[A] = INF;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.index;
			int dist = node.dist;

			if (d[now] > dist)	continue;

			for (Node next : graph[now]) {
				// a -> b -> c 일 경우
				// 한번에 움직일수 있는 중량이므로 a -> b / b -> c 둘중 작은 값이어야 한다.
				// a -> b 돌고 b -> c 돌때 둘다 문제 없이 통과해야 하므로 min
				int cost = Math.min(dist, next.dist);

				if (cost > d[next.index]) {
					d[next.index] = cost;
					pq.offer(new Node(next.index, cost));
				}
			}
		}
	}


	/**
	 * bfs + 이진탐색
	 */
	/*static class Node {
		int index, dist;

		public Node(int index, int dist) {
			this.index = index;
			this.dist = dist;
		}
	}

	static int N, M, A, B;
	static List<Node>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		int start = 0, end = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to, dist));
			graph[to].add(new Node(from, dist));

			end = Math.max(end, dist);
		}

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		int result = 0;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (bfs(mid)) {
				result = Math.max(result, mid);
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		System.out.println(result);
	}

	private static boolean bfs(int limit) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		q.offer(A);
		visited[A] = true;

		while (!q.isEmpty()) {
			Integer now = q.poll();

			if (now == B)
				return true;

			for (Node node : graph[now]) {
				int next = node.index;
				int dist = node.dist;

				if (visited[next] || dist < limit)
					continue;

				visited[next] = true;
				q.offer(next);
			}
		}

		return false;
	}*/


	/*static class Node {
		int index, dist;

		public Node(int index, int dist) {
			this.index = index;
			this.dist = dist;
		}
	}

	static int N, M, A, B;
	static List<Node>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		int start = 0, end = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			graph[n1].add(new Node(n2, dist));
			graph[n2].add(new Node(n1, dist));
			end = Math.max(end, dist);
		}

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		while (start <= end) {
			int mid = (start + end) / 2;

			// 최대 mid 크기로 n1 -> n2 갈 수 있다면 최대값을 구하기 위해 start를 늘려본다
			if (bfs(mid)) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		System.out.println(end);
	}

	private static boolean bfs(int limit) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		q.offer(A);
		visited[A] = true;

		while (!q.isEmpty()) {
			Integer now = q.poll();

			if (now == B)
				return true;

			for (Node next : graph[now]) {
				int index = next.index;
				int dist = next.dist;

				if (visited[index] || dist < limit)	continue;

				visited[index] = true;
				q.offer(index);
			}
		}

		return false;
	}*/
}
