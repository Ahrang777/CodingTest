package sort.ch14;

import java.util.*;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42889
 * 
 * 실패율
 */
public class Ex3 {

    static class Stage implements Comparable<Stage>{
        private int index;
        private double failure;

        public Stage(int index, double failure){
            this.index = index;
            this.failure = failure;
        }

        public int getIndex(){
            return index;
        }

        public int compareTo(Stage other){
            if (this.failure != other.failure) {
                return Double.compare(other.failure, this.failure);
            } else {
                return Integer.compare(this.index, other.index);
            }
        }
    }

    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        ArrayList<Stage> result = new ArrayList<>();

        //스테이지 번호 1 ~ N
        for (int i = 1; i <= N; i++) {
            int sum = 0;    //스테이지 도달한 사람
            int cnt = 0;    //스테이지 도달했으나 아직 클리어X


            for (int j = 0; j < stages.length; j++) {
                if (i <= stages[j]) {
                    sum++;
                    if (i == stages[j]) {
                        cnt++;
                    }
                }
            }

            double fail = 0;
            if (sum != 0) {
                fail = (double) cnt / sum;
            }

            result.add(new Stage(i, fail));

        }

        Collections.sort(result);

        for (int i = 0; i < N; i++) {
            answer[i] = result.get(i).getIndex();
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] stages1 = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] stages2 = {4, 4, 4, 4, 4};

        for (int i : solution(5, stages1)) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : solution(4, stages2)) {
            System.out.print(i + " ");
        }
    }
}
