import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map;
	static int n;
	static int k;
	static List<Microbiome> list;
	public static void main(String[] args) throws IOException, InterruptedException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			map = new int[n][n];
			list = new ArrayList<>();
			
			list.add(new Microbiome(-1, -1, -1, 0, -1));
			
			for(int i = 1; i <= k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				
				list.add(new Microbiome(i, x, y, num, direction));
				map[x][y] = i;
			}
			
			for(int i = 0; i < m; i++) {
				for(int j = 1; j < list.size(); j++) {
					Microbiome microbiome = list.get(j);
					if(microbiome.microorganism == 0) {
						continue;
					}
					microbiome.move();
				}
				
				for(int j = 1; j < list.size(); j++) {
					Microbiome microbiome = list.get(j);
					if(microbiome.microorganism == 0) {
						continue;
					}
					microbiome.checkCollision();
				}
			}
			int sum = 0;
			for(int i = 1; i < list.size(); i++) {
				sum += list.get(i).microorganism;
			}
			
			System.out.println("#" + test_case+" "+sum);
		}
	}
	
	public static class Microbiome{
		int idx;
		int x;
		int y;
		int microorganism;
		int direction;
		
		public Microbiome(int idx, int x, int y, int num, int direction) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			microorganism = num;
			this.direction = direction -1;
		}
		
		public void move() {
			map[x][y] = map[x][y] - idx;
			x = x+dx[direction];
			y = y+dy[direction];
			checkBoundary();
			map[x][y] = map[x][y] + idx;
		}
		
		public void checkBoundary() {
			if(x==0 || x==n-1 || y==0 || y==n-1) {
				microorganism = (int) Math.floor(microorganism / 2);
				if(direction%2 == 0) {
					direction++;
				}else {
					direction--;
				}
			}
		}
		
		public boolean checkCollision() {
			if(map[x][y] != idx) {
				List<Microbiome> collidedMicrobiomes = new ArrayList<>();
				int max = 0;
				int sum = 0;
				for(int i = 1; i < list.size(); i++) {
					Microbiome m = list.get(i);
					if(m.x == x && m.y == y) {
						max = Math.max(max, m.microorganism);
						collidedMicrobiomes.add(m);
						sum += m.microorganism;
					}
				}
				for(int i = 0; i < collidedMicrobiomes.size(); i++) {
					Microbiome m = collidedMicrobiomes.get(i);
					if(m.microorganism != max) {
						m.microorganism = 0;
						map[m.x][m.y] -= m.idx; 
					} else {
						m.microorganism = sum;
					}
				}
				
			}
			return false;
		}
	}
	
}