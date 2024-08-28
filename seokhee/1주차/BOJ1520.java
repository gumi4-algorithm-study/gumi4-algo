
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520 {
    static int arr[][];
    static boolean visited[][];
    static int dx[] = { 0, 0, 1, -1 };
    static int dy[] = { 1, -1, 0, 0 };
    static int n, m;
    static int res;
    static int dp[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 입력

        res = 0;
        dfs(0, 0);

        System.out.println(res);

    }

    static void dfs(int x, int y) {
        if (x == n - 1 && y == m - 1) {
            res++;
            return;
        }

        visited[x][y] = true;
        int cur = arr[x][y];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                continue;

            if (visited[nx][ny])
                continue;

            if (arr[nx][ny] < cur) {
                dfs(nx, ny);
            }
        }

        visited[x][y] = false;

    }
}