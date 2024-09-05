import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2458 {

	static int N, M;
	static int[] count; // 각 정점과 연결된 정점의 수
	static boolean[] visited;
	static ArrayList<Integer>[] originList, reverseList; // 순방향, 역방향

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		count = new int[N + 1];
		visited = new boolean[N + 1];
		originList = new ArrayList[N + 1];
		reverseList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			originList[i] = new ArrayList<>();
			reverseList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			originList[a].add(b); // 순방향
			reverseList[b].add(a); // 역방향
		}

		for (int i = 1; i <= N; i++) {
			Arrays.fill(visited, false);
			dfs(i, originList); // 순방향 DFS 탐색

			Arrays.fill(visited, false);
			dfs(i, reverseList); // 역방향 DFS 탐색

		}
		int ans = 0;
		for (int a : count) { // 모든 정점과 연결되어 있다면 순서를 알 수 있음!
			if (a == N - 1)
				ans++;
		}

		System.out.println(ans);
	}

	static void dfs(int x, ArrayList<Integer>[] list) {
		visited[x] = true;

		for (int a : list[x]) {
			if (visited[a])
				continue;

			count[a]++; // 연결된 정점의 수 증가
			dfs(a, list);
		}
	}
}
