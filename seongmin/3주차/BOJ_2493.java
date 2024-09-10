import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
		int[] answer = new int[n + 1];
		Stack<int[]> stack = new Stack<>();
		
		for (int i = 1; i <= n; i++) {
			int height = Integer.parseInt(st.nextToken());

			while (!stack.isEmpty() && stack.peek()[1] < height) {
				stack.pop();
			}  // 현재 탑과 이전 탑들 비교

			if (!stack.isEmpty())
				answer[i] = stack.peek()[0];

			stack.push(new int[] {i, height});  // 현재 탑을 스택에 추가
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}
}