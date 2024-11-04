import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1283 {
    static int N;
    static boolean[] alphabets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        alphabets = new boolean[26];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            System.out.println(check(s));
        }
    }

    public static String check(String s) {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(s, " ");
        boolean flag = false; // 단축키가 발견 여부

        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            char first = str.charAt(0);
            char firstToLower = Character.toLowerCase(first);

            if (!alphabets[firstToLower - 'a']) {  // 단축키로 지정 안되어 있을 때
                alphabets[firstToLower - 'a'] = true; // 단축키 지정
                str = "[" + first + "]" + str.substring(1); // 문자열 업데이트
                flag = true; // 단축키 발견
            }
            sb.append(str).append(" ");
        }

        // 단축키가 발견되지 않았을 때
        if (!flag) {
            sb = new StringBuilder();
            boolean isSolved = false; // 단축키 지정 여부

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                char cToLower = Character.toLowerCase(c);

                if (cToLower >= 'a' && cToLower <= 'z') { // 범위 확인
                    if (!alphabets[cToLower - 'a'] && !isSolved) {  // 단축키로 지정되지 않은 알파벳일 때
                        alphabets[cToLower - 'a'] = true;
                        isSolved = true;
                        sb.append("[").append(c).append("]"); // 원래 문자
                    } else {
                        sb.append(c);  // 이미 단축키일 때
                    }
                } else {
                    sb.append(c);  // 알파벳이 아니면 추가
                }
            }
        }

        return sb.toString().trim();
    }
}
