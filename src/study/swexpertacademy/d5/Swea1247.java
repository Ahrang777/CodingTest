package study.swexpertacademy.d5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYj2mga6ZewDFASl&contestProbId=AV15OZ4qAPICFAYD&probBoxId=AYj2mga6Ze0DFASl&type=PROBLEM&problemBoxTitle=%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98+Track+%28%EB%82%9C%EC%9D%B4%EB%8F%84+%EC%83%81%29&problemBoxCnt=6
 * 최적 경로
 */
public class Swea1247 {
	static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static int[][] arr;
	static int sx, sy, ex, ey, min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());

			arr = new int[N][2];
			for (int i = 0; i < N; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}

			boolean[] visited = new boolean[N];
			int[] output = new int[N];
			dfs(output, visited, 0);
			sb.append("#").append(tc + " ").append(min + "\n");
		}

		System.out.println(sb);
	}

	private static void dfs(int[] output, boolean[] visited, int depth) {
		if (depth == N) {
			int result = 0;
			result += (Math.abs(sx - arr[output[0]][0]) + Math.abs(sy - arr[output[0]][1]));
			result += (Math.abs(ex - arr[output[N - 1]][0]) + Math.abs(ey - arr[output[N - 1]][1]));

			for (int i = 0; i < N - 1; i++) {
				result += (Math.abs(arr[output[i]][0] - arr[output[i + 1]][0]) + Math.abs(
					arr[output[i]][1] - arr[output[i + 1]][1]));
			}

			min = Math.min(min, result);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output[depth] = i;
				dfs(output, visited, depth + 1);
				visited[i] = false;
			}
		}
	}
}
