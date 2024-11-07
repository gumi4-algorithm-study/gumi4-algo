import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12851 {
    static int N, K;
    static int[] visited;
    static int count, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 수빈이 있는 위치
        K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치
        visited = new int[100001];
        count = 0;
        min = Integer.MAX_VALUE;
        if (N == K) {
            System.out.println(0);
            System.out.println(1);
        } else if (N > K) {
            System.out.println(N - K);
            System.out.println(1);
        } else {
            bfs();
            System.out.println(min);
            System.out.println(count);
        }
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);// 수빈 위치 넣기
        visited[N]=1;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (min < visited[current]) { // 현재 값보다 크면 최소가 아님 -> 패스
                continue;
            }
            for (int i = 0; i < 3; i++) {
                int next = 0;
                if (i == 0) { // N+1
                    next = current + 1;
                } else if (i == 1) { // N-1
                    next = current - 1;
                } else if (i == 2) { // 2*N
                    next = current * 2;
                }

                if (next < 0 || next > 100000) continue;

                if (next == K) {
                    if (visited[current] < min) {
                        min = visited[current];
                        count = 1;
                    } else {
                        count++;
                    }
                }

                if (visited[next] == 0 || visited[next] == visited[current] + 1) {
                    queue.offer(next);
                    visited[next] = visited[current] + 1;
                }
            }
        }
    }
}
