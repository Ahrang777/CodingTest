package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/21608
 *
 * 상어 초등학교
 * 삼성전자 공채
 * 
 * 다시 풀기
 */
public class Baekjoon21608 {

    static class Student {
        int x, y;
        int[] fList;

        public Student(int x, int y, int[] fList) {
            this.x = x;
            this.y = y;
            this.fList = fList;
        }
    }

    public static int n;
    public static int[][] classroom, nearEmptySeatCnt;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static int[] score = {0, 1, 10, 100, 1000};

    public static Map<Integer, Student> map = new HashMap<>();

    public static int getResult() {
        int result = 0;

        for (int i = 1; i <= n * n; i++) {
            Student student = map.get(i);
            int cnt = 0;

            for (int friend : student.fList) {
                if (Math.abs(student.x - map.get(friend).x) + Math.abs(student.y - map.get(friend).y) == 1) {
                    cnt++;
                }
            }

            result += score[cnt];
        }

        return result;
    }

    public static void fillNearEmptySeat() {
        nearEmptySeatCnt = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 4;

                // 경계는 주변이 빈칸이 더 적다.
                if (i == 0 || i == n-1) cnt--;
                if (j == 0 || j == n-1) cnt--;
                
                // 해당 칸 주변의 빈칸
                nearEmptySeatCnt[i][j] = cnt;
            }
        }
    }

    public static void findSeat(int index, int[] friends) {
        // 친한 친구 자리가 이미 정해졌으면 주변 자리 점수 높여서 우선 선택 되도록
        int[][] nearScore = new int[n][n];

        for (int friend : friends) {
            // 친한 친구의 자리가 이미 정해진 경우
            if (map.containsKey(friend)) {
                Student student = map.get(friend);
                int x = student.x;
                int y = student.y;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 주변 앉을 수 있는 자리 숫자 증가
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && classroom[nx][ny] == 0) {
                        nearScore[nx][ny]++;
                    }
                }
            }
        }

        int emptyCntMax = -1;
        int nearScoreMax = -1;
        int x = -1;
        int y = -1;

        // 0, 0 ~ n-1, n-1 자리 중 숫자 가장 높은자리 선택
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 아직 비어있는 좌석만 대상
                if (classroom[i][j] != 0)   continue;

                if (nearScoreMax < nearScore[i][j]) {
                    x = i;
                    y = j;
                    nearScoreMax = nearScore[i][j];
                    emptyCntMax = nearEmptySeatCnt[i][j];
                } else if (nearScoreMax == nearScore[i][j] && emptyCntMax < nearEmptySeatCnt[i][j]) {
                    emptyCntMax = nearEmptySeatCnt[i][j];
                    x = i;
                    y = j;
                }
            }
        }

        classroom[x][y] = index;
        map.put(index, new Student(x, y, friends));

        // 자리가 확정되었으니 주변 좌석들의 빈칸 갯수 -1
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                nearEmptySeatCnt[nx][ny]--;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        n = Integer.parseInt(bf.readLine());
        classroom = new int[n][n];
        fillNearEmptySeat();

        for (int i = 0; i < n * n; i++) {
            stk = new StringTokenizer(bf.readLine());
            int index = Integer.parseInt(stk.nextToken());
            int f1 = Integer.parseInt(stk.nextToken());
            int f2 = Integer.parseInt(stk.nextToken());
            int f3 = Integer.parseInt(stk.nextToken());
            int f4 = Integer.parseInt(stk.nextToken());

            findSeat(index, new int[]{f1, f2, f3, f4});
        }

        System.out.println(getResult());
    }
}
