package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.acmicpc.net/problem/1541
 *
 * 잃어버린 괄호
 */
public class Baekjoon1541 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/*String str = br.readLine();

		List<String> list = new ArrayList<>();
		String sub = str;
		while (sub.indexOf('-') != -1) {
			int index = sub.indexOf('-');
			list.add(sub.substring(0, index));
			sub = sub.substring(index + 1);
		}

		list.add(sub.substring(0));
		int result = 0;
		for (String s : list.get(0).split("[+]")) {
			result += Integer.parseInt(s);
		}

		for (int i = 1; i < list.size(); i++) {
			for (String s : list.get(i).split("[+]")) {
				result -= Integer.parseInt(s);
			}
		}

		System.out.println(result);*/

		String[] sub = br.readLine().split("-");
		int result = 0;

		for (int i = 0; i < sub.length; i++) {
			int sum = 0;

			for (String s : sub[i].split("\\+")) {
				sum += Integer.parseInt(s);
			}

			if (i == 0) {
				result = sum;
			} else {
				result -= sum;
			}
		}

		System.out.println(result);
	}
}
