import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static int N, M, answer;
    static int[] parents, trueP;
    static ArrayList<Integer>[] map;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        int T = sc.nextInt();

        // 진실을 아는 사람들의 번호를 배열에 저장
        trueP = new int[T];
        for (int i = 0; i < T; i++) {
            trueP[i] = sc.nextInt();
        }

        // 파티 리스트 초기화 및 입력 받기
        map = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            map[i] = new ArrayList<>();
            int partySize = sc.nextInt();  // 각 파티의 인원 수
            for (int j = 0; j < partySize; j++) {
                map[i].add(sc.nextInt());  // 파티에 참석하는 사람들의 번호 저장
            }
        }

        // parents 배열 초기화
        make();

        // 각 파티에서 같은 파티에 있는 사람들은 모두 같은 그룹으로 묶음 (유니온)
        for (int i = 0; i < M; i++) {
            int first = map[i].get(0);  // 파티에서 첫 번째 사람
            for (int j = 1; j < map[i].size(); j++) {
                union(first, map[i].get(j));  // 같은 파티에 있는 사람끼리 연결
            }
        }

        // 각 파티에 대해 진실을 아는 사람과 같은 그룹에 있는지 확인
        answer = 0;
        for (int i = 0; i < M; i++) {
            boolean possible = true;
            int cur = map[i].get(0);  // 파티의 첫 번째 사람
            for (int j = 0; j < trueP.length; j++) {
                if (findSet(cur) == findSet(trueP[j])) {  // 진실을 아는 사람과 같은 그룹이면
                    possible = false;  // 이 파티에서 거짓말 불가
                    break;
                }
            }
            if (possible) answer++;  // 거짓말이 가능한 파티 수 증가
        }
        System.out.println(answer);
    }

    static void make() {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = -1;
        }
    }

    static int findSet(int a) {
        if (parents[a] < 0) {
            return a;
        }
        return parents[a] = findSet(parents[a]);  // 경로 압축
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }
}