import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {

	static int L, C;
	static char[] chars, result;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); // 암호 길이
		C = Integer.parseInt(st.nextToken()); // 가능한 알파벳 개수
		chars = new char[C]; // 알파벳 저장용
		result = new char[L]; // 암호 저장용
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			chars[i] = st.nextToken().charAt(0);
		}
		// 알파벳 정렬
		Arrays.sort(chars);
		dfs(0, 0, 0, 0);
		System.out.println(sb);
	}

	// C개 중에 L개 뽑기 (조합)
	static void dfs(int depth, int start, int a, int b) { // a: 모음의 수, b: 자음의 수
		if (depth == L) {
			// 암호는 최소 1개의 모음, 최소 2개의 자음으로 구성됨
			if (a >= 1 && b >= 2) {
				for (char c : result) {
					sb.append(c);
				}
				sb.append("\n");
			}
			return;
		}

		for (int i = start; i < C; i++) {
			result[depth] = chars[i];
			if (checkChar(chars[i]))
				dfs(depth + 1, i + 1, a + 1, b);
			else
				dfs(depth + 1, i + 1, a, b + 1);
		}
	}

	static boolean checkChar(char c) {
		if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
			return true;
		return false;
	}
}
