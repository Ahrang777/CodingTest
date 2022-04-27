package binary_search.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Ex7 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        HashSet<Integer> s = new HashSet<>();
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(stk.nextToken());
            s.add(x);
        }

        int m = Integer.parseInt(bf.readLine());
        int[] targets = new int[m];

        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < m; i++) {
            targets[i] = Integer.parseInt(stk.nextToken());
        }

        for (int i = 0; i < m; i++) {
            if (s.contains(targets[i])) {
                System.out.print("yes ");
            } else{
                System.out.print("no ");
            }
        }
    }
}
