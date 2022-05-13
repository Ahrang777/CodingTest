package grid.ch11;

import java.util.*;

/*
https://programmers.co.kr/learn/courses/30/lessons/42891

food_times: [3,1,2], k: 5
result: 1
 */
public class Ex6 {

    static class Food implements Comparable<Food>{

        private int time;
        private int index;

        public Food(int time, int index){
            this.time = time;
            this.index = index;
        }

        public int getTime(){
            return time;
        }

        public int getIndex(){
            return index;
        }

        @Override
        public int compareTo(Food other){
            return Integer.compare(this.time, other.time);
        }
    }

    static class Solution {
        public int solution(int[] food_times, long k) {
            //전체 음식 먹는 시간이 k 보다 작거나 같으면 -1
            long sum = 0;
            for(int i=0;i<food_times.length;i++){
                sum+=food_times[i];
            }
            if(sum <= k) return -1;

            PriorityQueue<Food> pq = new PriorityQueue<>();
            for(int i=0;i<food_times.length;i++){
                pq.offer(new Food(food_times[i], i+1));
            }

            sum = 0;    //먹은 시간
            long prev = 0;  //가장 최근에 다 먹은 음식의 원래 할당 시간
            long length = food_times.length;    //남은 음식 개수

            //sum+((pq.peek().getTime() - prev)*length) > k 이면 남은 음식들은 k분 내로 완전히 다 못먹음
            while(sum+((pq.peek().getTime() - prev)*length)<=k){
                int now = pq.poll().getTime();  //음식 하나 다 먹음
                sum+=(now-prev)*length; //음식 하나 다 먹는데 걸린 시간 반영(한바퀴 도는걸로 기준)
                length--;   //다 먹은 음식 제외
                prev = now; //가장 최근에 먹은 음식의 시간 설정
            }


            ArrayList<Food> result = new ArrayList<>();
            while(!pq.isEmpty()){
                result.add(pq.poll());
            }

            //다 못먹고 남은 음식들 다시 번호 순대로 정렬
            Collections.sort(result, new Comparator<Food>(){
                @Override
                public int compare(Food a, Food b) {
                    return Integer.compare(a.getIndex(), b.getIndex());
                }
            });

            //result 리스트에 있는 음식은 다 못 먹는 음식이기에 없어질 걱정 안 하고 아래처럼 단순히 index 찾기 가능
            return result.get((int) ((k-sum)%length)).getIndex();
        }
    }

    public static void main(String[] args) {
        int[] food_times = {3, 1, 2};
        int k =5;
        Solution s = new Solution();
        System.out.println(s.solution(food_times, k));
    }
}
