import java.util.HashSet;
import java.util.Scanner;

public class BOJ_1043 {

    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        boolean[] people_know = new boolean[n + 1]; // 알고있으면 T, 아니면 F

        HashSet<Integer>[] parties = new HashSet[m + 1];
        for (int i = 1; i <= m; i++) {
            parties[i] = new HashSet<>();
        }

        int know_num = sc.nextInt();
        for (int i = 0; i < know_num; i++) { // 진실을 아는사람 T
            int tmp = sc.nextInt();
            people_know[tmp] = true;
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            int party_num = sc.nextInt(); // 파티에 오는 사람 수

            if (party_num <= 1) {
                parties[i].add(sc.nextInt());
                continue;
            }

            int firstPerson = sc.nextInt();
            parties[i].add(firstPerson);
            for (int j = 1; j < party_num; j++) { // 연관 관계 이어줌
                int nextPerson = sc.nextInt();
                if (find(firstPerson) != find(nextPerson)) {
                    union(firstPerson, nextPerson);
                }
                parties[i].add(nextPerson);
                firstPerson = nextPerson;
            }
        }

        // T로 변경
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (people_know[i] && !visited[i]) {
                int root = find(i);
                for (int j = 1; j <= n; j++) {
                    if (find(j) == root) {
                        people_know[j] = true;
                        visited[j] = true;
                    }
                }
            }
        }

        // 모든 파티 참석자가 F여야 과장된 얘기를 할 수 있음
        int result = 0;
        for (int i = 1; i <= m; i++) {
            boolean flag = false;
            for (int person : parties[i]) {
                if (people_know[person]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) result++;
        }

        System.out.println(result);
    }

    public static int find(int idx) {
        if (parent[idx] == idx) {
            return idx;
        }
        parent[idx] = find(parent[idx]);
        return parent[idx];
    }

    public static void union(int a, int b) {
        int parent_b = find(b);
        parent[parent_b] = a; // b의 parent를 a로 변경
    }
}

