package study.swexpertacademy.d4;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYj2mga6ZewDFASl&contestProbId=AV15QRX6APsCFAYD&probBoxId=AYj2mga6Ze0DFASl&type=PROBLEM&problemBoxTitle=%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98+Track+%28%EB%82%9C%EC%9D%B4%EB%8F%84+%EC%83%81%29&problemBoxCnt=6
 * 보급로
 */
public class Swea1249 {
	static class Position implements Comparable<Position> {
		int x, y, time;

		public Position(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.time, o.time);
		}
	}
	static int N;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}

			int result = bfs(0, 0);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}

	private static int bfs(int x, int y) {
		PriorityQueue<Position> pq = new PriorityQueue();
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		pq.offer(new Position(x, y, 0));
		int min = Integer.MAX_VALUE;

		while (!pq.isEmpty()) {
			Position now = pq.poll();

			if (now.x == N - 1 && now.y == N - 1) {
				min = Math.min(min, now.time);
				continue;
			}

			if (now.time > min) {
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (!isRange(nx, ny) || visited[nx][ny]) {
					continue;
				}

				visited[nx][ny] = true;
				pq.offer(new Position(nx, ny, now.time + map[nx][ny]));
			}
		}

		return min;
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	// static class Position {
	// 	int x, y, time;
	//
	// 	public Position(int x, int y, int time) {
	// 		this.x = x;
	// 		this.y = y;
	// 		this.time = time;
	// 	}
	// }
	//
	// static int[] dx = {-1, 0, 1, 0};
	// static int[] dy = {0, -1, 0, 1};
	//
	// static int N;
	// static int[][] map;
	//
	// public static void main(String[] args) throws Exception {
	// 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 	StringBuilder sb = new StringBuilder();
	//
	// 	int T = Integer.parseInt(br.readLine());
	//
	// 	for (int tc = 1; tc <= T; tc++) {
	// 		N = Integer.parseInt(br.readLine());
	// 		map = new int[N][N];
	//
	// 		for (int i = 0; i < N; i++) {
	// 			String str = br.readLine();
	// 			for (int j = 0; j < N; j++) {
	// 				map[i][j] = str.charAt(j) - '0';
	// 			}
	// 		}
	//
	// 		int result = bfs(0, 0);
	//
	// 		sb.append("#").append(tc + " ").append(result + "\n");
	// 	}
	//
	// 	System.out.println(sb);
	// }
	//
	// private static int bfs(int x, int y) {
	// 	int[][] visited = new int[N][N];
	// 	for (int i = 0; i < N; i++) {
	// 		Arrays.fill(visited[i], Integer.MAX_VALUE);
	// 	}
	// 	Queue<Position> q = new LinkedList<>();
	// 	visited[x][y] = 0;
	// 	q.offer(new Position(x, y, 0));
	//
	// 	int min = Integer.MAX_VALUE;
	//
	// 	while (!q.isEmpty()) {
	// 		Position now = q.poll();
	// 		if (now.x == N - 1 && now.y == N - 1) {
	// 			min = Math.min(min, now.time);
	// 			continue;
	// 		}
	//
	// 		if (now.time > min) {
	// 			continue;
	// 		}
	//
	// 		for (int i = 0; i < 4; i++) {
	// 			int nx = now.x + dx[i];
	// 			int ny = now.y + dy[i];
	// 			if (!isRange(nx, ny)) {
	// 				continue;
	// 			}
	//
	// 			int time = now.time + map[nx][ny];
	// 			if (visited[nx][ny] > time) {
	// 				visited[nx][ny] = time;
	// 				q.offer(new Position(nx, ny, time));
	// 			}
	// 		}
	// 	}
	//
	// 	return min;
	// }
	//
	// private static boolean isRange(int x, int y) {
	// 	return x >= 0 && x < N && y >= 0 && y < N;
	// }
	//
	// /**
	//  * DP + BFS
	//  * BFS 내에서 visited라는 2차원 int 배열을 만들어서 방문체크와 동시에 DP로 활용
	//  * visited[x][y] 는 x, y까지의 최소 복구시간
	//  * 인접한 네 곳을 방문할 때는 기존 지나갔던 비용보다 현재 비용이 더 적을 경우에만 그곳을 방문할 수 있게 구현
	//  * 큐에서 꺼낸 위치가 도착지인 경우 출발지 부터 도착지까지 걸리는 비용들을 최솟값으로 계속 갱신
	//  */
}
