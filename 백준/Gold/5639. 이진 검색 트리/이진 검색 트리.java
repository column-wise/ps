import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node BST = null;

		while(true) {
			try {
				int key = Integer.parseInt(br.readLine());
				if(BST == null) {
					BST = new Node(key);
				} else {
					insert(BST, key);
				}
			} catch(Exception e) {
				break;
			}
		}

		postOrderTraversal(BST);

		System.out.println(sb);
	}

	private static class Node {
		int key;
		Node left, right;

		public Node (int key) {
			this.key = key;
			left = right = null;
		}
	}

	private static void insert(Node root, int key) {
		if(root == null) return;
		if(root.key == key) return;
		if(root.key > key) {
			if(root.left != null) insert(root.left, key);
			else root.left = new Node(key);
		} else {
			if(root.right != null) insert(root.right, key);
			else root.right = new Node(key);
		}
	}

	private static void postOrderTraversal(Node root) {
		if(root == null) return;
		if(root.left != null) postOrderTraversal(root.left);
		if(root.right != null) postOrderTraversal(root.right);
		sb.append(root.key).append("\n");
	}
}
