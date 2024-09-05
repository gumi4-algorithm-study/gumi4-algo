import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13335 {

	static int n, w, L;
	static Queue<Integer> truckQ, bridgeQ;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 트럭의 개수
		w = Integer.parseInt(st.nextToken()); // 다리의 길이
		L = Integer.parseInt(st.nextToken()); // 다리의 최대 하중

		st = new StringTokenizer(br.readLine());
		truckQ = new ArrayDeque<>();
		bridgeQ = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			truckQ.add(Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < w; i++) { // 초기화
			bridgeQ.offer(0);
		}

		int time = 0, totalL = 0;
		// 다리가 비어있지 않을 때까지 반복
		while (!bridgeQ.isEmpty()) {
			time++;
			totalL -= bridgeQ.poll(); // 제일 앞에 있는 것 빼기

			// 뽑아낼 트럭이 있는 경우
			if (!truckQ.isEmpty()) {
				// 다리의 무게 제한을 넘지 않는다면 다리에 올리기
				if (truckQ.peek() + totalL <= L) {
					int truck = truckQ.poll();
					totalL += truck;
					bridgeQ.offer(truck);
				} else {
					bridgeQ.offer(0);
				}
			}
		}
		System.out.println(time);
	}
}
