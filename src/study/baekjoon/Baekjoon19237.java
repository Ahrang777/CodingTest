package study.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
입력1
5 4 4
0 0 0 0 3
0 2 0 0 0
1 0 0 0 4
0 0 0 0 0
0 0 0 0 0
4 4 3 1
2 3 1 4
4 1 2 3
3 4 2 1
4 3 1 2
2 4 3 1
2 1 3 4
3 4 1 2
4 1 2 3
4 3 2 1
1 4 3 2
1 3 2 4
3 2 1 4
3 4 1 2
3 2 4 1
1 4 2 3
1 4 2 3

출력1
14
============
입력2
4 2 6
1 0 0 0
0 0 0 0
0 0 0 0
0 0 0 2
4 3
1 2 3 4
2 3 4 1
3 4 1 2
4 1 2 3
1 2 3 4
2 3 4 1
3 4 1 2
4 1 2 3

출력2
26
============
입력3
5 4 1
0 0 0 0 3
0 2 0 0 0
1 0 0 0 4
0 0 0 0 0
0 0 0 0 0
4 4 3 1
2 3 1 4
4 1 2 3
3 4 2 1
4 3 1 2
2 4 3 1
2 1 3 4
3 4 1 2
4 1 2 3
4 3 2 1
1 4 3 2
1 3 2 4
3 2 1 4
3 4 1 2
3 2 4 1
1 4 2 3
1 4 2 3

출력3
-1
============
입력4
5 4 10
0 0 0 0 3
0 0 0 0 0
1 2 0 0 0
0 0 0 0 4
0 0 0 0 0
4 4 3 1
2 3 1 4
4 1 2 3
3 4 2 1
4 3 1 2
2 4 3 1
2 1 3 4
3 4 1 2
4 1 2 3
4 3 2 1
1 4 3 2
1 3 2 4
3 2 1 4
3 4 1 2
3 2 4 1
1 4 2 3
1 4 2 3

출력4
-1
 */

/**
 * https://www.acmicpc.net/problem/19237
 * <p>
 * 어른 상어
 * 삼성전자 공채
 *
 * 다시 풀기
 *
 * 참고: https://bcp0109.tistory.com/213?category=847928
 *
 *
 * 1 번 상어가 남거나 1000 초가 지날 때까지 다음 순서대로 반복하면 됩니다.
 *
 * moveShark() : 상어 이동
 *  - 다음 방향 계산
 *  - 이동
 *  - 경쟁을 통해 작은 번호의 상어 생존
 * decreaseSmellTime() : 모든 냄새들 1 씩 감소
 * createSmell() : 상어가 이동한 자리에 새로운 냄새 생성
 *
 * 주의할점
 * 1)상어가 이동한 후에는 상어의 방향을 이동한 방향으로 바꿔주어야 합니다.
 * 2)위 순서에서 2번과 3번을 바꾸게 되면 상어가 새로운 냄새를 생성하자마자 1 씩 깎이기 때문에 순서를 지켜야 합니다.
 * 3)상어 리스트를 for 문으로 돌면서 경쟁에서 패배한 상어를 바로 제거하니 런타임 에러가 발생했습니다. for 문 도중에는 요소를 삭제하지 말고 기록해두었다가 나중에 삭제합니다.
 *
 * noSmellSet, mySmellSet
 * - 상어가 이동할 수 있는 공간을 미리 구하기 위해 사용합니다.
 * - 상어는 먼저 냄새가 없는 곳을 찾고 만약 4방향 전부 냄새가 존재한다면 자기 냄새가 있는 방향으로 이동합니다.
 * - 이동할 수 있는 곳은 여러 개가 될 수 있기 때문에 Set 자료구조에 저장하고 상어의 현재 방향에 따른 우선순위를 통해 비교합니다.
 */
public class Baekjoon19237 {

    static class Shark {
        int index, dir, x, y;
        int[][] priority = new int[4][4];  // U, D, L, R 방향에 대한 각각의 방향 우선순위

        public Shark() {
        }

        // 현재 상어가 다음에 이동가능한 방향들
        // 1. 냄새 없는 칸
        // 2. 자신의 냄새가 있는 칸
        public int findNextDir(Set<Integer> candidates) {
            for (int i = 0; i < 4; i++) {
                if (candidates.contains(priority[dir][i])) {
                    return priority[dir][i];
                }
            }
            return -1;
        }
    }

    public static int n, m, k;

    public static final int BLANK = 0;

    public static Map<Integer, Shark> sharks = new HashMap<>();

    public static int time = 0;

    public static int[][] arr = new int[20][20];    // 상어 표시
    public static int[][] smell = new int[20][20];  // 냄새 표시
    public static int[][] leftTime = new int[20][20];   // 냄새의 남은 시간 표시

    public static void solution() {
        while (time++ < 1000) {
            moveShark();
            decreaseSmellTime();
            createSmell();

            if (sharks.size() == 1) {
                System.out.println(time);
                return;
            }
        }

        System.out.println(-1);
    }

    public static void moveShark() {

        // U, D, L, R
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        ArrayList<Integer> willRemove = new ArrayList<>();

        for (Integer id : sharks.keySet()) {

            Set<Integer> noSmellSet = new HashSet<>();
            Set<Integer> mySmellSet = new HashSet<>();

            Shark s = sharks.get(id);

            for (int i = 0; i < 4; i++) {
                int nx = s.x + dx[i];
                int ny = s.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (smell[nx][ny] == BLANK) {
                    noSmellSet.add(i);
                } else if (smell[nx][ny] == s.index) {
                    mySmellSet.add(i);
                }
            }

            // 냄새 없는 곳부터 스캔
            int nextDir = s.findNextDir(noSmellSet);

            // 냄새 없는 곳X >> 자기 냄새 있는 곳 스캔
            if (nextDir == -1) {
                nextDir = s.findNextDir(mySmellSet);
            }

            // 상어 이동
            arr[s.x][s.y] = BLANK;
            if (nextDir == 0) {
                s.x -= 1;
            } else if (nextDir == 1) {
                s.x += 1;
            } else if (nextDir == 2) {
                s.y -= 1;
            } else if (nextDir == 3) {
                s.y += 1;
            }

            // 이동할 위치에 상어 있으면 경쟁 후 작은 번호가 승리
            if (arr[s.x][s.y] == BLANK || s.index < arr[s.x][s.y]) {
                arr[s.x][s.y] = s.index;
                s.dir = nextDir;
            } else {
                willRemove.add(s.index);
            }
        }

        for (int id : willRemove) {
            sharks.remove(id);
        }
    }

    public static void decreaseSmellTime() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (leftTime[i][j] == BLANK) continue;

                leftTime[i][j] -= 1;

                if (leftTime[i][j] == BLANK) {
                    smell[i][j] = BLANK;
                }
            }
        }
    }

    public static void createSmell() {
        for (int id : sharks.keySet()) {
            Shark s = sharks.get(id);

            smell[s.x][s.y] = s.index;
            leftTime[s.x][s.y] = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(stk.nextToken());
                arr[i][j] = x;
                if (x != BLANK) {
                    Shark shark = new Shark();
                    shark.index = x;
                    shark.x = i;
                    shark.y = j;

                    sharks.put(x, shark);

                    leftTime[i][j] = k;
                    smell[i][j] = x;
                }
            }
        }

        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < m; i++) {
            Shark s = sharks.get(i + 1);
            s.dir = Integer.parseInt(stk.nextToken()) - 1;
        }

        for (int s = 0; s < m; s++) {

            Shark shark = sharks.get(s + 1);

            for (int i = 0; i < 4; i++) {
                stk = new StringTokenizer(bf.readLine(), " ");

                for (int j = 0; j < 4; j++) {
                    shark.priority[i][j] = Integer.parseInt(stk.nextToken()) - 1;
                }
            }
        }

        solution();
    }
}
