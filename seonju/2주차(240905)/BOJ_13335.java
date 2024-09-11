import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ_13335 트럭
public class BOJ_13335 {
    static int n, w, l; // 트럭 수, 다리 길이, 다리 최대하중
    static int time;    // 건너는 시간
    static int[] trucks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        trucks = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        int current_weight = 0;
        int idx = 0;
        time = 0;
        st = new StringTokenizer(br.readLine(), " ");

        // 트럭의 무게 저장
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < w; i++) {
            queue.add(0);
        }

        while (!queue.isEmpty()) {
            time++; // 시간 증가
            current_weight -= queue.poll(); // 다리에서 내려오는 트럭 빼줌

            if (idx < n) {    // 트럭 남아있을 때
                if (trucks[idx] + current_weight <= l) {    // 최대보다 작을 때
                    current_weight += trucks[idx];
                    queue.offer(trucks[idx]);   // 트럭 무게 추가
                    idx++;
                } else {    // 0 추가
                    queue.offer(0);
                }
            }
        }
        System.out.println(time);
    }
}
