package interviewQuestions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree 
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.

	Example 1:
						3
					   / \
					  9  20
					     / \
 					    15	7
		Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
		Output: [3,9,20,null,null,15,7]
		
	Example 2:
		Input: preorder = [-1], inorder = [-1]
		Output: [-1]
		
	Constraints:
		1 <= preorder.length <= 3000
		inorder.length == preorder.length
		-3000 <= preorder[i], inorder[i] <= 3000
		preorder and inorder consist of unique values.
		Each value of inorder also appears in preorder.
		preorder is guaranteed to be the preorder traversal of the tree.
		inorder is guaranteed to be the inorder traversal of the tree.
 */
public class ConstructBinaryTreeFromPreorderInorder {
	
	int preOrderIndex;
	
	private TreeNode buildTree(int[] preorder, Map<Integer, Integer> inOrderIndexMap,
			int inorderStartIndex, int inorderEndIndex) {
		if(inorderStartIndex>inorderEndIndex) {
			return null;
		}
		int preOrderVal = preorder[preOrderIndex++];
		int inOrderIndex = inOrderIndexMap.get(preOrderVal);
		TreeNode root = new TreeNode(preOrderVal);
		root.left = buildTree(preorder, inOrderIndexMap, inorderStartIndex, inOrderIndex-1);
		root.right = buildTree(preorder, inOrderIndexMap, inOrderIndex+1, inorderEndIndex);
		return root;
	}
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		Map<Integer, Integer> inOrderIndexMap = new HashMap<>();
		for(int i=0;i<inorder.length;i++) {
			inOrderIndexMap.put(inorder[i], i);
		}
		preOrderIndex=0;
		return buildTree(preorder, inOrderIndexMap, 0, inorder.length-1);
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
		ConstructBinaryTreeFromPreorderInorder constructTree = new ConstructBinaryTreeFromPreorderInorder();
		int[] preorder1 = {3,9,20,15,7};
		int[] inorder1 = {9,3,15,20,7};
		TreeNode root1 = constructTree.buildTree(preorder1, inorder1);
		printTree(root1);
		int[] preorder2 = {-1};
		int[] inorder2 = {-1};
		TreeNode root2 = constructTree.buildTree(preorder2, inorder2);
		printTree(root2);
	}
	
	public static class TreeNode {
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
}
