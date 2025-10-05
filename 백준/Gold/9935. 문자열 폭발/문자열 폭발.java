import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String bomb = br.readLine();
		int sl = s.length();
		int bl = bomb.length();
		char[] charBomb = bomb.toCharArray();
		StringBuilder sb = new StringBuilder(sl);

		for(int i = 0; i < sl; i++) {
			char c = s.charAt(i);
			sb.append(c);

			if(c != charBomb[bl-1] || sb.length() < bl) continue;

			boolean match = true;
			for(int j = 0; j < bl; j++) {
				if(sb.charAt(sb.length() - bl + j) != charBomb[j]) {
					match = false;
					break;
				}
			}

			if(match) {
				sb.delete(sb.length() - bl, sb.length());
			}
		}

		System.out.println(sb.length() == 0 ? "FRULA" : sb);
	}
}
