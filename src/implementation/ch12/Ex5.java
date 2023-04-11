package implementation.ch12;


import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/3190
 *
 * 뱀
 *
 * TODO 다시풀기: 2
 */
public class Ex5 {
    
    /*static class Direction{
        private int time;
        private char dir;
        
        public int getTime() {
            return time;
        }
        
        public char getDir(){
            return dir;
        }
        
        public Direction(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    static class Position {
        private int x;
        private int y;

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    //동0, 남1, 서2, 북3    >> D
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static int rotate(int direction, char c) {
        if(c=='L') direction = (direction == 0 ? 3 : direction - 1);
        else direction = (direction + 1) % 4;
        return direction;
    }

    //보드크기(n), 사과 개수(k), 방향전환 수(l)
    public static int n, k, l;

    //(1,1) ~ (n,n)
    public static int[][] board = new int[101][101];

    //방향 전환 정보
    public static ArrayList<Direction> directions = new ArrayList<>();

    public static final int BLANK = 0;
    public static final int APPLE = 1;
    public static final int SNAKE = 2;

    public static int play() {

        //뱀 머리
        int x = 1, y = 1;
        board[x][y] = SNAKE;
        //시작은 동쪽 보고있다.
        int direction = 0;
        int time = 0;

        //directions 에서 뽑아내기 위한 index 변수
        int index = 0;

        //Queue의 앞이 꼬리
        Queue<Position> snake = new LinkedList<>();
        snake.offer(new Position(x, y));

        while (true) {
            //다음에 이동할 위치
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            //다음 위치가 벽X, snake(2) X
            if (1 <= nx && nx <= n && 1 <= ny && ny <= n && board[nx][ny] != SNAKE) {
                //사과O
                if (board[nx][ny] == APPLE) {
                    board[nx][ny] = SNAKE;
                    snake.offer(new Position(nx, ny));
                }
                //사과X
                else {
                    board[nx][ny] = SNAKE;
                    snake.offer(new Position(nx, ny));
                    Position p = snake.poll();
                    board[p.getX()][p.getY()] = BLANK;
                }
            }
            else{
                time++;
                break;
            }

            x = nx;
            y = ny;
            time++;
            if (index < directions.size() && time == directions.get(index).getTime()) {
                direction = rotate(direction, directions.get(index).getDir());
                index++;
            }
        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        k = Integer.parseInt(bf.readLine());

        //보드에 사과 반영
        StringTokenizer stk = null;
        for (int i = 0; i < k; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            board[x][y] = APPLE;
        }

        l = Integer.parseInt(bf.readLine());

        //방향 전환 정보
        for (int i = 0; i < l; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int time = Integer.parseInt(stk.nextToken());
            char dir = stk.nextToken().charAt(0);
            directions.add(new Direction(time, dir));
        }

        System.out.println(play());
    }*/








    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    final static int BLANK = 0;
    final static int APPLE = 1;
    final static int SNAKE = 2;
    final static char RIGHT = 'D';

    static int N, K, L;
    static int[][] map;

    static Map<Integer, Character> directions;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = APPLE;
        }

        L = Integer.parseInt(br.readLine());

        directions = new HashMap<>();

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            directions.put(X, dir);
        }

        System.out.println(play());
    }

    private static int play() {
//        Queue<Position> snake = new LinkedList<>();
//        int x = 0;
//        int y = 0;
//
//        map[x][y] = SNAKE;
//        snake.offer(new Position(x, y));

        LinkedList<Position> snake = new LinkedList<>();
        map[0][0] = SNAKE;
        snake.offer(new Position(0, 0));

        int time = 0;
        int dir = 3;

        while (true) {
//            int nx = x + dx[dir];
//            int ny = y + dy[dir];

            Position head = snake.getLast();
            int nx = head.x + dx[dir];
            int ny = head.y + dy[dir];

            time++;

            if (!isRange(nx, ny) || isSnake(nx, ny)) {
                break;
            }

            // 이동 && 해당 위치 사과 확인
            // 사과에 따라 꼬리 결정
            if (map[nx][ny] != APPLE) {
                Position tail = snake.poll();
                map[tail.x][tail.y] = BLANK;
            }

            snake.offer(new Position(nx, ny));
            map[nx][ny] = SNAKE;
//            x = nx;
//            y = ny;

            // 방향 조정
            if (directions.containsKey(time)) {
                char command = directions.get(time);
                if (command == RIGHT) {
                    dir = rotateRight(dir);
                } else {
                    dir = rotateLeft(dir);
                }
            }
        }

        return time;
    }

    private static int rotateLeft(int dir) {
        return (dir + 1) % 4;
    }

    private static int rotateRight(int dir) {
        return (dir + 4 - 1) % 4;
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static boolean isSnake(int x, int y) {
        return map[x][y] == SNAKE;
    }
}



/*

//Queue를 활용할 생각
//회전에 대한 이해

import java.util.*;

class Node {

    private int time;
    private char direction;

    public Node(int time, char direction) {
        this.time = time;
        this.direction = direction;
    }

    public int getTime() {
        return this.time;
    }

    public char getDirection() {
        return this.direction;
    }
}

class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

public class Main {

    public static int n, k, l;
    public static int[][] arr = new int[101][101]; // 맵 정보
    public static ArrayList<Node> info = new ArrayList<>(); // 방향 회전 정보

    // 처음에는 오른쪽을 보고 있으므로(동, 남, 서, 북)
    public static int dx[] = {0, 1, 0, -1};
    public static int dy[] = {1, 0, -1, 0};

    public static int turn(int direction, char c) {
        if (c == 'L') direction = (direction == 0)? 3 : direction - 1;
        else direction = (direction + 1) % 4;
        return direction;
    }

    public static int simulate() {
        int x = 1, y = 1; // 뱀의 머리 위치
        arr[x][y] = 2; // 뱀이 존재하는 위치는 2로 표시
        int direction = 0; // 처음에는 동쪽을 보고 있음
        int time = 0; // 시작한 뒤에 지난 '초' 시간
        int index = 0; // 다음에 회전할 정보
        // 뱀이 차지하고 있는 위치 정보(꼬리가 앞쪽)
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(x, y));

        while (true) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            // 맵 범위 안에 있고, 뱀의 몸통이 없는 위치라면
            if (1 <= nx && nx <= n && 1 <= ny && ny <= n && arr[nx][ny] != 2) {
                // 사과가 없다면 이동 후에 꼬리 제거
                if (arr[nx][ny] == 0) {
                    arr[nx][ny] = 2;
                    q.offer(new Position(nx, ny));
                    Position prev = q.poll();
                    arr[prev.getX()][prev.getY()] = 0;
                }
                // 사과가 있다면 이동 후에 꼬리 그대로 두기
                if (arr[nx][ny] == 1) {
                    arr[nx][ny] = 2;
                    q.offer(new Position(nx, ny));
                }
            }
            // 벽이나 뱀의 몸통과 부딪혔다면
            else {
                time += 1;
                break;
            }
            // 다음 위치로 머리를 이동
            x = nx;
            y = ny;
            time += 1;
            if (index < info.size() && time == info.get(index).getTime()) { // 회전할 시간인 경우 회전
                direction = turn(direction, info.get(index).getDirection());
                index += 1;
            }
        }
        return time;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();

        // 맵 정보(사과 있는 곳은 1로 표시)
        for (int i = 0; i < k; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[a][b] = 1;
        }

        // 방향 회전 정보 입력
        l = sc.nextInt();
        for (int i = 0; i < l; i++) {
            int x = sc.nextInt();
            char c = sc.next().charAt(0);
            info.add(new Node(x, c));
        }

        System.out.println(simulate());
    }

}
 */
