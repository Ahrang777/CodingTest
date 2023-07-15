package study.swexpertacademy.d5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYj2mga6ZewDFASl&contestProbId=AV15PTkqAPYCFAYD&probBoxId=AYj2mga6Ze0DFASl&type=PROBLEM&problemBoxTitle=%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98+Track+%28%EB%82%9C%EC%9D%B4%EB%8F%84+%EC%83%81%29&problemBoxCnt=6
 * 공통조상
 */
public class Swea1248 {
	static int V, E, A, B;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			parents = new int[V + 1];
			parents[1] = 1;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				parents[c] = p;
			}

			List<Integer> aList = new ArrayList<>();
			List<Integer> bList = new ArrayList<>();

			addParents(A, aList);
			addParents(B, bList);

			int target = 1;
			for (int n : aList) {
				if (bList.contains(n)) {
					target = n;
					break;
				}
			}

			parents[target] = target;

			for (int i = 1; i <= V; i++) {
				findRoot(i);
			}

			int cnt = 0;
			for (int i = 1; i <= V; i++) {
				if (parents[i] == target) {
					cnt++;
				}
			}

			sb.append("#").append(tc + " ").append(target + " " + cnt + "\n");
		}

		System.out.println(sb);
	}

	static void addParents(int x, List<Integer> targetParents) {
		if (x == parents[x]) {
			return;
		}

		targetParents.add(parents[x]);
		addParents(parents[x], targetParents);
	}

	static int findRoot(int x) {
		if (x == parents[x]) {
			return x;
		}

		return parents[x] = findRoot(parents[x]);
	}

	static void union(int a, int b) {
		a = findRoot(a);
		b = findRoot(b);

		if (a < b)	parents[b] = a;
		else parents[a] = b;
	}
}
