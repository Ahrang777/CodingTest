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
            return this.time - other.time;
            //return Integer.compare(this.time, other.time);
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

    /*
    //효율성 테스트에서 1개 막힘
    static class Solution {
        
        Comparator<Food> comIdx = new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                return o1.getIndex() - o2.getIndex();
            }
        };
        
        public int solution(int[] food_times, long k) {
            ArrayList<Food> foods = new ArrayList<>();
            int length = food_times.length;

            for (int i = 0; i < length; i++) {
                foods.add(new Food(food_times[i], i + 1));
            }

            Collections.sort(foods);

            //이전에 완전히 다 먹은 음식의 시간
            int prev = 0;
            int idx = 0;
            for (Food food : foods) {
                int diff = food.getTime() - prev;
                if (diff != 0) {
                    long spend = diff * length;
                    if (spend <= k) {   //한 음식 다 먹음
                        k -= spend;
                        prev = food.getTime();
                    } else {
                        k %= length;    //한바퀴 묶음으로 돌리고 나머지로 몇번째인지


                        ArrayList<Food> result = new ArrayList<>(foods.subList(idx, food_times.length));
                        result.sort(new Comparator<Food>() {
                            @Override
                            public int compare(Food o1, Food o2) {
                                return o1.getIndex() - o2.getIndex();
                            }
                        });

                        return result.get((int) k).getIndex();

                        *//*foods.subList(idx, food_times.length).sort(new Comparator<Food>() {
                            @Override
                            public int compare(Food o1, Food o2) {
                                return o1.getIndex() - o2.getIndex();
                            }
                        });

                        return foods.get(idx + (int) k).getIndex();*//*
                    }
                }

                length--;
                idx++;
            }

            return -1;
        }
    }*/

    public static void main(String[] args) {
        int[] food_times = {3, 1, 2};
        int k =5;
        Solution s = new Solution();
        System.out.println(s.solution(food_times, k));
    }
}
