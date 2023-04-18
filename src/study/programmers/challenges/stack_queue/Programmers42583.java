package study.programmers.challenges.stack_queue;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42583
 *
 * 다리를 지나는 트럭
 */
public class Programmers42583 {

    static  class Truck {
        int weight, move;

        public Truck(int weight) {
            this.weight = weight;
            this.move = 1;
        }

        public void moving() {
            move++;
        }
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> waitQ = new LinkedList<>();    // 대기중인 트럭
        Queue<Truck> moveQ = new LinkedList<>();    // 다리 위에 있는 트럭

        for (int t : truck_weights) {
            waitQ.offer(new Truck(t));
        }

        int answer = 0;
        int curWeight = 0;  // 다리 위 트럭의 무게 총합

        while (!waitQ.isEmpty() || !moveQ.isEmpty()) {
            answer++;

            // 처음에 다리위에 아무것도 없는경우
            // waitQ, moveQ 둘다 아무것도없는 경우는 while에서 걸러지므로 waitQ 에는 값이 있는 상태
            if (moveQ.isEmpty()) {
                Truck t = waitQ.poll();
                curWeight += t.weight;
                moveQ.offer(t);
                continue;
            }

            // 다리 위 트럭 한칸씩 이동
            for (Truck t : moveQ) {
                t.moving();
            }

            // 제일 앞에있는 트럭이 다리 지난경우
            if (moveQ.peek().move > bridge_length) {
                Truck t = moveQ.poll();
                curWeight -= t.weight;
            }

            // 아직 대기중인 트럭이 남아있고 && 다음 트럭무게를 반영했을때 한계 무계를 넘지 않는 경우
            if (!waitQ.isEmpty() && curWeight + waitQ.peek().weight <= weight) {
                Truck t = waitQ.poll();
                curWeight += t.weight;
                moveQ.offer(t);
            }
        }

        return answer;
    }

    /*public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        // [0]: 트럭 무게, [1]: 다리 위 위치
        Queue<int[]> q = new LinkedList<>();

        int w = 0;  // 다리 위에있는 트럭의 무게합
        int index = 0;  // truck_weights 배열에서 타겟 인덱스
        while (true) {
            answer++;

            // 트럭 모두 다리 올라간적 없고 && 다리에 다음 트럭 올라갈 공간 있고 && 다음 트럭올라가도 무게 초과하지 않는 경우
            if (index < truck_weights.length && q.size() < bridge_length && w + truck_weights[index] <= weight) {
                q.offer(new int[]{truck_weights[index], 0});
                w += truck_weights[index];
                index++;
            }

            if (q.isEmpty()) {
                break;
            }

            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] now = q.poll();
                // 다리 위 위치 증가
                now[1]++;

                // 이동한 위치가 다리 지남
                if (now[1] == bridge_length) {
                    w -= now[0];
                } else {    // 이동한 위치가 다리위
                    q.offer(now);
                }
            }
        }

        return answer;
    }*/

    public static void main(String[] args) {
        int[] bridge_length = {2, 100, 100};
        int[] weight = {10, 100, 100};
        int[][] truck_weights = {
                {7, 4, 5, 6},
                {10},
                {10, 10, 10, 10, 10, 10, 10, 10, 10, 10}
        };

        for (int i = 0; i < 3; i++) {
            System.out.println(solution(bridge_length[i], weight[i], truck_weights[i]));
        }
    }
}
