package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/87694
 *
 * 아이템 줍기
 * 다시풀기
 * 참고: https://loosie.tistory.com/544
 */
public class Programmers87694 {

    static class Rectangle {
        int x1, y1, x2, y2;

        public Rectangle (int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    static List<Rectangle> list = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};


    /**
     *
     * 1 1
     * 1 1
     * 이 경우 ㄷ 모양인지 ㅁ 인지 구분이 안된다.
     *
     * 때문에 map 의 범위를 2배를 하면
     * 1 1 1
     * 1 0 0
     * 1 1 1
     * 처럼 ㄷ 자 모양인지 ㅁ 모양인지 구분이 된다.
     *
     * 때문에 좌표들을 2배를 늘리고 좌표가 2배씩 늘어났기때문에
     * 마지막에 이동한 칸수는 원래 이동한 갯수보다 2배 늘어났을거라서 / 2 를 해준다.
     */
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        int[][] map = new int[102][102];

        for (int[] r : rectangle) {
            int sx = r[0] * 2;
            int sy = r[1] * 2;
            int ex = r[2] * 2;
            int ey = r[3] * 2;

            list.add(new Rectangle(sx, sy, ex, ey));

            for (int i = sy; i <= ey; i++) {
                for (int j = sx; j <= ex; j++) {
                    map[i][j] = 1;
                }
            }
        }

        answer = bfs(map, characterX*2, characterY*2, itemX*2, itemY*2);

        return answer;
    }

    public int bfs(int[][] map, int x, int y, int tx, int ty) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, 1});
        map[y][x] = -1;

        while (!q.isEmpty()){
            int[] now = q.poll();
            int curX = now[0];
            int curY = now[1];
            int move = now[2];

            if (curX == tx && curY == ty) {
                return move / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (map[ny][nx] == 1 && isBoundary(nx, ny)) {
                    map[ny][nx] = -1;
                    q.add(new int[]{nx, ny, move + 1});
                }
            }

        }

        return -1;
    }

    // 모든 사각형들에 대해서 하나의 사각형이라도 내부에 존재하게 되면 해당 좌표는 경계가 아님
    public boolean isBoundary(int x, int y) {
        for (Rectangle r : list) {
            if (r.x1 < x && x < r.x2 && r.y1 < y && y < r.y2)   return false;
        }

        return true;
    }

    /**
     *   LUX  RUX
     *     L  R
     * LUX UL UL RUX
     * L  LDX RDX R
     * LDX D  D  RDX
     */
    /*
    static String[][] shape;
    static int startX, startY, targetX, targetY, answer, total;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        shape = new String[52][52];
        startX = characterX; startY = characterY; targetX = itemX; targetY = itemY;
        answer = total = 0;

        for(int i=0; i<52; i++) Arrays.fill(shape[i],""); // ""로 초기화

        for(int[] xy : rectangle){
            int leftX = xy[0], rightX = xy[2], leftY = xy[1], rightY = xy[3];

            // 꼭지점 (왼쪽아래(LDX), 오른쪽아래(RDX), 왼쪽위(LUX), 오른쪽위(RUX))
            shape[leftX][leftY] = "LDX"; shape[rightX][leftY] = "RDX"; shape[leftX][rightY] = "LUX"; shape[rightX][rightY] = "RUX";

            for(int x=leftX+1; x<rightX; x++){// 상(U), 하(D)
                shape[x][rightY] += "U"; shape[x][leftY] += "D";
            }

            for(int y=leftY+1; y<rightY; y++){// 좌(L), 우(R)
                shape[leftX][y] += "L"; shape[rightX][y] += "R";
            }
        }

        followLine(characterX, characterY);

        return Math.min(answer, total-answer);
    }

    public void followLine(int x, int y){
        String location = shape[x][y];
        if(location.equals("RU") || location.equals("UR") || location.equals("LUX") || location.equals("U"))  x++;
        if(location.equals("LD") || location.equals("DL") || location.equals("RDX") || location.equals("D"))  x--;
        if(location.equals("LU") || location.equals("UL") || location.equals("LDX") || location.equals("L"))  y++;
        if(location.equals("RD") || location.equals("DR") || location.equals("RUX") || location.equals("R"))  y--;
        total++;
        if(x == targetX && y == targetY)    answer = total;
        if(x == startX && y == startY)      return;
        followLine(x, y);
    }
     */
}
