import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[] parents;
	static int N;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeSet();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 0) continue;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];

					if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
					if(map[nx][ny] == 0) continue;
					union(i*N+j, nx*N+ny);
				}
			}
		}
		
		for(int i = N-1; i > -1; i--) {
			for(int j = N-1; j > -1; j--) {
				if(map[i][j] == 0) continue;
				for(int k = 3; k > -1; k--) {
					int nx = i + dx[k];
					int ny = j + dy[k];

					if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
					if(map[nx][ny] == 0) continue;
					union(i*N+j, nx*N+ny);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(parents[i*N+j] != -1) {
					Deque<Bridge> queue = new ArrayDeque<>();
					boolean visited[] = new boolean[N*N];
					int start = parents[i*N+j];
					
					queue.add(new Bridge(i*N+j, 0));
					visited[i*N+j] = true;
					
					while(!queue.isEmpty()) {
						Bridge cur = queue.poll();
						int coord = cur.coord;
						int length = cur.length;
						
						if(parents[coord] != -1 && parents[coord] != start) {
							min = Math.min(min, length);
							break;
						}
						
						if(length >= min) break;
						
						for(int k = 0; k < 4; k++) {
							int nx = coord/N + dx[k];
							int ny = coord%N + dy[k];
							
							if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
							if(parents[nx*N+ny] == start) continue;
							if(visited[nx*N+ny]) continue;
							
							queue.add(new Bridge(nx*N+ny, length+1));
							visited[nx*N+ny] = true;
						}
					}
					
				}
			}
		}
		
		System.out.println(min-1);

	}
	
	static void makeSet() {
		parents = new int[N*N];
		for(int i = 0; i < N*N; i++) {
			if(map[i/N][i%N] != 0) {
				parents[i] = i;
			} else {
				parents[i] = -1;
			}
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) return false;
		
		if(rootA < rootB) {
			parents[rootB] = rootA;
		} else {
			parents[rootA] = rootB;
		}
		
		return true;
	}
	
	static class Bridge{
		int coord;
		int length;
		
		private Bridge(int coord, int length) {
			this.coord = coord;
			this.length = length;
		}
	}
}