import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2564 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken()); // 가로
		int H = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(br.readLine()); // 상점의 수
		Point[] stores = new Point[M]; // 상점 정보
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			stores[i] = new Point(dir, dist);
		}

		// 동근이 정보
		st = new StringTokenizer(br.readLine());
		int dir = Integer.parseInt(st.nextToken());
		int dist = Integer.parseInt(st.nextToken());

		int minDist = 0;
		for (Point store : stores) {
			if (dir == store.dir) { // 같은 방향이면 둘의 dist만 비교
				minDist += Math.abs(dist - store.dist);
				continue;
			}

			int d1 = -1, d2 = -1;
			// 방향에 따라 최소 거리 구하기
			switch (store.dir) {
			case 1:
				if (dir == 2) {
					d1 = store.dist + H + dist;
					d2 = W - store.dist + H + W - dist;
				} else if (dir == 3) {
					d1 = store.dist + dist;
					d2 = W - store.dist + H + W + H - dist;
				} else {
					d1 = W - store.dist + dist;
					d2 = store.dist + H + W + H - dist;
				}
				break;
			case 2:
				if (dir == 1) {
					d1 = store.dist + H + dist;
					d2 = W - store.dist + H + W - dist;
				} else if (dir == 3) {
					d1 = store.dist + H - dist;
					d2 = W - store.dist + H + W + dist;
				} else {
					d1 = W - store.dist + H - dist;
					d2 = store.dist + H + W + dist;
				}
				break;
			case 3:
				if (dir == 1) {
					d1 = store.dist + dist;
					d2 = H - store.dist + W + H + W - dist;
				} else if (dir == 2) {
					d1 = H - store.dist + dist;
					d2 = store.dist + H + W + W - dist;
				} else {
					d1 = store.dist + W + dist;
					d2 = H - store.dist + W + H - dist;
				}
				break;
			case 4:
				if (dir == 1) {
					d1 = store.dist + W - dist;
					d2 = H - store.dist + W + H + dist;
				} else if (dir == 2) {
					d1 = H - store.dist + W - dist;
					d2 = store.dist + W + H + dist;
				} else {
					d1 = store.dist + W + dist;
					d2 = H - store.dist + W + H - dist;
				}
				break;
			}
			minDist += Math.min(d1, d2); // 최소 거리 계산
		}
		System.out.println(minDist);
	}

	static class Point {
		int dir, dist; // dir: 방향, dist: 거리

		public Point(int dir, int dist) {
			this.dir = dir;
			this.dist = dist;
		}
	}
}
