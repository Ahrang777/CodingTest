package dfs_bfs.ch13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
입력
5
X S X X T
T X S X X
X X X X X
X T X X X
X X T X X

출력
YES
==========
입력
4
S S S T
X X X X
X X X X
T T T X

출력
NO
 */

//조합을 이용한 풀이

/**
 * 조합을 이용한 풀이
 *
 * https://www.acmicpc.net/problem/18428
 * '감시 피하기' 문제
 */
public class Ex6_1 {

    static class Position{
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

    static class Combination{
        private int n;
        private int r;
        private int[] now;
        private ArrayList<ArrayList<Position>> result;

        public Combination(int n, int r) {
            this.n = n;
            this.r = r;
            this.now = new int[r];
            this.result = new ArrayList<>();
        }

        public ArrayList<ArrayList<Position>> getResult() {
            return result;
        }

        public void combination(ArrayList<Position> arr, int depth, int index, int target) {
            if (depth == r) {
                ArrayList<Position> temp = new ArrayList<>();
                for (int i : now) {
                    temp.add(arr.get(i));
                }
                result.add(temp);
                return;
            }
            if (target == n) {
                return;
            }
            now[index] = target;
            combination(arr, depth + 1, index + 1, target + 1);
            combination(arr, depth, index, target + 1);
        }
    }

    public static int n; // 복도의 크기
    public static char[][] board = new char[6][6]; // 복도 정보 (N x N)
    public static ArrayList<Position> teachers = new ArrayList<>(); // 모든 선생님 위치 정보
    public static ArrayList<Position> spaces = new ArrayList<>(); // 모든 빈 공간 위치 정보

    // 특정 방향으로 감시를 진행 (학생 발견: true, 학생 미발견: false)
    // 선생님이 해당 방향으로 찾는 경우
    public static boolean watch(int x, int y, int direction) {

        // 왼쪽 방향으로 감시
        if (direction == 0) {
            while (y >= 0) {
                if (board[x][y] == 'S') { // 학생이 있는 경우
                    return true;
                }
                if (board[x][y] == 'O') { // 장애물이 있는 경우
                    return false;
                }
                y -= 1;
            }
        }
        // 오른쪽 방향으로 감시
        if (direction == 1) {
            while (y < n) {
                if (board[x][y] == 'S') { // 학생이 있는 경우
                    return true;
                }
                if (board[x][y] == 'O') { // 장애물이 있는 경우
                    return false;
                }
                y += 1;
            }
        }
        // 위쪽 방향으로 감시
        if (direction == 2) {
            while (x >= 0) {
                if (board[x][y] == 'S') { // 학생이 있는 경우
                    return true;
                }
                if (board[x][y] == 'O') { // 장애물이 있는 경우
                    return false;
                }
                x -= 1;
            }
        }
        // 아래쪽 방향으로 감시
        if (direction == 3) {
            while (x < n) {
                if (board[x][y] == 'S') { // 학생이 있는 경우
                    return true;
                }
                if (board[x][y] == 'O') { // 장애물이 있는 경우
                    return false;
                }
                x += 1;
            }
        }
        return false;
    }

    // 장애물 설치 이후에, 한 명이라도 학생이 감지되는지 검사
    // 선생님 입장에서 찾는 경우
    public static boolean process() {
        for (Position teacher : teachers) {
            int x = teacher.getX();
            int y = teacher.getY();

            for (int i = 0; i < 4; i++) {
                if (watch(x, y, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        n = Integer.parseInt(bf.readLine());

        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                board[i][j] = stk.nextToken().charAt(0);
                // 선생님이 존재하는 위치 저장
                if (board[i][j] == 'T') {
                    teachers.add(new Position(i, j));
                }
                // 장애물을 설치할 수 있는 (빈 공간) 위치 저장
                if (board[i][j] == 'X') {
                    spaces.add(new Position(i, j));
                }
            }
        }

        Combination comb = new Combination(spaces.size(), 3);
        comb.combination(spaces, 0, 0, 0);
        ArrayList<ArrayList<Position>> spaceList = comb.getResult();

        // 학생이 한 명도 감지되지 않도록 설치할 수 있는지의 여부
        boolean found = false;
        for (int i = 0; i < spaceList.size(); i++) {
            // 장애물들을 설치해보기
            for (Position now : spaceList.get(i)) {
                int x = now.getX();
                int y = now.getY();
                board[x][y] = 'O';
            }

            // 학생이 한 명도 감지되지 않는 경우
            if (!process()) {
                // 원하는 경우를 발견한 것임
                found = true;
                break;
            }
            // 설치된 장애물을 다시 없애기
            for (int j = 0; j < spaceList.get(i).size(); j++) {
                int x = spaceList.get(i).get(j).getX();
                int y = spaceList.get(i).get(j).getY();
                board[x][y] = 'X';
            }
        }

        if (found) System.out.println("YES");
        else System.out.println("NO");
    }
}
