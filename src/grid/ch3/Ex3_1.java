package grid.ch3;

public class Ex3_1 {
    public static void main(String[] args) {

        int money = 1260;
        int cnt = 0;
        int[] coinType = {500, 100, 50, 10};

        for(int i=0;i<coinType.length;i++){
            int coin = coinType[i];
            cnt += money/coin;
            money %= coin;
        }
        System.out.println(cnt);
    }
}
