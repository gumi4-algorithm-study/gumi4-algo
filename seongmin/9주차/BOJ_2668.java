import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] seq;
    static boolean[] visited;
    static TreeSet<Integer> set = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        seq = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(br.readLine());
        }  // 1번 인덱스부터 사용

        for (int i = 1; i <= N; i++) {
            dfs(i, i);
        }

        System.out.println(set.size());
        for (int i : set) {
            System.out.println(i);
        }
    }

    static void dfs(int cur, int start) {
        if (visited[cur]) {
            if (cur == start) {
                set.add(cur);
            } // 싸이클 존재 시 두 집합 일치
            return;
        }

        visited[cur] = true;
        dfs(seq[cur], start);
        visited[cur] = false;
    }
}