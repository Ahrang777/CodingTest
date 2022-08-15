package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/5373
 *
 * 큐빙
 * 삼성전자 공채
 * 
 * 경우의수 하나하나 작성하기보단 규칙으로 해결하는 방법 찾기
 * https://aahc.tistory.com/7
 * 이해 안됨,,,
 */
public class Baekjoon5373 {

    // test case수, 큐브 돌린 횟수
    static int testCase, n;
    static String[] arr;
    
    // B, L, U, R, F, D
    static char[][][] cube = new char[6][3][3];
    static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
    static char[] colors = {'w', 'y', 'r', 'o', 'g', 'b'};
    
    static void init() {
        for (int f = 0; f < 6; f++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cube[f][i][j] = colors[f];
                }
            }
        }
    }
    
    static void print() {
        for (int i = 0; i < 3; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 3; j++) {
//                sb.append(cube[U][i][j]);
                sb.append(cube[U][j][2-i]);
            }
            System.out.println(sb.toString());
        }
    }

    // 기준면, 돌려야할 면의 위, 왼쪽, 아래, 오른쪽에 접한 면, 회전방향
    static void alter(int f, int u, int l, int d, int r, boolean clk) {

        char[][] tmp = new char[3][3];
        char[] tmp2 = new char[3];

        if (clk) {
            for (int i=0; i<3; ++i)
                for (int j=0; j<3; ++j) {
                    tmp[i][j] = cube[f][2-j][i];
                }
            for (int i=0; i<3; ++i)
                tmp2[i] = cube[u][i][0];
            for (int i=0; i<3; ++i)
                cube[u][i][0] = cube[l][0][2-i];
            for (int i=0; i<3; ++i)
                cube[l][0][2-i] = cube[d][2][i];
            for (int i=0; i<3; ++i)
                cube[d][2][i] = cube[r][2-i][2];
            for (int i=0; i<3; ++i)
                cube[r][2-i][2] = tmp2[i];
        }
        else {
            for (int i=0; i<3; ++i)
                for (int j=0; j<3; ++j) {
                    tmp[i][j] = cube[f][j][2-i];
                }
            for (int i=0; i<3; ++i)
                tmp2[i] = cube[u][i][0];
            for (int i=0; i<3; ++i)
                cube[u][i][0] = cube[r][2-i][2];
            for (int i=0; i<3; ++i)
                cube[r][2-i][2] = cube[d][2][i];
            for (int i=0; i<3; ++i)
                cube[d][2][i] = cube[l][0][2-i];
            for (int i=0; i<3; ++i)
                cube[l][0][2-i] = tmp2[i];
        }
        cube[f] = tmp;

        /*// 기준면 회전을 위한 임시 배열
        char[][] tmp = new char[3][3];

        // 위, 왼, 아래, 오른쪽 면 회전을 위한 임시 배열
        char[] tmp2 = new char[3];

        // 시계
        if (clk) {
            // 기준면 회전
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tmp[i][j] = cube[f][2 - j][i];
                }
            }

            for (int i = 0; i < 3; i++) {
                tmp2[i] = cube[u][i][0];
            }
        } else {    // 반시계
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tmp[i][j] = cube[f][j][2 - i];
                }
            }


        }*/
    }

    static void rotate(String str) {
        // true == 시계방향, false == 반시계 방향
        boolean clk = str.charAt(1) == '+';
        
        // 기준 면
        switch (str.charAt(0)) {
            case 'U': alter(U, L, F, R, B, clk); break;
            case 'D': alter(D, B, R, F, L, clk); break;
            case 'F': alter(F, U, L, D, R, clk); break;
            case 'B': alter(B, R, D, L, U, clk); break;
            case 'L': alter(L, F, U, B, D, clk); break;
            case 'R': alter(R, D, B, U, F, clk); break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            n = Integer.parseInt(br.readLine());
            
            init();
            
            // 0: 면, 1: 방향
            arr = new String[n];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                rotate(st.nextToken());
            }

            print();
        }

    }
}
