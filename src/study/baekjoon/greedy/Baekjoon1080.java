package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1080
 *
 * 행렬
 */
public class Baekjoon1080 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] A = new int[N][M];
		int[][] B = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				A[i][j] = str.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				B[i][j] = str.charAt(j) - '0';
			}
		}

		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (A[i][j] != B[i][j] && i + 2 < N && j + 2 < M) {
					for (int r = i; r < i + 3; r++) {
						for (int c = j; c < j + 3; c++) {
							A[r][c] = A[r][c] == 0 ? 1 : 0;
						}
					}
					cnt++;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (A[i][j] != B[i][j]) {
					cnt = -1;
					break;
				}
			}
		}

		System.out.println(cnt);
	}
}
