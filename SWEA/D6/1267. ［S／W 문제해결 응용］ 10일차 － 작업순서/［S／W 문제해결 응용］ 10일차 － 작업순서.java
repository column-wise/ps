import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			List<List<Integer>> adjList = new ArrayList<>();
			for(int i = 0; i < V; i++) {
				adjList.add(new ArrayList<>());
			}
			
			int[] inDegree = new int[V];
			List<Integer> topologicalOrder = new ArrayList<>();
			Deque<Integer> queue = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken())-1;
				int to = Integer.parseInt(st.nextToken())-1;
				adjList.get(from).add(to);
				inDegree[to] ++;
			}
			
			for(int i = 0; i < V; i++) {
				if(inDegree[i] == 0) {
					queue.offer(i);
				}
			}
			
			while(!queue.isEmpty()) {
				int v = queue.poll();
				topologicalOrder.add(v);
				
				for(int neighbor:adjList.get(v)) {
					inDegree[neighbor] --;
					if(inDegree[neighbor] == 0) {
						queue.offer(neighbor);
					}
				}
			}
			
			System.out.print("#"+test_case+" ");
			for(int i = 0; i < V; i++) {
				System.out.print(topologicalOrder.get(i)+1 +" ");
			}
			System.out.println();
			
		}
	}
}