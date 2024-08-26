import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> list;
	static List<List<Integer>> tree;
	static int k;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		tree = new ArrayList<>();
		
		for(int i = 0; i < k; i++) {
			tree.add(new ArrayList<Integer>());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		fillTree(0,0,list.size()-1);
		
		for(int i = 0; i < k; i++) {
			for(int j = 0; j < tree.get(i).size(); j++) {
				System.out.print(tree.get(i).get(j)+" ");
			}
			System.out.println();
		}
	}
	
	public static void fillTree(int level, int start, int end) {
		tree.get(level).add(list.get((start+end)/2));
		if(level == k-1) {
			return;
		} else {
			fillTree(level+1, start, (start+end)/2 - 1);
			fillTree(level+1, (start+end)/2+1, end);
		}
	}
}