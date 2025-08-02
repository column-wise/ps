import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] matchResults = new int[6][3];
	static int[][] games = new int[15][2];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int idx = 0;
		for(int i = 0; i < 6; i++) {
			for(int j = i+1; j < 6; j++) {
				games[idx][0] = i;
				games[idx][1] = j;
				idx++;
			}
		}

		for(int test_case = 1; test_case <= 4; test_case++) {
			st = new StringTokenizer(br.readLine());
			for(int team = 0; team < 6; team++) {
				for(int result = 0; result < 3; result++) {
					matchResults[team][result] = Integer.parseInt(st.nextToken());
				}
			}

			boolean valid = true;
			boolean possible = false;

			for(int i = 0; i < 6; i++) {
				int totalResult = 0;
				for(int j = 0; j < 3; j++) {
					totalResult += matchResults[i][j];
				}
				if(totalResult != 5) {
					valid = false;
					break;
				}
			}

			if(valid) {
				possible = check(0);
			}
			sb.append(valid && possible ? 1 : 0).append(" ");
		}
		System.out.println(sb);
	}

	public static boolean check(int depth) {
		if(depth == 15) {
			return true;
		}

		int teamA = games[depth][0];
		int teamB = games[depth][1];
		boolean possible = false;

		if(matchResults[teamA][0] > 0 && matchResults[teamB][2] > 0) {
			matchResults[teamA][0]--;
			matchResults[teamB][2]--;
			possible |= check(depth + 1);
			matchResults[teamA][0]++;
			matchResults[teamB][2]++;
		}

		if(matchResults[teamA][1] > 0 && matchResults[teamB][1] > 0) {
			matchResults[teamA][1]--;
			matchResults[teamB][1]--;
			possible |= check(depth + 1);
			matchResults[teamA][1]++;
			matchResults[teamB][1]++;
		}

		if(matchResults[teamA][2] > 0 && matchResults[teamB][0] > 0) {
			matchResults[teamA][2]--;
			matchResults[teamB][0]--;
			possible |= check(depth + 1);
			matchResults[teamA][2]++;
			matchResults[teamB][0]++;
		}

		return possible;
	}
}
