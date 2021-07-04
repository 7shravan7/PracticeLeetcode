package codes.LeetCode.may2020Challenges.week_1_1to7;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javafx.util.Pair;

/*
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
   Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
   We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
   Return true if and only if the nodes corresponding to the values x and y are cousins.
   Example 1:
		Input: root = [1,2,3,4], x = 4, y = 3
		Output: false
   Example 2:
		Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
		Output: true
   Example 3:
		Input: root = [1,2,3,null,4], x = 2, y = 3
		Output: false
	Note:
		The number of nodes in the tree will be between 2 and 100.
		Each node has a unique integer value from 1 to 100.
 */
public class CousinsInBinaryTree {
	
	public static boolean isCousins(TreeNode root, int x, int y) {
		if(root == null) {
			return false;
		}
		if(root.val == x || root.val == y) {
			return false;
		}
		Map<Integer,Pair<Integer,TreeNode>> map = new HashMap<>();
		Queue<Pair<TreeNode,Integer>> queue = new LinkedList<>();
		queue.add(new Pair<TreeNode, Integer>(root, 0));
		while(!queue.isEmpty()) {
			Pair<TreeNode, Integer> pair = queue.poll();
			TreeNode parent = pair.getKey();
			TreeNode leftChild = parent.left;
			TreeNode rightChild = parent.right;
			int level = pair.getValue() + 1;
			if(leftChild!=null) {
				if(leftChild.val == x || leftChild.val == y) {
					map.put(leftChild.val, new Pair<Integer, TreeNode>(level, parent));
				}
				queue.add(new Pair<TreeNode, Integer>(leftChild, level));
			}
			if(rightChild!=null) {
				if(rightChild.val == x || rightChild.val == y) {
					map.put(rightChild.val, new Pair<Integer, TreeNode>(level, parent));
				}
				queue.add(new Pair<TreeNode, Integer>(rightChild, level));
			}
			if(map.containsKey(x) && map.containsKey(y)) {
				break;
			}
		}
        return map.get(x).getKey() == map.get(y).getKey() && map.get(x).getValue()!=map.get(y).getValue();
    }
	
	public static void main(String[] args) {
		//1,2,3,null,4,null,5
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(5);
		int x =1;
		int y =3;
		System.out.println(CousinsInBinaryTree.isCousins(root, x, y));
	}
}

class TreeNode {
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
