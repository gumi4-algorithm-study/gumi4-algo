import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_1283 {

	static HashSet<String> key = new HashSet<>();
	static boolean[] used = new boolean[26];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = st.countTokens();
			String[][] words = new String[M][];
			for (int j = 0; j < M; j++) {
				words[j] = st.nextToken().split("");
			}

			// 각 문자의 제일 앞 확인하기
			boolean setting = false;
			for (int j = 0; j < M; j++) {
				// 단어의 첫 글자가 단축키로 지정되어 있지 않다면 단축키로 지정하기
				int word = words[j][0].toLowerCase().charAt(0) - 'a';
				if (!used[word]) {
					used[word] = true;
					words[j][0] = "[" + words[j][0] + "]";
					setting = true;
					break;
				}
			}

			// 첫 글자를 단축키로 지정하지 못한 경우
			if (!setting) {
				// 왼쪽부터 단축키로 지정할 알파벳 찾기
				for (int j = 0; j < M; j++) {
					for (int k = 0; k < words[j].length; k++) {
						int word = words[j][k].toLowerCase().charAt(0) - 'a';
						// 단축키로 지정할 알파벳을 찾은 경우
						if (!used[word]) {
							used[word] = true;
							words[j][k] = "[" + words[j][k] + "]";
							setting = true;
							break;
						}
					}
					if (setting) break;
				}
			}

			for (int j = 0; j < M; j++) {
				for (int k = 0; k < words[j].length; k++) {
					sb.append(words[j][k]);
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
