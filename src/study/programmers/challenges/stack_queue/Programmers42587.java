package study.programmers.challenges.stack_queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42587
 *
 * 프린터
 * 다시풀기
 */
public class Programmers42587 {

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        int l = location;

        Queue<Integer> que = new LinkedList<Integer>();
        for(int i : priorities){
            que.add(i);
        }

        Arrays.sort(priorities);
        int size = priorities.length-1;

        // 큐의 값과 location이 같이 움직이는 것
        while(!que.isEmpty()){
            Integer i = que.poll();
            if(i == priorities[size - answer]){
                answer++;
                l--;
                if(l <0)
                    break;
            }else{
                que.add(i);
                l--;
                if(l<0)
                    l=que.size()-1;
            }
        }

        return answer;
    }

    /*public static int solution(int[] priorities, int location) {
        int answer = 1;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int p : priorities) {
            pq.offer(p);
        }

        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (pq.peek() == priorities[i]) {
                    if (i == location) {
                        return answer;
                    }

                    answer++;

                    // 타겟에서 제외되게 pq에서 빼고, priorities 도 1~9까지니까 범위 벗어나서 제외되게
                    pq.poll();
                    priorities[i] = 0;
                }
            }
        }

        return answer;
    }*/

    public static void main(String[] args) {
        int[][] priorities = {
                {2, 1, 3, 2},
                {1, 1, 9, 1, 1, 1}
        };
        int[] location = {2, 0};

        for (int i = 0; i < 2; i++) {
            System.out.println(solution(priorities[i], location[i]));
        }
    }
}
