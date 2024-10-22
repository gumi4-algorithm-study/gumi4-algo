import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();
    static Set<Character> used = new HashSet<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 옵션 개수

        for (int t = 0; t < N; t++) {
            String line = br.readLine();
            String[] words = line.split(" ");
            boolean found = false;

            // 1단계 : 각 단어의 첫 글자 확인
            for (int i = 0; i < words.length; i++) {
                char first = words[i].charAt(0);

                if (!used.contains(Character.toLowerCase(first))) {
                    used.add(Character.toLowerCase(first));
                    words[i] = "[" + first + "]" + words[i].substring(1);
                    found = true;
                    break;
                }
            }

            // 2단계 : 첫 글자로 못 찾을 시 모든 글자 확인
            if (!found) {
                for (int i = 0; i < words.length; i++) {
                    if (found) break;  // 찾았으면 break;

                    String word = words[i];

                    for (int j = 1; j < word.length(); j++) {
                        char next = word.charAt(j);
                        if (!used.contains(Character.toLowerCase(next))) {
                            used.add(Character.toLowerCase(next));
                            words[i] = words[i].substring(0, j) + "[" + next + "]" + words[i].substring(j + 1);
                            found = true;
                            break;
                        }
                    }
                }
            }

            for (String word : words) {
                sb.append(word).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}