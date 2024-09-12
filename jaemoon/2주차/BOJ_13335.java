import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_13335 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		// 트럭 개수
		int n = sc.nextInt();
		// 다리 길이
		int w = sc.nextInt();
		// 최대 하중
		int l = sc.nextInt();

		// 트럭 배열
		int[] tList = new int[n];
		for (int i = 0; i < n; i++) {
			tList[i] = sc.nextInt();
		}

		// 다리 역할을 할 큐
		Queue<Integer> bridge = new ArrayDeque<>();

		// 트럭 배열 인덱스
		int tIdx = 1;
		// 결과 값
		int result = 1;
		
		// 다리길이 -1 만큼 0으로 패딩
		for (int i = 0; i < w - 1; i++) {
			bridge.offer(0);
		}
		// 첫 트럭 삽입
		bridge.offer(tList[0]);
		
		// 모든 트럭이 통과할 때까지 반복
		while (!bridge.isEmpty()) {
			// 모든 트럭이 다리위를 지나거나 올라갔을 경우
			if (tIdx == n) {
				// 트럭 뽑기
				bridge.poll();
				// 시간 증가
				result++;
			} else {
				// 다리가 트럭으로 가득 찼을 경우
				if (checkSize(bridge) == w) {
					// 트럭 뽑기
					bridge.poll();
					// 남은 트럭과 들어올 트럭 총 무게가 최대 하중과 같거나 작을 경우
					if (checkSum(bridge, l, tList[tIdx])) {
						// 트럭 삽입
						bridge.offer(tList[tIdx++]);
					} else {
						// 패딩 삽입
						bridge.offer(0);
					}
					// 시간 증가
					result++;
				} else {
					// 트럭 뽑기
					bridge.poll();
					// 남은 트럭과 들어올 트럭 총 무게가 최대 하중과 같거나 작을 경우
					if (checkSum(bridge, l, tList[tIdx])) {
						// 트럭 삽입
						bridge.offer(tList[tIdx++]);
					} else {
						// 패딩 삽입
						bridge.offer(0);
					}
					// 시간 증가
					result++;
				}
			}
		}
		// 정답 출력
		System.out.println(result);
	}

	static int checkSize(Queue<Integer> b) {
		// 0을 제외한 트럭 개수
		int size = 0;
		// 다리 길이
		int bSize = b.size();
		for (int i = 0; i < bSize; i++) {
			// 트럭인지 확인
			int poll = b.poll();
			if (poll != 0) {
				size++;
			}
			// 뽑은 트럭 다시 삽입
			b.offer(poll);
		}

		return size;
	}

	static boolean checkSum(Queue<Integer> b, int l, int peek) {
		// 트럭 무게 총합
		int sum = 0;
		// 다리 길이
		int bSize = b.size();
		for (int i = 0; i < bSize; i++) {
			// 트럭인지 확인
			int poll = b.poll();
			// 총합 증가
			sum += poll;
			// 뽑은 트럭 다시 삽입
			b.offer(poll);
		}
		
		// 총합이 다리 최대 하중보다 작거나 같을 경우
		if (l >= (sum + peek)) {
			return true;
		}
		return false;
	}
}

