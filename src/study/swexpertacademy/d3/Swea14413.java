package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AYEXgKnKKg0DFARx&categoryId=AYEXgKnKKg0DFARx&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=6
 *
 * 격자판 칠하기
 * TODO 다시풀기
 */
public class Swea14413 {
    static class Coordinate {
        int row;
        int col;
        char value;

        public Coordinate(int row, int col, char value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int upDown[] = { -1, 1, 0, 0 };
        int leftRight[] = { 0, 0, -1, 1 };

        for(int test_case = 1; test_case <= T; test_case++)
        {

            sb.append("#" + test_case + " ");
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            char board[][] = new char[row][col];
            Queue<Coordinate> queue = new LinkedList<>();

            for (int i = 0; i < row; i++) {
                char[] section = br.readLine().toCharArray();
                for (int j = 0; j < col; j++) {
                    board[i][j] = section[j];
                    if (section[j] != '?') {
                        queue.add(new Coordinate(i, j, board[i][j]));
                    }
                }
            }

            boolean isValid = true;

            while (!queue.isEmpty()) {
                Coordinate curr = queue.poll();
                char nextValue = curr.value == '#' ? '.' : '#';

                for (int k = 0; k < 4; k++) {
                    int nextRow = curr.row + upDown[k];
                    int nextCol = curr.col + leftRight[k];

                    if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) {
                        continue;
                    }
                    if (board[nextRow][nextCol] != '?' && board[nextRow][nextCol] != nextValue) {
                        isValid = false;
                        break;
                    }
                    if (board[nextRow][nextCol] == '?') {
                        board[nextRow][nextCol] = nextValue;
                        queue.add(new Coordinate(nextRow, nextCol, nextValue));
                    }
                }
                if (!isValid) {
                    break;
                }
            }

            if (isValid) {
                sb.append("possible");
            } else {
                sb.append("impossible");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
