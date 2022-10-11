package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/17143
 * 
 * 낚시왕
 * 삼성전자 공채
 * 
 * 다시 풀기 (2)
 */
public class Baekjoon17143 {

    static class Shark {

        // 위치: (x,y), 속력: s, 방향: d, 크기: z
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static int R, C, M;

    public static long result = 0;

    public static Shark[][] map;

    // 위, 왼, 아래, 오른
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine());

        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        map = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(stk.nextToken()) - 1;
            int c = Integer.parseInt(stk.nextToken()) - 1;
            int s = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken()) - 1;
            int z = Integer.parseInt(stk.nextToken());

            if (d == 1) {
                d = 2;
            } else if (d == 3) {
                d = 1;
            } else if (d == 2) {
                d = 3;
            }

            map[r][c] = new Shark(r, c, s, d, z);
        }
        
        solution();
    }
    
    public static void solution() {
        // 낚시왕 이동
        for (int col = 0; col < C; col++) {
            // 상어 잡기
            catchShark(col);

            // 상어 이동
            moveShark();
        }

        System.out.println(result);
    }

    // 상어 잡기
    public static void catchShark(int col) {
        for (int row = 0; row < R; row++) {
            // 상어 있는 경우 
            if (map[row][col] != null) {
                // 가장 가까운 상어 잡기
                result += map[row][col].z;
                map[row][col] = null;
                break;
            }
        }
    }

    // 상어 이동
    public static void moveShark() {
        ArrayList<Shark> sharks = new ArrayList<>();
        
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (map[row][col] != null) {
                    sharks.add(map[row][col]);

                    // 상어 이동하는 경우 반영하려고 리스트에 정보 저장하고 null 처리
                    // null 처리 안하면 한번에 모든 상어 이동해야 하는데 아직 이동 반영 안된 상어와 크기 비교하게됨
                    map[row][col] = null;
                }
            }
        }

        for (Shark now : sharks) {
            int speed = now.s;

            // 시간 초과 안되게 연산 줄임
            // 아래 speed 연산의 경우 현재 위치에서 방향 그대로 유지되고 연산 결과 speed 만큼만 이동처리하면 됨
            // 상, 하
            if (now.d == 0 || now.d == 2) {
                speed %= (R - 1) * 2;
            } 
            // 좌, 우
            else if (now.d == 1 || now.d == 3) {
                speed %= (C - 1) * 2;
            }

            for (int s = 0; s < speed; s++) {
                int nr = now.r + dx[now.d];
                int nc = now.c + dy[now.d];

                // 다음으로 이동할 위치가 범위를 벗어나는 경우
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {

                    // 다음 이동할 위치가 범위가 벗어났으니
                    // 반대방향으로 가야한다
                    // 다음 이동할 칸을 반대방향 칸으로 변경
                    nr = now.r - dx[now.d];
                    nc = now.c - dy[now.d];
                    
                    // 방향 반대로 변경
                    now.d = (now.d + 2) % 4;
                }

                // 위치 적용
                now.r = nr;
                now.c = nc;
            }

            // 상어가 이동을 마친후 두마리 이상이 있을 경우
            if (map[now.r][now.c] != null) {
                // 이동한 위치의 기존 상어보다 현재 상어가 큰 경우
                if (map[now.r][now.c].z < now.z) {
                    map[now.r][now.c] = now;
                }
            } else {    // 이동할 위치가 빈 칸인 경우
                map[now.r][now.c] = now;
            }
        }
    }
}
