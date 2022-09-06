package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42627
 *
 * 디스크 컨트롤러
 * 다시 풀기
 */
public class Programmers42627 {

    static class Job {
        int start, process;

        public Job(int start, int process) {
            this.start = start;
            this.process = process;
        }
    }

    public static int solution(int[][] jobs) {

        int answer = 0;
        int end = 0;    // 작업이 수행된 직후 시간
        int index = 0;
        int cnt = 0;

        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        while (cnt < jobs.length) {

            // 하나의 작업이 완료되는 시점까지 들어오는 모든 요청을 큐에 넣는다 
            while (index < jobs.length && jobs[index][0] <= end) {
                pq.offer(jobs[index++]);
            }

            if (pq.isEmpty()) {
                end = jobs[index][0];
            } else {
                int[] job = pq.poll();
                end += job[1];
                answer += (end - job[0]);
                cnt++;
            }
        }

        return answer / jobs.length;

        /*int answer = 0;

        PriorityQueue<Job> startPQ = new PriorityQueue<>((o1, o2) ->
        {
            if (o1.start != o2.start)
                return o1.start - o2.start;
            return o1.process - o2.process;
        });


        // 소요시간 우선순위 큐
        PriorityQueue<Job> processPQ = null;

        for (int[] j : jobs) {
            Job job = new Job(j[0], j[1]);
            startPQ.offer(job);
        }

        int time = 0;
        Job now = null;
        while (!startPQ.isEmpty()) {
            if (time < startPQ.peek().start) {
                now = startPQ.poll();
                time = now.start + now.process;
            } else {
                Iterator<Job> it = startPQ.iterator();
                processPQ = new PriorityQueue<>((o1, o2) -> o1.process - o2.process);

                while (it.hasNext()) {
                    Job next = it.next();
                    if (next.start <= time) {
                        processPQ.offer(next);
                    }
                }

                now = processPQ.poll();
                time += now.process;
                startPQ.remove(now);
            }

            answer += (time - now.start);
        }

        return answer / jobs.length;*/

    }

    public static void main(String[] args) {
        int[][] jobs = {
                {0, 3}, {1, 9}, {2, 6}
        };

        System.out.println(solution(jobs));
    }
}
