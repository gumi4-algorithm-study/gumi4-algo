import java.io.*;
import java.util.*;

public class Main {

    static int N, M, answer;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MIN_VALUE;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, map[i][j], 1);
                visited[i][j] = false;
            }
        }
        System.out.println(answer);
    }

    static void dfs(int cx, int cy, int curSum, int cnt) {
        if (cnt == 4) {
            answer = Math.max(answer, curSum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            // 범위를 벗어나거나 이미 방문을 했다면
            if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                continue;
            }

            visited[nx][ny] = true;

            // 'ㅓ ㅗ ㅏ ㅓ'는 2개 블록이 쌓였을 때 dfs 처리 (이전 좌표를 파라미터로 넘겨준다)
            if (cnt == 2) dfs(cx, cy, curSum + map[nx][ny], cnt + 1);

            dfs(nx, ny, curSum + map[nx][ny], cnt + 1);
            visited[nx][ny] = false;  // 백트래킹
        }
    }
}