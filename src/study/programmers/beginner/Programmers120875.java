package study.programmers.beginner;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/120875
 * 평행
 */
public class Programmers120875 {
	public int solution(int[][] dots) {
		int answer = 0;

		boolean[] check = new boolean[4];
		int[] start = dots[0];

		check[0] = true;

		for (int i = 1; i < dots.length; i++) {
			int[] end = dots[i];
			check[i] = true;
			int n = 0;
			int[][] index = new int[2][2];
			for (int j = 1; j < dots.length; j++) {
				if (!check[j]) {
					index[n++] = dots[j];
				}
			}

			double first = ((double) (end[1] - start[1])) / (end[0] - start[0]);
			double second =((double) (index[1][1] - index[0][1])) / (index[1][0] - index[0][0]);

			if (first == second) {
				answer = 1;
				break;
			}

			check[i] = false;
		}

		return answer;
	}
}
