package grid.ch11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
입력1
5 3
1 3 2 3 2

출력1
8
==========
입력2
8 5
1 5 4 3 2 4 5 2

출력2
25
 */
public class Ex5 {

    //n: 볼링공 개수, m: 무게 최대값(1~M 형태)
    public static int n, m;
    //public static ArrayList<Integer> arr = new ArrayList<>();

    public static int[] arr = new int[11];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(stk.nextToken());
            arr[x]++;
        }

        int result = 0;

        for (int i = 1; i < m; i++) {
            n -= arr[i];
            result += arr[i] * n;
        }

        System.out.println(result);

        /*stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(stk.nextToken()));
        }

        Collections.sort(arr);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr.get(i) != arr.get(j)) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);*/
    }
}
