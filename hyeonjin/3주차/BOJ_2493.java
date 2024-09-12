import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Top> stack = new Stack<>();
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		stack.push(new Top(1, Integer.parseInt(st.nextToken()))); // 첫 번째 탑
		sb.append(0).append(" "); // 첫 번째 탑은 항상 레이저가 닿는 곳이 없음
		for (int i = 2; i <= N; i++) {
			int height = Integer.parseInt(st.nextToken());
			while (true) {
				// 현재 탑의 높이가 앞의 탑보다 더 높다면 더 앞의 탑이랑 비교해야 함
				int frontHeight = stack.peek().height;
				if (height > frontHeight) {
					stack.pop();
					if (stack.isEmpty()) { // 앞의 탑이 없으면 현재 탑을 넣어야 함
						stack.push(new Top(i, height));
						sb.append(0).append(" ");
						break;
					}
				}

				// 현재 탑의 높이가 앞의 탑보다 더 낮다면 앞의 탑에 레이저가 닿게 됨 (높이가 같은 경우 없음)
				else {
					sb.append(stack.peek().num).append(" "); // 레이저가 닿는 탑 기록
					stack.push(new Top(i, height)); // 현재 탑 넣기
					break;
				}
			}
		}
		System.out.println(sb);
	}

	static class Top {
		int num, height;

		public Top(int num, int height) {
			this.num = num;
			this.height = height;
		}
	}
}
