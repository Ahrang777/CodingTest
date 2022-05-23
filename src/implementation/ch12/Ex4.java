package implementation.ch12;

/**
 * key: [[0, 0, 0], [1, 0, 0], [0, 1, 1]]
 * lock: [[1, 1, 1], [1, 1, 0], [1, 0, 1]]
 * 
 * 출력
 * result: true
 */
public class Ex4 {

    public static int[][] rotateKey(int[][] key) {
        int n = key.length;
        int[][] result = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                result[j][n-i-1] = key[i][j];
            }
        }

        return result;
    }

    public static boolean check(int[][] lock){

        int length = lock.length / 3;

        //중앙 lock의 값
        for(int i = length; i < length * 2; i++){
            for(int j = length; j < length * 2; j++){
                if(lock[i][j] != 1)
                    return false;
            }
        }

        return true;
    }

    public static boolean solution(int[][] key, int[][] lock) {
        //boolean answer = true;

        int size = lock.length;
        int[][] newLock = new int[3*size][3*size];

        //newLock 중앙에 lock 채워넣음, 자물쇠 영역 외엔 0
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                newLock[i + size][j + size] = lock[i][j];
            }
        }

        //90, 180, 270, 360 회전의 경우 확인
        //for문으로 한칸씩 다 확인할거라서 이동 메서드 필요 없다.
        for(int rotation = 0; rotation < 4; rotation++){
            key = rotateKey(key);
            for(int x = 0; x < size * 2; x++){
                for(int y = 0; y < size * 2; y++){

                    for(int i = 0; i < key.length; i++){
                        for(int j = 0; j < key.length; j++){
                            newLock[x+i][y+j] += key[i][j];
                        }
                    }

                    //열쇠 맞는 경우
                    if(check(newLock)){
                        return true;
                    }

                    //열쇠 안맞는 경우, newLock을 원래 상태로
                    for(int i = 0; i < key.length; i++){
                        for(int j = 0; j < key.length; j++){
                            newLock[x+i][y+j] -= key[i][j];
                        }
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] key = {
                {0, 0, 0},
                {1, 0, 0},
                {0, 1, 1}
        };

        int[][] lock = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        System.out.println("result = " + solution(key, lock));
    }
}
