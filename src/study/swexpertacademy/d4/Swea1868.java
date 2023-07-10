package study.swexpertacademy.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYj2mga6ZewDFASl&contestProbId=AV5LwsHaD1MDFAXc&probBoxId=AYj2mga6Ze0DFASl&type=PROBLEM&problemBoxTitle=%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98+Track+%28%EB%82%9C%EC%9D%B4%EB%8F%84+%EC%83%81%29&problemBoxCnt=6
 * 파핑파핑 지뢰찾기
 */
public class Swea1868 {
	static int N;
	static char[][] map;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static boolean[][] visited;
	static int[][] arr;

	static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			map = new char[N][N];
			arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j);
				}
			}

			count();

			int result = 0;

			// 0인 위치 찾고 퍼뜨리기
			// 방문 안한 0이 아닌 위치들 카운트

			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && arr[i][j] == 0) {
						bfs(i, j);
						result++;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && arr[i][j] > 0) {
						result++;
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(result + "\n");
		}

		System.out.println(sb);
	}

	private static void bfs(int x, int y) {
		Queue<Position> q = new LinkedList<>();
		q.offer(new Position(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Position now = q.poll();

			for (int i = 0; i < 8; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (!isRange(nx, ny) || visited[nx][ny]) {
					continue;
				}

				// 다음이 0인 경우
				if (arr[nx][ny] == 0) {
					visited[nx][ny] = true;
					q.offer(new Position(nx, ny));
					continue;
				}

				if (arr[nx][ny] > 0) { // 다음이 0이 아닌경우
					visited[nx][ny] = true;
				}
			}
		}
	}

	private static void count() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '*') {
					arr[i][j] = -1;
					continue;
				}

				int cnt = 0;
				for (int d = 0; d < 8; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (!isRange(nx, ny))
						continue;

					if (map[nx][ny] == '*') {
						cnt++;
					}
				}

				arr[i][j] = cnt;
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
