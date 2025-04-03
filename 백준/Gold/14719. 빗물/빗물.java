import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		boolean[][] world = new boolean[H][W];
		int sum = 0;

		st = new StringTokenizer(br.readLine());
		for(int j = 0; j < W; j++) {
			int h = Integer.parseInt(st.nextToken());
			for(int i = 0; i < h; i++) {
				world[i][j] = true;
			}
		}

		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(world[i][j]) {
					int empty = 0;
					while(j+empty+1 < W && !world[i][j+empty+1]) {
						empty++;
					}

					if(j+empty+1 != W) sum += empty;
					j += empty;
				}
			}
		}

		System.out.println(sum);
	}
}