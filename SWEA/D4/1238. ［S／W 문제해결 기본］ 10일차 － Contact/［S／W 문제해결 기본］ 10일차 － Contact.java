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
		boolean[] visited;
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			Deque<Node> queue = new ArrayDeque<>();
			visited = new boolean[101];
			int maxDepth = 0;
			int maxIdx = 0;
			
			ArrayList<Integer> [] graph = new ArrayList[101];
			for(int i = 0; i < 101; i++) {
				graph[i] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
			}
			
			queue.offer(new Node(start, 0));
			visited[start] = true;
			
			while(!queue.isEmpty()) {
				Node cur = queue.poll();
				int depth = cur.depth;
				
				if(depth > maxDepth) {
					maxIdx = cur.idx;
					maxDepth = depth;
				} else if(depth == maxDepth && cur.idx > maxIdx) {
					maxIdx = cur.idx;
				}
				
				for(int neighbor : graph[cur.idx]) {
					if(!visited[neighbor]) {
						queue.offer(new Node(neighbor, depth+1));
						visited[neighbor] = true;
					}
				}
			}
			
			System.out.println("#"+test_case+" "+maxIdx);
			
		}
	}
	
	public static class Node{
		int idx;
		int depth;
		
		public Node(int idx, int depth) {
			this.idx = idx;
			this.depth = depth;
		}
	}
}