package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/21611
 *
 * 마법사 상어와 블리자드
 * 삼성전자 공채
 *
 * 다시 풀기
 *
 * 참고
 * https://namhandong.tistory.com/221
 */
public class Baekjoon21611 {

    static int N, M, mid;
    static int[][] map;
    static int[][] attack;
    static List<Integer> list;
    static int[] dr = {0, -1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, 0, -1, 1};
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        attack = new int[M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            attack[i][0] = Integer.parseInt(st.nextToken());    // d
            attack[i][1] = Integer.parseInt(st.nextToken());    // s
        }
        mid = N / 2;

        list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int d = attack[i][0];
            int s = attack[i][1];
            list.clear();
            attack(d, s);
            move();
            remove();
            map = new int[N][N];
            divide();
        }
        System.out.println(sum);
    }

    private static void attack(int d, int s) {
        for (int i = 1; i <= s; i++) {
            int nr = mid + dr[d] * i;
            int nc = mid + dc[d] * i;
            if (canMove(nr, nc)) {
                map[nr][nc] = 0;
            } else {
                break;
            }
        }
    }

    private static boolean canMove(int r, int c) {
        return (r >= 0 && c >= 0 && r < N && c < N);
    }

    private static void move() {
        int cnt = 0;
        int size = 1;
        int corner = 0;
        int dir = 3;
        int nr = mid;
        int nc = mid;
        while (true) {
            cnt++;
            nr += dr[dir];
            nc += dc[dir];

            if (nr == 0 && nc == -1) {
                break;
            }

            if (map[nr][nc] != 0) {
                list.add(map[nr][nc]);
            }

            if (cnt == size) {
                corner++;
                dir = dirChange(dir);
                cnt = 0;
            }

            if (corner == 2) {
                corner = 0;
                size++;
            }
        }
    }

    private static void remove() {
        int[][] removeList = new int[N * N][2];

        int size = list.size();
        int connectCnt = 1;
        int removeCnt = 0;
        boolean isRemove = false;
        for (int i = 0; i < size - 1; i++) {
            if (list.get(i) == list.get(i + 1)) {
                connectCnt++;
            } else {
                if (connectCnt >= 4) {
                    removeList[removeCnt][0] = i - connectCnt + 1;
                    removeList[removeCnt][1] = connectCnt;
                    removeCnt++;
                    isRemove = true;
                }
                connectCnt = 1;
            }
        }

        if (connectCnt >= 4) {
            removeList[removeCnt][0] = size - connectCnt;
            removeList[removeCnt][1] = connectCnt;
            removeCnt++;
            isRemove = true;
        }

        if (isRemove) {
            for (int k = removeCnt - 1; k >= 0; k--) {
                int start = removeList[k][0];
                int len = removeList[k][1];

                for (int i = 0; i < len; i++) {
                    sum += list.get(start);
                    list.remove(start);
                }
            }
        }

        if (isRemove) {
            remove();
        }
    }

    private static void divide() {
        ArrayList<Integer> newList = new ArrayList<>();

        int size = list.size();
        int connectCnt = 1;
        for (int i = 0; i < size - 1; i++) {
            if (list.get(i) == list.get(i + 1)) {
                connectCnt++;
            } else {
                newList.add(connectCnt);
                newList.add(list.get(i));
                connectCnt = 1;
            }
        }

        // 마지막 처리
        if (size >= 1) {
            newList.add(connectCnt);
            newList.add(list.get(size - 1));
        }

        int cnt = 0;
        size = 1;
        int corner = 0;
        int dir = 3;
        int nr = mid;
        int nc = mid;
        int time = newList.size();
        int total = 0;
        while (time-- > 0) {
            cnt++;
            nr += dr[dir];
            nc += dc[dir];

            if (nr == 0 && nc == -1) {
                break;
            }

            map[nr][nc] = newList.get(total++);

            if (cnt == size) {
                corner++;
                dir = dirChange(dir);
                cnt = 0;
            }

            if (corner == 2) {
                corner = 0;
                size++;
            }
        }
    }

    private static int dirChange(int d) {
        if (d==3) return 2;
        else if (d==2) return 4;
        else if (d==4) return 1;
        else if (d==1) return 3;
        return 0;
    }

    /*static int n, m;
    static int[][] map;
    
    // 칸 번호와 좌표를 매칭하기 위한 배열
    static int[][] xy;

    // 폭발한 1, 2, 3번 구슬
    static int score;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    // 상어 위치
    static int s_x = n / 2, s_y = n / 2;

    static List<Integer> list = new ArrayList<>();

    static void solution(int d, int s) {
        // 블리자드 마법
        magic(d, s);

        // 구슬 당겨서 채워짐
        move();

        // 4개 이상 연속된 구슬 폭발 >> n1, n2, n3 에 +
        bomb();

        // 구슬 변화 단계
        split();
        list.clear();
    }

    private static void split() {
        map = new int[n][n];

        // 쪼개진 항목들 순서대로 저장
        ArrayList<Integer> newList = new ArrayList<>();

        int size = list.size();
        int cnt = 1;
        for (int i = 0; i < size - 1; i++) {
            if (list.get(i) == list.get(i + 1)) {
                cnt++;
            } else {
                newList.add(cnt);
                newList.add(list.get(i));
                cnt = 1;
            }
        }

        // 마지막 항목 처리
        if (size >= 1) {
            newList.add(cnt);
            newList.add(list.get(size - 1));
        }

        // newList 를 map 에 반영
        cnt = 1;
        int x = s_x;
        int y = s_y;
        int d = 1;
        int time = newList.size();
        int index = 0;

        while (time-- > 0) {

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < cnt; j++) {
                    x += dx[d];
                    y += dy[d];

                    if (x == 0 && y == -1) {
                        return;
                    }

                    map[x][y] = newList.get(index++);
                }

                d = (d + 1) % 4;
            }

            cnt++;
        }
    }

    private static void bomb() {

        // 제거해야할 항목들의 시작위치, 삭제 개수
        int[][] remove = new int[n * n][2];

        int size = list.size();
        int cnt = 1;
        int r_cnt = 0;
        boolean isRemove = false;

        for (int i = 0; i < size - 1; i++) {
            if (list.get(i) == list.get(i + 1)) {
                cnt++;
            } else {
                if (cnt >= 4) {
                    // 시작 위치
                    remove[r_cnt][0] = i - cnt + 1;
                    
                    // 삭제 개수
                    remove[r_cnt][1] = cnt;
                    r_cnt++;
                    isRemove = true;
                }
                cnt = 1;
            }
        }

        // 마지막 항목 처리
        if (cnt >= 4) {
            remove[r_cnt][0] = size - cnt;
            remove[r_cnt][1] = cnt;
            r_cnt++;
            isRemove = true;
        }

        if (isRemove) {
            // 뒤에서부터 제거해야 한다. 그래야 계산이 줄어든다.
            // LinkedList 사용?
            for (int i = r_cnt - 1; i >= 0; i--) {
                int start = remove[i][0];
                int len = remove[i][1];

                // start 위치 삭제되고 List라서 당겨지므로 길이만큼 반복해서 start 위치 삭제하면 됨
                // 삭제, 당겨오는거 동시에 처리 되는것
                for (int j = 0; j < len; j++) {
                    score += list.get(start);
                    list.remove(start);
                }
            }
        }

        // 구슬들이 폭발하고 빈칸이 당겨져서 다시 4개 연속으로 중복될 수 있기에 다시 체크
        if (isRemove) {
            bomb();
        }
    }


    private static void move() {

        int x = s_x;
        int y = s_y;
        int cnt = 1;
        int d = 1;  // 방향: 좌

        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < cnt; j++) {
                    x += dx[d];
                    y += dy[d];

                    if (x == 0 && y == -1) {
                        return;
                    }

                    if (map[x][y] != 0) {
                        list.add(map[x][y]);
                    }
                }

                d = (d + 1) % 4;
            }

            cnt++;
        }
    }

    private static void magic(int d, int s) {
        for (int i = 1; i <= s; i++) {
            int x = s_x + dx[d] * i;
            int y = s_y + dy[d] * i;

            if (!isRange(x, y)) break;

            map[x][y] = 0;
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            if (d == 2) d = 1;
            else if (d == 1) d = 2;

            solution(d, s);
        }

        System.out.println(score);
    }*/
}

