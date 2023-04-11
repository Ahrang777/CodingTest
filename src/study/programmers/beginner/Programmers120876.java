package study.programmers.beginner;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/120876
 * 겹치는 선분의 길이
 */
public class Programmers120876 {
	/*public int solution(int[][] lines) {
		int answer = 0;
		int[] arr = new int[201];

		for (int[] line : lines) {
			int start = line[0] + 100;
			int end = line[1] + 100;

			while(start < end) {
				arr[start++]++;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 1) {
				answer++;
			}
		}

		return answer;
	}*/

	private int xPos = 0;
	private int yPos = 1;
	private int[][] checkCases = {{0, 1, 2, 3}, {0, 2, 1, 3}, {0, 3, 1, 2}};
	public int solution(int[][] dots) {
		int answer = 0;
		for (int[] checkCase : checkCases) {
			if (checkParallel(checkCase, dots)) {
				answer = 1;
				break;
			}
		}

		return answer;
	}

	private boolean checkParallel(int[] checkCase, int[][] dots) {
		int xPos1 = dots[checkCase[0]][xPos];
		int yPos1 = dots[checkCase[0]][yPos];
		int xPos2 = dots[checkCase[1]][xPos];
		int yPos2 = dots[checkCase[1]][yPos];
		int xPos3 = dots[checkCase[2]][xPos];
		int yPos3 = dots[checkCase[2]][yPos];
		int xPos4 = dots[checkCase[3]][xPos];
		int yPos4 = dots[checkCase[3]][yPos];
		// (y4 - y3)(x2 - x1) - (x4 - x3)(y2 - y1) 평행여부 조건
		int chk = ((yPos4 - yPos3) * (xPos2 - xPos1)) - ((xPos4 - xPos3) * (yPos2 - yPos1));
		if (chk == 0) {
			return true;
		}
		return false;
	}
}
