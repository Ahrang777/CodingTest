package algorithm;

/*
-------- 1. Swap ---------
1 2 3
1 3 2
2 1 3
2 3 1
3 2 1
3 1 2
-------- 2. DFS ---------
1 2 3
1 3 2
2 1 3
2 3 1
3 1 2
3 2 1
-------- 2. DFS 중복순열 ---------
3 3
3 6
3 9
6 3
6 6
6 9
9 3
9 6
9 9
 */

/**
 * Permutation, 순열
 * nPr
 * O(n!)
 *
 * nPr = n! / (n-r)!
 *
 * 5P2 = (5 x 4 x 3 x 2 x 1) / (3 x 2 x 1) = 5 x 4 = 20
 */
public class Permutation {

    //1. Swap 함수를 이용해 구현 - 순서 없이 n 개중에서 r 개를 뽑는 경우, nPr         {1, 2, 3}, 0, 3, 3
    public static void permutation1(int[] arr, int depth, int n, int r) {
        if (depth == r) {
            print(arr, r);
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            permutation1(arr, depth + 1, n, r); //3, 2, 1 이런식으로 처음 나온것보다 이전 것들도 뽑히기 때문에 depth + 1로
            swap(arr, depth, i);    //1,3,2 로 변경된 걸 다시 1,2,3으로 변경하는 식으로 처음 상태로 만들어서 모든 경우를 확인한다.
        }
    }

    public static void swap(int[] arr, int depth, int i) {
        int tmp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = tmp;
    }

    //2. DFS를 이용해 구현  - 순서를 지키면서 n 개중에서 r 개를 뽑는 경우
    //output: 출력용 배열
    //순열은 1,2,3 과 3,2,1 은 다른거라서 크기적으로 작은것도 확인해봐야한다. 따라서 0 ~ n까지 즉, 처음부터 끝까지 방문 안한거 다 찾는것
    //1,2,3 과 3,2,1은 다른거라서 단순히 앞에서부터 순서대로 방문한 것들 뽑는걸로 해결 안된다. 별도로 출력을 위한 배열이 필요하다.
    public static void permutation2(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            print(output, r);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation2(arr, output, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    public static void print(int[] arr, int r) {
        for (int i = 0; i < r; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static int[] dfsArr = {3, 6, 9};
//    public static boolean[] dfsVisited = new boolean[3];
    public static int[] answer = new int[2];


    //중복 순열
    public static void dfs(int count) {
        if (count == 2) {
            dfsPrint();
            return;
        }
        for (int i = 0; i < 3; i++) {
            answer[count] = dfsArr[i];
            dfs(count + 1);
        }
    }

    public static void dfsPrint() {
        for (int i = 0; i < 2; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }

    /*public static final int MAX = 5;
    public static int[] dfsArr = new int[MAX];
    public static boolean[] dfsVisited = new boolean[MAX];
    public static int[] answer = new int[3];    //3: nPr의 r 과 같다

    public static void dfs(int count) {
        if (count == 3) {
            dfsPrint();
            return;
        }
        for (int i = 0; i < MAX; i++) {
            if (!dfsVisited[i]) {
                dfsVisited[i] = true;
                answer[count] = dfsArr[i];
                dfs(count + 1);
                dfsVisited[i] = false;
            }
        }
    }

    public static void dfsPrint() {
        for (int i = 0; i < MAX; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }*/

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int n = arr.length;

        System.out.println("-------- 1. Swap ---------");
        permutation1(arr, 0, n, 3);

        int[] output = new int[n];
        boolean[] visited = new boolean[n];

        System.out.println("-------- 2. DFS ---------");
        permutation2(arr, output, visited, 0, n, 3);

        System.out.println("-------- 3. DFS 중복순열 ---------");
        dfs(0);
    }
}
