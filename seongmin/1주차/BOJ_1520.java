import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution {
    static int n, m;
    static int[][] map, dp;
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dp = new int[n][m]; // 경로 수를 저장할 dp 배열 (메모이제이션)

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(new Point(0, 0))); // 출발점은 좌상단 고정
    }

    static int dfs(Point p) {
        int cx = p.x, cy = p.y;
        // 도착점에 도달한 경우
        if (cx == n - 1 && cy == m - 1) {
            return 1;
        }

        // 방문한 적이 없는 경우
        if (dp[cx][cy] == -1) {

            dp[cx][cy] = 0;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx > n - 1 || ny < 0 || ny > m - 1)
                    continue;

                // 내리막 길인 경우
                if (map[nx][ny] < map[cx][cy]) {
                    dp[cx][cy] += dfs(new Point(nx, ny));
                }
            }
        }
        return dp[cx][cy];
    }
}
