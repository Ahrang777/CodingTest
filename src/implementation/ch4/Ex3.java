package implementation.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String pos = bf.readLine();
        /*char x = pos.charAt(1); // 1 ~ 8
        char y = pos.charAt(0); // a ~ h
        int cnt = 0;

        int[][] steps = {
                {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
                {-1, -2}, {1, -2}, {-1, 2}, {1, 2}
        };

        for (int i = 0; i < 8; i++) {

            char tmpx = (char)(x + steps[i][0]);
            char tmpy = (char)(y + steps[i][1]);
            if(tmpx > '8' || tmpx < '1' || tmpy > 'h' || tmpy < 'a')
                continue;

            cnt++;
        }*/

        int row = pos.charAt(1) - '0';
        int col = pos.charAt(0) - 'a' +1;

        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};

        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if (nextRow >= 1 && nextRow <= 8 && nextCol >= 1 && nextCol <= 8) {
                cnt++;
            }
        }

        System.out.println("cnt = " + cnt);
    }
}
