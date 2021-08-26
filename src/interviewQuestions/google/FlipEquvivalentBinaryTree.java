package interviewQuestions.google;

/*  **Medium**       951. Flip Equivalent Binary Trees
 * 
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and 
 * right child subtrees.

   A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after 
   some number of flip operations.

   Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivelent or
    false otherwise.

	Example 1:
		Flipped Trees Diagram
		Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
		Output: true
		Explanation: We flipped at nodes with values 1, 3, and 5.
		
	Example 2:
		Input: root1 = [], root2 = []
		Output: true
		
	Example 3:
		Input: root1 = [], root2 = [1]
		Output: false
		
	Example 4:
		Input: root1 = [0,null,1], root2 = []
		Output: false
		
	Example 5:
		Input: root1 = [0,null,1], root2 = [0,1]
		Output: true

	Constraints:
		The number of nodes in each tree is in the range [0, 100].
		Each tree will have unique node values in the range [0, 99].
 */
public class FlipEquvivalentBinaryTree {
	
	/*
	 * Time Complexity : O(min(n1,n2)) n1,n2 are lengths of root1 and root2
	 * Space Complexity: O(min(h1,h2)) h1,h2 are height of the trees root1 and root2
	 */
	public boolean flipEquiv(TreeNode root1, TreeNode root2) {
		if(root1==null && root2==null) {
			return true;
		} else if (root1 == null || root2 == null) {
			return false;
		} else {
			//System.out.println(root1.val+":"+root2.val);
			return root1.val == root2.val && (
					(flipEquiv(root1.left,root2.left) && flipEquiv(root1.right,root2.right)) || 
					(flipEquiv(root1.left,root2.right) && flipEquiv(root1.right,root2.left)));
		}
}


	public static void main(String[] args) {
		//root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
		FlipEquvivalentBinaryTree flipBT = new FlipEquvivalentBinaryTree();
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);
		root1.left.left = new TreeNode(4);
		root1.left.right = new TreeNode(5);
		root1.left.right.left = new TreeNode(7);
		root1.left.right.right = new TreeNode(8);
		root1.right.left = new TreeNode(6);
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(3);
		root2.right = new TreeNode(2);
		root2.left.right = new TreeNode(6);
		root2.right.left = new TreeNode(4);
		root2.right.right = new TreeNode(5);
		root2.right.right.left = new TreeNode(8);
		root2.right.right.right = new TreeNode(7);
		System.out.println(flipBT.flipEquiv(root1, root2));
	}

}
