package implementation.ch12;

import java.util.ArrayList;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/60062
 * 
 * 외벽 점검
 */
public class Ex8 {

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


    public static int solution(int n, int[] weak, int[] dist) {
        //원형이라 크기 2배로 늘린다.
        ArrayList<Integer> weakList = new ArrayList<>();

        for(int i=0;i<weak.length;i++){
            weakList.add(weak[i]);
        }

        for(int i=0;i<weak.length;i++){
            weakList.add(weak[i] + n);
        }

        //친구 수, 최소를 찾기 위해 초기값은 최대값으로
        int answer = dist.length + 1;

        Permutation p = new Permutation(dist.length, dist.length);
        p.permutation(dist, 0);

        ArrayList<ArrayList<Integer>> result = p.getResult();

        // 0부터 length - 1까지의 위치를 각각 시작점으로 설정
        for(int start = 0; start < weak.length; start++){

            // 친구를 나열하는 모든 경우 각각에 대하여 확인
            // 친구 나열한 경우마다 체크
            for(int i = 0; i < result.size(); i++){
                //5~6, 10~13 이런식으로 나눌수 있기에 단순히 5 ~ 13 의 길이인 8로 계산하면 안됨
                //마지막 지점 위치로 계산

                //투입한 친구 수
                int cnt = 1;

                //해당 친구가 점검가능한 마지막 위치
                int pos = weakList.get(start) + result.get(i).get(cnt - 1);

                // 시작점부터 모든 취약한 지점을 확인
                // 시작점부터 모든 취약점의 위치와 친구가 점검가능한 마지막 위치 비교
                for (int j = start; j < start + weak.length; j++) {

                    // 점검할 수 있는 위치를 벗어나는 경우
                    if (pos < weakList.get(j)) {
                        // 새로운 친구를 투입
                        cnt += 1;

                        // 더 투입이 불가능하다면 종료
                        if (cnt > dist.length) {
                            break;
                        }

                        // 이전 친구로 커버 안되는 지점부터 시작해서 새로운 친구가 커버 가능한 마지막 지점으로 갱신
                        pos = weakList.get(j) + result.get(i).get(cnt - 1);
                    }
                }
                answer = Math.min(answer, cnt);
            }
        }
        if (answer > dist.length) {
            return -1;
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 3, 4, 9, 10};
        int[] weak2 = {1, 5, 6, 10};
        int[] dist = {3, 5, 7};
        int[] dist2 = {1, 2, 3, 4};

        System.out.println(solution(n, weak2, dist2));
        System.out.println(solution(n, weak, dist));
    }
}
