package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42860
 *
 * 조이스틱
 * 다시풀기
 * 참고: https://born2bedeveloper.tistory.com/26
 */
public class Programmers42860 {

    public static int solution(String name) {
        int answer = 0;

        // 좌우 이동횟수
        // 오른쪽으로 쭉 가는 경우로 먼저 초기화(최댓값)
        int move = name.length() - 1;


        // 기준은 처음위치 0
        for (int i = 0; i < name.length(); i++) {
            // 현재 위치 알파벳 계산, ▲, ▼ 횟수 추가
            answer += Math.min(name.charAt(i) - 'A', 26 - (name.charAt(i) - 'A'));
            if (i < name.length() - 1 && name.charAt(i + 1) == 'A') {
                // 마지막으로 연속된 A가 끝난 위치
                int endA = i + 1;
                while (endA < name.length() && name.charAt(endA) == 'A') {
                    endA++;
                }

                // 0->
                // 0<-
                //              <-
                move = Math.min(move, i * 2 + (name.length() - endA));// 오른쪽으로 갔다 다시 왼쪽으로 꺾기

                //0      <-
                //       ->
                //->
                move = Math.min(move, i + (name.length() - endA) * 2);// 왼쪽으로 갔다 다시 오른쪽으로 꺾기
            }
        }

        return answer + move;
    }
    /**
     * 1. 조이스틱 상,하 조작으로 알파벳 조작하기
     * - 현재 알파벳: 알파벳1, 찾는 알파벳: 알파벳2
     * - 1) 조이스틱 위로 조작해서 찾는 경우: 알파벳2 - 알파벳1
     * - 2) 조이스택 아래로 조작해서 찾는 경우: 26(전체 알파벳 수) - (알파벳2 - 알파벳1)
     * - 두 경우 중 더 작은것 적용
     *
     * 2. 조이스틱 좌,우 조작으로 커서 위치 조정
     * - 커서가 바뀌는 경우 총 3가지
     * - 1) 처음부터 쭉 한 방향으로 가는 경우: name.length() - 1
     * - 2) 오른쪽으로 갔다가 다시 왼쪽으로 가는 경우
     *      ex) 현재 위치: 0, BBAAAAAYYY 의 경우, BB까지 갔다가 다시 돌아가 YYY를 완성해준다.
     * - 3) 왼쪽으로 갔다가 다시 오른쪽으로 가는 경우
     *      ex) 현재 위치: 0, CCCAAAAAAY 의 경우, 처음부터 왼쪽으로 움직여 Y를 완성해주고 다시 오른쪽으로 돌아가 CCC를 완성해준다.
     *
     * <로직>
     * 1. name을 0부터 length() - 1 까지 name의 알파벳 검사를 한다.
     * 2. 각 알파벳별로 상,하 조작을 구해준다.
     * 3. 만약 내 다음 알파벳이 'A'라면, 'A'가 끝나는 지점을 구한다.
     * 4. index == 0인 상태를 기준으로 (커서가 처음 위치하는 부분) 좌우조작의 2, 3번 경우 중 최솟값을 따져본다.
     */




    /*public static int solution(String name) {
        int answer = 0;
        int[] diff={0,1,2,3,4,5,6,7,8,9,10,11,12,13,12,11,10,9,8,7,6,5,4,3,2,1};
        for(char c:name.toCharArray())
            answer+=diff[c-'A'];

        int length=name.length();
        int min=length-1;

        for(int i=0;i<length;i++){
            int next=i+1;
            while(next<length && name.charAt(next)=='A'){
                next++;
            }
            min=Math.min(min,i+length-next+Math.min(i,length-next));
        }

        return answer+min;
    }*/

    public static void main(String[] args) {
        String[] name = {
                "JEROEN", "JAN"
        };

        for (int i = 0; i < 2; i++) {
            System.out.println(solution(name[i]));
        }
    }
}
