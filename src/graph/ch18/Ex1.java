package graph.ch18;

import java.io.*;
import java.util.*;

/*
입력
5 4
0 1 0 1 1
1 0 1 1 0
0 1 0 0 0
1 1 0 0 0
1 0 0 0 0
2 3 4 3

출력
YES
 */

/**
 * 여행 계획
 *
 * 계획으로 제공된 번호들이 모두 연결 되어있어야 한다. == 루트 노드가 모두 같아야 한다.
 */
public class Ex1 {

    public static int n, m;
    public static int[] parent = new int[501];

    public static int findRoot(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = findRoot(parent[x]);
    }

    public static void union(int a, int b) {
        a = findRoot(a);
        b = findRoot(b);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(stk.nextToken());
                if (x == 1) {
                    union(i + 1, j + 1);
                }
            }
        }

        ArrayList<Integer> plan = new ArrayList<>();
        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < m; i++) {
            plan.add(Integer.parseInt(stk.nextToken()));
        }

        boolean flag = true;
        for (int i = 0; i < m - 1; i++) {
            if (findRoot(plan.get(i)) != findRoot(plan.get(i + 1))) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
