package study.programmers.challenges.binarysearch;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43236
 *
 * 징검다리
 */
public class Programmers43236 {

    public static int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);
        int left = 0;
        int right = distance;

        while (left <= right) {
            // 사이거리 최솟값
            int mid = (left + right) / 2;
            int prev = 0;   // 거리 측정 시작점
            int remove = 0; // 제거해야 하는 바위

            for (int i = 0; i < rocks.length; i++) {
                // 사이거리가 최솟값으로 정한 mid보다 작은경우 해당 바위 제거
                if (rocks[i] - prev < mid) {
                    remove++;
                    if (remove > n) break;
                } else { // 사이거리가 mid보다 크면 시작점 갱신
                    prev = rocks[i];
                }
            }

            // 최솟값 mid를 줄여야함
            if (remove > n) {
                right = mid - 1;
            } else {
                // n보다 적거나 같게 바위를 지우는 경우 현재 mid보다 더 큰 값이 존재할 수 있기에 확인해야함
                // mid는 현재 최솟값이기에 최솟값중 가장 큰 answer를 구하기 위해서는 크기비교가 필요
                left = mid + 1;
                answer = answer > mid ? answer : mid;
            }
        }
        
        return answer;
    }

    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;

        System.out.println(solution(distance, rocks, n));

    }
}
