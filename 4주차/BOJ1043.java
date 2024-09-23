// 4 5
// 1 1
// 1 1
// 1 2
// 1 3
// 1 4
// 2 4 1

import java.io.*;
import java.util.*;

public class BOJ1043 {
  static int n, partyNum;
  static int m, truthNum;
  static ArrayList<Integer> truth;
  static int[] person;
  static ArrayList<Integer>[] partyList;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine(), " ");
    n = Integer.parseInt(st.nextToken());
    partyNum = Integer.parseInt(st.nextToken());
    partyList = new ArrayList[partyNum];
    for (int i = 0; i < partyNum; i++) {
      partyList[i] = new ArrayList<>();
    }

    // make
    person = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      person[i] = i;
    }

    st = new StringTokenizer(br.readLine(), " ");
    m = Integer.parseInt(st.nextToken());

    // 진실을 알고있는 사람 배열
    truth = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      truth.add(Integer.parseInt(st.nextToken()));
    }

    for (int i = 0; i < partyNum; i++) {
      st = new StringTokenizer(br.readLine(), " ");

      // 파티에 참가하는 사람 수
      int num = Integer.parseInt(st.nextToken());

      int person = Integer.parseInt(st.nextToken());
      partyList[i].add(person);

      // 진실을 아는 사람이 있다면 같이 묶어주기
      for (int k = 1; k < num; k++) {
        int otherPerson = Integer.parseInt(st.nextToken());
        union(person, otherPerson);
        partyList[i].add(otherPerson);
      }
    }

    int cnt = 0;
    for (int i = 0; i < partyNum; i++) {
      boolean isLie = false;
      for (int node : partyList[i]) {
        if (truth.contains(find(node))) {
          isLie = true;
          break;
        }
      }
      if (!isLie) {
        cnt++;
      }
    }

    System.out.println(cnt);

  }

  static int find(int k) {
    if (person[k] == k) {
      return k;
    }
    // path compression
    person[k] = find(person[k]);
    return person[k];
  }

  static void union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);

    if (rootA != rootB) {
      if (truth.contains(rootA) || truth.contains(rootB)) {
        truth.add(rootA);
        truth.add(rootB);
      }
      person[rootA] = rootB;
    }

  }

}
