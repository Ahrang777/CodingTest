package study.programmers.challenges.sort;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42747
 *
 * H-Index
 * 이해안됨...
 */
public class Programmers42747 {

    public static int solution(int[] citations) {

        int answer = 0;
        Arrays.sort(citations);

        for (int i = 0; i < citations.length; i++) {
            int smaller = Math.min(citations[i], citations.length - i);
            answer = Math.max(answer, smaller);
        }

        return answer;

        /*int answer = Integer.MIN_VALUE;
        Arrays.sort(citations);
        int len = citations.length;

        int index = 0;
        // 0, 1, 4, 5, 6 도 H-index는 3
        for (int h = 0; h <= citations[len-1]; h++){

            // index부터 확인
            // 정렬을 했기때문에 index가 한번 바뀌면 그 이전내용은 확인할 필요x
            for (int j = index; j < len; j++){

                if (citations[j] >= h){
                    index = j;
                    break;
                }
            }

            // h번 이상 인용된 논문의 수
            int size = len - index;

            // h번 이상 인용된 논문의 수가 h편 이상인 경우 최댓값 갱신
            if(size >= h){
                answer = Math.max(answer, h);
            }
        }

        return answer;*/
    }

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};

        System.out.println(solution(citations));
    }
}
