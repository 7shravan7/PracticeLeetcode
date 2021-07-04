package june2020Challenges.week_1_1to7;

public class InvertBinaryTree {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	
	public TreeNode invertTree(TreeNode root) {
		if(root==null) {
			return null;
		}
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.left = right;
		root.right = left;
        return root;
    }
	
	public void printPostOrderTraversal(TreeNode node) {
		if(node==null) {
			return;
		}
		printPostOrderTraversal(node.left);
		printPostOrderTraversal(node.right);
		System.out.print(node.val+",");
	}

	public static void main(String[] args) {
		InvertBinaryTree bt = new InvertBinaryTree();
		TreeNode root = bt.new TreeNode(4);
		root.left = bt.new TreeNode(2);
		root.right = bt.new TreeNode(7);
		root.left.left = bt.new TreeNode(1);
		root.left.right = bt.new TreeNode(3);
		root.right.left = bt.new TreeNode(6);
		root.right.right = bt.new TreeNode(9);
		bt.printPostOrderTraversal(root);
		System.out.println();
		bt.invertTree(root);
		bt.printPostOrderTraversal(root);
	}

}
