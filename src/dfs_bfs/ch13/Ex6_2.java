package dfs_bfs.ch13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
public class Ex6_2 {

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
    public static final char BLANK = 'X';
    public static final char TEACHER = 'T';
    public static final char STUDENT = 'S';

    public static ArrayList<Point> teachers = new ArrayList<>();

    public static boolean search(int x, int y) {

        //위로 이동
        for (int i = x; i > 0; i--) {
            if (map[i][y] == STUDENT) {
                return true;
            } else if (map[i][y] == WALL) {
                break;
            }
        }

        //아래로 이동
        for (int i = x; i <= n; i++) {
            if (map[i][y] == STUDENT) {
                return true;
            } else if (map[i][y] == WALL) {
                break;
            }
        }


        //오른쪽으로 이동
        for (int i = y; i <= n; i++) {
            if (map[x][i] == STUDENT) {
                return true;
            } else if (map[x][i] == WALL) {
                break;
            }
        }

        //왼쪽으로 이동
        for (int i = y; i > 0; i--) {
            if (map[x][i] == STUDENT) {
                return true;
            } else if (map[x][i] == WALL) {
                break;
            }
        }

        //이 선생님이 모든 방향에서 학생을 못 찾은 경우
        return false;
    }

    public static boolean solve(int count) {
        if (count == 3) {
            for (Point teacher : teachers) {
                int x = teacher.getX();
                int y = teacher.getY();
                
                //학생 찾은 경우
                if(search(x, y))
                    return false;
            }
            
            //모든 선생님이 학생을 못 찾은 경우
            return true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == BLANK) {
                    map[i][j] = WALL;
                    if(solve(count + 1)) return true;
                    map[i][j] = BLANK;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
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

        if (solve(0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
