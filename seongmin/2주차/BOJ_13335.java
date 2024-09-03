import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();  // 다리를 건너는 트럭 수
        int w = sc.nextInt();  // 다리의 길이
        int l = sc.nextInt();  // 다리의 최대 하중

        Queue<Integer> truck = new ArrayDeque<>();  // 트럭의 무게
        for (int i = 0; i < n; i++) {
            truck.offer(sc.nextInt());
        }

        Queue<Integer> bridge = new ArrayDeque<>();  // 0으로 w 만큼 받는다.
        for (int i = 0; i < w; i++) {
            bridge.offer(0);
        }

        int curWeight = 0;  // 다리에 놓인 총 무게
        int time = 0;
        while (!bridge.isEmpty()) {
            time++;
            curWeight -= bridge.poll();  // 매초당 다리 위에 있는 무게를 하나씩 빼준다.

            if (!truck.isEmpty()) {  // 트럭이 아직 있다면
                if (truck.peek() + curWeight <= l) {  // 최상단 트럭과 현재 무게를 더한 값이 l이하
                    curWeight += truck.peek();        // 현재 무게에 최상단 트럭 무게를 더 해주고 다리 위에 올린다.
                    bridge.offer(truck.poll());
                } else {
                    bridge.offer(0);
                }

            }
            System.out.println(time);
        }
    }
}