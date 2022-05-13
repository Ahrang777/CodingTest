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
2 3 1 2 2

출력
2
 */
public class Ex1 {

    public static int n;
    public static ArrayList<Integer> people = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            people.add(Integer.parseInt(stk.nextToken()));
        }

        Collections.sort(people);

        int result = 0; //전체 그룹의 수
        int count = 0;  //특정 그룹의 인원수

        for (int i = 0; i < n; i++) {
            count++;
            if (count >= people.get(i)) {
                result++;
                count = 0;
            }
        }

        System.out.println(result);
    }
}
