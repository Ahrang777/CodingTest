package algorithm;

import java.util.ArrayList;

public class test {

    static class Permutation{

        private int n;
        private int r;
        private int[] now;
        private ArrayList<ArrayList<Integer>> result;

        public ArrayList<ArrayList<Integer>> getResult(){
            return result;
        }

        public Permutation(int n, int r){
            this.n = n;
            this.r = r;
            now = new int[r];
            result = new ArrayList<>();
        }

        private void swap(int[] arr, int depth, int i){
            int tmp = arr[depth];
            arr[depth] = arr[i];
            arr[i] = tmp;
        }

        public void permutation(int[] arr, int depth){
            if(depth == r){

                ArrayList<Integer> tmp = new ArrayList<>();
                for(int i = 0; i < now.length; i++){
                    tmp.add(now[i]);
                }

                result.add(tmp);

                return;
            }

            for(int i = depth; i < n; i++){
                swap(arr, depth, i);
                now[depth] = arr[depth];
                permutation(arr, depth + 1);
                swap(arr, depth, i);
            }
        }
    }

    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 3, 4, 9, 10};
        int[] weak2 = {1, 5, 6, 10};
        int[] dist = {3, 5, 7};
        int[] dist2 = {1, 2, 3, 4};

        Permutation p1 = new Permutation(dist.length, dist.length);
        p1.permutation(dist, 0);
        ArrayList<ArrayList<Integer>> result1 = p1.getResult();
        System.out.println("result1.size() = " + result1.size());

        Permutation p2 = new Permutation(dist2.length, dist2.length);
        p2.permutation(dist2, 0);
        ArrayList<ArrayList<Integer>> result2 = p2.getResult();
        System.out.println("result2.size() = " + result2.size());
    }
}
