import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_1043 거짓말
public class BOJ_1043 {
    static int N, M; // 사람의 수, 파티 수
    static boolean[] isPossible;    // 거짓말 가능한 사람인지 확인
    static int[] parents;   // 그룹
    static int[][] party;   // 파티 정보 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        isPossible = new boolean[N + 1];
        Arrays.fill(isPossible, true);
        parents = new int[N + 1];
        make(); // 초기화

        // 진실을 말해야 하는 사람이 있을 때
        int ppl = Integer.parseInt(st.nextToken());
        if (ppl != 0) {
            int first = Integer.parseInt(st.nextToken());
            isPossible[first] = false;
            for (int i = 0; i < ppl - 1; i++) { // 거짓말 불가
                int num = Integer.parseInt(st.nextToken());
                isPossible[num] = false;
                union(first, num);  // 같은 그룹으로 묶기
            }
        }


        // 파티 수만큼 확인
        party = new int[M][];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken());  // 사람 수
            party[i] = new int[p];  // 사람 수만큼 초기화
            party[i][0] = Integer.parseInt(st.nextToken());
            for (int j = 1; j < p; j++) {   // 첫번째 기준으로 union
                party[i][j] = Integer.parseInt(st.nextToken());
                union(party[i][0], party[i][j]);
            }
        }

        int count = 0;
        for (int[] p : party) {
            boolean flag = true;
            for (int person : p) {  // 거짓말 못하는 사람이 섞여있는지 확인
                if (!isPossible[findSet(person)]) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void make() { // 그룹 초기화
        for (int i = 1; i <= N; i++) {
            parents[i] = i; // 부모를 본인으로 설정
        }
    }

    public static int findSet(int a) {
        if (parents[a] == a) {    // 자기자신이 부모일 때
            return a;
        }

        return parents[a] = findSet(parents[a]);
    }

    public static void union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);

        if (rootA != rootB) {

            // 거짓말 못하는 사람이 있을 때 반영
            if (!isPossible[rootA] || !isPossible[rootB]) {
                isPossible[rootB] = false;
                isPossible[rootA] = false;
            }
            parents[rootA] = rootB; // union
        }
    }
}
