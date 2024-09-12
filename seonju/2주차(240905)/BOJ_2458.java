import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_2458 키 순서
public class BOJ_2458 {
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        int[] count = new int[N];
        int total = 0; // 자신의 키가 몇 번째인지 알 수 있는 학생 수

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num1 = Integer.parseInt(st.nextToken()) - 1;
            int num2 = Integer.parseInt(st.nextToken()) - 1;

            map[num1][num2] = 1;
        }

        for (int m = 0; m < N; m++) { // 중간 노드
            for (int i = 0; i < N; i++) { // 처음
                for (int j = 0; j < N; j++) { // 끝
                    if (map[i][m] == 1 && map[m][j] == 1) { // 중간 노드를 통해 연결 가능할 때
                        map[i][j] = 1;
                    }
                }
            }
        }

        // 연결된 학생 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 연결됨 -> 본인 제외 N-1이면 모든 학생과 비교했다는 뜻
                if (map[i][j] == 1 || map[j][i] == 1) {
                    count[i]++;
                }
            }
        }

        for (int i : count) {
            if (i == N - 1)
                total++;
        }

        System.out.println(total);
    }
}
