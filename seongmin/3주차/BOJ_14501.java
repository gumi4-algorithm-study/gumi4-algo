import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	static int N, answer;
	static int[] T, P;

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		T = new int[N + 1]; // 상담을 완료하는 데 걸리는 기간
		P = new int[N + 1]; // 상담 했을 때 받을 수 있는 금액

		for (int i = 1; i <= N; i++) {
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}

		answer = 0;
		subset(1, 0);
		System.out.println(answer);
	}

	static void subset(int start, int profit) {
		if (start > N + 1)
			return;

		answer = Math.max(answer, profit); // 최대 이익 갱신

		for (int i = start; i <= N; i++) {
			if (i + T[i] <= N + 1) {  // 현재 상담의 종료일이 N+1(퇴사일)을 초과하지 않는지 확인
				subset(i + T[i], profit + P[i]);
			}
		}
	}
}