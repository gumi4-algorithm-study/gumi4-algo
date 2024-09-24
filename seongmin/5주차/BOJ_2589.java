import java.io.*;
import java.util.*;

public class Main {

    static int L, W;
    static char[][] map;
    static int[][] move;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        ArrayList<int[]> searchCoor = new ArrayList<>();
        map = new char[L][W];
        for (int i = 0; i < L; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                map[i][j] = tmp[j];
                if (map[i][j] == 'L') {
                    searchCoor.add(new int[]{i, j});
                }  // 탐색할 좌표 저장
            }
        }

        move = new int[L][W];
        int answer = 0;

        for (int[] search : searchCoor) {
            int minDistance = bfs(search[0], search[1]);
            answer = Math.max(answer, minDistance);
        }

        System.out.println(answer);
    }

    static int bfs(int sx, int sy) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {sx, sy});
        move[sx][sy] = 1;
        int distance = 0;  // 최단 거리 계산

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= L || ny < 0 || ny >= W ) {
                    continue;
                }  // 범위 검사

                if (move[nx][ny] == 0 && map[nx][ny] == 'L') {
                    move[nx][ny] = move[cx][cy] + 1;
                    distance = Math.max(distance, move[nx][ny]);
                    queue.offer(new int[] {nx, ny});
                }  // 방문하지 않았고 육지라면
            }
        }

        for (int i = 0; i < L; i++) {
            Arrays.fill(move[i], 0);
        }  // move 배열 초기화
        return distance - 1;  // 시작점이 1이므로
    }
}