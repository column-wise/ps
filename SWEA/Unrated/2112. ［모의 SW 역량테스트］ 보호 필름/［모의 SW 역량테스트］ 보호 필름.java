import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int[][] film;
	static int d;
	static int w;
	static int k;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			film = new int[d][w];
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int min = d;
			int injectionCnt = 0;
			
			// 약품을 투입할 행 선택하는 조합
			for(int i = 0; i < (1<<d); i++) {
				
				injectionCnt = 0;
				int[][] modifiedFilm = new int[d][w];
				
				// 약품을 투입하기로 한 행의 idx를 저장
				List<Integer> injectionIdx = new ArrayList<>();
				for(int j = 0; j < d; j++) {
					if((i&(1<<j)) != 0) {
						injectionCnt ++;
						injectionIdx.add(j);
					}
				}
				
				// 약품 주입하도록 선택 된 행의 개수가 최소값보다 크면
				if(injectionCnt >= min) {
					continue;
				}
				
				// 약품 투입 행에 투입할 약품 종류 조합
				for(int j = 0; j < (1<<injectionCnt); j++) {
					int idx = 0;
					
					// 수정할 필름 복사
					for(int a = 0; a < d; a++) {
						for(int b = 0; b < w; b++) {
							modifiedFilm[a][b] = film[a][b];
						}
					}
					
					for(int k = 0; k < injectionCnt; k++) {
						if((j&(1<<k)) != 0) {
							inject_drug(modifiedFilm, injectionIdx.get(idx), 1);
						} else {
							inject_drug(modifiedFilm, injectionIdx.get(idx), 0);
						}
						
						idx++;
						
					}
					
					if(film_check(modifiedFilm)) {
						min = Math.min(min, injectionCnt);
						break;
					}
				}
			}

			System.out.println("#"+test_case+" "+min);
		}
	}
	
	public static boolean film_check(int[][] modifiedFilm) {
		boolean isPossible = true;
		
		for(int i = 0; i < w; i++) {
			int serialCnt = 1;
			int max = 0;
			for(int j = 1; j < d; j++) {
				if(modifiedFilm[j-1][i] == modifiedFilm[j][i]) {
					serialCnt ++;
				}else {
					serialCnt = 1;
				}
				max = Math.max(max, serialCnt);
			}
			if(max < k) {
				isPossible = false;
				break;
			}
		}
		
		return isPossible;
	}
	
	public static int[][] inject_drug(int[][] modifiedFilm, int d, int drugType) {
		for(int i = 0; i < w; i++) {
			modifiedFilm[d][i] = drugType;
		}
		
		return modifiedFilm;
	}
	
}