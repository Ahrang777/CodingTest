package algorithm;

import implementation.ch12.Ex7;

import java.util.ArrayList;

/**
 * 5C2 = 5 x 4 / 2 x 1 = 5C3 = 5 x 4 x 3 / 3 x 2 x 1 = 10
 *
 * nCn = nC0 = 1
 *
 * nCr = n-1Cr-1 + n-1Cr
 */
public class Combination {

    //nCr
    public static int combination(int n, int r) {
        //nCn = 1, nC0 = 1
        if (n == r || r == 0) {
            return 1;
        } else {    //nCr = n-1Cr-1 + n-1Cr
            return combination(n - 1, r - 1) + combination(n - 1, r);
        }
    }

    //조합 묶음 구하기 : [1,2,3] >> (1,2) (1,3) (2,3)
    //백트래킹 방법
    //start 는 기준으로 start부터 쌍 확인, start 이전 내용은 다 체크한것으로 간주
    public static void comb1(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        } else {
            //순서대로 자신 뽑는 경우 확인, 뽑지 않는 경우는 현재 i 보다 큰 나머지 들에서 확인됨
            for (int i = start; i < n; i++) {
                visited[i] = true;
                comb1(arr, visited, i + 1, n, r - 1);   //1,2 이런게 뽑히고 2, 1은 안된다. 배열 앞에서 부터 다 뽑는 경우 생각하며 가기에 뒤에 뽑히는건 무조건 앞에 뽑힌것보다 숫자가 커야 한다. 그래서 depth + 1이 아닌 i + 1
                visited[i] = false;
            }
        }
    }

    //조합 묶음 구하기 : [1,2,3] >> (1,2) (1,3) (2,3)
    //재귀
    public static void comb2(int[] arr, boolean[] visited, int depth, int n, int r) {
        //다 찾음
        if (r == 0) {
            print(arr, visited, n);
            return;
        }
        //못 찾았는데 끝남
        if (depth == n) {
            return;
        }
        else {
            //하나의 원소 선택한 경우 n-1Cr-1
            //n-1Cr-1에서 n-1은 depth + 1을 함으로 탐색 대상을 줄였다.
            visited[depth] = true;
            comb2(arr, visited, depth + 1, n, r - 1);

            //하나의 원소를 선택하지 않은 경우 n-1Cr
            //n-1Cr에서 n-1은 depth + 1을 함으로 탐색 대상을 줄였다.
            visited[depth] = false;
            comb2(arr, visited, depth + 1, n, r);
        }
    }

    //조합 쌍 출력
    public static void print(int[] arr, boolean[] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[i] == true) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
    
    //visited 없이 index로 해결
    //arr자체는 빈 배열
    //0 ~ n-1 숫자조합
    //target 을 늘려가며 target을 넣는것
    //index는 빈 arr에 조합으로 선택된 숫자를 넣을때만 +1, 즉 index가 뽑은 갯수가 된다.
    public static void comb3(int[] arr, int index, int n, int r, int target) {
        if (r == 0) {
            print3(arr, index);
            return;
        }
        else if (target == n) {
            return;
        }
        else{
            arr[index] = target;
            comb3(arr, index + 1, n, r - 1, target + 1);
            comb3(arr, index, n, r, target + 1);
        }
    }

    public static void print3(int[] arr, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //Combination3 을 이용해서 0 ~ n-1 숫자 조합을 이용해서 다른 배열이나 리스트의 인덱스로 사용하여 해당 배열이나 리스트의 조합으로 사용할 수 있다.
    /*static class Combination {
        private int n;
        private int r;

        //현재 조합 >> 파라미터로 넘어온 arr에서 값을 꺼내기 위해 arr의 index를 모아놓음
        //now 안에 0 ~ n-1 사이 숫자 중 r개 뽑은 조합이 들어있다.
        //이는 arr의 index
        //이처럼 index를 이용한 combination으로 조합 만들 배열, 리스트의 인덱스 조합으로 이용할 수 있다.
        private int[] now;
        private ArrayList<ArrayList<Ex7.Position>> result;  //전체 조합

        public int getN() {
            return n;
        }

        public int getR() {
            return r;
        }

        public int[] getNow() {
            return now;
        }

        public ArrayList<ArrayList<Ex7.Position>> getResult() {
            return result;
        }

        public Combination(int n, int r) {
            this.n = n;
            this.r = r;
            now = new int[r];
            result = new ArrayList<ArrayList<Ex7.Position>>();
        }

        public void combination(ArrayList<Ex7.Position> arr, int index, int n, int r, int target) {
            if (r == 0) {
                ArrayList<Ex7.Position> tmp = new ArrayList<>();
                for (int i = 0; i < now.length; i++) {
                    tmp.add(arr.get(now[i]));   //now의 인덱스 조합을 통한 Position 배열인 arr의 조합
                }

                //현재 Position 조합을 전체 조합에 반영
                result.add(tmp);

                return;
            }
            else if(target == n)    return;

            now[index] = target;
            combination(arr, index + 1, n, r - 1, target + 1);
            combination(arr, index, n, r, target + 1);
        }
    }*/

    public static void main(String[] args) {
        System.out.println("-------- 0. 조합 경우의 수 ---------");
        System.out.println(combination(3, 2));
        System.out.println();

        //조합 만들 배열
        int[] arr = {1, 2, 3};
        //arr의 각 원소 기준으로 방문 여부 체크
        boolean[] visited = new boolean[arr.length];

        //nC1, nC2 ,,, nCn 각 쌍
        //백트래킹 이용해서 구현
        System.out.println("-------- 1. 백트래킹 ---------");
        for (int r = 1; r <= arr.length; r++) {
            System.out.print(arr.length + "개 중에 " + r + "개 뽑음");
            System.out.println();
            comb1(arr, visited, 0, arr.length, r);
            System.out.println();
        }

        //nC1, nC2 ,,, nCn 각 쌍
        //재귀 이용해서 구현
        System.out.println("---------- 2. 재귀 ----------");
        for (int r = 1; r <= arr.length; r++) {
            System.out.print(arr.length + "개 중에 " + r + "개 뽑음");
            System.out.println();
            comb2(arr, visited, 0, arr.length, r);
            System.out.println();
        }

        int[] arr3 = new int[3];
        //0 ~ n-1
        System.out.println("---------- 3. visited 없이 index로 해결 ----------");
        comb3(arr3, 0, 3, 2, 0);
    }
}
