package implementation.ch12;

import java.io.*;
import java.util.*;

/*
5 3
0 0 1 0 0
0 0 2 0 1
0 1 2 0 0
0 0 1 0 0
0 0 0 0 2

5
==========
5 2
0 2 0 1 0
1 0 1 0 0
0 0 0 0 0
2 0 0 1 1
2 2 0 1 2

10
==========
5 1
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0

11
==========
5 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1

32
 */

/**
 * https://www.acmicpc.net/problem/15686
 *
 * 치킨 배달
 *
 * TODO 다시풀기: 2
 *
 * 풀이방법
 * 1. 조합
 * 2. DFS, 백트래킹
 */
public class Ex7 {
    /*
    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Combination {
        int n, r;
        int[] now;
        List<List<Position>> results;

        public Combination(int n, int r) {
            this.n = n;
            this.r = r;
            now = new int[r];
            results = new ArrayList<>();
        }

        public void combination(List<Position> chickens, int index, int target) {
            if (index == r) {
                List<Position> tmp = new ArrayList<>();
                for (int i : now) {
                    tmp.add(chickens.get(i));
                }

                results.add(tmp);

                return;
            }

            if (target == n) {
                return;
            }

            now[index] = target;
            combination(chickens, index + 1, target + 1);
            combination(chickens, index, target + 1);
        }
    }

    static final int BLANK = 0;
    static final int HOUSE = 1;
    static final int CHICKEN = 2;

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Position> chickens = new ArrayList<>();
        List<Position> houses = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == CHICKEN) {
                    chickens.add(new Position(i, j));
                }
                if (map[i][j] == HOUSE) {
                    houses.add(new Position(i, j));
                }
            }
        }

        // 치킨집 개수 중 M 개 선택 >> nCr 조합 누적
        Combination c = new Combination(chickens.size(), M);
        c.combination(chickens, 0, 0);
        List<List<Position>> results = c.results;

        int minValue = Integer.MAX_VALUE;

        for (List<Position> result : results) {
            minValue = Math.min(minValue, cityChickenDistance(result, houses));
        }

        System.out.println(minValue);
    }

    private static int cityChickenDistance(List<Position> chickens, List<Position> houses) {
        int total = 0;

        for (Position house : houses) {
            total += chickenDistance(chickens, house);
        }

        return total;
    }

    private static int chickenDistance(List<Position> chickens, Position house) {
        int minValue = Integer.MAX_VALUE;

        for (Position chicken : chickens) {
            minValue = Math.min(minValue, Math.abs(chicken.x - house.x) + Math.abs(chicken.y - house.y));
        }

        return minValue;
    }
     */

    static class Combination {
        private int n;
        private int r;

        //현재 조합 >> 파라미터로 넘어온 arr에서 값을 꺼내기 위해 arr의 index를 모아놓음
        //now 안에 0 ~ n-1 사이 숫자 중 r개 뽑은 조합이 들어있다.
        //이는 arr의 index
        //이처럼 index를 이용한 combination으로 조합 만들 배열, 리스트의 인덱스 조합으로 이용할 수 있다.
        private int[] now;
        private ArrayList<ArrayList<Position>> result;  //전체 조합

        public int getN() {
            return n;
        }

        public int getR() {
            return r;
        }

        public int[] getNow() {
            return now;
        }

        public ArrayList<ArrayList<Position>> getResult() {
            return result;
        }

        public Combination(int n, int r) {
            this.n = n;
            this.r = r;
            now = new int[r];
            result = new ArrayList<ArrayList<Position>>();
        }

        public void combination(ArrayList<Position> arr, int depth, int index, int target) {
            if (depth == r) {
                ArrayList<Position> temp = new ArrayList<>();
                for (int i = 0; i < now.length; i++) {
                    temp.add(arr.get(now[i]));
                }
                result.add(temp);
                return;
            }
            if (target == n) return;
            now[index] = target;
            // 선택될 경우 r-1 대신 depth + 1 로 r은 그대로 유지 
            combination(arr, depth + 1, index + 1, target + 1);
            combination(arr, depth, index, target + 1);
        }

        public void combination(ArrayList<Position> arr, int index, int n, int r, int target) {
            if (r == 0) {
                ArrayList<Position> tmp = new ArrayList<>();
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
    }

    /*static class Combination {
        private int n;
        private int r;
        private int[] now; // 현재 조합
        private ArrayList<ArrayList<Position>> result; // 모든 조합

        public ArrayList<ArrayList<Position>> getResult() {
            return result;
        }

        public Combination(int n, int r) {
            this.n = n;
            this.r = r;
            this.now = new int[r];
            this.result = new ArrayList<ArrayList<Position>>();
        }

        public void combination(ArrayList<Position> arr, int depth, int index, int target) {
            if (depth == r) {
                ArrayList<Position> temp = new ArrayList<>();
                for (int i = 0; i < now.length; i++) {
                    temp.add(arr.get(now[i]));
                }
                result.add(temp);
                return;
            }
            if (target == n) return;
            now[index] = target;
            combination(arr, depth + 1, index + 1, target + 1);
            combination(arr, depth, index, target + 1);
        }
    }*/

    static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static int n, m;
    public static int[][] map = new int[50][50];    //n: 2~50

    public static final int HOUSE = 1;
    public static final int CHICKEN = 2;

    public static ArrayList<Position> chicken = new ArrayList<>();
    public static ArrayList<Position> house = new ArrayList<>();


    // 도시의 치킨 거리
    public static int getSum(ArrayList<Position> candidates) {
        int result = 0;
        //모든 집에 대해서
        //각 집에서 가장 가까운 치킨집 거리 합하기
        for (int i = 0; i < house.size(); i++) {
            int hx = house.get(i).getX();
            int hy = house.get(i).getY();

            int min = (int) 1e9;
            for (int j = 0; j < candidates.size(); j++) {
                int cx = candidates.get(j).getX();
                int cy = candidates.get(j).getY();
                min = Math.min(min, Math.abs(cx - hx) + Math.abs(cy - hy));
            }
            result += min;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for (int r = 0; r < n; r++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(stk.nextToken());
                if(map[r][c]==HOUSE) house.add(new Position(r, c));
                else if(map[r][c]==CHICKEN) chicken.add(new Position(r, c));
            }
        }
        
        //모든 치킨 집 에서 m개의 치킨 집 뽑는 조합 >> 전체 치킨집 C m
        Combination comb = new Combination(chicken.size(), m);
        comb.combination(chicken, 0, chicken.size(), m, 0);
//        comb.combination(chicken, 0, 0, 0);
        ArrayList<ArrayList<Position>> chickenComb = comb.getResult();

        //치킨 거리 합의 최소값 구하기
        int result = (int) 1e9;
        for (int i = 0; i < chickenComb.size(); i++) {
            //모든 조합들을 비교
            result = Math.min(result, getSum(chickenComb.get(i)));
        }

        System.out.println(result);
    }
}
