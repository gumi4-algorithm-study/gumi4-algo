import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1043 {

	static int N, M, K;
	static int[] parents, people;
	static ArrayList<Integer>[] party;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		make();
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		if (K == 0) { // 진실을 아는 사람이 없으면 "파티의 수 = 과장된 이야기를 할 수 있는 파티 개수"
			System.out.println(M);
			return;
		}

		people = new int[K];
		people[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < K; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			parents[people[i]] = people[0]; // 제일 첫 번째 사람을 루트로 한 뒤 하나의 그룹으로 만들기
		}

		party = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int first = Integer.parseInt(st.nextToken());
			party[i].add(first);

			// 같은 파티에 속하는 사람끼리 연결하기 (처음 사람을 기준으로 연결)
			for (int j = 1; j < p; j++) {
				int now = Integer.parseInt(st.nextToken());
				party[i].add(now);
				union(first, now);
			}
		}

		int ans = 0, root = find(people[0]);
		for (int i = 0; i < M; i++) {
			// 진실을 아는 사람과 같은 그룹이 아니면 과장된 이야기를 할 수 있는 파티
			if (root != find(party[i].get(0))) {
				ans++;
			}
		}
		System.out.println(ans);
	}

	static void make() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (a == parents[a])
			return a;

		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}
}
