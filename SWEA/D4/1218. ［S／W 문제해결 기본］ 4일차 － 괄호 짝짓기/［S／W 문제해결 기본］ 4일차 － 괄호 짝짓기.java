import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Solution {
	static List<String> openBracket = new ArrayList<>(Arrays.asList("(", "[", "{", "<"));
	static List<String> closeBracket = new ArrayList<>(Arrays.asList(")", "]", "}", ">"));
	
	public static void main(String[] agrs) throws IOException{
		int T = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			String[] inputs = br.readLine().split("");
			Deque<String> stack = new ArrayDeque<>();
			boolean isPossible = true;
			
			for(int i = 0; i < n; i++) {
				if(openBracket.contains(inputs[i])) {
					stack.push(closeBracket.get(openBracket.indexOf(inputs[i])));
				} else {
					if(stack.poll().equals(inputs[i])) {
						continue;
					}else {
						isPossible = false;
						break;
					}
				}
			}
			
			if(isPossible) {
				System.out.println("#" + test_case + " " +1);
			}else {
				System.out.println("#" + test_case + " " +0);
			}
			
		}
	}
}