package study.swexpertacademy.d4;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYj2mga6ZewDFASl&contestProbId=AWKaG6_6AGQDFARV&probBoxId=AYj2mga6Ze0DFASl&type=USER&problemBoxTitle=%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98+Track+%28%EB%82%9C%EC%9D%B4%EB%8F%84+%EC%83%81%29&problemBoxCnt=6
 * 수영대회 결승전(완전탐색 + 구현)
 */
public class Swea4193 {
	static class Position {
		int x, y, time;

		public Position(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] map;
	static int N;
	static int sx, sy, ex, ey;
	static int result;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());

			sb.append("#").append(tc + " ").append(bfs() ? result : -1).append("\n");
		}

		System.out.println(sb);
	}

	private static boolean bfs() {
		boolean[][] visited = new boolean[N][N];
		Queue<Position> q = new LinkedList<>();
		visited[sx][sy] = true;
		q.offer(new Position(sx, sy, 0));

		while (!q.isEmpty()) {
			Position now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (!isRange(nx, ny))	continue;

				if (nx == ex && ny == ey) {
					result = now.time + 1;
					return true;
				}

				if (visited[nx][ny] || map[nx][ny] == 1)
					continue;

				if (map[nx][ny] == 2) {
					if (now.time % 3 == 2) {
						visited[nx][ny] = true;
						q.offer(new Position(nx, ny, now.time + 1));
					} else {
						q.offer(new Position(now.x, now.y, now.time + 1));
					}
				} else {
					visited[nx][ny] = true;
					q.offer(new Position(nx, ny, now.time + 1));
				}
			}
		}

		return false;
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
