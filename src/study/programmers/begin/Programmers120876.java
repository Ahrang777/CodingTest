package study.programmers.begin;

/**
 * 겹치는 선분의 길이
 * https://school.programmers.co.kr/learn/courses/30/lessons/120876
 */
public class Programmers120876 {
	public int solution(int[][] lines) {
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
	}
}
