package study.baekjoon.implementation;

import java.util.*;
import java.io.*;

/**
 * 백준 2573번
 * 빙산
 */
public class Baekjoon2573 {
	static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Ice {
		int x, y, cnt;

		public Ice(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;

		while(true) {
			int cnt = count();

			if (cnt >= 2) {
				break;
			}

			if (cnt == 0) {
				time = 0;
				break;
			}

			time++;
			melt();
		}

		System.out.println(time);
	}

	private static void melt() {
		List<Ice> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					list.add(new Ice(i, j, waterCount(i, j)));
				}
			}
		}

		for (Ice ice : list) {
			int iceCnt = map[ice.x][ice.y];
			int waterCnt = ice.cnt;

			map[ice.x][ice.y] = iceCnt >= waterCnt ? iceCnt - waterCnt : 0;
		}
	}

	private static int waterCount(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			if (map[nx][ny] == 0)	cnt++;
		}

		return cnt;
	}

	//	private static void melt() {
	//		boolean[][] isMelted = new boolean[N][M];
	//		for (int i = 0; i < N; i++) {
	//			for (int j = 0; j < M; j++) {
	//				if (map[i][j] > 0 && !isMelted[i][j] && check(i, j)) {
	//					isMelted[i][j] = true;
	//					map[i][j]--;
	//				}
	//			}
	//		}
	//	}
	//	private static boolean check(int x, int y) {
	//		for (int i = 0; i < 4; i++) {
	//			int nx = x + dx[i];
	//			int ny = y + dy[i];
	//			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
	//			if (map[nx][ny] == 0) {
	//				return true;
	//			}
	//		}
	//
	//		return false;
	//	}
	private static void bfs(boolean[][] visited, int x, int y) {
		Queue<Position> q = new LinkedList<>();
		visited[x][y] = true;
		q.offer(new Position(x, y));

		while (!q.isEmpty()) {
			Position now = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny])	continue;

				if (map[nx][ny] > 0) {
					visited[nx][ny] = true;
					q.offer(new Position(nx, ny));
				}
			}
		}
	}


	private static int count() {
		int cnt = 0;
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0 && !visited[i][j]) {
					cnt++;
					bfs(visited, i, j);
				}
			}
		}

		return cnt;
	}
}
