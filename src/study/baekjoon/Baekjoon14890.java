package study.baekjoon;

import java.io.*;
import java.util.*;

/*
입력1
6 2
3 3 3 3 3 3
2 3 3 3 3 3
2 2 2 3 2 3
1 1 1 2 2 2
1 1 1 3 3 1
1 1 2 3 3 2

출력1
3
==================
입력2
6 2
3 2 1 1 2 3
3 2 2 1 2 3
3 2 2 2 3 3
3 3 3 3 3 3
3 3 3 3 2 2
3 3 3 3 2 2

출력2
7
==================
입력3
6 3
3 2 1 1 2 3
3 2 2 1 2 3
3 2 2 2 3 3
3 3 3 3 3 3
3 3 3 3 2 2
3 3 3 3 2 2

출력3
3
==================
입력4
6 1
3 2 1 1 2 3
3 2 2 1 2 3
3 2 2 2 3 3
3 3 3 3 3 3
3 3 3 3 2 2
3 3 3 3 2 2

출력4
11
 */

/**
 * https://www.acmicpc.net/problem/14890
 * 
 * 경사로 
 * 삼성전자 공채
 * 
 * 다시 풀기
 */
public class Baekjoon14890 {

    // 지도 크기, 경사로 길이
    public static int N, L;
    public static int[][] map = new int[100][100];
    public static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        N = Integer.parseInt(stk.nextToken());
        L = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        solution();
    }

    // 가로 확인
    public static boolean calRow(int row) {

        // 경사면 설치 여부 확인 배열
        // 설치하면 true
        boolean[] isIncline = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int diff = map[row][i] - map[row][i + 1];

            // 높이차이가 1 넘게 나는 경우
            if (diff < -1 || diff > 1) {
                return false;
            }
            // 다음 칸이 1칸 더 높은 경우
            else if (diff == -1) {
                // 현재칸 포함 이전에 L개의 칸에 대해 체크
                for (int j = 0; j < L; j++) {
                    // 범위를 넘어선 경우 or 이미 경사로가 설치된 경우 경사로 설치 못함 >> 지나가지 못하는 길
                    if (i - j < 0 || isIncline[i - j]) {
                        return false;
                    }
                    // 현재 계단과 이전 계단 높이가 같지 않은 경우
                    if (map[row][i] != map[row][i - j]) {
                        return false;
                    }
                    
                    // 경사면 설치
                    isIncline[i - j] = true;    
                }

            }
            // 다음칸이 1칸 더 낮은 경우
            else if (diff == 1) {
                // 다음 L개의 칸에 대해 체크
                for (int j = 1; j <= L; j++) {
                    // 범위 넘어서는 경우 or 이미 설치한 경우
                    if (i + j >= N || isIncline[i + j]) {
                        return false;
                    }
                    // 다음 L개 칸 높이가 다른 경우
                    // 현재 높이가 1큰거라서 -1
                    if (map[row][i] - 1 != map[row][i + j]) {
                        return false;
                    }

                    isIncline[i + j] = true;
                }
            }
        }

        return true;
    }

    // 세로 확인
    public static boolean calCol(int col) {
        boolean[] isIncline = new boolean[N]; //경사면 설치 여부를 확인하는 배열

        for(int i = 0; i < N - 1; i++) {
            int diff = map[i][col] - map[i + 1][col];

            if(diff > 1 || diff < -1) return false; //높이차 1 초과하므로 false
            else if(diff == -1) { // 다음 계단이 한 계단 높다
                for(int j = 0; j < L; j++) { // 올라가는 경사로를 설치할 수 있는지 확인한다.
                    if(i - j < 0 || isIncline[i - j]) return false;
                    if(map[i][col] != map[i - j][col]) return false;
                    isIncline[i - j]  = true; //경사면 설치
                }
            }
            else if(diff == 1) { //다음 계단이 한 계단 낮다
                for(int j = 1; j <= L; j++) { //내려가는 경사로를 설치할 수 있는지 확인한다.
                    if(i + j >= N || isIncline[i + j]) return false;
                    if(map[i][col] - 1 != map[i + j][col]) return false;
                    isIncline[i + j] = true; //경사면 설치
                }
            }
        }
        return true;
    }

    public static void solution() {

        for (int i = 0; i < N; i++) {
            if (calRow(i)) cnt++;
            if (calCol(i)) cnt++;
        }

        System.out.println(cnt);
    }
}
