package study.programmers.challenges.search;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42842
 *
 * 카펫
 */
public class Programmers42842 {
    /*
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        List<Integer> divisors = divisor(yellow);

        for (int i = 0; i < divisors.size(); i += 2) {
            int n1 = divisors.get(i);
            int n2 = divisors.get(i + 1);

            if ((n1 + n2 + 2) == brown / 2) {
                answer[0] = Math.max(n1, n2) + 2;
                answer[1] = Math.min(n1, n2) + 2;
                break;
            }
        }

        return answer;
    }

    // 약수 구하기 >> 4의 경우 2 하나만 있는게 아니라 가로세로 모두 필요하니까 2, 2 두번 들어가게
    private List<Integer> divisor(int n) {
        List<Integer> divisors = new ArrayList<>();
        int sqrt = (int) Math.sqrt(n);

        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
                divisors.add(i);
                divisors.add(n / i);
            }
        }

        return divisors;
    }
     */

    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        // 노랑, 가로 + 세로
        int add = (brown - 4)/2;

        // 노랑, 가로 * 세로
        int mul = yellow;

        int x = 0, y = 0;

        // 노랑 가로 + 2, 노랑 세로 + 2 >> max, min 이 전체 가로, 세로
        // x, y 둘중 큰게 가로, 작은게 세로
        // x를 add/2 까지 돌리면서 해도 괜찮다. x가 작은쪽이라 생각했을때 최대는 x == y 인 경우
        // for (x = 1; x <= add; x++) {
        for (x = 1; x <= add/2; x++) {
            y = add - x;
            if (x * y == mul) {
                x += 2;
                y += 2;
                break;
            }
        }

        answer[0] = Math.max(x, y);
        answer[1] = Math.min(x, y);

        return answer;

        /*int a = (brown+4)/2;
        int b = yellow+2*a-4;
        int[] answer = {(int)(a+Math.sqrt(a*a-4*b))/2,(int)(a-Math.sqrt(a*a-4*b))/2};
        return answer;*/
    }

    public static void main(String[] args) {
        int[] brown = {10, 8, 24};
        int[] yellow = {2, 1, 24};

        for (int i = 0; i < 3; i++) {
            int[] solution = solution(brown[i], yellow[i]);
            for (int s : solution) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
