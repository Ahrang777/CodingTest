package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/23290
 *
 * 마법사 상어와 복제
 * 삼성전자 공채
 */
public class Baekjoon23290 {

    static class Fish implements Cloneable {
        int x;
        int y;
        int d;

        public Fish(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        protected Fish clone() throws CloneNotSupportedException { // 클래스 복제
            return (Fish) super.clone();
        }

    }

    static int M, S;
    static int fdx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 }; // 물고기 이동
    static int fdy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

    static int sdx[] = { 0, -1, 0, 1, 0 }; // 상어 이동
    static int sdy[] = { 0, 0, -1, 0, 1 };

    static ArrayList<Fish> map[][] = new ArrayList[4][4];
    static ArrayList<Fish> fishes = new ArrayList<>();
    static int smell[][] = new int[4][4]; // 물고기 냄새
    static int sx, sy; // 상어 위치

    static int result[] = new int[3]; // 상어 이동 방향(임시)
    static int sharkDir[] = new int[3]; // 상어 이동 방향(최종)
    static int max = Integer.MIN_VALUE;

    static int res; // 격자에 있는 물고기 수(정답)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 물고기 수
        S = Integer.parseInt(st.nextToken()); // 마법 시행 횟수

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                map[i][j] = new ArrayList<Fish>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            fishes.add(new Fish(r, c, d));

        }

        // 상어 위치 입력
        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;

        simulation();
        System.out.println(res);

    }

    private static void simulation() throws CloneNotSupportedException {
        for (int time = 0; time < S; time++) {
            // 1. 물고기 복제 마법
            ArrayList<Fish> copy = copy(fishes);

            // 2. 물고기 이동
            for (int i = 0; i < fishes.size(); i++) {
                Fish cur = fishes.get(i);
                cur = moveFish(cur);
            }
            // 2-1. 물고기 이동한 후 map에 배치
            setMap();

            // 3. 상어 연속 이동(백트래킹)
            max = Integer.MIN_VALUE; // 해당 방향으로 갈 때 먹는 물고기 수 초기화
            findPath(0); // 가장 많이 먹고, 사전순으로 적은 방향 찾기 by 중복순열
            moveShark();

            // 4. 물고기 냄새 격자에서 사라짐
            smellRemove();

            // 5. 복제마법 map에 처리
            setCopyMap(copy);

            // 6. map에 있는 내용 list에 담기(물고기 개수도 세기)
            reset();

        }
    }

    private static void reset() { // 다음 턴을 위해 map의 정보를 list에 저장하고, map 리셋
        fishes.clear(); // 기존 list 클리어
        int cnt = 0; // 물고기 개수
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (Fish fish : map[i][j]) {
                    fishes.add(fish);
                    cnt++;
                }
                
                map[i][j].clear();
            }
        }
        res = cnt; // 정답 갱신
    }

    private static void setCopyMap(ArrayList<Fish> copy) { // 1번에서 시전한 복제마법 map에 적용시키기
        for (Fish fish : copy) {
            map[fish.x][fish.y].add(fish);
        }
    }

    private static void smellRemove() { // 물고기 냄새 지우기
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smell[i][j] > 0) // 냄새가 남아있을 경우
                    smell[i][j]--;
            }
        }
    }

    private static void moveShark() { // sharkBacktracking에서 얻은 최종 방향으로 이동하며 물고기 개수 줄이기 + 물고기 냄새 남기기

        for (int i = 0; i < 3; i++) {
            sx += sdx[sharkDir[i]];
            sy += sdy[sharkDir[i]];
            if (map[sx][sy].size() > 0) {
                smell[sx][sy] = 3;
                map[sx][sy].clear();
            }

        }

    }

    // dfs + 백트래킹
    private static void findPath(int depth) { // 상어 이동방향 정하기 by 중복순열
        if (depth == 3) {
            int eat = checkEat(); // 해당 방향으로 상어가 이동했을 때 물고기 수
            if(eat==-1) // 못 가는 지역
                return;
            if (max < eat) {
                max = eat;
                for (int i = 0; i < 3; i++) {
                    sharkDir[i] = result[i];
                }
            }
            return;
        }

        for (int i = 1; i <= 4; i++) { // 1~4까지 중복순열
            result[depth] = i;
            findPath(depth + 1);
        }

    }

    private static int checkEat() { // sharkBacktracking에서 정한 방향으로 갔을 때 먹는 물고기 수
        boolean visited[][] = new boolean[4][4];
        int cnt = 0; // 물고기 수
        int nx = sx, ny = sy;
        for (int i = 0; i < 3; i++) {
            nx += sdx[result[i]];
            ny += sdy[result[i]];

            if (!isRange(nx, ny)) {
                cnt = -1; // 범위 벗어남
                break;
            }
            if (visited[nx][ny]) // 이미 방문한 지역이면 물고기 수 책정 x
                continue;
            cnt += map[nx][ny].size();
            visited[nx][ny] = true;
        }
        return cnt;

    }

    private static void setMap() {
        for (Fish fish : fishes) {
            map[fish.x][fish.y].add(fish);
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }

    private static Fish moveFish(Fish fish) {
        int nx = 0, ny = 0;
        int cnt = 0;
        while (true) {
            nx = fish.x + fdx[fish.d];
            ny = fish.y + fdy[fish.d];

            // 격자 안이고, 물고기 냄새 x이고, 상어 위치 아니면
            if (isRange(nx, ny) && smell[nx][ny] == 0 && !(nx == sx && ny == sy))
                break;

            fish.d = fish.d - 1; // 반시계 45도 회전
            if (fish.d <= 0)
                fish.d = 8;
            cnt++;
            if (cnt == 8) // 8바퀴 다 돌았을 경우 -> 갈 곳 x
                break;
        }
        if (cnt < 8) {
            fish.x = nx;
            fish.y = ny;
        }

        return fish;
    }

    private static ArrayList<Fish> copy(ArrayList<Fish> list) throws CloneNotSupportedException { // 리스트 복사
        ArrayList<Fish> tmp = new ArrayList<Fish>();

        for (Fish fish : list) {
            //tmp.add(fish.clone());
            tmp.add(new Fish(fish.x, fish.y, fish.d));
        }

        return tmp;
    }




    /*static class Fish {
        int x, y, d;

        public Fish() {

        }

        public Fish(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int m, s, s_x, s_y;

    // 0보다 큰 숫자는 해당 칸의 물고기 수 
    static int[][] map = new int[4][4];
    static int[][] smell = new int[4][4];

    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙ >> 1 ~ 8
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    // 상어 이동 1 ~ 4  ↑, ←, ↓, →
    static int[] s_dx = {0, -1, 0, 1, 0};
    static int[] s_dy = {0, 0, -1, 0, 1};
    
    static final int BLANK = 0;
    static final int SMELL = 3;

    static List<Fish> fishes = new ArrayList<>();
    static List<Fish> copiedFishes = new ArrayList<>();

    static int max = Integer.MIN_VALUE;
    
    // 상어 이동방향 임시
    static int[] output;
    
    // 상어 이동방향 최종
    static int[] sharkDir;

    static void solution() {
        for (int i = 0; i < s; i++) {
            // 복제 마법 시작
            startMagic();

            // 물고기 한 칸 이동
            moveFish();

            // 상어 연속 3칸 이동
            max = Integer.MIN_VALUE;
            output = new int[3];
            sharkDir = new int[3];
            findSharkPath(0);
            moveShark();

            // 2번 전 냄새 제거 >> cnt == 2 인 칸 BLANK 로 만들기
            removeSmell();

            // 복제 마법 완료
            endMagic();
        }

        // 물고기 수 구하기
        System.out.println(getAllFish());
    }

    static int getAllFish() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] > BLANK) {
                    cnt += map[i][j];
                }
            }
        }
        return cnt;
    }

    static void endMagic() {
        for (Fish f : copiedFishes) {
            fishes.add(f);
            map[f.x][f.y]++;
        }
        copiedFishes.clear();
    }

    static void removeSmell() {
        // SMELL = 3
        // 이전 2단계에 대해 지나야 제거되는데 이번 단계에서 지우는 과정이 있기에 2 + 1
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smell[i][j] > BLANK) {
                    smell[i][j]--;
                }
            }
        }
    }

    static void moveShark() {
        ArrayList<Fish> remove = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            s_x += s_dx[sharkDir[i]];
            s_y += s_dy[sharkDir[i]];
            
            // 물고기 있는 경우
            if (map[s_x][s_y] > BLANK) {
                map[s_x][s_y] = BLANK;
                smell[s_x][s_y] = SMELL;
                
                // 제거될 물고기
                for (Fish f : fishes) {
                    if (f.x == s_x && f.y == s_y) {
                        remove.add(f);
                    }
                }
            }
        }

        fishes.removeAll(remove);
    }
    
    // dfs + 백트래킹
    static void findSharkPath(int depth) {
        if (depth == 3) {
            int eat = checkEat();
            
            // 경계 넘은 경우
            if (eat == -1) return;

            // 어차피 초반에 나온게 사전상 앞부분 1,1,1 / 1,1,2 이런식으로 확인하므로
            if (max < eat) {
                max = eat;
                for (int i = 0; i < 3; i++) {
                    sharkDir[i] = output[i];
                }
            }
            return;
        }

        // 상어가 이동할 방향 저장
        for (int i = 1; i <= 4; i++) {
            output[depth] = i;
            findSharkPath(depth + 1);
        }
    }

    // 먹은 물고기수
    static int checkEat() {
        boolean[][] visited = new boolean[4][4];
        int cnt = 0;
        int nx = s_x;
        int ny = s_y;

        for (int i = 0; i < 3; i++) {
            nx += s_dx[output[i]];
            ny += s_dy[output[i]];
            
            if (!isRange(nx, ny)) return -1;

            // 상어가 연속 3번 움직일때 방문했던 칸을 또 방문할 수 있다.
            // 중복으로 카운트 되지 않게 방문했던 칸은 카운트 하지 않는다.
            if (visited[nx][ny]) continue;

            cnt += map[nx][ny];
            visited[nx][ny] = true;
        }

        return cnt;
    }

    static void moveFish() {
        // List 에 있는 물고기들 이동
        // map에서 기존 위치 -1, 이동한 위치 +1
        for (Fish f : fishes) {
            int d = f.d;
            int nx = 0, ny = 0, cnt = 0;

            while (true) {
                nx = f.x + dx[d];
                ny = f.y + dy[d];

                // 한바퀴 돌아도 갈곳 없는 경우
                if (cnt++ >= 8) break;

                if (!isRange(nx, ny) || (smell[nx][ny] != BLANK) || (nx == s_x && ny == s_y)) {
                    continue;
                }
                d = rotate(d);
            }

            // 이동 가능한 칸 없으면 이동X
            if (cnt >= 8) continue;

            // 이동
            map[f.x][f.y]--;
            map[nx][ny]++;
            f.x = nx;
            f.y = ny;
            f.d = d;
        }
    }
    
    // 반시계 45도 회전
    static int rotate(int d) {
        return d = (d == 1 ? 8 : d - 1);
    }
    
    static boolean isRange(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }
    
    static void startMagic() {
        // 원본 복사해두기
        for (Fish f : fishes) {
            copiedFishes.add(new Fish(f.x, f.y, f.d));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            Fish fish = new Fish();
            fish.x = Integer.parseInt(st.nextToken()) - 1;
            fish.y = Integer.parseInt(st.nextToken()) - 1;
            fish.d = Integer.parseInt(st.nextToken());

            fishes.add(fish);
            map[fish.x][fish.y]++;
        }

        st = new StringTokenizer(br.readLine());
        s_x = Integer.parseInt(st.nextToken()) - 1;
        s_y = Integer.parseInt(st.nextToken()) - 1;

        solution();
    }*/
}
