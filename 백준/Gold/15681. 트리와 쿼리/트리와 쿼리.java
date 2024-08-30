import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] graph;
	static int[] dpTable;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken())-1;
		int Q = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N];
		dpTable = new int[N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			graph[a].add(b);
			graph[b].add(a);
		}
		
		Node root = new Node(R);
		makeTree(root);
		countSubTreeNode(root);
	
		for(int i = 0; i < Q; i++) {
			System.out.println(dpTable[Integer.parseInt(br.readLine())-1]);
		}
		
	}
	
	static class Node{
		int id;
		List<Node> childs;
		
		public Node(int id) {
			this.id = id;
			childs = new ArrayList<>();
		}
	}
	
	static void makeTree(Node curNode) {
		visited[curNode.id] = true; 
		for(int child : graph[curNode.id]) {
			if(!visited[child]) {
				Node childNode = new Node(child);
				curNode.childs.add(childNode);
				makeTree(childNode);				
			}
		}
	}
	
	static void countSubTreeNode(Node root) {
		dpTable[root.id] = 1;
		for(Node childNode : root.childs) {
			countSubTreeNode(childNode);
			dpTable[root.id]+=dpTable[childNode.id]; 
		}
	}
	
}