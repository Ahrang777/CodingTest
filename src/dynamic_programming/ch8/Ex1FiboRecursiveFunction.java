package dynamic_programming.ch8;

/**
 * 이 방식을 쓰지 말자
 * 시간 복잡도가 매우 크다
 * O(2ⁿ)
 */
public class Ex1FiboRecursiveFunction {

    public static void main(String[] args) {
        System.out.println(fibo(4));
    }

    private static int fibo(int x) {

        if (x == 1 || x == 2) {
            return 1;
        }

        return fibo(x - 1) + fibo(x - 2);
    }
}
