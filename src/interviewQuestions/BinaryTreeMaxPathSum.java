package interviewQuestions;

/* **Hard**    124. Binary Tree Maximum Path Sum
 * 
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an 
 * edge connecting them. A node can only appear in the sequence at most once. 
 * Note that the path does not need to pass through the root.

   The path sum of a path is the sum of the node's values in the path.

   Given the root of a binary tree, return the maximum path sum of any path.

	Example 1:
				1
			   / \
			  2   3
		Input: root = [1,2,3]
		Output: 6
		Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
		
	Example 2:
				-10
			    /  \
			   9    20
			       /  \
			      15   7
		Input: root = [-10,9,20,null,null,15,7]
		Output: 42
		Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

	Constraints:
		The number of nodes in the tree is in the range [1, 3 * 104].
		-1000 <= Node.val <= 1000
 */
public class BinaryTreeMaxPathSum {
	
	int maxPathSum;
	
	private int dfs(TreeNode root) {
		if(root == null) {
			return 0;
		}
		int left = dfs(root.left);
		int right = dfs(root.right);
		maxPathSum = Math.max(maxPathSum, left+root.val+right);
		return root.val+ Math.max(left,right); // Errichto used Math.max(0, root.val+ Math.max(left,right));
	}
	
	/*
	 * Time Complexity : O(n)
	 * Space Complexity: O(h) h - height of tree (recursion stack space)
	 */
	public int maxPathSum(TreeNode root) {
		maxPathSum = Integer.MIN_VALUE;
		dfs(root);
		return maxPathSum;
	}

	public static void main(String[] args) {
		BinaryTreeMaxPathSum binaryTreeMaxPathSum = new BinaryTreeMaxPathSum();
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);
		System.out.println("Max Path sum root1 : "+binaryTreeMaxPathSum.maxPathSum(root1));
		TreeNode root2 = new TreeNode(-10);
		root2.left = new TreeNode(-9);
		root2.right = new TreeNode(-20);
		root2.right.left = new TreeNode(-15);
		root2.right.right = new TreeNode(-7);
		System.out.println("Max Path sum root2 : "+binaryTreeMaxPathSum.maxPathSum(root2));
	}

}
