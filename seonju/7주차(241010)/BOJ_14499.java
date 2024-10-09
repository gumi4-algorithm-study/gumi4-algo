import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ_14499
/* 주사위 하단을 dice[5], 상단을 dice[0]으로 지정
 * 한번 굴릴 때마다 지도 내에서 범위 확인한 후 이동
 * map 값 확인하고 갱신
 * */
public class BOJ_14499 {
    static int N, M, x, y, K; // 세로 크기 N, 가로 크기 M, 주사위 좌표 x,y, 명령 개수 K
    static int[][] map;

    // 동서남북
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dice = new int[6];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");   // 이동하는 명령
        for (int i = 0; i < K; i++) {
            int command = Integer.parseInt(st.nextToken());
            int nx = x + dx[command - 1];
            int ny = y + dy[command - 1];
            if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) continue;
            move(x, y, command);   // 주사위 이동
            x = nx;
            y = ny;
            if (map[x][y] == 0) {
                map[x][y] = dice[5];
            } else {
                dice[5] = map[x][y];
                map[x][y] = 0;
            }
            System.out.println(dice[0]);
        }
    }

    public static void move(int x, int y, int command) {
        int temp = 0;
        switch (command) {
            case 1: // 동
                temp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[2];
                dice[2] = temp;
                break;
            case 2: // 서
                temp = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[3];
                dice[3] = temp;
                break;
            case 3: // 북
                temp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[1];
                dice[1] = temp;
                break;
            case 4: // 남
                temp = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[4];
                dice[4] = temp;
                break;
        }
    }
}
