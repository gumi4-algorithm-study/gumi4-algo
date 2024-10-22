import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] map = new int[101];
    static int[] moves = new int[101];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 사다리의 수
        M = Integer.parseInt(st.nextToken()); // 뱀의 수

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            moves[start] = end;
        } // 사다리 + 뱀

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (map[100] != 0) {
                break;
            }

            for (int dice = 1; dice <= 6; dice++) {
                int next = cur + dice;

                if (next > 100) break;

                if (moves[next] != 0) {
                    next = moves[next];
                }

                if (map[next] == 0) {
                    map[next] = map[cur] + 1;
                    queue.offer(next);
                }  // 방문 X
            }
        }
        System.out.println(map[100]);
    }
}