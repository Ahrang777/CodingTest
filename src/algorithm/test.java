package algorithm;

import java.io.*;
import java.util.*;

public class test {



    public static void main(String[] args) throws IOException {
        System.out.println(-7%3);
    }



//    public static void main(String[] args) {
//        List<Integer> arr = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            arr.add(i);
//        }
//
//        int index = arr.indexOf(5);
//
//        // 만약 new ArrayList로 새로 만들지 않고 List<Integer> sub = arr.subList(index, arr.size()) 형태로 하고 arr에서 removeAll 하면 에러 발생
//        // java.util.ConcurrentModificationException
//        // List<Integer> sub = arr.subList(index, arr.size())
//        List<Integer> sub = new ArrayList<>(arr.subList(index, arr.size()));
//
//        System.out.println("arr : " + arr);
//        System.out.println("sub : " + sub);
//
//        arr.removeAll(sub);
//
//        /*for (Integer i : sub) {
//            arr.remove(i);
//        }*/
//
//        System.out.println("=================");
//        System.out.println("arr : " + arr);
//        System.out.println("sub : " + sub);
//        Collections.reverse(sub);
//        System.out.println(sub);
//    }
}
