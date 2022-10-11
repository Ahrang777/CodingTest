package study.baekjoon;

import java.io.*;
import java.util.*;

/*
입력
3
2 2 2
4 4 4
8 8 8

출력
16
 */

/**
 * https://www.acmicpc.net/problem/12100
 *
 * 2048 (Easy)
 * 삼성전자 공채
 *
 * dfs, 백트래킹
 * 
 * 다시 풀기 (2)
 */
public class Baekjoon12100 {

    public static int n;

    // U, L, D, R
    /*public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};*/
    public static final int BLANK = 0;
    public static int maxValue;

    public static void solution(int cnt, int[][] board) {
        if (cnt == 5) {

            // 최대 블록 숫자 출력
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    maxValue = Math.max(maxValue, board[i][j]);
                }
            }

            return;
        }

        // 블록 이동
        for (int i = 0; i < 4; i++) {

            int[][] copiedBoard = copyBoard(board);
            move(copiedBoard);
            solution(cnt + 1, copiedBoard);
            board = rotate(board);
        }
    }

    // 위로 이동
    public static void move(int[][] copiedBoard) {
        for (int c = 0; c < n; c++) {
            // 맨 위에서부터 아래로 한칸씩 >> 타겟이 이동할 위치
            int index = 0;

            // 블록 충돌을 위한 값, 이전 블록의 값 
            // 0이면 없던거고 0이 아니면 이전에 충돌 안한 움직인 블록이 있는것
            // 따라서 이전에 움직인 블록과 다르면 그냥 이동만, 같으면 충돌처리
            int block = 0;  

            for (int r = 0; r < n; r++) {
                if (copiedBoard[r][c] != BLANK) {

                    // 충돌처리
                    if (block == copiedBoard[r][c]) {
                        copiedBoard[index - 1][c] = block * 2;
                        block = 0;
                        copiedBoard[r][c] = BLANK;
                    } else {    // 단순 이동처리
                        block = copiedBoard[r][c];
                        copiedBoard[r][c] = BLANK;
                        copiedBoard[index][c] = block;
                        index++;
                    }
                }
            }
        }
    }

    public static int[][] rotate(int[][] board) {
        int[][] temp = new int[n][n];
        for (int r = 0; r < n; r++) {

            for (int c = 0; c < n; c++)
                temp[c][n - 1 - r] = board[r][c];
        }
        return temp;
    }

    /*public static void solution(int cnt, int[][] board) {
        if (cnt == 5) {
            
            // 최대 블록 숫자 출력
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    maxValue = Math.max(maxValue, board[i][j]);
                }
            }

            return;
        }

        // 블록 이동
        for (int i = 0; i < 4; i++) {

            int[][] copiedBoard = copyBoard(board);
            move(i, copiedBoard);
            solution(cnt + 1, copiedBoard);
        }
    }

    public static void move(int dir, int[][] copiedBoard) {
        switch (dir) {
            //위로 몰기
            case 0:
                for(int i = 0; i < n; i++) {
                    int index = 0;  // 값을 넣을 위치
                    int block = 0;  // 최근 블록
                    for(int j = 0; j < n; j++) {
                        if(copiedBoard[j][i] != BLANK) {
                            if(block == copiedBoard[j][i]) {
                                copiedBoard[index - 1][i] = block * 2;
                                block = 0;
                                copiedBoard[j][i] = 0;
                            }
                            else {
                                block = copiedBoard[j][i];
                                copiedBoard[j][i] = 0;
                                copiedBoard[index][i] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            //아래로 몰기
            case 1:
                for(int i = 0; i < n; i++) {
                    int index = n - 1;
                    int block = 0;
                    for(int j = n - 1; j >= 0; j--) {
                        if(copiedBoard[j][i] != 0) {
                            if(block == copiedBoard[j][i]) {
                                copiedBoard[index + 1][i] = block * 2;
                                block = 0;
                                copiedBoard[j][i] = 0;
                            }
                            else {
                                block = copiedBoard[j][i];
                                copiedBoard[j][i] = 0;
                                copiedBoard[index][i] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
            //왼쪽으로 몰기
            case 2:
                for(int i = 0; i < n; i++) {
                    int index = 0;
                    int block = 0;
                    for(int j = 0; j < n; j++) {
                        if(copiedBoard[i][j] != 0) {
                            if(block == copiedBoard[i][j]) {
                                copiedBoard[i][index - 1] = block * 2;
                                block = 0;
                                copiedBoard[i][j] = 0;
                            }
                            else {
                                block = copiedBoard[i][j];
                                copiedBoard[i][j] = 0;
                                copiedBoard[i][index] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            //오른쪽으로 몰기
            case 3:
                for(int i = 0; i < n; i++) {
                    int index = n - 1;
                    int block = 0;
                    for(int j = n - 1; j >= 0; j--) {
                        if(copiedBoard[i][j] != 0) {
                            if(block == copiedBoard[i][j]) {
                                copiedBoard[i][index + 1] = block * 2;
                                block = 0;
                                copiedBoard[i][j] = 0;
                            }
                            else {
                                block = copiedBoard[i][j];
                                copiedBoard[i][j] = 0;
                                copiedBoard[i][index] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
        }
    }*/

    public static int[][] copyBoard(int[][] board) {
        int[][] copiedBoard = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copiedBoard[i][j] = board[i][j];
            }
        }

        return copiedBoard;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        n = Integer.parseInt(bf.readLine());
        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        solution(0, board);
        System.out.println(maxValue);
    }
}
