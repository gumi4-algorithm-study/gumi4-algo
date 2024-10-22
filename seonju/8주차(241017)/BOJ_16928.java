import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;


// BOJ_16928 뱀과 사다리 게임
public class BOJ_16928 {
    static int N, M;
    static int[] board; // 100까지 도달하는지 확인
    static int[] visited; // 방문 확인
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new int[101];
        board = new int[101];
        queue = new LinkedList<>();
        for (int i = 0; i < 101; i++) {
            board[i] = i;
        }

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // 보드에 표시
            board[from] = to;
        }

        int result = bfs(1);
        System.out.println(result - 1);
    }

    public static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = 1;
        while (true) {
            int cur = queue.poll();
            for (int i = 1; i <= 6; i++) {
                int next = cur + i; // 주사위 돌림
                if (next > 100) {
                    continue;
                }
                if (visited[board[next]] == 0) {
                    queue.add(board[next]);
                    visited[board[next]] = visited[cur] + 1;
                }
                if (board[next] == 100) {
                    return visited[next];
                }
            }
        }

    }
}
