import java.util.*;
import java.io.*;

public class BOJ13335 {
    public static void main(String[] args) throws Exception {
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Queue<Integer> bridge = new LinkedList<>();
            // 다리의 길이 w
            // 다리의 하중 L
            // 다리에 하중을 넘지 않는 한 트럭을 하나씩 올리면서
            // bridge의 size가 다리의 길이 w를 넘지 않으면 계속 진행

            int time = 0;
            int weight = 0;
            for (int i = 0; i < n; i++) { // 트럭이 하나씩 bridge에 들어가야함
                if (bridge.isEmpty()) {
                    bridge.add(arr[i]);
                    weight += arr[i];
                    time++;
                } else {
                    if (bridge.size() == w) { // 꽉 찬 경우
                        weight -= bridge.peek();
                        bridge.poll();
                    }
                    if (weight + arr[i] <= L) { // 더 들어갈 수 있는 경우
                        bridge.add(arr[i]);
                        weight += arr[i];
                        time++;
                    } else { // 더 못 들어가는 경우
                        bridge.add(0);
                        time++;
                        i--;
                    }
                }
            }
            System.out.println(time + w);
        }
    }
}
