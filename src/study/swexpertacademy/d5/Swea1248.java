package study.swexpertacademy.d5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYj2mga6ZewDFASl&contestProbId=AV15PTkqAPYCFAYD&probBoxId=AYj2mga6Ze0DFASl&type=PROBLEM&problemBoxTitle=%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98+Track+%28%EB%82%9C%EC%9D%B4%EB%8F%84+%EC%83%81%29&problemBoxCnt=6
 * 공통조상
 */
public class Swea1248 {
	static class Node {
		int parent, left, right;

	}
	static int V, E, N1, N2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			N1 = Integer.parseInt(st.nextToken());
			N2 = Integer.parseInt(st.nextToken());

			Node[] nodes = new Node[V + 1];
			for (int i = 0; i < V + 1; i++) {
				nodes[i] = new Node();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int p = Integer.parseInt(st.nextToken());	// 부모
				int c = Integer.parseInt(st.nextToken());	// 자식

				if (nodes[p].left == 0) {
					nodes[p].left = c;
				} else {
					nodes[p].right = c;
				}

				nodes[c].parent = p;
			}

			boolean[] visited = new boolean[V + 1];

			int root = 1;	// 공통 루트노드 >> 적어도 최상단 노드인 1은 공통 부모이다.
			int n1 = N1;
			int n2 = N2;

			while (true) {	// 못해도 n1, n2가 최상단 루트인 1에서는 만나기에 반복문 탈출 가능
				if (n1 != 1) {
					int parent = nodes[n1].parent;
					if (visited[parent]) {	// 다른 정점의 부모 노드들 중 가장 먼저 만났기에 가장 가까운 공통 부모
						root = parent;
						break;
					}
					visited[parent] = true;
					n1 = parent;
				}
				if (n2 != 1) {
					int parent = nodes[n2].parent;
					if (visited[parent]) {
						root = parent;
						break;
					}
					visited[parent] = true;
					n2 = parent;
				}
			}
			
			int size = dfs(nodes, root);

			sb.append("#").append(tc)
				.append(" ").append(root)
				.append(" ").append(size)
				.append("\n");
		}
		System.out.println(sb);
	}
	
	private static int dfs(Node[] nodes, int index) {
		int result = 1;	// 말단 노드의 경우 자신을 카운트하기 위해 1
		if (nodes[index].left != 0) {	// 왼쪽 자식이 있는 경우
			// 왼쪽 파고들기
			// 왼쪽 부분 모아서 반영
			result += dfs(nodes, nodes[index].left);
		}
		if (nodes[index].right != 0) {	// 오른쪽 자식이 있는 경우
			result += dfs(nodes, nodes[index].right);
		}
		return result;
	}


	/*static int V, E, A, B;
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

			// A정점의 조상 가까운 순으로 저장
			List<Integer> aList = new ArrayList<>();
			
			// B정점의 조상 가까운 순으로 저장
			List<Integer> bList = new ArrayList<>();

			addParents(A, aList);
			addParents(B, bList);

			// 적어도 루트는 동일하기에 최상단 루트인 1을 기본으로 설정
			// 가장 가까운 루트 정점
			int target = 1;	
			for (int n : aList) {
				if (bList.contains(n)) {
					target = n;
					break;
				}
			}

			// 가장 가까운 공통정점에서 최상단 루트와의 연결 끊기
			parents[target] = target;

			// 각 루트 노드 갱신
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
	}*/
}
