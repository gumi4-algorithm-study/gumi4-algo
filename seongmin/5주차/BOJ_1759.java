import java.io.*;
import java.util.*;

public class Main {

    static int L, C;
    static boolean[] visited;
    static char[] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());  // 암호 제한 길이
        C = Integer.parseInt(st.nextToken());  // 문자의 종류
        map = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            map[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(map);  // 정렬

        // System.out.println(Arrays.toString(map));
        visited = new boolean[C];
        dfs(0, 0);
    }

    static void dfs(int depth, int start) {
        if (depth == L) {
            int check1 = 0, check2 = 0;  // 최소 모음 1개, 자음 2개 확인
            for (int i = 0; i < C; i++) {
                if (visited[i]) {
                    char cur = map[i];
                    if (cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o' || cur == 'u') {
                        check1++;
                    }  // 모음 체크
                    else {
                        check2++;
                    }  // 자음 체크
                    sb.append(map[i]);
                }
            }

            if (check1 >= 1 && check2 >= 2) {
                System.out.println(sb);
            }
            sb.delete(0, L);  // 초기화
            return;
        }

        for (int i = start; i < C; i++) {
            visited[i] = true;
            dfs(depth + 1, i + 1);
            visited[i] = false;
        }
    }
}