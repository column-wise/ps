import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static List<Integer> selectedHoney1;
	static List<Integer> selectedHoney2;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			int max = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] reducedDimension = new int[N*N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					reducedDimension[i*N+j] = map[i][j];
				}
			}
			
			int start1;
			int start2;
			int end1;
			int end2;
			
			for(int i = 0; i < N*N-M+1; i++) {
				start1 = i;
				end1 = i+M-1;
				if(start1/N != end1/N) {
					continue;
				}
				for(int j = end1 + 1; j < N*N-M+1; j++) {
					start2 = j;
					end2 = j+M-1;
					if(start2/N != end2/N) {
						continue;
					}
					
					for(int a = 0; a < (1<<M); a++) {
						int total1 = 0;
						selectedHoney1 = new ArrayList<>();
						for(int b = 0; b < M; b++) {
							if((a&(1<<b)) != 0) {
								
								if(total1+reducedDimension[start1+b] <= C) {
									total1 += reducedDimension[start1+b];
									selectedHoney1.add(reducedDimension[start1+b]);
								}
							}
						}
						
						for(int c = 0; c < (1<<M); c++) {
							int sum = 0;
							int total2 = 0;
							selectedHoney2 = new ArrayList<>();
							for(int d = 0; d < (1<<M); d++) {
								if((c&(1<<d)) != 0) {
									if(total2 + reducedDimension[start2+d] <= C) {
										total2 += reducedDimension[start2+d];
										selectedHoney2.add(reducedDimension[start2+d]);
									}
								}
							}
							
							for(int honey : selectedHoney1) {
								//System.out.print("honey1: " + honey+" ");
								sum += honey*honey;
							}
							//System.out.println();
							
							for(int honey : selectedHoney2) {
								//System.out.print("honey2: " + honey+" ");
								sum += honey*honey;
							}
							//System.out.println();
							//System.out.println("sum: " + sum);
							
							
							max = Math.max(max, sum);
							//Thread.sleep(1000);
						}
					}
					
				}
				
			}
			System.out.println("#"+test_case+" "+max);
		}
	}
}