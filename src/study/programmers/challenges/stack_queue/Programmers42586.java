package study.programmers.challenges.stack_queue;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42586
 *
 * 기능개발
 */
public class Programmers42586 {

    public static int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < speeds.length; i++) {
            double remain = (100 - progresses[i]) / (double) speeds[i];
            // 남은 작업일
            int date = (int) Math.ceil(remain);

            // 배포 처리
            if (!q.isEmpty() && q.peek() < date) {
                answerList.add(q.size());
                q.clear();
            }

            q.offer(date);
        }

        answerList.add(q.size());

        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    /*public static int[] solution(int[] progresses, int[] speeds) {
        int[] end = new int[100];
        int day = 0;
        for (int i = 0; i < progresses.length; i++) {
            while (progresses[i] + (day * speeds[i]) < 100) {
                day++;
            }
            end[day]++;
        }
        return Arrays.stream(end).filter(i -> i != 0).toArray();
    }*/

    /*public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        List<Integer> res = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            q.offer(new int[]{progresses[i], speeds[i]});
        }

        while (!q.isEmpty()) {
            int cnt = 0;
            int[] now = q.poll();
            now[0] += now[1];
            boolean flag = true;
            int size = q.size();

            if (now[0] >= 100) {
                cnt++;
                flag = false;
            } else {
                q.offer(now);
            }

            for (int i = 0; i < size; i++) {
                int[] next = q.poll();
                next[0] += next[1];

                // 처음부터 100 못넘은 경우
                if (flag) {
                    q.offer(next);
                } else {
                    // 연속으로 100넘는 경우
                    if (next[0] >= 100) {
                        cnt++;
                    } else {
                        q.offer(next);
                        flag = true;
                    }
                }
            }

            if (cnt != 0) res.add(cnt);
        }
        answer = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            answer[i] = res.get(i);
        }

        return answer;
    }*/

    public static void main(String[] args) {
        int[][] progresses = {
                {93, 30, 55},
                {95, 90, 99, 99, 80, 99}
        };

        int[][] speeds = {
                {1, 30, 5},
                {1, 1, 1, 1, 1, 1}
        };

        for (int i = 0; i < 2; i++) {
            for (int s : solution(progresses[i], speeds[i])) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
