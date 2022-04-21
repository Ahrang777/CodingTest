package implementation.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 4 4
 * 1 1 0
 * 1 1 1 1
 * 1 0 0 1
 * 1 1 0 1
 * 1 1 1 1
 */
public class Ex4 {

    public static int turnLeft(int dir){
        dir--;
        if(dir == -1) dir = 3;
        return dir;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        //3 ~ 50
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(bf.readLine(), " ");

        int x = Integer.parseInt(stk.nextToken());
        int y = Integer.parseInt(stk.nextToken());
        
        //0 북 / 1 동 / 2 남 / 3 서
        int dir = Integer.parseInt(stk.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int cnt = 0;
        int turnCnt = 0;

        // dir = 0 ~ 3,  Math.abs((dir--))%4
        
        while(true){
            dir = turnLeft(dir);

            //현재 방향에서 1칸 이동했을때 위치
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];

            //이동 가능한 경우
            if(arr[nextX][nextY] == 0){
                cnt++;
                turnCnt=0;
                //방향 확정

                //이동
                x = nextX;
                y = nextY;

                arr[x][y] = 2;  //이동한 표시
                continue;
            } else{
                turnCnt++;
            }

            if(turnCnt == 4){
                //현재 보는 방향의 반대로 이동 >> 1칸 뒤로 이동
                nextX = x - dx[dir];
                nextY = y - dy[dir];

                //뒤가 바다인 경우
                if(arr[nextX][nextY] == 1){
                    break;
                } else{ //뒤로 갈 수 있는 경우
                    x = nextX;
                    y = nextY;
                    turnCnt=0;
                    //cnt++; 이동횟수가 아니라 방문 칸수라서 필요없다.
                }
            }
        }

        System.out.println("cnt = " + cnt);
    }
}
