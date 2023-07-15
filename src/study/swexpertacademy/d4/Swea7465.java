package study.swexpertacademy.d4;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU&categoryId=AWngfZVa9XwDFAQU&categoryType=CODE&problemTitle=7465&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 * 창용 마을 무리의 개수
 */
public class Swea7465 {
	static int[] parents;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			parents = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}

			//            int result = 0;
			//            Set<Integer> set = new HashSet<>();
			//            for (int i = 1; i <= N; i++) {
			//                set.add(findRoot(i));
			//            }
			//            result = set.size();

			int result = 0;
			for (int i = 1; i <= N; i++) {
				int x = findRoot(i);
				if (!visited[x]) {
					visited[x] = true;
					result++;
				}
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}

	private static int findRoot(int x) {
		if (x == parents[x])
			return x;

		return parents[x] = findRoot(parents[x]);
	}

	private static void union(int a, int b) {
		a = findRoot(a);
		b = findRoot(b);
		if (a < b)  parents[b] = a;
		else parents[a] = b;
	}
}
