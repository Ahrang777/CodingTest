package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42577
 *
 * 전화번호 목록
 */
public class Programmers42577 {

    /*
    // 풀이 1
    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        // 사전적 정렬
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i ++ ){
            if (phone_book[i+1].startsWith(phone_book[i])){
                answer = false;
                break;
            }
        }

        return answer;
    }
    */

    // 풀이 2
    public static boolean solution(String[] phone_book) {
        Map<String, Integer> map = new HashMap<>();

        // 모든 전화번호를 HashMap에 넣는다.
        for (int i = 0; i < phone_book.length; i++) {
            map.put(phone_book[i], i);
        }

        // 모든 전화번호의 subString이 HashMap 에 존재하는지 확인한다.
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 0; j < phone_book[i].length(); j++) {
                if (map.containsKey(phone_book[i].substring(0, j))) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[][] phone_num = {
                {"119", "97674223", "1195524421"},
                {"123", "456", "789"},
                {"12", "123", "1235", "567", "88"}
        };

        for (int i = 0; i < phone_num.length; i++) {
            System.out.println(solution(phone_num[i]));
        }
    }
}
