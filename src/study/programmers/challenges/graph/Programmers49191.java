package study.programmers.challenges.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/49191?language=java
 *
 * 순위
 */
public class Programmers49191 {
	public static int solution(int n, int[][] results) {
		int answer = 0;

		int[][] arr = new int[n + 1][n + 1];

		for (int[] result : results) {
			// a승, b패 
			int a = result[0];
			int b = result[1];

			arr[a][b] = 1;	// a입장에서는 승
			arr[b][a] = -1;	// b입장에서는 패
		}

		// a > b, b > c == a > c
		for (int i = 1; i <= n; i++) {
			for (int a = 1; a <= n; a++) {
				for (int b = 1; b <= n; b++) {
					if (arr[a][i] == 1 && arr[i][b] == 1) {
						arr[a][b] = 1;
						arr[b][a] = -1;
					}
				}
			}
		}

		// 각 사람마다 n - 1번의 대결을 해야 순위가 확정된다. 
		for (int i = 1; i <= n; i++) {
			int cnt = 0;
			for (int j = 1; j <= n; j++) {
				if (arr[i][j] != 0) {
					cnt++;
				}
			}

			if (cnt == n - 1)   answer++;
		}

		return answer;
	}

	public static void main(String[] args) {
		int n = 5;
		int[][] results = {
			{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}
		};

		System.out.println(solution(n, results));;
	}
}
