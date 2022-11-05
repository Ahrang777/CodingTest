package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AW9j74FacD0DFAUY&categoryId=AW9j74FacD0DFAUY&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=3&&&&&&&&&&
 *
 * 진용이네 주차타워
 */
public class SWEA9280 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int total = 0;
            int carIndex;
            int position;
            PriorityQueue<Integer> emptySpace = new PriorityQueue<>();
            List<Integer> spaceFee = new ArrayList<>();
            List<Integer> carWeight = new ArrayList<>();
            Map<Integer, Integer> parkingAreas = new HashMap<>();
            Queue<Integer> waits = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                spaceFee.add(Integer.parseInt(br.readLine()));
                emptySpace.offer(i);
            }

            for (int i = 0; i < M; i++) {
                carWeight.add(Integer.parseInt(br.readLine()));
            }

            for (int i = 0; i < 2 * M; i++) {
                carIndex = Integer.parseInt(br.readLine());	// 차량 번호

                if (carIndex > 0) {
                    if (emptySpace.isEmpty()) {
                        waits.offer(carIndex);
                    } else {
                        position = emptySpace.poll();
                        parkingAreas.put(carIndex, position);
                    }
                } else if (carIndex < 0) {
                    carIndex *= -1;
                    position = parkingAreas.remove(carIndex);
                    emptySpace.offer(position);
                    total += (spaceFee.get(position) * carWeight.get(carIndex - 1));

                    if (!waits.isEmpty()) {
                        carIndex = waits.poll();
                        position = emptySpace.poll();
                        parkingAreas.put(carIndex, position);
                    }
                }
            }

            sb.append("#" + tc + " " + total + "\n");
        }

        System.out.println(sb);
        br.close();
    }
}
