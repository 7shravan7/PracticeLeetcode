package interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/* **Medium**    366. Find Leaves of Binary Tree
 * 
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
		Collect all the leaf nodes.
		Remove all the leaf nodes.
		Repeat until the tree is empty.
		
	Example 1:
				1           1
			   / \         /
			  2   3  =>   2   => 1 => 
			 / \
			4   5
		Input: root = [1,2,3,4,5]
		Output: [[4,5,3],[2],[1]]
		Explanation:
			[[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level 
						it does not matter the order on which elements are returned.
						
	Example 2:
		Input: root = [1]
		Output: [[1]]

	Constraints:
		The number of nodes in the tree is in the range [1, 100].
		-100 <= Node.val <= 100
 */
public class FindLeavesOfBinaryTree {
	
	/*
	 * calculate height of node and place that in that index in the arrayList
	 * Time Complexity : O(n)
	 * Space Complexity: O(n)
	 */
	private int height(TreeNode node, List<List<Integer>> leavesList) {
		if(node == null) {
			return -1;
		}
		int leftHeight = height(node.left, leavesList);
		int rightHeight = height(node.right, leavesList);
		int currHeight = Math.max(leftHeight, rightHeight) + 1;
		if(leavesList.size()==currHeight) {
			leavesList.add(new ArrayList<>());
		}
		leavesList.get(currHeight).add(node.val);
		return currHeight;
	}

	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> leavesList = new ArrayList<>();
		height(root, leavesList);
		return leavesList;
	}
	
	public static void printResult(List<List<Integer>> leavesList) {
		for(List<Integer> leaves: leavesList) {
			System.out.print("[");
			for(int leaf: leaves) {
				System.out.print(leaf);
				if(leaf != leaves.get(leaves.size()-1)) {
					System.out.print(",");
				}
			}
			System.out.print("]");
			if(leaves != leavesList.get(leavesList.size()-1)) {
				System.out.print(",");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		FindLeavesOfBinaryTree leavesBinaryTree = new FindLeavesOfBinaryTree();
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);
		root1.left.left = new TreeNode(4);
		root1.left.right = new TreeNode(5);
		List<List<Integer>> leavesList1 = leavesBinaryTree.findLeaves(root1);
		printResult(leavesList1);
		TreeNode root2 = new TreeNode(1);
		List<List<Integer>> leavesList2 = leavesBinaryTree.findLeaves(root2);
		printResult(leavesList2);
	}

}
