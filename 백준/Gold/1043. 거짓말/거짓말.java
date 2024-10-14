import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] parents;
	static Node[] peoples;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parents = new int[N];
		
		peoples = new Node[N];
		for(int i = 0; i < N; i++) {
			peoples[i] = new Node(i, false);
			parents[i] = -1;
		}
		
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		List<Integer>[] parties = new ArrayList[M];
		for(int i = 0; i < M; i++) {
			parties[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < num; i++) {
			peoples[Integer.parseInt(st.nextToken()) - 1].isKnown = true;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int participants = Integer.parseInt(st.nextToken());
			for(int j = 0; j < participants; j++) {
				parties[i].add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
		
		for(int i = 0; i < M; i++) {
			List<Integer> party = parties[i];
			int a = party.get(0);
			
			for(int participant : party) {
				union(a, participant);
			}
		}
		
		int count = 0;
		for(int i = 0; i < M; i++) {
			boolean canLie = true;
			
			for(int participant : parties[i]) {
				if(peoples[find(participant)].isKnown) {
					canLie = false;
					break;
				}
			}
			
			if(canLie) count++;
		}
		
		System.out.println(count);
		
	}
	private static class Node{
		int n;
		boolean isKnown;
		
		public Node(int n, boolean isKnown) {
			super();
			this.n = n;
			this.isKnown = isKnown;
		}		
	}
	
	private static int find(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return false;
		

		if(peoples[aRoot].isKnown){
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
			peoples[bRoot].isKnown = true;
		} else if(peoples[bRoot].isKnown) {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
			peoples[aRoot].isKnown = true;
		} else {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		}
		return true;
	}
}