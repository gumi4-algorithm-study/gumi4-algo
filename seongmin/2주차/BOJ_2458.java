import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, m, edge, answer;
    static boolean[] visited;
    static ArrayList<Integer>[] inMap, outMap;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        inMap = new ArrayList[n + 1];
        outMap = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            inMap[i] = new ArrayList<>();
            outMap[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            outMap[a].add(b);
            inMap[b].add(a);
        }

        answer = 0;
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            edge = 0;
            dfs(i, outMap);
            int outEdge = edge;  // i번째로 나가는 간선
            visited = new boolean[n + 1];

            edge = 0;
            dfs(i, inMap);
            int inEdge = edge;   // i번째로 들어오는 간선
            visited = new boolean[n + 1];

            if (inEdge + outEdge == n + 1) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static void dfs(int cur, ArrayList<Integer>[] map) {
        visited[cur] = true;  // 현재 방문
        edge++;

        for (int next : map[cur]) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, map);
            }
        }
    }
}