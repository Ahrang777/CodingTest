package implementation.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 5
 * R R R U D D
 */
public class Ex1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        int n = Integer.parseInt(stk.nextToken());
        String[] plans = bf.readLine().split(" ");  //R R R U D D

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        char[] moveTypes = {'L', 'R', 'U', 'D'};

        int x = 1;
        int y = 1;

        //x = 1 ~ 5,  y = 1 ~ 5

        for (int i = 0; i < plans.length; i++) {
            char plan = plans[i].charAt(0);

//            System.out.println("plan = " + plan);
            int tmpx = 0;
            int tmpy = 0;
            for (int j = 0; j < 4; j++) {
                if(plan == moveTypes[j]){
                    tmpx = x + dx[j];
                    tmpy = y + dy[j];
                }
            }

            if(tmpx>n || tmpx<1 || tmpy>n || tmpy<1) continue;

            x = tmpx;
            y = tmpy;
        }

        System.out.println(x + " " + y);
    }
}
