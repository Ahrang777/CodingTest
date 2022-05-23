package implementation.ch12;

import java.io.*;
import java.util.*;

/*
입력1
K1KA5CB7

출력1
ABCKK13
===================
입력2
AJKDLSI412K4JSJ9D

출력2
ADDIJJJKKLSS20
 */
public class Ex2 {

    public static String s;
    public static ArrayList<Character> arr = new ArrayList<>();
    public static int sum = 0;

    public static void main(String[] args) throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                arr.add(c);
            } else {
                sum += (c - '0');
            }
        }

        Collections.sort(arr);

        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i));
        }
        if(sum!=0) System.out.print(sum);
    }
}
