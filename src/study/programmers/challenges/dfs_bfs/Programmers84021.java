package study.programmers.challenges.dfs_bfs;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/84021
 *
 * 퍼즐 조각 채우기
 */
public class Programmers84021 {

    /*
    int answer = 0, n = 0;
    int[][] wh = {{1, 0, -1, 0}, {0, 1, 0, -1}}, board;
    StringBuilder sb = new StringBuilder();
    List<String> empty = new ArrayList<>();
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        board = game_board;
        // 빈 칸을 문자열로 만들어 저장
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 0) {
                    sb = new StringBuilder();
                    dfs(i, j, 1);
                    empty.add(sb.toString());
                }
            }
        }
        // dfs를 쓰기 위해 table의 값들을 -1
        for(var t : table) {
            for(int i = 0; i < n; i++) {
                t[i]--;
            }
        }
        board = table;

        // table을 90도씩 돌리면서 확인해본다.
        for(int r = 0; r < 4; r++) {
            rotate();
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(board[i][j] != -1) {
                        sb = new StringBuilder();
                        dfs(i, j, board[i][j] ^ 1);
                        // 존재한다면 퍼즐 조각 만큼 answer에 더해줌
                        int idx = empty.indexOf(sb.toString());
                        if(idx != -1) {
                            empty.remove(idx);
                            answer += sb.toString().replaceAll("[()]", "").length() + 1;
                            dfs(i, j, -1);
                        }
                    }
                }
            }
        }
        return answer;
    }

    void rotate() {
        int[][] rotate = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotate[i][j] = board[n - j - 1][i];
            }
        }
        board = rotate;
    }

    void dfs(int a, int b, int v) {
        board[a][b] = v;
        sb.append("(");
        for(int i = 0; i < 4; i++) {
            int x = a + wh[0][i], y = b + wh[1][i];
            if(0 <= x && 0 <= y && x < n && y < n && board[x][y] != board[a][b] && board[x][y] != -1) {
                sb.append(i);
                dfs(x, y, v);
            }
        }
        sb.append(")");
    }
     */

    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point other) {
            if (this.x != other.x) {
                return this.x - other.x;
            }

            return this.y - other.y;
        }
    }

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;

        int size = table.length;
        boolean[][] gVisited = new boolean[size][size];
        boolean[][] tVisited = new boolean[size][size];

        List<List<Point>> gList = new ArrayList<>();
        List<List<Point>> tList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!gVisited[i][j] && game_board[i][j] == 0) {
                    bfs(i, j, gVisited, game_board, 0, gList);
                }

                if (!tVisited[i][j] && table[i][j] == 1) {
                    bfs(i, j, tVisited, table, 1, tList);
                }
            }
        }

        answer = find(gList, tList);
        return answer;
    }

    public int find(List<List<Point>> g, List<List<Point>> t) {
        int cnt = 0;

        int gSize = g.size();
        int tSize = t.size();

        // g 항목들에 블록 들어갔는지 여부
        boolean[] visited = new boolean[gSize];

        for (int i = 0; i < tSize; i++) {
            List<Point> tp = t.get(i);
            for (int j = 0; j < gSize; j++) {
                List<Point> gp = g.get(j);

                if (tp.size() == gp.size() && !visited[j]) {

                    if (isRotate(gp, tp)) {
                        visited[j] = true;
                        cnt += tp.size();
                        break;
                    }

                }
            }
        }

        return cnt;
    }

    public boolean isRotate(List<Point> g, List<Point> t) {
        boolean isCorrect = false;

        Collections.sort(g);

        for (int i = 0; i < 4; i++) {
            Collections.sort(t);
            int x = t.get(0).x;
            int y = t.get(0).y;

            // 다시 0, 0 기준으로 변경
            for (Point p : t) {
                p.x -= x;
                p.y -= y;
            }

            boolean isCorrectPoint = true;
            for (int j = 0; j < g.size(); j++) {
                Point gp = g.get(j);
                Point tp = t.get(j);

                if (gp.x != tp.x || gp.y != tp.y) {
                    isCorrectPoint = false;
                    break;
                }
            }

            if (isCorrectPoint) {
                isCorrect = true;
                break;
            } else { // 90도 회전
                for (Point tp : t) {
                    int tmp = tp.x;
                    tp.x = -tp.y;
                    tp.y = tmp;
                }
            }
        }

        return isCorrect;
    }

    public void bfs(int x, int y, boolean[][] visited, int[][] graph, int emptyOrBlock, List<List<Point>> list) {
        Queue<Point> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new Point(x, y));
        List<Point> sub = new ArrayList<>();
        sub.add(new Point(x-x, y-y));

        while(!q.isEmpty()) {
            Point now = q.poll();
            int curX = now.x;
            int curY = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx < 0 || nx >= graph.length || ny < 0 || ny >= graph.length)   continue;

                if (!visited[nx][ny] && graph[nx][ny] == emptyOrBlock) {
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                    sub.add(new Point(nx - x, ny - y));
                }
            }
        }

        list.add(sub);
    }

    /*
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;

        boolean[][] visitedTable = new boolean[table.length][table.length];
        boolean[][] visitedBoard = new boolean[game_board.length][game_board.length];
        List<List<int[]>> boardList = new ArrayList<>();
        List<List<int[]>> tableList = new ArrayList<>();

        for (int i = 0; i < table.length; i++){
            for (int j = 0; j < table.length; j++){

                if (table[i][j] == 1 && !visitedTable[i][j]){
                    bfs(i, j, visitedTable, table, 1, tableList);
                }

                if (game_board[i][j] == 0 && !visitedBoard[i][j]){
                    bfs(i, j, visitedBoard, game_board, 0, boardList);
                }
            }
        }

        answer = findBlock(boardList, tableList);

        return answer;
    }

    public int findBlock(List<List<int[]>> board, List<List<int[]>> table){
        int size = 0;
        int tableLen = table.size();
        int boardLen = board.size();
        boolean[] visitedBoard = new boolean[boardLen];
        for (int i = 0; i < tableLen; i++){
            List<int[]> tablePoints = table.get(i);
            for (int j = 0; j < boardLen; j++){
                List<int[]> boardPoints = board.get(j);

                if (tablePoints.size() == boardPoints.size() && !visitedBoard[j]){ //좌표 개수 같을때
                    if(isRotate(boardPoints, tablePoints)){ //회전해서 맞는지 확인
                        size += tablePoints.size();
                        visitedBoard[j] = true;
                        break;
                    }
                }
            }

        }

        return size;
    }

    public boolean isRotate(List<int[]> board, List<int[]> table){
        boolean isCollect = false;

        board.sort((o1, o2) -> {
            return o1[0] > o2[0]?1 : o1[0] < o2[0]?-1 : Integer.compare(o1[1], o2[1]);
        });

        for (int i = 0; i < 4; i++){ //table퍼즐 0, 90, 180, 270도 회전

            table.sort((o1, o2) -> {
                return o1[0] > o2[0]?1 : o1[0] < o2[0]?-1 : Integer.compare(o1[1], o2[1]);
            });
            int nearZeroX = table.get(0)[0];
            int nearZeroY = table.get(0)[1];

            for (int j = 0; j < table.size(); j++){
                table.get(j)[0] -= nearZeroX;
                table.get(j)[1] -= nearZeroY;
            }


            boolean isCollectPoint = true;
            for (int j = 0; j < board.size(); j++){ //좌표 비교
                int[] boardPoint = board.get(j);
                int[] tablePoint = table.get(j);

                if (boardPoint[0] != tablePoint[0] || boardPoint[1] != tablePoint[1]){
                    isCollectPoint = false;
                    break;
                }
            }

            if (isCollectPoint){
                isCollect = true;
                break;
            } else{ //90도 회전 : x,y -> y, -x
                for(int j = 0; j < table.size(); j++){
                    int temp = table.get(j)[0];
                    table.get(j)[0] = table.get(j)[1];
                    table.get(j)[1] = -temp;
                }
            }
        }
        return isCollect;

    }



    public void bfs(int currentX, int currentY, boolean[][] visited, int[][] graph,
                      int blockOrEmpty, List<List<int[]>> list){

        Queue<int[]> queue = new LinkedList<>();
        visited[currentX][currentY] = true;
        queue.add(new int[]{currentX, currentY});
        List<int[]> subList = new ArrayList<>();
        subList.add(new int[]{currentX-currentX, currentY-currentY});

        while (!queue.isEmpty()){
            int[] pop = queue.remove();
            int popX = pop[0];
            int popY = pop[1];

            for (int i = 0; i < 4; i++){
                int nextX = popX + dx[i];
                int nextY = popY + dy[i];
                if (nextX < 0 || nextX >= graph.length || nextY < 0 || nextY >= graph.length){
                    continue;
                }
                if (!visited[nextX][nextY] && graph[nextX][nextY] == blockOrEmpty){

                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                    subList.add(new int[]{nextX-currentX, nextY-currentY});
                }
            }
        }
        list.add(subList);
    }
     */
}
