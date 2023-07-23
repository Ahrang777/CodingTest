package study.programmers.lv1;

import java.util.ArrayList;

public class Programmers81301 {
	String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	public int solution(String s) {
		int answer = 0;

		for (int i = 0; i < arr.length; i++) {
			s = s.replaceAll(arr[i], Integer.toString(i));
		}

		answer = Integer.parseInt(s);

		return answer;
	}
}
