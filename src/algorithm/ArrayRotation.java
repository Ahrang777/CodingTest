package algorithm;

/**
 * 2차원 배열 회전
 *
 * 규칙
 * n = arr.length, m = arr[0].length
 *
 * n x m -> m x n 형태가 되는 90도, 270도 회전인 경우 행은 j, 열은 i 로 계산
 * n x m -> n x m 그대로인 180도 회전, 좌우 뒤집기, 위아래 뒤집기의 경우 행은 i, 열은 j 그대로 계산
 *
 * 행에 계산이 들어가는 경우 n - 1, 열에 계산이 들어가는 경우 m - 1
 */
public class ArrayRotation {

    static int n = 2;
    static int m = 3;
    static int[][] arr;

    // 원본 배열
    static void origin() {
        int index = 1;
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = index++;
            }
        }

        System.out.println("========= origin =========");

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 3 x 3 배열
     * arr     rotate
     * 0, 0 -> 0, 2
     * 0, 1 -> 1, 2
     * 0, 2 -> 2, 2
     *
     * 1, 0 -> 0, 1
     * 1, 1 -> 1, 1
     * 1, 2 -> 2, 1
     *
     * 2, 0 -> 0, 0
     * 2, 1 -> 1, 0
     * 2, 2 -> 2, 0
     */
    // 시계방향 90도 회전
    static int[][] rotate90() {
        int[][] rotate = new int[m][n];

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[0].length; j++) {
                rotate[i][j] = arr[n - 1 - j][i];
            }
        }

        System.out.println("==================");

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[0].length; j++) {
                System.out.print(rotate[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return rotate;
    }

    // 90, 180, 270도

    /**
     * 90, 270 일때는 n x m -> m x n 이 되니까 계산과정에서 행 부분에 j 가 열 부분에 i가 들어간다.
     * 행과 열이 뒤집히는 것
     *
     * 행 부분엔 n - 1, 열 부분엔 m - 1 >> 크기가 아닌 인덱스로 계산 하기 위해서
     *
     * 90도 m x n
     * rotate[i][j] = arr[n-1-j][i]
     *
     * 180도 n x m
     * rotate[i][j] = arr[n-1-i][m-1-j]
     *
     * 270도 m x n
     * rotate[i][j] = arr[j][m-1-i]
     *
     *
     * arr의 경우 n x m 이기에 [][] 에서 연산이 있는 경우  [n ,,,][m ,,,] 이런식으로 각가 n, m에 대해서 연산이 일어날수 밖에 없다.
     * 90, 270의 경우 rotate[][] 을 생각해보면 m x n 으로 뒤집힌다. 따라서 arr에서 각 [][] 에 i, j 뒤집힌 채로 들어감 arr[n ,,, j][m ,,, i]
     */
    static int[][] rotate(int degree) {
        int[][] rotate;
        int n = arr.length;
        int m = arr[0].length;

        // 각도에 따른 rotate 배열 생성
        switch (degree) {
            case 90:
            case 270:
                rotate = new int[m][n];
                break;
            case 180:
                rotate = new int[n][m];
                break;
            default:
                throw new IllegalStateException();
        }

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                switch (degree) {
                    case 90:
                        rotate[i][j] = arr[n - 1 - j][i];
                        break;
                    case 180:
                        rotate[i][j] = arr[n - 1 - i][m - 1 - j];
                        break;
                    case 270:
                        rotate[i][j] = arr[j][m - 1 - i];
                        break;
                }
            }
        }

        System.out.println("=========" + degree + "=========");
        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                System.out.print(rotate[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return rotate;
    }

    // 위, 아래로 뒤집기
    static int[][] reverseTopBottom() {
        int[][] tmp = new int[n][m];

        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                tmp[i][j] = arr[n - 1 - i][j];
            }
        }

        System.out.println("========= 위, 아래로 뒤집기 =========");
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                System.out.print(tmp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return tmp;
    }

    // 좌우로 뒤집기
    static int[][] reverseLeftRight() {
        int[][] tmp = new int[n][m];

        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                tmp[i][j] = arr[i][m - 1 - j];
            }
        }

        System.out.println("========= 좌우로 뒤집기 =========");
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                System.out.print(tmp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return tmp;
    }

    public static void main(String[] args) {

        /*origin();
        arr = rotate90();*/

        origin();
        arr = rotate(90);

        origin();
        arr = rotate(180);

        origin();
        arr = rotate(270);

        origin();
        arr = reverseTopBottom();

        origin();
        arr = reverseLeftRight();
    }
}
