import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2668{
    static int N;
    static int[] arr;
    static boolean[] visited, cycle;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        cycle = new boolean[N + 1];
        result = new ArrayList<>();

        // 입력
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 각 숫자에 대해 DFS
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i, new ArrayList<>());
            }
        }

        // 오름차순 정렬
        Collections.sort(result);

        System.out.println(result.size());
        for (int num : result) {
            System.out.println(num);
        }
    }

    public static boolean dfs(int start, List<Integer> path) {
        visited[start] = true; // 현재 노드를 방문 처리
        path.add(start); // 경로에 추가
        int next = arr[start]; // 다음 노드

        if (!visited[next]) { // 다음 노드 방문 안했을 때
            if (dfs(next, path)) {
                return true;
            }
        } else if (path.contains(next)) { // 다음을 포함하고 있을 때(사이클 발생)
            // 사이클에 포함된 숫자들만 추가
            for (int i = path.indexOf(next); i < path.size(); i++) {
                result.add(path.get(i));
            }
            return true;
        }

        return false;
    }
}