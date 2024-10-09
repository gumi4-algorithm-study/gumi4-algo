import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1504 {

    public static class Pair implements Comparable<Pair> {
        int end, cost;

        public Pair(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair o) {
            return this.cost - o.cost;
        }
    }

    static int N, E;
    static ArrayList<Pair>[] adjList;
    static int[] cost;
    static boolean[] visited;
    static final int INF = 200000000; // 값 설정

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        cost = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int co = Integer.parseInt(st.nextToken());
            adjList[a].add(new Pair(b, co));
            adjList[b].add(new Pair(a, co));
        }

        st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());

        // 1 -> p1 -> p2 -> N
        int path1 = 0;
        path1 += dijkstra(1, p1);
        path1 += dijkstra(p1, p2);
        path1 += dijkstra(p2, N);

        // 1 -> p2 -> p1 -> N
        int path2 = 0;
        path2 += dijkstra(1, p2);
        path2 += dijkstra(p2, p1);
        path2 += dijkstra(p1, N);

        // 두 경로가 모두 유효하지 않을 때 -> -1
        if (path1 >= INF && path2 >= INF) {
            System.out.println("-1");
        } else {
            System.out.println(Math.min(path1, path2));
        }
    }

    // 다익스트라 알고리즘
    static int dijkstra(int start, int end) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Arrays.fill(visited, false);
        Arrays.fill(cost, INF);

        cost[start] = 0;              // 시작 정점의 비용 -> 0 설정
        pq.add(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (visited[current.end]) continue;  // 이미 방문한 정점은 넘김
            visited[current.end] = true;         // 현재 정점 방문 처리

            for (Pair next : adjList[current.end]) {
                // 현재 정점을 거쳐서 가는 것이 더 짧은 경우 비용 갱신
                if (cost[next.end] > current.cost + next.cost) {
                    cost[next.end] = current.cost + next.cost;
                    pq.add(new Pair(next.end, cost[next.end]));
                }
            }
        }

        // 도착지점까지의 최소비용
        return cost[end];
    }
}
