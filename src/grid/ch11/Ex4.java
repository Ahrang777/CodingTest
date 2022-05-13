package grid.ch11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
입력
5
3 2 1 1 9

출력
8
 */
public class Ex4 {

    public static int n;
    public static ArrayList<Integer> coins = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(stk.nextToken()));
        }

        Collections.sort(coins);

        int target = 1;
        for (int i = 0; i < n; i++) {
            // 만들 수 없는 금액을 찾았을 때 반복 종료
            //coin = coins.get(i)
            //coin 자체로 해당 값 만들 수 있고 1~target-1을 만들 수 있으니까 coin + 1 ~ target + coin -1 까지 만들 수 있다.(이전 것들에 coin만 더한것)
            //즉, 1 ~ target -1, coint, coin + 1 ~ target + coin -1 로 1 ~ target + coin -1 까지 만들 수 있는것
            if (target < coins.get(i)) break;
            target += coins.get(i);
        }

        System.out.println(target);
    }
}
