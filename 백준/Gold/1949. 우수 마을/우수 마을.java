import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[][] DPTable;
	static int[] people;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		people = new int[N];
		graph = new ArrayList[N];
		visited = new boolean[N];
		DPTable = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph[a].add(b);
			graph[b].add(a);
		}
		
		Node root = new Node(0);
		makeTree(root);
		visited = new boolean[N];
		selectBestCity(root, null);
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < 2; j++) {
//				System.out.print(DPTable[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		System.out.println(Math.max(DPTable[root.id][0], DPTable[root.id][1]));
		
	}

	static void makeTree(Node root) {
		visited[root.id] = true; 
		for (int childId : graph[root.id]) {
			if (!visited[childId]) {
				Node child = new Node(childId);
				root.child.add(child);
				makeTree(child);
			}
		}
	}
	
	static void selectBestCity(Node curNode, Node parent) {

		visited[curNode.id] = true; 
		for(Node child : curNode.child) {
			if(!visited[child.id]) {
				selectBestCity(child, curNode);
				DPTable[curNode.id][0] += Math.max(DPTable[child.id][1], DPTable[child.id][0]);
				DPTable[curNode.id][1] += DPTable[child.id][0];
			}
		}
		
		DPTable[curNode.id][1] += people[curNode.id];
	}

	public static class Node {
		int id;
		List<Node> child;

		public Node(int id) {
			this.id = id;
			child = new ArrayList<>();
		}
	}
}