import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static char[][] map;
	static int R;
	static int C;
	static int count = 0;
	static boolean flag;
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		
		R = Integer.parseInt(inputs[0]);
		C = Integer.parseInt(inputs[1]);
		
		map = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			inputs = br.readLine().split("");
			for(int j = 0; j < C; j++) {
				map[i][j] = inputs[j].charAt(0);
			}
		}
		
		for(int i = 0; i < R; i++) {
			flag = false;
			setPipe(i, 0);
		}
		
//		for(int i = 0; i < R; i++) {
//			for(int j = 0; j < C; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		System.out.println(count);
		
	}
	
	public static boolean setPipe(int curX, int curY) throws InterruptedException {
		if(flag || 0 > curX || curX >= R || curY > C-1 || map[curX][curY] != '.') {
			return flag;
		}
		
		if(curY == C-1) {
			if(!flag) {
				flag = true;
				count++;
			}
			return flag;
		}
		

		
		map[curX][curY] = '-';
//		
//		for(int i = 0; i < R; i++) {
//			for(int j = 0; j < C; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//		Thread.sleep(400);
		
		for(int i = -1; i < 2; i++) {
			if(setPipe(curX+i, curY+1)) {
				return flag;
			}
		}
		return flag;
		
	}
	
	
	public static List<Point> checkNextColumn(int x, int y) {
		
		List<Point> points = new ArrayList<>();
		
		for(int i = -1; i < 2; i++) {
			if(0 <= x+i && x+i < R && y+1 < C && map[x+i][y+1] == '.') {
				points.add(new Point(x+i, y+1));
			}
		}
		return points;
	}
	
	public static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
}

//15 15
//.xxxxxxxxxx....
//...x.......xxx.
//...x.......x...
//..xx.......xx..
//...x........xx.
//.x.x......x.x..
//...x......xx...
//.x.x....xxx....
//.x....x.x......
//.x.....xx.x....
//.x..x.xx.......
//.....xx........
//....x..........
//......x........
//...............
//    
//답:4