package study.programmers.challenges.hash;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42578
 *
 * 위장
 */
public class Programmers42578 {

    public static int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> map = new HashMap<>();

        for (String[] cloth : clothes){
            String type = cloth[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        // 각 옷에서 1개 선택하는 경우 반영(아무것도 안 입는 경우도 있기에 +1 한 후 곱한다.)
        // 만약, 안경, 썬글라스 있으면 안경, 썬글라스, 안입음 으로 경우의 수를 1 추가하는것
        /*for (String key : map.keySet()){
            answer *= (map.get(key) + 1);
        }*/

        /*for (int value : map.values()) {
            answer *= value + 1;
        }*/

        // 각 종류의 옷 가지수 + 1 의 경우의 수로 정렬
        // 여기서 + 1은 해당 종류의 옷 안 입는 경우
        Iterator<Integer> it = map.values().iterator();
        while (it.hasNext()) {
            answer *= it.next() + 1;
        }

        // 마지막에 각 그룹마다 안입는 경우가 선택되어 전체적으로 아무것도 안 입는 경우가 생기기에 이를 제거한다.
        answer -= 1;

        return answer;
    }

    public static void main(String[] args) {
        String[][][] clothes = {
                {
                        {"yellow_hat", "headgear"},
                        {"blue_sunglasses", "eyewear"},
                        {"green_turban", "headgear"}
                },
                {
                        {"crow_mask", "face"},
                        {"blue_sunglasses", "face"},
                        {"smoky_makeup", "face"}
                }
        };

        for (int i = 0; i < clothes.length; i++) {
            System.out.println(solution(clothes[i]));
        }
    }
}
