package study.programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers17680 {
	public int solution(int cacheSize, String[] cities) {
		int answer = 0;

		Queue<String> q = new LinkedList<>();

		for (String city : cities) {
			city = city.toUpperCase();
			if (q.contains(city)) {
				q.remove(city);
				q.offer(city);
				answer++;
				continue;
			}

			// cacheSize 가 0인 경우 고려해서 순서 생각
			q.offer(city);

			if (q.size() > cacheSize) {
				q.poll();
			}

			answer += 5;
		}

		return answer;
	}
}
