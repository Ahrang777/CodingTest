package study.swexpertacademy.d2;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYj2mga6ZewDFASl&contestProbId=AXuARWAqDkQDFARa&probBoxId=AYj2nEQ6ZfkDFASl&type=USER&problemBoxTitle=%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98+Track+%28%EB%82%9C%EC%9D%B4%EB%8F%84+%EC%A4%91%29&problemBoxCnt=5
 * 파리퇴치3
 */
public class Swea12712 {
	static int[][] map;
	static int N, M;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int cross = countCross(i, j);
					int diagonal = countDiagonal(i, j);

					max = Math.max(max, Math.max(cross, diagonal));
				}
			}

			sb.append("#").append(tc + " ").append(max + "\n");
		}

		System.out.println(sb);
	}

	private static int countDiagonal(int x, int y) {
		int score = 0;
		int[] dx = {-1, -1, 1, 1};
		int[] dy = {-1, 1, 1, -1};
		int nx = x;
		int ny = y;

		score += map[x][y];

		for (int i = 0; i < 4; i++) {
			nx = x;
			ny = y;
			for (int j = 0; j < M - 1; j++) {
				nx += dx[i];
				ny += dy[i];

				if (!isRange(nx, ny))
					break;

				score += map[nx][ny];
			}
		}

		return score;
	}

	private static int countCross(int x, int y) {
		int score = 0;
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		int nx = x;
		int ny = y;

		score += map[x][y];

		for (int i = 0; i < 4; i++) {
			nx = x;
			ny = y;
			for (int j = 0; j < M - 1; j++) {
				nx += dx[i];
				ny += dy[i];

				if (!isRange(nx, ny))
					break;

				score += map[nx][ny];
			}
		}

		return score;
	}

	private static boolean isRange(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < N;
	}
}
