package study.baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1068
 * 트리
 */
public class Baekjoon1068 {
	static Integer[] parents;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		parents = new Integer[N];

		st = new StringTokenizer(br.readLine());
		int root = 0;
		for (int i = 0; i < N; i++) {
			parents[i] = Integer.parseInt(st.nextToken());
			if (parents[i] == -1) {
				root = i;
			}
		}

		int target = Integer.parseInt(br.readLine());
		int cnt = 0;

		if (target == root) {
			System.out.println(cnt);
			return;
		}


		removeNode(target);
		System.out.println(countLeafNode());
	}

	private static int countLeafNode() {
		List<Integer> list = List.of(parents);
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			int n = list.get(i);
			if (n != -2 && !list.contains(i)) {
				cnt++;
			}
		}
		return cnt;
	}

	private static void removeNode(int target) {
		parents[target] = -2;

		for (int i = 0; i < N; i++) {
			if (parents[i] == target) {
				removeNode(i);
			}
		}
	}
}
