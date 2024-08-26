import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static char[] alphabets = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','W','V','X','Y','Z'};
	static int alphabetNum = 26;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		List<Alphabet> list = new ArrayList<>();
		for(int i = 0; i < alphabets.length; i++) {
			list.add(new Alphabet(alphabets[i], 0));
		}
		
		for(int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			int s = str.length;
			for(int j = s-1; j >= 0; j--) {
				int n = (int)Math.pow(10, s-1-j);
				
				for(int k = 0; k < alphabetNum; k++) {
					if(list.get(k).a == str[j].charAt(0)) {
						list.get(k).n += n;
						break;
					}
				}
			}
			
		}
		
		Collections.sort(list);
		
		int i = 0;
		int multiplier = 9;
		int sum = 0;
		while(list.get(i).n != 0) {
			sum += list.get(i).n * multiplier;
			i++;
			multiplier--;
		}
		
		System.out.println(sum);
		
	}
	
	public static class Alphabet implements Comparable<Alphabet>{
		char a;
		int n;
		
		public Alphabet(char a, int n) {
			this.a = a;
			this.n = n;
		}

		@Override
		public String toString() {
			return "Alphabet [a=" + a + ", n=" + n + "]";
		}

		@Override
		public int compareTo(Alphabet o) {
			return -(n-o.n);
		}
	}
}