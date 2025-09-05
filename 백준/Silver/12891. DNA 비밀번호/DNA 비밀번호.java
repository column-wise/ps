import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		char[] seq = new char[S];
		String str = br.readLine();
		for(int i = 0; i < S; i++) {
			seq[i] = str.charAt(i);
		}

		// A C G T
		int[] requires = new int[4];
		int[] cur = new int[4];
		int count = 0;

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			requires[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		for(int end = 0; end < S; end++) {
			char e = seq[end];
			if(e == 'A') cur[0]++;
			else if(e == 'C') cur[1]++;
			else if(e == 'G') cur[2]++;
			else if(e == 'T') cur[3]++;

			if(end-start == P-1) {
				boolean satisfied = true;
				for(int j = 0; j < 4; j++) {
					if(cur[j] < requires[j]) {
						satisfied = false;
						break;
					}
				}

				if(satisfied) count++;

				char s = seq[start];
				if(s == 'A') cur[0]--;
				else if(s == 'C') cur[1]--;
				else if(s == 'G') cur[2]--;
				else if(s == 'T') cur[3]--;
				start++;
			}
		}

		System.out.println(count);
	}
}
