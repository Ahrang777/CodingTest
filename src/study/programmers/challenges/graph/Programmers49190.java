package study.programmers.challenges.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/49190
 *
 * 방의 개수
 * 참고: https://pangtrue.tistory.com/350
 */
public class Programmers49190 {
	private static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Node node = (Node) o;
			return x == node.x && y == node.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}

	public static int solution(int[] arrows) {
		int answer = 0;

		int[] dx = { 0,  1, 1, 1, 0, -1, -1, -1};
		int[] dy = {-1, -1, 0, 1, 1,  1,  0, -1};
		Node curNode = new Node(0, 0);

		// 방문 여부 관련 선언
		// key = 시작 node의 hashcode, value = 연결된 node들의 hashcode
		Map<Node, List<Node>> visited = new HashMap<>();

		for (int arrow : arrows) {
			for (int i = 0; i <= 1; i++) { // 교차점 처리를 위한 스케일업
				Node nextNode = new Node(curNode.x + dx[arrow], curNode.y + dy[arrow]);

				// 처음 방문하는 경우 = map에 키값이 없는 경우
				if (!visited.containsKey(nextNode)) {
					// 리스트에 연결점 추가
					visited.put(nextNode, makeEdgeList(curNode));

					// List<Node> edges = visited.getOrDefault(curNode, new ArrayList<>());
					// visited.put(curNode, edges);
					// edges.add(nextNode);

					// 반대편 점 처리 >> curNode ~ nextNode
					if (visited.get(curNode) == null) {
						visited.put(curNode, makeEdgeList(nextNode));
					} else {
						visited.get(curNode).add(nextNode);
					}

					// 해당 정점을 재방문했고, 간선을 처음 통과하는 경우
				} else if (!visited.get(nextNode).contains(curNode)) {
					visited.get(nextNode).add(curNode);
					visited.get(curNode).add(nextNode);
					answer++;
				}

				// 이동 완료
				curNode = nextNode;
			}
		}

		return answer;
	}

	private static List<Node> makeEdgeList(Node node) {
		List<Node> edge = new ArrayList<>();
		edge.add(node);
		return edge;
	}

	public static void main(String[] args) {
		int[] arrows;

		arrows = new int[]{6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};
		System.out.println(solution(arrows)); // 답: 3
	}
}
