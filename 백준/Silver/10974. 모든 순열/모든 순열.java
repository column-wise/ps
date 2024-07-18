import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int n;

	public static void main(String[] args) throws IOException{
		
		n = Integer.parseInt(reader.readLine());
		BackTracking("");
	}
	
	public static void BackTracking(String str) {
		if(str.length() == n) {
			for(int i = 0; i < str.length(); i++) {
				System.out.print(str.charAt(i)+" ");
			}
			System.out.println();
		}else {
			for(int i = 1; i <= n; i++) {
				String s = Integer.toString(i);
				if(!str.contains(s)) {
					str += s;
					BackTracking(str);
					str = str.substring(0,str.length()-1);
				}
			}
		}
	}
}
