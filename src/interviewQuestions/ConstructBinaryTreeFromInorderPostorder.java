package interviewQuestions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import interviewQuestions.ConstructBinaryTreeFromPreorderInorder.TreeNode;

/* **Medium**    106. Construct Binary Tree from Inorder and Postorder Traversal
 * 
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
 *  and postorder is the postorder traversal of the same tree, construct and return the binary tree.

	Example 1:
							3
						   / \
						  9	  20
						     /  \
                            15   7
		Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
		Output: [3,9,20,null,null,15,7]
		
	Example 2:
		Input: inorder = [-1], postorder = [-1]
		Output: [-1]

	Constraints:
		1 <= inorder.length <= 3000
		postorder.length == inorder.length
		-3000 <= inorder[i], postorder[i] <= 3000
		inorder and postorder consist of unique values.
		Each value of postorder also appears in inorder.
		inorder is guaranteed to be the inorder traversal of the tree.
		postorder is guaranteed to be the postorder traversal of the tree.
 */
public class ConstructBinaryTreeFromInorderPostorder {
	
	int postOrderIndex;
	
	private TreeNode buildTree(int[] postorder, Map<Integer, Integer> inorderMap ,int startIndex, 
			int endIndex) {
		if(startIndex>endIndex) {
			return null;
		}
		int val = postorder[postOrderIndex--];
		int inorderIndex = inorderMap.get(val);
		TreeNode node = new TreeNode(val);
		node.right = buildTree(postorder, inorderMap, inorderIndex+1, endIndex);
		node.left = buildTree(postorder, inorderMap, startIndex, inorderIndex-1);
		return node;
	}
	
	/*
	 * Time Complexity : O(n)
	 * Space Complexity: O(n)
	 * 
	 * Similar as of Construct BinaryTree From Inorder and Preorder
	 * only change is to start from reverse of postorder and fill right child and then left child
	 */
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		Map<Integer, Integer> inorderMap = new HashMap<>();
		for(int i=0;i<inorder.length;i++) {
			inorderMap.put(inorder[i], i);
		}
		postOrderIndex = inorder.length-1;
		return buildTree(postorder, inorderMap, 0, inorder.length-1);
	}
	
	public static void printTree(TreeNode node) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(node);
		System.out.println("The tree : ");
		while(!queue.isEmpty()) {
			TreeNode t=queue.poll();
			System.out.print(t.val+",");
			if(t.left==null) {
				System.out.print("null,");
			} else {
				queue.add(t.left);
			}
			if(t.right==null) {
				System.out.print("null,");
			} else {
				queue.add(t.right);
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ConstructBinaryTreeFromInorderPostorder BT = new ConstructBinaryTreeFromInorderPostorder();
		int[] inorder1 = {9,3,15,20,7};
		int[] postorder1 = {9,15,7,20,3};
		TreeNode tree1 = BT.buildTree(inorder1, postorder1);
		printTree(tree1);
		int[] inorder2 = {-1};
		int[] postorder2 = {-1};
		TreeNode tree2 = BT.buildTree(inorder2, postorder2);
		printTree(tree2);
	}

}
