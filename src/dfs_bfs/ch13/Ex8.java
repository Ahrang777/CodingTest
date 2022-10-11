package dfs_bfs.ch13;

import java.util.*;

/*
출력
7
 */

/**
 * https://programmers.co.kr/learn/courses/30/lessons/60063
 */
public class Ex8 {

    static class Node{
        private int x1;
        private int y1;
        private int x2;
        private int y2;
        private int distance;

        public Node(int x1, int y1, int x2, int y2, int distance) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.distance = distance;
        }

        public int getX1() {
            return x1;
        }

        public int getY1() {
            return y1;
        }

        public int getX2() {
            return x2;
        }

        public int getY2() {
            return y2;
        }

        public int getDistance() {
            return distance;
        }
    }

    public static final int BLANK = 0;
    public static final int WALL = 1;

    public static ArrayList<Node> getNextPos(Node pos, int[][] board) {
        // 반환 결과(이동 가능한 위치들)
        ArrayList<Node> nextPos = new ArrayList<Node>();

        // (상, 하, 좌, 우)로 이동하는 경우에 대해서 처리
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int x1 = pos.getX1();
        int y1 = pos.getY1();
        int x2 = pos.getX2();
        int y2 = pos.getY2();
        int time = pos.getDistance();

        // 한 칸 이동하는 경우
        for (int i = 0; i < 4; i++) {
            int nx1 = x1 + dx[i];
            int ny1 = y1 + dy[i];
            int nx2 = x2 + dx[i];
            int ny2 = y2 + dy[i];

            if (board[nx1][ny1] != WALL && board[nx2][ny2] != WALL) {
                nextPos.add(new Node(nx1, ny1, nx2, ny2, time + 1));
            }
        }

        // 현재 로봇이 가로로 놓여 있는 경우
        int[] hor = {-1, 1};
        if (x1 == x2) {
            for (int i = 0; i < hor.length; i++) {
                if (board[x1 + hor[i]][y1] == BLANK && board[x2 + hor[i]][y2] == BLANK) {
                    nextPos.add(new Node(x1, y1, x1 + hor[i], y1, time + 1));
                    nextPos.add(new Node(x2, y2, x2 + hor[i], y2, time + 1));
                }
            }
        }

        // 현재 로봇이 세로로 놓여 있는 경우
        int[] ver = {-1, 1};
        if (y1 == y2) {
            for (int i = 0; i < ver.length; i++) {
                if (board[x1][y1 + ver[i]] == BLANK && board[x2][y2 + ver[i]] == BLANK) {
                    nextPos.add(new Node(x1, y1, x1, y1 + ver[i], time + 1));
                    nextPos.add(new Node(x2, y2, x2, y2 + ver[i], time + 1));
                }
            }
        }

        return nextPos;

        /*for (int i = 0; i < 4; i++) {
            int nx1 = pos.getX1() + dx[i];
            int ny1 = pos.getY1() + dy[i];
            int nx2 = pos.getX2() + dx[i];
            int ny2 = pos.getY2() + dy[i];
            int nextDistance = pos.getDistance() + 1;

            // 이동하고자 하는 두 칸이 모두 비어 있다면
            if (board[nx1][ny1] == 0 && board[nx2][ny2] == 0) {
                nextPos.add(new Node(nx1, ny1, nx2, ny2, nextDistance));
            }
        }

        // 현재 로봇이 가로로 놓여 있는 경우
        int[] hor = {-1, 1};
        if (pos.getX1() == pos.getX2()) {
            for (int i = 0; i < 2; i++) { // 위쪽으로 회전하거나, 아래쪽으로 회전
                // 위쪽 혹은 아래쪽 두 칸이 모두 비어 있다면
                if (board[pos.getX1() + hor[i]][pos.getY1()] == 0 && board[pos.getX2() + hor[i]][pos.getY2()] == 0) {
                    // 각 지점을 축으로 도는 경우 각각 추가 >> x1,y1 을 축으로 도는 경우, x2, y2를 축으로 도는 경우 >> 2가지
                    nextPos.add(new Node(pos.getX1(), pos.getY1(), pos.getX1() + hor[i], pos.getY1(), pos.getDistance() + 1));
                    nextPos.add(new Node(pos.getX2(), pos.getY2(), pos.getX2() + hor[i], pos.getY2(), pos.getDistance() + 1));
                }
            }
        }

        // 현재 로봇이 세로로 놓여 있는 경우
        int[] ver = {-1, 1};
        if (pos.getY1() == pos.getY2()) {
            for (int i = 0; i < 2; i++) { // 왼쪽으로 회전하거나, 오른쪽으로 회전
                // 왼쪽 혹은 오른쪽 두 칸이 모두 비어 있다면
                if (board[pos.getX1()][pos.getY1() + ver[i]] == 0 && board[pos.getX2()][pos.getY2() + ver[i]] == 0) {
                    nextPos.add(new Node(pos.getX1(), pos.getY1(), pos.getX1(), pos.getY1() + ver[i], pos.getDistance() + 1));
                    nextPos.add(new Node(pos.getX2(), pos.getY2(), pos.getX2(), pos.getY2() + ver[i], pos.getDistance() + 1));
                }
            }
        }

        return nextPos;*/
    }

    public static int solution(int[][] board) {
        // 맵 외곽에 벽을 두는 형태로 맵 변형
        // (1,1) 이 시작점이니까 자동으로 이에 맞게
        int n = board.length;
        int[][] newBoard = new int[n + 2][n + 2];

        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                newBoard[i][j] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i + 1][j + 1] = board[i][j];
            }
        }

        //BFS
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> visited = new ArrayList<>();
        Node pos = new Node(1, 1, 1, 2, 0); // 시작 위치 설정
        q.offer(pos); // 큐에 삽입한 뒤에
        visited.add(pos); // 방문 처리

        while (!q.isEmpty()) {
            pos = q.poll();

            // (n, n) 위치에 로봇이 도달했다면, 최단 거리이므로 반환
            if ((pos.getX1() == n && pos.getY1() == n) || (pos.getX2() == n && pos.getY2() == n)) {
                return pos.getDistance();
            }

            // 현재 위치에서 이동할 수 있는 위치 확인
            // pos: 현재위치
            ArrayList<Node> nextPos = getNextPos(pos, newBoard);
            for (Node next : nextPos) {
                // 아직 방문하지 않은 위치라면 큐에 삽입하고 방문 처리
                boolean check = true;

                //방문한것
                for (Node visit : visited) {
                    if (next.getX1() == visit.getX1() && next.getY1() == visit.getY1() && next.getX2() == visit.getX2() && next.getY2() == visit.getY2()) {
                        check = false;
                        break;
                    }
                }

                if (check) {
                    q.offer(next);
                    visited.add(next);
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}
        };

        System.out.println(solution(board));
    }
}
