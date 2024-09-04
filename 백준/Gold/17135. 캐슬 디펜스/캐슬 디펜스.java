import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int maxCount;
	static final int archorNum = 3;
	static List<List<Integer>> comb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		List<Archor> archorList = new ArrayList<>();
		List<Enemy> enemyList = new ArrayList<>();
		maxCount = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					enemyList.add(new Enemy(i, j));
				}
			}
		}
		
		// MC3 (궁수 3명 위치 선택)
		comb = new ArrayList<>();
		generateComb(M, archorNum, 0, new ArrayList<>());
		
		for(int i = 0; i < archorNum; i++) {
			archorList.add(new Archor(R));
		}
		
		// 각 궁수의 위치 마다 N번 시뮬레이션
		for(int i = 0; i < comb.size(); i++) {
			int count = 0;
			
			// 궁수 위치 설정
			List<Integer> archorLocation = comb.get(i);
			for(int j = 0; j < archorNum; j++) {
				archorList.get(j).y = archorLocation.get(j);
			}
			//System.out.println();
			
			// enemyList 카피
			List<Enemy> copy = new ArrayList<>();
			for(int j = 0; j < enemyList.size(); j++) {
				Enemy e = enemyList.get(j);
				copy.add(new Enemy(e.x, e.y));
			}
			Collections.sort(copy);
			
			// 행 수 만큼 반복
			for(int t = 0; t < N; t++) {
				for(int j = 0; j < archorNum; j++) {
					Archor a = archorList.get(j);
					
					int enemyIdx = a.findClosestEnemy(copy);
					if(enemyIdx == -1) {
						continue;
					}
					Enemy e = copy.get(enemyIdx);
					e.isShooted = true;
				}
				
				for(int j = 0; j < copy.size(); j++) {
					Enemy e = copy.get(j);
					e.x ++;
					if(e.isShooted && !e.dead) {
						count ++;
						e.isShooted = false;
						e.dead = true;
					}
				}
			}
			maxCount = Math.max(maxCount, count);
		}
		System.out.println(maxCount);
	}
	
	static void generateComb(int n, int r, int start, ArrayList chosen) {
		if(chosen.size() == r) {
			comb.add(new ArrayList<>(chosen));
			return;
		}
		
		for(int i = start; i < n; i++) {
			chosen.add(i);
			generateComb(n, r, i+1, chosen);
			chosen.remove(chosen.size()-1);
		}
	}
	
	static class Archor{
		int y;
		int range;
		
		private Archor(int range) {
			this.range = range;
		}
		
		int getDistance(Enemy e) {
			return N-e.x + Math.abs(this.y-e.y);
		}
		
		int findClosestEnemy(List<Enemy> enemyList) {
			int idx = -1;
			int min = N*M;
			
			for(int i = 0; i < enemyList.size(); i++) {
				Enemy e = enemyList.get(i);
				if(e.x >= N || e.dead == true) continue;
				int d = getDistance(e);
				if(d <= range && min > d){
					min = d;
					idx = i;
				}
			}
			return idx;
		}
	}
	
	static class Enemy implements Comparable<Enemy>{
		int x;
		int y;
		boolean isShooted;
		boolean dead;
		
		private Enemy(int x, int y) {
			this.x = x;
			this.y = y;
			isShooted = false;
			dead = false;
		}
		
		@Override
		public String toString() {
			return "Enemy [x=" + x + ", y=" + y + ", isShooted=" + isShooted + ", dead=" + dead + "]";
		}

		@Override
		public int compareTo(Enemy o) {
			// TODO Auto-generated method stub
			return y - o.y;
		}
		
	}
	
}