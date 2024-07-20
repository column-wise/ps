import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		String[] inputs = br.readLine().split(" ");
		int[] id = new int[inputs.length];
		for(int i = 0; i<inputs.length; i++) {
			id[i] = Integer.parseInt(inputs[i]);
		}
		
		Arrays.sort(id);
		int count = 0;
		int startIdx = 0;
		int endIdx = id.length - 1;
		
		while(startIdx < endIdx) {
			if(id[startIdx]+id[endIdx] == m) {
				count++;
				endIdx--;
			}else if(id[startIdx]+id[endIdx] > m) {
				endIdx --;
			}else {
				startIdx++;
			}
		}
		System.out.println(count);
	}
}
