package grid.ch11;

import java.util.*;

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
            long sum = 0;
            for(int i=0;i<food_times.length;i++){
                sum+=food_times[i];
            }
            if(sum <= k) return -1;

            PriorityQueue<Food> pq = new PriorityQueue<>();
            for(int i=0;i<food_times.length;i++){
                pq.offer(new Food(food_times[i], i+1));
            }

            sum = 0;
            long prev = 0;
            long length = food_times.length;

            while(sum+((pq.peek().getTime() - prev)*length)<=k){
                int now = pq.poll().getTime();
                sum+=(now-prev)*length;
                length--;
                prev = now;
            }

            ArrayList<Food> result = new ArrayList<>();
            while(!pq.isEmpty()){
                result.add(pq.poll());
            }

            Collections.sort(result, new Comparator<Food>(){
                @Override
                public int compare(Food a, Food b) {
                    return Integer.compare(a.getIndex(), b.getIndex());
                }
            });

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
