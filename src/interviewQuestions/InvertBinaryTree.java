package interviewQuestions;

import java.util.LinkedList;
import java.util.Queue;

/* **Easy**      226. Invert Binary Tree
 * 
 * Given the root of a binary tree, invert the tree, and return its root.

	Example 1:
					4                  4 
				  /   \              /   \
				 2     7            7     2
				/ \   / \          / \   / \
			   1   3 6   9        9   6  3  1
		Input: root = [4,2,7,1,3,6,9]
		Output: [4,7,2,9,6,3,1]
		
	Example 2:
					2               2
                   / \             / \
                  1   3           3   1
		Input: root = [2,1,3]
		Output: [2,3,1]
		
	Example 3:
		Input: root = []
		Output: []
 
	Constraints:
		The number of nodes in the tree is in the range [0, 100].
		-100 <= Node.val <= 100
 */
public class InvertBinaryTree {
	
	/*
	 * Time Complexity : O(n)
	 * Space Complexity: O(1)
	 */
	public TreeNode invertTree(TreeNode root) {
		if(root == null) {
			return null;
		}
		if(root.left==null && root.right==null) {
			return root;
		}
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		invertTree(root.left);
		invertTree(root.right);
		return root;
	}
	
	public static void printTree(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		System.out.println("The tree : ");
		while(!queue.isEmpty()) {
			TreeNode t=queue.poll();
			System.out.print(t.val+",");
			if(t.left!=null) {
				queue.add(t.left);
			}
			if(t.right!=null) {
				queue.add(t.right);
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		InvertBinaryTree invertBT = new InvertBinaryTree();
		TreeNode root1 = new TreeNode(4);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(7);
		root1.left.left = new TreeNode(1);
		root1.left.right = new TreeNode(3);
		root1.right.left = new TreeNode(6);
		root1.right.right = new TreeNode(9);
		TreeNode invertRoot1 = invertBT.invertTree(root1);
		printTree(invertRoot1);
		TreeNode root2 = new TreeNode(2);
		root2.left = new TreeNode(1);
		root2.right = new TreeNode(3);
		TreeNode invertRoot2 = invertBT.invertTree(root2);
		printTree(invertRoot2);
		TreeNode root3 = null;
		TreeNode invertRoot3 = invertBT.invertTree(root3);
		System.out.println(invertRoot3);
	}

}
