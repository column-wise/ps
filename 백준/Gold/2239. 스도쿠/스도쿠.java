import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] board;
	static int[][] possible;
	static final int all = (0x1<<9) + (0x1<<8) + (0x1<<7) + (0x1<<6) + (0x1<<5)
			+ (0x1<<4) + (0x1<<3) + (0x1<<2) + (0x1<<1);
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		board = new int[9][9];
		possible = new int[9][9];

		for(int i = 0; i < 9; i++) {
			String[] input = br.readLine().split("");
			for(int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(input[j]);
			}
		}

		int p = findNextBlank(0, -1);

		while(p != -1) {
			int curX = p/9;
			int curY = p%9;
			int possible = findPossibleNumbers(curX, curY);
			boolean isChanged = false;

			for(int i = (board[curX][curY]%10) + 1; i <= 9; i++) {
				if((possible & (0x1 << i)) > 0) {
					board[curX][curY] = i + 10;
					isChanged = true;
					break;
				}
			}

			if(isChanged) {
				p = findNextBlank(curX, curY);
			} else {
				board[curX][curY] = 0;
				p = findPrevBlank(curX, curY);
			}
		}

		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				sb.append(board[i][j]%10);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int findPossibleNumbers(int r, int c) {
		int rowPossible = checkRow(r, c);
		int colPossible = checkColumn(r, c);
		int boxPossible = checkBox(r, c);

		return rowPossible & colPossible & boxPossible;
	}

	private static int checkRow(int r, int c) {
		int possible = all;
		for(int i = 0; i < 9; i++) {
			if(i == c) continue;
			possible ^= (0x1 << (board[r][i]%10));
		}
		return possible;
	}

	private static int checkColumn(int r, int c) {
		int possible = all;
		for(int i = 0; i < 9; i++) {
			if(i == r) continue;
			possible ^= (0x1 << (board[i][c]%10));
		}
		return possible;
	}

	private static int checkBox(int r, int c) {
		int possible = all;
		int sr = (r / 3) * 3;
		int sc = (c / 3) * 3;

		for(int i = sr; i < sr + 3; i++) {
			for(int j = sc; j < sc + 3; j++) {
				possible ^= (0x1 << (board[i][j]%10));
			}
		}
		return possible;
	}

	private static int findNextBlank(int r, int c) {
		for(int i = r * 9 + c + 1; i < 81; i++) {
			if(board[i/9][i%9] == 0) return i;
		}
		return -1;
	}

	private static int findPrevBlank(int r, int c) {
		for(int i = r * 9 + c - 1; i >= 0; i--) {
			if(board[i/9][i%9] > 10) return i;
		}
		return -1;
	}
}
