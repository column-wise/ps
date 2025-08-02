import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] dices = new int[N][6];

		// A-F, B-D, C-E 인덱스 차 3이 되도록
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			dices[i][0] = Integer.parseInt(st.nextToken());
			dices[i][1] = Integer.parseInt(st.nextToken());
			dices[i][2] = Integer.parseInt(st.nextToken());
			dices[i][4] = Integer.parseInt(st.nextToken());
			dices[i][5] = Integer.parseInt(st.nextToken());
			dices[i][3] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;

		for(int j = 0; j < 6; j++) {
			int sum = 0;
			int bottom = j;
			int top = (bottom+3) % 6;

			for(int i = 0; i < N; i++) {
				sum += maxSide(dices[i], dices[i][bottom], dices[i][top]);

				if(i < N-1) {
					bottom = index(dices[i+1], dices[i][top]);
					top = (bottom+3) % 6;
				}
			}

			answer = Math.max(answer, sum);
		}

		System.out.println(answer);
	}

	public static int maxSide(int[] dice, int bottomValue, int topValue) {
		int max = 0;
		for(int i = 0; i < dice.length; i++) {
			if(dice[i] != bottomValue && dice[i] != topValue) {
				max = Math.max(max, dice[i]);
			}
		}
		return max;
	}

	public static int index(int[] dice, int value) {
		int index = -1;
		for(int i = 0; i < 6; i++) {
			if(dice[i] == value) {
				index = i;
				break;
			}
		}
		return index;
	}
}
