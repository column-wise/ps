import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		List<List<Integer>> adjList = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			adjList.add(new ArrayList<>());
		}
		
		int[] inDegree = new int[n];
		Deque<Integer> queue = new ArrayDeque<>();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())- 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			inDegree[to]++;
			
			adjList.get(from).add(to);
		}
		
		for(int i = 0; i < n; i++) {
			if(inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		List<Integer> topologicalOrder = new ArrayList<>();
		
		while(!queue.isEmpty()) {
			int v = queue.poll();
			topologicalOrder.add(v);
			
			for(int neighbor : adjList.get(v)) {
				inDegree[neighbor]--;
				if(inDegree[neighbor] == 0) {
					queue.add(neighbor);
				}
			}
			
		}
		
		for(int i = 0; i < topologicalOrder.size(); i++) {
			System.out.print(topologicalOrder.get(i) + 1 +" ");
		}
	}
}