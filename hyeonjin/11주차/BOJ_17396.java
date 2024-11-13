import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17396 {

	static int N, M;
	static long INF = 10_000_000_000L;
	static long[] minDist;
	static boolean[] isMove;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isMove = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			isMove[i] = Integer.parseInt(st.nextToken()) == 0;
		}
		isMove[N - 1] = true; // N-1번째 분기점은 시야에 보이지만 이동 가능함

		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			// 양방향 연결
			graph[a].add(new Node(b, t));
			graph[b].add(new Node(a, t));
		}
		
		minDist = new long[N];
		System.out.println(dijkstra(0, N - 1));
	}

	static long dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Long.compare(n1.d, n2.d));
		pq.offer(new Node(start, 0));
		Arrays.fill(minDist, INF);
		minDist[start] = 0;

		boolean[] visited = new boolean[N];
		while (!pq.isEmpty()) {
			Node now = pq.poll();

			// 이미 방문했다면 넘어가기
			if (visited[now.x])
				continue;
			visited[now.x] = true;
			if (now.x == end)
				return minDist[end];

			for (Node next : graph[now.x]) {
				// 방문하지 않음 & 시야에 보이지 않음 & now를 거쳐서 갈 때 더 경로가 짧은 경우
				if (!visited[next.x] && isMove[next.x] && minDist[next.x] > minDist[now.x] + next.d) {
					minDist[next.x] = minDist[now.x] + next.d;
					pq.offer(new Node(next.x, minDist[next.x]));
				}
			}
		}
		return -1;
	}

	static class Node {
		int x;
		long d;

		public Node(int x, long d) {
			this.x = x;
			this.d = d;
		}
	}
}
