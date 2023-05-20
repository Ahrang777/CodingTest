package study.baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon14502 {
	static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static final int BLANK = 0;
	static final int WALL = 1;
	static final int VIRUS = 2;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int N, M;
	static int[][] map;
	static int[][] copy;
	static List<Position> viruses = new ArrayList<>();
	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == VIRUS) {
					viruses.add(new Position(i, j));
				}
			}
		}

		dfs(0);

		System.out.println(result);
	}

	private static void dfs(int depth) {
		if (depth == 3) {
			// 지도 복사
			copy();

			// 바이러스 전파
			bfs();

			// 안전지대 계산
			result = Math.max(result, countSafe());
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == BLANK) {
					map[i][j] = WALL;
					dfs(depth + 1);
					map[i][j] = BLANK;
				}
			}
		}
	}

	private static void copy() {
		copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}

	private static void bfs() {
		Queue<Position> q = new LinkedList<>();
		for (Position virus : viruses) {
			q.offer(virus);
		}

		while (!q.isEmpty()) {
			Position now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (isRange(nx, ny) && copy[nx][ny] == BLANK) {
					copy[nx][ny] = VIRUS;
					q.offer(new Position(nx, ny));
				}
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static int countSafe() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == BLANK) {
					cnt++;
				}
			}
		}

		return cnt;
	}
}
