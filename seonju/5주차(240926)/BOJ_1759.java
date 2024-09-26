import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
    static int L;
    static int C;
    static ArrayList<String> arr;
    static char[] alphabet;
    static char[] vowels = { 'a', 'e', 'i', 'o', 'u' };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();
        alphabet = new char[C];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            alphabet[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alphabet);// 알파벳 순서로 정렬

        track("", 0, 0, 0);// 처음부터 시작, 모음과 자음 개수는 0
        for (String s : arr) {
            System.out.println(s);
        }
    }

    static void track(String str, int idx, int v_count, int c_count) {
        if (str.length() == L) { // 길이 같으면
            if (v_count >= 1 && c_count >= 2) { // 모음이 최소 하나 이상, 자음이 최소 두개 이상일 때만
                arr.add(str); // arr에 저장
            }
            return;
        } else { // 계산
            for (int i = idx; i < C; i++) { // 다음문자 선택
                char c = alphabet[i];
                if (check(c)) { // 모음일 때
                    track(str + c, i + 1, v_count + 1, c_count); // 모음 증가
                } else {
                    track(str + c, i + 1, v_count, c_count + 1); // 자음 증가
                }
            }
        }
    }

    static boolean check(char c) { // 모음인지 체크
        for (char ch : vowels) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }
}
