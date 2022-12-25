package dfs_bfs.ch13;

import java.time.Year;
import java.util.*;
import java.io.*;

/*
입력
5
X S X X T
T X S X X
X X X X X
X T X X X
X X T X X

출력
YES
==========
입력
4
S S S T
X X X X
X X X X
T T T X

출력
NO
 */

/**
 * https://www.acmicpc.net/problem/18428
 * 
 * 감시 피하기
 */
public class Ex6 {

    static class Point{
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    
    //n: 3 ~ 6
    public static int n;
    public static char[][] map = new char[7][7];
    public static final char WALL = 'O';
    public static final char EMPTY = 'X';
    public static final char TEACHER = 'T';
    public static final char STUDENT = 'S';

    public static ArrayList<Point> teachers = new ArrayList<>();

    //방향
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static String result = "NO";


    //감시 피할 수 있는지 여부
    //선생님 위치 x, y
    public static boolean check(int x, int y) {

        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            while (true) {
                nx = nx + dx[i];    //이전 위치에서 계속 더해지는거라서 nx + dx[i] 인거 주의!!
                ny = ny + dy[i];

                //map 범위 내에서만 확인
                if (nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
                    if (map[nx][ny] == STUDENT) {
                        return false;
                    } else if (map[nx][ny] == WALL) {   //벽이면 더이상 해당 방향으로 갈 필요 X
                        break;
                    }
                } else {    //map 범위를 넘어서면 while문 stop
                    break;
                }
            }
        }

        return true;
    }

    //장애물 세우기
    public static void setWall(int count) {
        if (count == 3) {
            int pass = 0;
            //각 선생님마다 학생 찾는지 체크
            for (Point teacher : teachers) {
                int x = teacher.getX();
                int y = teacher.getY();

                if (check(x, y)) {  //감시 피할 수 있다면
                    pass++;
                } else {
                    break;
                }
            }
            if (pass == teachers.size()) {
                result = "YES";
            }
            return; //여기서 return이 나와야 백트래킹이 돌아간다. 일단 끝내야함
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == EMPTY) {
                    map[i][j] = WALL;
                    setWall(count + 1);
                    map[i][j] = EMPTY;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        n = Integer.parseInt(bf.readLine());

        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = stk.nextToken().charAt(0);
                if (map[i][j] == TEACHER) {
                    //선생님 위치 저장
                    teachers.add(new Point(i, j));
                }
            }
        }

        setWall(0);

        System.out.println(result);

        /*for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }*/
    }
}
