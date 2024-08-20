import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		int n = Integer.parseInt(inputs[0]);
		int m = Integer.parseInt(inputs[1]);
		
		Map<String,String> name2group = new HashMap<>();
		Map<String,List<String>> group2name = new HashMap<>();
		
		for(int i = 0; i < n; i++) {
			String group = br.readLine();
			int memberNum = Integer.parseInt(br.readLine());
			for(int j = 0; j < memberNum; j++) {
				String name = br.readLine();
				name2group.put(name, group);
				
				if(!group2name.containsKey(group)) {
					group2name.put(group, new ArrayList<String>());
				}
				group2name.get(group).add(name);
			}
		}
		
		for(int i = 0; i < m; i++) {
			String input = br.readLine();
			int quizType = Integer.parseInt(br.readLine());
			
			if(quizType == 1) {
				System.out.println(name2group.get(input));
			}else {
				List<String> members = group2name.get(input);
				Collections.sort(members);
				for(String member:members) {
					System.out.println(member);
				}
			}
		}
		
	}
}