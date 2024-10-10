import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] buildings = new int[N];
		double[][] gradients = new double[N][N];
		int max = -1;
		
		for(int i = 0; i < N; i++) {
			buildings[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(gradients[i], -10000);
		}
		
		for(int i = 0; i < N; i++) {
			int ref = buildings[i];
			for(int j = 0; j < N; j++) {
				if(i == j) continue;
				gradients[i][j] = ((double)(ref - buildings[j]) / Math.abs(i-j));
			}
		}
		
		for(int i = 0; i < N; i++) {
			int count = 0;
			double gradientMin = 1000000000;

			for(int j = i-1; j >= 0; j--) {
				if(gradients[i][j] < gradientMin) {
					count ++;
					gradientMin = gradients[i][j];
				}
			}
			
			gradientMin = 1000000000;
			
			for(int j = i+1; j < N; j++) {
				if(gradients[i][j] < gradientMin) {
					count ++;
					gradientMin = gradients[i][j];
				}
			}
			
			max = Math.max(max, count);
		}
		
		System.out.println(max);
	}
}