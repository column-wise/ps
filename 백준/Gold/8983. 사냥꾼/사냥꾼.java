import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		int[] launchPads = new int[m];
		Animal[] animals = new Animal[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			launchPads[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			animals[i] = new Animal(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		boolean[] visited = new boolean[n];
		
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!visited[i] && Math.abs(animals[i].x-launchPads[j]) + animals[i].y <= l) {
					visited[i] = true;
					count ++;
				}
			}
		}
		
		System.out.println(count);
		
	}
	
	public static class Animal{
		int x;
		int y;
		
		public Animal(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}