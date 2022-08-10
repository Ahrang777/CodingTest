package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/20061
 *
 * 모노미노도미노 2
 * 삼성전자 공채
 */
public class Baekjoon20061 {

    public static int score;
    public static boolean blue[][], green[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        score = 0;
        blue = new boolean[4][6];
        green = new boolean[6][4];

        int loop = Integer.parseInt(br.readLine());

        for (int i = 0; i < loop; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            moveBlock(t, x, y);
            getScore();
            pushGreen(checkGreen());
            pushBlue(checkBlue());
        }

        System.out.println(score);
        System.out.println(count());
    }

    public static int count() {
        int count = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j]) {
                    count++;
                }
                if (blue[j][i]) {
                    count++;
                }
            }
        }

        return count;
    }
    
    public static void pushGreen(int count) {
        for (int i = 5; i >= 2; i--) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = green[i - count][j];
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = false;
            }
        }
    }


    public static void pushBlue(int count) {
        for (int i = 5; i >= 2; i--) {
            for (int j = 0; j < 4; j++) {
                blue[j][i] = blue[j][i - count];
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                blue[j][i] = false;
            }
        }
    }
    
    public static int checkGreen() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j]) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
    
    public static int checkBlue() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (blue[j][i]) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    public static void moveBlock(int t, int x, int y) {
        int index;
        switch(t) {
            //1개
            case 1 :
                blue[x][0] = true;
                green[0][y] = true;
                index = 1;
                while(index < 6 && blue[x][index] == false) {
                    blue[x][index-1] = false;
                    blue[x][index] = true;
                    index++;
                }
                index = 1;
                while(index < 6 && green[index][y] == false) {
                    green[index-1][y] = false;
                    green[index][y] = true;
                    index++;
                }
                break;
            //1x2 ㅡ
            case 2 :
                // 처음 라인 2개는 이 연산이 수행될때는 무조건 비어있는 상태
                blue[x][0] = true;
                blue[x][1] = true;
                green[0][y] = true;
                green[0][y+1] = true;
                index = 2;
                while(index < 6 && blue[x][index] == false) {
                    blue[x][index-2] = false;
                    blue[x][index] = true;
                    index++;
                }
                index = 1;
                while(index < 6 && green[index][y] == false && green[index][y+1] == false) {
                    green[index-1][y] = false;
                    green[index-1][y+1] = false;
                    green[index][y] = true;
                    green[index][y+1] = true;
                    index++;
                }
                break;
            //2x1 ㅣ
            case 3 :
                blue[x][0] = true;
                blue[x+1][0] = true;
                green[0][y] = true;
                green[1][y] = true;
                index = 1;
                while(index < 6 && blue[x][index] == false && blue[x+1][index] == false) {
                    blue[x][index-1] = false;
                    blue[x+1][index-1] = false;
                    blue[x][index] = true;
                    blue[x+1][index] = true;
                    index++;
                }
                index = 2;
                while(index < 6 && green[index][y] == false) {
                    green[index-2][y] = false;
                    green[index][y] = true;
                    index++;
                }
                break;
        }
    }
    
    public static void getScore() {
        for (int i = 5; i >= 2; i--) {
            int count = 0;
            for (int j = 0; j < 4; j++) {
                if (!green[i][j]) {
                    break;
                } else {
                    count++;
                }
            }

            if (count == 4) {
                score++;
                clearGreen(i);

                // 현재 줄 제거되고 그 윗줄들이 아래로 한칸씩 당겨졌으니 다시 현재 줄부터 확인 해야 한다.
                i++;
            }
        }

        for (int i = 5; i >= 2; i--) {
            int count = 0;
            for (int j = 0; j < 4; j++) {
                if (!blue[j][i]) {
                    break;
                } else {
                    count++;
                }
            }

            if (count == 4) {
                score++;
                clearBlue(i);
                i++;
            }
        }
    }

    public static void clearGreen(int line) {
        for (int i = line; i > 0; i--) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = green[i - 1][j];
            }
        }

        Arrays.fill(green[0], false);
    }

    public static void clearBlue(int line) {
        for (int i = line; i > 0; i--) {
            for (int j = 0; j < 4; j++) {
                blue[j][i] = blue[j][i - 1];
            }
        }

        for (int i = 0; i < 4; i++) {
            blue[i][0] = false;
        }
    }

    /*public static int score, blue[][], green[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        score = 0;
        blue = new int[4][6];
        green = new int[6][4];
        int loop = Integer.parseInt(br.readLine());
        StringTokenizer stz;

        for(int i = 0; i < loop; i++) {
            stz = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(stz.nextToken());
            int x = Integer.parseInt(stz.nextToken());
            int y = Integer.parseInt(stz.nextToken());

            // 블록 이동
            moveBlock(t, x, y);
            
            // 한 줄이 채워진 경우 점수 올리고 줄 제거
            getScore();

            // 초록 보드의 0, 1 행에 블록 있는지 확인하고 블록있는 행의 갯수 만큼 맨 아래줄 제거
            pushGreen(checkGreen());

            // 파랑 보드의 0, 1 열에 블록 있는지 확인하고 블록있는 열의 갯수 만큼 맨 오른쪽줄 제거
            pushBlue(checkBlue());
        }
        System.out.println(score);
        System.out.println(count());
    }

    // 블록을 초록 보드, 파랑 보드에 반영
    public static void moveBlock(int t, int x, int y) {
        int index;
        switch(t) {
            //1개
            case 1 :
                blue[x][0] = 1;
                green[0][y] = 1;
                index = 1;
                while(index < 6 && blue[x][index] == 0) {
                    blue[x][index-1] = 0;
                    blue[x][index] = 1;
                    index++;
                }
                index = 1;
                while(index < 6 && green[index][y] == 0) {
                    green[index-1][y] = 0;
                    green[index][y] = 1;
                    index++;
                }
                break;
            //1x2 ㅡ
            case 2 :
                // 처음 라인 2개는 이 연산이 수행될때는 무조건 비어있는 상태
                blue[x][0] = 1;
                blue[x][1] = 1;
                green[0][y] = 1;
                green[0][y+1] = 1;
                index = 2;
                while(index < 6 && blue[x][index] == 0) {
                    blue[x][index-2] = 0;
                    blue[x][index] = 1;
                    index++;
                }
                index = 1;
                while(index < 6 && green[index][y] == 0 && green[index][y+1] == 0) {
                    green[index-1][y] = 0;
                    green[index-1][y+1] = 0;
                    green[index][y] = 1;
                    green[index][y+1] = 1;
                    index++;
                }
                break;
            //2x1 ㅣ
            case 3 :
                blue[x][0] = 1;
                blue[x+1][0] = 1;
                green[0][y] = 1;
                green[1][y] = 1;
                index = 1;
                while(index < 6 && blue[x][index] == 0 && blue[x+1][index] == 0) {
                    blue[x][index-1] = 0;
                    blue[x+1][index-1] = 0;
                    blue[x][index] = 1;
                    blue[x+1][index] = 1;
                    index++;
                }
                index = 2;
                while(index < 6 && green[index][y] == 0) {
                    green[index-2][y] = 0;
                    green[index][y] = 1;
                    index++;
                }
                break;
        }
    }

    // 초록의 0, 1 행 / 파랑의 0, 1 열은 무조건 비어있는 상태에서 블록 1개 들어왔으니
    // 이 연한 부분에서 행, 열은 꽉 찰 일이 없다. 때문에 줄이 꽉 차는 경우는 5 ~ 2 까지만 체크
    public static void getScore() {
        // 초록 보드 체크
        for(int i = 5; i >= 2; i--) {
            int count = 0;
            for(int j = 0; j < 4; j++) {
                if(green[i][j] == 0)
                    break;
                else
                    count++;
            }
            // 현재 줄이 꽉 찰 경우 점수 올리고 현재 줄 제거
            if(count == 4) {
                score++;
                cleanGreen(i);
                // 현재 줄 제거되고 그 윗줄들이 아래로 한칸씩 당겨졌으니 다시 현재 줄부터 확인 해야 한다.
                i++;
            }
        }

        // 파랑 보드 체크
        for(int i = 5; i >= 2; i--) {
            int count = 0;
            for(int j = 0; j < 4; j++) {
                if(blue[j][i] == 0)
                    break;
                else
                    count++;
            }
            if(count == 4) {
                score++;
                cleanBlue(i);
                i++;
            }
        }
    }

    // 초록 보드 줄 제거
    // 타겟이 되는 줄의 윗 부분을 아래로 한칸씩 이동
    public static void cleanGreen(int line) {
        for(int i = line; i > 0; i--) {
            for(int j = 0; j < 4; j++) {
                green[i][j] = green[i-1][j];
            }
        }
    }

    // 파랑 보드의 줄 제거 
    // 타겟이 되는 줄의 왼쪽 부분을 오른쪽으로 한칸씩 이동
    public static void cleanBlue(int line) {
        for(int j = line; j > 0; j--) {
            for(int i = 0; i < 4; i++) {
                blue[i][j] = blue[i][j-1];
            }
        }
    }

    // 0, 1 행에 블록이 몇개 행에 있는지 체크
    public static int checkGreen() {
        int count = 0;
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++)
                if(green[i][j] == 1) {
                    count++;
                    break;
                }
        }

        return count;
    }

    // 0, 1 열에 블록이 몇개 열에 있는지 체크
    public static int checkBlue() {
        int count = 0;
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++)
                if(blue[j][i] == 1) {
                    count++;
                    break;
                }
        }

        return count;
    }

    public static void pushGreen(int count) {
        // count 는 최대 2라서
        for(int i = 5; i >= 2; i--) {
            for(int j = 0; j < 4; j++)
                green[i][j] = green[i-count][j];
        }

        // 0, 1 행은 무조건 비워짐. 때문에 다음 반복되는 연산에서 0, 1 행의 값에 대해 고민x
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 4; j++)
                green[i][j] = 0;
    }

    public static void pushBlue(int count) {
        for(int i = 5; i >= 2; i--) {
            for(int j = 0; j < 4; j++)
                blue[j][i] = blue[j][i-count];
        }

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 4; j++)
                blue[j][i] = 0;
    }

    // 초록 보드, 파랑 보드의 블록 개수 체크
    public static int count() {
        int count = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 6; j++) {
                if(blue[i][j] == 1)
                    count++;
                if(green[j][i] == 1)
                    count++;
            }
        }

        return count;
    }*/
}
