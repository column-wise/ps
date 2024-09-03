import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> punch;
	static List<Integer> dx;
	static List<Integer> dy;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int size = (int)Math.pow(2, k);
		int xStart = 0;
		int xEnd = size;
		int yStart = 0;
		int yEnd = size;
		
		int [][] paper = new int[size][size];
		
		while(st.hasMoreTokens()) {
			String command = st.nextToken();
			switch(command) {
			case "U":
				xEnd = xEnd - ((xEnd - xStart)/2);
				break;
			case "D":
				xStart = (xStart+xEnd) / 2;
				break;
			case "R":
				yStart = (yStart+yEnd) / 2;
				break;
			case "L":
				yEnd = yEnd - ((yEnd - yStart)/2);
				break;
			}
		}
		
		int location = Integer.parseInt(br.readLine());
		
		punch = Arrays.asList(0,1,2,3);
		dx = Arrays.asList(2,3,0,1);
		dy = Arrays.asList(1,0,3,2);

		paper[xStart][yStart] = location;
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				int rX = Math.abs(i-xStart)%2;
				int rY = Math.abs(j-yStart)%2;
				int hole = location;
				
				if(rX == 1) {
					hole = dx.get(punch.indexOf(location));
				}
				
				if(rY == 1) {
					hole = dy.get(punch.indexOf(hole));
				}
				
				paper[i][j] = hole;
			}
		}
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				System.out.print(paper[i][j] + " ");
			}
			System.out.println();
		}
	}
}