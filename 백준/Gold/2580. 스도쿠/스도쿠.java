import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] board = new int[9][9];
		for(int i = 0; i < 9; i++) {
			String[] inputs = br.readLine().split(" ");
			for(int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		BT(board, 0, 0);
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	public static boolean BT(int[][] map, int startX, int startY) {
		for(int i = startX; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(map[i][j] == 0) {
					List<Integer> list = new ArrayList<>();
					Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9);
					rowCheck(map, list, i, j);
					columnCheck(map, list, i, j);
					boxCheck(map, list, i, j);
					if(list.isEmpty()) {
						return false;
					}
					for(int n:list) {
						map[i][j] = n;
						boolean completed = BT(map, i, 0);
						if(completed) {
							return true;
						}
						map[i][j] = 0;
					}
					
					return false;

				}
			}
		}
		return true;
	}
	
	public static List<Integer> rowCheck(int[][] map, List<Integer> list, int x, int y) {
		for(int i = 0; i < 9; i++) {
			if(list.contains(map[x][i])) {
				list.remove((Integer)map[x][i]);
			}
		}
		return list;
	}
	
	public static List<Integer> columnCheck(int[][] map, List<Integer> list, int x, int y) {
		for(int i = 0; i < 9; i++) {
			if(list.contains(map[i][y])) {
				list.remove((Integer)map[i][y]);
			}
		}
		return list;
	}
	
	public static List<Integer> boxCheck(int[][] map, List<Integer> list, int x, int y) {
		int r = (int) Math.floor(x/3)*3;
		int c = (int) Math.floor(y/3)*3;
		for(int i = r; i < r+3; i++) {
			for(int j = c; j < c+3; j++) {
				if(list.contains(map[i][j])) {
					list.remove((Integer)map[i][j]);
				}
			}
		}
		return list;
	}
	
}
