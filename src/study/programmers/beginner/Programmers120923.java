package study.programmers.beginner;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/120923
 * 연속된 수의 합
 */
public class Programmers120923 {
	public int[] solution(int num, int total) {
		int[] answer = new int[num];
		int sum = num * (num + 1) / 2;
		int start = (total - sum) / num + 1;
		for (int i = 0; i < answer.length; i++) {
			answer[i] = start + i ;
		}
		return answer;
	}
}
