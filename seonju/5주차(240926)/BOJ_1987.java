import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ_1987
public class BOJ_1987 {
    static int R, C;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[] alphabets;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        alphabets = new boolean[26];   //알파벳
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j) - 'A';
            }
        }
        count = 0;
        alphabets[map[0][0]] = true;
        dfs(0, 0, 1);  // 좌측 상단 시작

        System.out.println(count);
    }

    public static void dfs(int x, int y, int d) {
        if (count < d) {    // 최대 이동 갱신
            count = d;
        }

        alphabets[map[x][y]] = true;    // 알파벳 방문처리
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx > R - 1 || ny > C - 1) continue;

            // 방문 안했을 때
            if (!alphabets[map[nx][ny]]) {
                dfs(nx, ny, d + 1);
                alphabets[map[nx][ny]] = false; // 방문 안함 처리
            }
        }
    }
}
