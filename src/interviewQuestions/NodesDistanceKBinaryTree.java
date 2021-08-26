package interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/* **Medium**         863. All Nodes Distance K in Binary Tree
 * 
 * We are given a binary tree (with root node root), a target node, and an integer value k.

   Return a list of the values of all nodes that have a distance k from the target node.  The answer can be returned in any order.

	Example 1:
		Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
		Output: [7,4,1]
		Explanation: 
		                           3
		                         /   \
		                        5     1
		                       / \   / \
		                      6   2 0   8
		                         / \
		                        7   4
			The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
			Note that the inputs "root" and "target" are actually TreeNodes.
			The descriptions of the inputs above are just serializations of these objects.
	
	Note:
		The given tree is non-empty.
		Each node in the tree has unique values 0 <= node.val <= 500.
		The target node is a node in the tree.
		0 <= k <= 1000.
 */
public class NodesDistanceKBinaryTree {
	
	private void addChildNodesAtLevelK(TreeNode node, int k, List<Integer> resultList) {
		if(node == null) {
			return;
		}
		if(k==0) {
			resultList.add(node.val);
			return;
		}
		addChildNodesAtLevelK(node.left, k-1, resultList);
		addChildNodesAtLevelK(node.right, k-1, resultList);
	}
	
	// basic getDistance to get nodes at dist k down only
	// what if there is nodes to target at dist k up?
	/*public void getDistance(TreeNode node, TreeNode target, int k, List<Integer> resultList) {
		if(node == null) {
			return;
		}
		if(node == target) {
			addChildNodesAtLevelK(node, k, resultList);// add child at dist k
			return;
		}
		getDistance(node.left, target, k, resultList);
		getDistance(node.right, target, k, resultList);
	}*/
	
	/*
	 * To get nodes at dist up means need to return the dist during each recursive calls
	 */
	public int getDistance(TreeNode node, TreeNode target, int k, List<Integer> resultList) {
		if(node == null) {
			return -1;
		}
		if(node == target) {
			addChildNodesAtLevelK(node, k, resultList);// add child at dist k
			return 0;
		}
		int leftDist = getDistance(node.left, target, k, resultList);
		if(leftDist!=-1) {
			if(leftDist+1 == k) {
				resultList.add(node.val);
			} else {
				addChildNodesAtLevelK(node.right, k-leftDist-2, resultList);
			}
			return leftDist+1;
		}
		int rightDist = getDistance(node.right, target, k, resultList);
		if(rightDist!=-1) {
			if(rightDist+1 == k) {
				resultList.add(node.val);
			} else {
				addChildNodesAtLevelK(node.left, k-rightDist-2, resultList);
			}
			return rightDist+1;
		}
		return -1;
	}
	

	public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
		List<Integer> resultList = new ArrayList<>();
		getDistance(root, target, k, resultList);
		return resultList;
	}
	
	public static void printList(List<Integer> resultList) {
		for(int i:resultList) {
			System.out.print(i+" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		NodesDistanceKBinaryTree distK = new NodesDistanceKBinaryTree();
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);
		List<Integer> resultList = distK.distanceK(root, root.left , 2);
		printList(resultList);
		List<Integer> resultList1 = distK.distanceK(root, root.left , 3);
		printList(resultList1);
	}

}
