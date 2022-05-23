package algorithm;

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

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int n = arr.length;

        System.out.println("-------- 1. Swap ---------");
        permutation1(arr, 0, n, 3);

        int[] output = new int[n];
        boolean[] visited = new boolean[n];

        System.out.println("-------- 2. DFS ---------");
        permutation2(arr, output, visited, 0, n, 3);
    }
}
