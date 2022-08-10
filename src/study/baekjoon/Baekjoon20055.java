package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/20055
 *
 * 컨베이어 벨트 위의 로봇
 * 삼성전자 공채
 */
public class Baekjoon20055 {

    public static int n, k;

    // 내구도
    public static int[] arr;

    // 로봇
    // n-1 번째에서 무조건 빠지고 0번째에서 들어옴, 때문에 뒷부분 인덱스가 먼저 들어온것
    public static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[2 * n];
        robot = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();
    }

    public static void solution() {
        int cnt = 0;

        while (isOk()) {
            moveBelt();
            moveRobot();
            addRobot();
            cnt++;
        }
        
        System.out.println(cnt);
    }

    public static void addRobot() {
        if (arr[0] > 0) {
            robot[0] = true;
            arr[0]--;
        }
    }
    
    public static void moveRobot() {
        for (int i = n - 1; i > 0; i--) {
            if (robot[i - 1] && !robot[i] && arr[i] >= 1) {
                robot[i - 1] = false;
                robot[i] = true;
                arr[i]--;
            }
        }

        robot[n - 1] = false;
    }

    public static void moveBelt() {
        // 벨트 이동
        int tmp = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = tmp;

        // 로봇 같이 이동
        for (int i = n - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = false;
        robot[n - 1] = false;
    }
    
    public static boolean isOk() {
        int cnt = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                cnt++;
            }
            if (cnt >= k) {
                return false;
            }
        }
        return true;
    }
}

