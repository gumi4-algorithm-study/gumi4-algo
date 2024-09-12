import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_14501 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 남은 일수
		int n = sc.nextInt();
		
		// 일정표
		List<int[]> cal = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			cal.add(new int[] {sc.nextInt(), sc.nextInt()});
		}
		
		// 모든 경우의 수 순회
		for (int i = 0; i < n; i++) {
			check(cal, i, n, 0);
		}
		
		// 정답 출력
		System.out.println(result);
	}
	
	// 결과값
	static int result = 0;
	static void check(List<int[]> cal, int start, int n, int sum) {
		// 남은 일수를 넘은 경우
		if (start >= n) {
			// 최대값 갱신
			if (sum > result) {
				result = sum;
			}
			return;
		}
		
		// 해당 요일
		int[] element = cal.get(start);
		
		// 하루만에 끝나는 경우
		if (element[0] == 1) {
			check(cal, start + 1, n, sum + element[1]);
		} else {
			// 다음 일정을 진행했을 때 남은 일수를 넘지 않는 경우
			if (n >= start + element[0]) {
				// 일정 수행
				check(cal, start + element[0], n, sum + element[1]);
				// 일정 미수행
				check(cal, start + 1, n, sum);
			} else {
				// 다음 일정을 진행했을 때 남은 일수를 넘는 경우
				// 일정 수행
				check(cal, start + element[0], n, sum);
				// 일정 미수행
				check(cal, start + 1, n, sum);
			}
		}
	}
}

