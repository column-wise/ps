import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int maxDepth = 0;
	static int offset = 0;
	static int[] minOffsetPerDepth;
	static int[] maxOffsetPerDepth;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[N+1];
		Node root = null;
		boolean[] called = new boolean[N+1];

		for(int i = 1; i <= N; i++) {
			nodes[i] = new Node(i);
		}

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			if(left != -1) {
				called[left] = true;
				nodes[n].left = nodes[left];
			}
			if(right != -1) {
				called[right] = true;
				nodes[n].right = nodes[right];
			}
		}

		for(int i = 1; i <= N; i++) {
			if(!called[i]) {
				root = nodes[i];
				break;
			}
		}

		int totalWidth = root.init(1);

		minOffsetPerDepth = new int[maxDepth+1];
		Arrays.fill(minOffsetPerDepth, totalWidth);
		maxOffsetPerDepth = new int[maxDepth+1];
		int targetDepth = 0;
		int maxWidth = 0;
		root.check();

		for(int i = 1; i <= maxDepth; i++) {
			if(maxWidth < maxOffsetPerDepth[i] - minOffsetPerDepth[i] + 1) {
				maxWidth = maxOffsetPerDepth[i] - minOffsetPerDepth[i] + 1;
				targetDepth = i;
			}
		}

		System.out.println(targetDepth + " " + maxWidth);
	}

	private static class Node {
		int n;
		Node left, right;
		int width = 0;
		int depth = 0;
		int height = 0;

		Node(int n) {
			this.n = n;
			left = null;
			right = null;
		}

		private int init(int depth) {
			int leftWidth = 0;
			int rightWidth = 0;
			this.depth = depth;
			maxDepth = Math.max(maxDepth, depth);

			if(left != null) leftWidth = left.init(depth + 1);
			if(right != null) rightWidth = right.init(depth + 1);
			width = leftWidth + rightWidth + 1;
			return width;
		}

		private void check() {
			if(left != null) left.check();

			minOffsetPerDepth[depth] = Math.min(minOffsetPerDepth[depth], offset);
			maxOffsetPerDepth[depth] = Math.max(maxOffsetPerDepth[depth], offset);
			offset++;

			if(right != null) right.check();
		}
	}
}
