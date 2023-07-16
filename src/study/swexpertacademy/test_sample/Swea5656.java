package study.swexpertacademy.test_sample;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo
 * 벽돌 깨기
 *
 * 방법은 맞았지만 상세구현에서 문제
 */
public class Swea5656 {
	static class Position {
		int x, y, cnt;

		public Position(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	static int N, W, H, min;
	static int[][] map, tmp;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			tmp = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					tmp[i][j] = map[i][j];
				}
			}

			int[] output = new int[N];	// N번 동안 몇번째 줄에 쏠지
			min = Integer.MAX_VALUE;

			perm(output, 0);	// 중복 순열

			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	static void down() {
		Stack<Integer> s = new Stack<>();
		for (int y = 0; y < W; y++) {
			for (int x = 0; x < H; x++) {
				if (tmp[x][y] != 0) {
					s.add(tmp[x][y]);
					tmp[x][y] = 0;
				}
			}

			for (int x = H - 1; x >= 0; x--) {
				if (s.isEmpty()) {
					break;
				}

				tmp[x][y] = s.pop();
			}
		}
	}

	static int count(int[][] tmp) {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (tmp[i][j] != 0) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	static void bfs(int x, int y) {
		Queue<Position> q = new LinkedList<>();
		q.offer(new Position(x, y, tmp[x][y]));
		tmp[x][y] = 0;

		while (!q.isEmpty()) {
			Position now = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = now.x;
				int ny = now.y;
				for (int i = 0; i < now.cnt - 1; i++) {
					nx += dx[d];
					ny += dy[d];

					if (!isRange(nx, ny) || tmp[nx][ny] == 0)	continue;

					q.offer(new Position(nx, ny, tmp[nx][ny]));
					tmp[nx][ny] = 0;
				}
			}
		}
	}

	static boolean isRange(int x, int y) {
		return x >= 0 && x < H && y >= 0 && y < W;
	}

	static void copy() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tmp[i][j] = map[i][j];
			}
		}
	}

	// 중복순열
	static void perm(int[] output, int depth) {
		if (depth == N) {
			copy();
			start(output);
			min = Math.min(min, count(tmp));
			return;
		}

		for (int i = 0; i < W; i++) {
			output[depth] = i;
			perm(output, depth + 1);
		}
	}

	static void start(int[] arr) {
		for (int y : arr) {
			for (int x = 0; x < H; x++) {
				if (tmp[x][y] != 0) {
					bfs(x, y);
					break;
				}
			}
			down();
		}
	}
}
