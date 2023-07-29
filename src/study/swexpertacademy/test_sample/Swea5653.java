package study.swexpertacademy.test_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRJ8EKe48DFAUo
 * [모의 SW 역량테스트] 줄기세포배양
 */
public class Swea5653 {
	static class Cell implements Comparable<Cell> {
		int x, y, life, birth;

		public Cell(int x, int y, int life, int birth) {
			this.x = x;
			this.y = y;
			this.life = life;
			this.birth = birth;
		}

		@Override
		public int compareTo(Cell o) {
			return Integer.compare(o.life, this.life);
		}
	}

	static int N, M, K;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	static Queue<Cell> q;
	static PriorityQueue<Cell> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			visited = new boolean[N + K + 1][M + K + 1];
			q = new ArrayDeque<>();
			pq = new PriorityQueue<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int life = Integer.parseInt(st.nextToken());

					if (life > 0) {
						int x = K / 2 + i;
						int y = K / 2 + j;

						visited[x][y] = true;
						q.offer(new Cell(x, y, life, 0));
					}
				}
			}
			for (int i = 1; i <= K; i++) {
				bfs(i);
			}
			System.out.println("#" + tc + " " + q.size());
		}
	}

	private static void bfs(int time) {

		int size = q.size();
		for (int i = 0; i < size; i++) {
			Cell c = q.poll();

			if (time <= c.birth + c.life) {	// 비활성
				q.offer(c);
			} else if (time == c.birth + c.life + 1) {	// 활성, 증식가능
				pq.offer(c);
			} else if (time < c.birth + c.life * 2) {	// 활성화
				q.offer(c);
			}
		}

		while (!pq.isEmpty()) {
			Cell c = pq.poll();

			// 살아있는 경우우
		if (time < c.birth + 2 * c.life)
				q.offer(c);

			for (int i = 0; i < 4; i++) {
				int nx = c.x + dx[i];
				int ny = c.y + dy[i];

				if (visited[nx][ny])
					continue;

				visited[nx][ny] = true;
				q.offer(new Cell(nx, ny, c.life, time));
			}
		}
	}
}