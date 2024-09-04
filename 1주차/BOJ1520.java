
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520 {
    static int arr[][];
    static int dx[] = { 0, 0, 1, -1 };
    static int dy[] = { 1, -1, 0, 0 };
    static int n, m;
    // dp자체로 방문처리 체크
    static int dp[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 입력 끝

        System.out.println(dfs(0, 0));

    }

    static int dfs(int x, int y) {
        if (x == n - 1 && y == m - 1) {
            return 1;
        }

        int cur = arr[x][y];

        // 이미 방문한 곳이면 그 값 리턴
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        // 방문하지 않은 곳
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                continue;

            if (arr[nx][ny] < cur) {
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y];
    }
}