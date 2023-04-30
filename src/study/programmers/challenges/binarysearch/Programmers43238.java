package study.programmers.challenges.binarysearch;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43238
 *
 * 입국심사
 */
public class Programmers43238 {

    public static long solution(int n, int[] times) {
        long answer = 0;

        long start = 1;
        long end = (long) 1000000000 * n;

        while (start <= end) {
            long mid = (start + end) / 2;
            long total = 0; // 총 심사한 인원

            for (int time : times) {
                total += mid / time;
            }

            if (total < n) { // 전체 인원보다 심사한 인원이 적은 경우 >> 시간이 더 필요
                start = mid + 1;
            } else { // 전체 인원보다 심사한 인원이 많은 경우 >> 시간을 더 줄여서 최솟값 찾기
                end = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};

        System.out.println(solution(n, times));
    }
}
