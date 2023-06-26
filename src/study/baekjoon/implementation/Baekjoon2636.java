package study.baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2636
 *
 * 치즈즈 */
public class Baekjoon2636 {
	static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] map;
	static int X, Y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		map = new int[X][Y];
		int cnt = 0;
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < X; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < Y; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if (n == 1) {
					cnt++;
				}
			}
		}

		list.add(cnt);

		int time = 0;

		while (true) {
			time++;

			bfs();
			cnt = getCnt();

			list.add(cnt);

			if (cnt == 0) {
				break;
			}
		}

		int size = list.size();
		System.out.println(time);
		System.out.println(list.get(size - 2));
	}

	static void bfs() {
		boolean[][] visited = new boolean[X][Y];
		Queue<Position> q = new LinkedList<>();
		visited[0][0] = true;
		q.offer(new Position(0, 0));

		while (!q.isEmpty()) {
			Position now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || nx >= X || ny < 0 || ny >= Y) {
					continue;
				}

				if (!visited[nx][ny]) {
					if (map[nx][ny] == 1) {
						map[nx][ny] = -1;
						visited[nx][ny] = true;
						continue;
					}
					if (map[nx][ny] == 0) {
						visited[nx][ny] = true;
						q.offer(new Position(nx, ny));
					}
				}
			}
		}

		removeCheese();
	}

	private static void removeCheese() {
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (map[i][j] == -1) {
					map[i][j] = 0;
				}
			}
		}
	}

	static int getCnt() {
		int cnt = 0;

		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (map[i][j] == 1) {
					cnt++;
				}
			}
		}

		return cnt;
	}
}
