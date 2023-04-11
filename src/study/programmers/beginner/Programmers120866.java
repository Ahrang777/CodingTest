package study.programmers.beginner;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/120866
 * 안전지대
 */
public class Programmers120866 {
	private static final int BOMB = 1;
	private static final int BLANK = 0;
	private static final int DANGER = 2;

	private int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	private int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

	public boolean check(int nx, int ny, int[][] board) {
		// return (0 <= nx && nx < board.length && 0 <= ny && ny < board.length) && (board[nx][ny] == BLANK);
		return (0 > nx || nx >= board.length || 0 > ny || ny >= board.length) || (board[nx][ny] != BLANK);
	}

	public int solution(int[][] board) {
		int answer = 0;
		int len = board.length;

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (board[i][j] == BOMB) {
					danger(board, i, j);
				}
			}
		}

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				System.out.print(board[i][j] + " ");
				if (board[i][j] == BLANK) {
					answer++;
				}
			}
			System.out.println();
		}

		return answer;
	}

	private void danger(int[][] board, int x, int y) {
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (check(nx, ny, board)) continue;
			board[nx][ny] = DANGER;
		}
	}
}
