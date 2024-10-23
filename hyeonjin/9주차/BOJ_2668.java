import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_2668 {

	static int N;
	static int[] arr;
	static boolean[] done, visited;
	static ArrayList<Integer> numberList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		done = new boolean[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (arr[i] == i) {
				done[i] = true;
				numberList.add(i);
			}
		}

		for (int i = 1; i <= N; i++) {
			if (!done[i]) { // 아직 탐색하지 않은 경우
				dfs(i);
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(numberList.size()).append("\n");
		Collections.sort(numberList);
		for (int number : numberList) {
			sb.append(number).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int x) {
		// 이미 탐색을 완료했으면 탐색 종료
		if (done[x])
			return;
		// 탐색을 완료하지 않았는데 이미 방문했으면 사이클에 포함됨
		if (visited[x]) {
			numberList.add(x);
			done[x] = true;
		}

		visited[x] = true;
		dfs(arr[x]); // 연결된 정수로 이동하기
		visited[x] = false;
		done[x] = true; // 사이클 포함 여부를 알았으므로 탐색 완료 처리
	}
}
