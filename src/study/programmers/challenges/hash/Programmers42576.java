package study.programmers.challenges.hash;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42576
 * 
 * 완주하지 못한 선수
 */
public class Programmers42576 {
    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        Arrays.stream(participant).forEach(name -> map.put(name, map.getOrDefault(name, 0) + 1));
        Arrays.stream(completion).forEach(name -> map.put(name, map.get(name) - 1));

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                answer = entry.getKey();
                break;
            }
        }

        return answer;

        /*String answer = "";
        Map<String, Integer> map = new HashMap<>();
        Arrays.stream(participant).forEach(name -> map.put(name, map.getOrDefault(name, 0) + 1));
        Arrays.stream(completion).forEach(name -> map.put(name, map.get(name) - 1));

        for (String name : map.keySet()) {
            if (map.get(name) != 0) {
                answer = name;
                break;
            }
        }

        return answer;*/

        /*String answer = "";

        HashMap<String, Integer> map = new HashMap<>();

        // getOrDefault(key, 기본값) : key 로 map 에서 조회, 만약 조회결과가 null이면 0 반환
        for (String player : participant) map.put(player, map.getOrDefault(player, 0) + 1);
        for (String player : completion) map.put(player, map.get(player) - 1);

        for (String key : map.keySet()) {
            if (map.get(key) != 0) {
                answer = key;
                break;
            }
        }

        return answer;*/
    }

    public static void main(String[] args) {
        String[][] participant = {
                {"leo", "kiki", "eden"},
                {"marina", "josipa", "nikola", "vinko", "filipa"},
                {"mislav", "stanko", "mislav", "ana"}
        };

        String[][] completion = {
                {"eden", "kiki"},
                {"josipa", "filipa", "marina", "nikola"},
                {"stanko", "ana", "mislav"}
        };

        for (int i = 0; i < 3; i++) {
            System.out.println(solution(participant[i], completion[i]));
        }
    }
}
