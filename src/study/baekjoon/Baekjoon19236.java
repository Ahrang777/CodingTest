package study.baekjoon;

import java.io.*;
import java.util.*;

/*
입력1
7 6 2 3 15 6 9 8
3 1 1 8 14 7 10 1
6 1 13 6 4 3 11 4
16 1 8 7 5 2 12 2

출력1
33
============
입력2
16 7 1 4 4 3 12 8
14 7 7 6 3 4 10 2
5 2 15 2 8 3 6 4
11 8 2 4 13 5 9 4

출력2
43
============
입력3
12 6 14 5 4 5 6 7
15 1 11 7 3 7 7 5
10 3 8 3 16 6 1 1
5 8 2 7 13 6 9 2

출력3
76
============
입력4
2 6 10 8 6 7 9 4
1 7 16 6 4 2 5 8
3 7 8 6 7 6 14 8
12 7 15 4 11 3 13 3

출력4
39
 */

/**
 * https://www.acmicpc.net/problem/19236
 *
 * 청소년 상어
 * 삼성전자 공채
 *
 *
 * 풀이 참고: https://bcp0109.tistory.com/215
 *
 * Solution
 * 백트래킹을 이용하여 상어가 이동하는 모든 경우의 수를 구하면 된다.
 *
 * 상어는 한 방향밖에 가지 못하기 때문에 4x4 배열에서 이동하는 경우의 수는 최대 3개
 *
 * 물고기가 먼저 이동해야 하기 때문에 DFS 시작하자마자 물고기를 전부 이동시키고 이동 가능한 경우의 수를 전부 확인하여 다시 DFS 를 들어갑니다.
 *
 * 원래 백트래킹을 할 때는 빠져 나오면서 값들을 원래 값으로 돌려놓아야 하는데
 * 배열이나 물고기 리스트들이 너무 바뀌는게 많기 때문에 다음 DFS 로 넘기는 값들은 전부 복사한 새로운 값으로 했습니다.
 * 그래서 DFS 를 빠져나온 후에도 기존 값들을 갖고 다음 경우의 수를 확인할 수 있습니다.
 *
 * 주의할 점
 * 1. 물고기들은 번호 순서대로 이동해야 합니다. 물고기가 순서대로 주어지지 않기 때문에 정렬이 필요합니다.
 * 2. 상어는 물고기가 있는 공간으로만 이동할 수 있습니다.
 * 3. 물고기는 빈칸 또는 또다른 물고기가 있는 곳으로만 이동할 수 있습니다.
 * 4. 상어나 물고기가 이동한 후에는 이동하기 전의 공간 뒷처리를 잘 해줘야합니다.
 */
public class Baekjoon19236 {

    static class Shark {
        int x, y, dir, eatSum;

        Shark() { }

        Shark(int x, int y, int dir, int eatSum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }

    static class Fish {
        int x, y, id, dir;
        boolean isAlive = true;

        Fish() { }

        Fish(int x, int y, int id, int dir, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    // 0 부터 7 까지 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0;

    public static final int BLANK = 0;
    public static final int SHARK = -1;

    public static void dfs(int[][] arr, Shark shark, List<Fish> fishes) {
        // 잡아먹은 양 최대값 구하기
        if (maxSum < shark.eatSum) {
            maxSum = shark.eatSum;
        }
        
        /*
        // 모든 물고기 이동
        moveFish(fishes, arr);
        */

        fishes.forEach(f -> moveFish(f, arr, fishes));
        
        // 상어 이동
        // 상어는 최대 3칸 이동 가능 (0, 0) -> (3, 3) RD 방향 3칸
        for (int dist = 1; dist < 4; dist++) {
            int nx = shark.x + dx[shark.dir] * dist;
            int ny = shark.y + dy[shark.dir] * dist;
            
            // 상어 이동 가능한 경우
            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > BLANK) {
                // 물고기 먹고 dfs
                // 백트래킹은 원래 원상 복구를 해야하는데 이 문제는 바뀌는게 너무 많아서 복사하는 형태로 사용
                // 상어가 해당 물고기를 먹기전 상태를 복사하고 먹는 처리를 하여
                // 상어가 해당 물고기를 안먹는 다른 경우도 체크하기 위해서 복사한 대상을 이용해서 체크하면 된다.
                // 원상 복구 할 필요 없이 원래 상태를 그대로 유지하고 변경된 상태를 처리하기 위한 복사 작업을 하는 것
                int[][] arrCopies = copyArr(arr);
                List<Fish> fishCopies = copyFishes(fishes);

                arrCopies[shark.x][shark.y] = BLANK;
                Fish f = fishCopies.get(arr[nx][ny] - 1);   // 상어가 먹을 물고기
                Shark newShark = new Shark(f.x, f.y, f.dir, shark.eatSum + f.id);
                f.isAlive = false;
                arrCopies[f.x][f.y] = -1;

                dfs(arrCopies, newShark, fishCopies);
            }
        }
    }

    // 이동가능한 칸은 빈칸, 다른 물고기가 있는 칸
    // 45도 반시계 방향으로 회전하면서 스캔
    static void moveFish(Fish fish, int[][] arr, List<Fish> fishes) {
        if (fish.isAlive == false) return;

        for (int i = 0; i < 8; i++) {
            int dir = (fish.dir + i) % 8;
            int nx = fish.x + dx[dir];
            int ny = fish.y + dy[dir];

            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > SHARK) {
                arr[fish.x][fish.y] = BLANK;

                if (arr[nx][ny] == BLANK) {
                    fish.x = nx;
                    fish.y = ny;
                } else {
                    Fish temp = fishes.get(arr[nx][ny] - 1);
                    temp.x = fish.x;
                    temp.y = fish.y;
                    arr[fish.x][fish.y] = temp.id;

                    fish.x = nx;
                    fish.y = ny;
                }

                arr[nx][ny] = fish.id;
                fish.dir = dir;
                return;
            }
        }
    }

    /*// 빈칸, 다른 물고기 있는 칸 으로 이동O
    // 이동 못 하는 경우는 반시계 45도 회전하면서 확인
    public static void moveFish(List<Fish> fishes, int[][] arr) {
        for (Fish fish : fishes) {

            if (fish.isAlive == false) continue;

            int dir = fish.dir;
            int x = fish.x;
            int y = fish.y;

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 이동 가능한 경우
                if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && arr[nx][ny] > SHARK) {

                    if (arr[nx][ny] == BLANK) {
                        arr[x][y] = BLANK;
                    } else {
                        Fish tmp = fishes.get(arr[nx][ny] - 1);
                        tmp.x = x;
                        tmp.y = y;
                        arr[x][y] = tmp.id;
                    }

                    fish.x = nx;
                    fish.y = ny;
                    arr[nx][ny] = fish.id;
                    break;
                }
                
                // 이동 불가능 한 경우 회전
                dir = turnLeft(dir);
            }
        }
    }

    public static int turnLeft(int dir) {
        return (dir + 1) % 8;
    }
     */

    public static int[][] copyArr(int[][] arr) {
        int[][] temp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        return temp;
    }

    public static List<Fish> copyFishes(List<Fish> fishes) {
        /*List<Fish> tmp = new ArrayList<>();
        for (Fish fish : fishes) {
            tmp.add(new Fish(fish.x, fish.y, fish.id, fish.dir, fish.isAlive));
        }

        return tmp;*/

        List<Fish> temp = new ArrayList<>();
        fishes.forEach(e -> temp.add(new Fish(e.x, e.y, e.id, e.dir, e.isAlive)));
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int[][] arr = new int[4][4];
        List<Fish> fishes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");

            for (int j = 0; j < 4; j++) {
                Fish f = new Fish(); 
                f.id = Integer.parseInt(stk.nextToken());  // 물고기 정보
                f.dir = Integer.parseInt(stk.nextToken()) - 1;  // 방향 정보 >> 1부터 시작이라 -1로 0부터 시작으로 변경
                f.x = i;
                f.y = j;

                fishes.add(f);
                arr[i][j] = f.id;
            }
        }

        // 작은 물고기 먼저 이동해야 해서 정렬
        Collections.sort(fishes, new Comparator<Fish>(){
            @Override
            public int compare(Fish f1, Fish f2) {
                return Integer.compare(f1.id, f2.id);
            }
        });

        // 상어는 (0, 0) 물고기 먹고 시작
        Fish f = fishes.get(arr[0][0] - 1);
        Shark shark = new Shark(0, 0, f.dir, f.id);
        f.isAlive = false;
        arr[0][0] = SHARK;
        
        // solution
        dfs(arr, shark, fishes);
        System.out.println(maxSum);
    }
}
