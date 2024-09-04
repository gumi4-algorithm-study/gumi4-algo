import java.io.*;
import java.util.*;

public class BOJ2458 {
    static int N, M;
    static ArrayList<Integer>[] arr;
    static int in[], out[];
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        in = new int[N + 1];
        out = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            dfs(i, i);
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (in[i] + out[i] == N - 1) {
                answer++;
            }
        }

        System.out.println(Arrays.toString(out));
        System.out.println(answer);

    }

    static void dfs(int origin, int start) {
        visited[start] = true;
        for (int nextNode : arr[start]) {
            if (!visited[nextNode]) {
                in[nextNode]++;
                out[origin]++;
                dfs(origin, nextNode);
            }
        }
    }

}