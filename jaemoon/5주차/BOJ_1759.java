import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class BOJ_1759 {

	static int l, c;
	static String strs[], sel[];
	static List<String>	res = new ArrayList<>();
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		l = sc.nextInt();
		c = sc.nextInt();
		
		strs = new String[c];
		
		
		for (int i = 0; i < c; i++) {
			strs[i] = sc.next();
		}
		
		Arrays.sort(strs);
		sel = new String[l];
		combination(0, 0);
	}
	
	static void combination(int cnt, int start) {
		
		if (cnt == l) {
			String result = "";
			int m = 0;
			int j = 0;
			for (int i = 0; i < l; i++) {
				result += sel[i];
				if (String.valueOf(sel[i]).matches("[aeiou]")) {
					m++;
				} else {
					j++;
				}
			}
			
			if (m >= 1 && j >= 2) {				
				System.out.println(result);
			}
			return;
		}
		
		for (int i = start; i < c; i++) {
			sel[cnt] = strs[i];
			combination(cnt + 1, i + 1);
		}	
	}
}

