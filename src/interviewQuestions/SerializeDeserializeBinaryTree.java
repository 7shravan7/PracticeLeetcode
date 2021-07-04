package interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* **Hard**  297. Serialize and Deserialize Binary Tree
 * 
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
 * in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or 
 * another computer environment.

   Design an algorithm to serialize and deserialize a binary tree. 
   There is no restriction on how your serialization/deserialization algorithm should work. 
   You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the 
   original tree structure.

   Clarification: The input/output format is the same as how LeetCode serializes a binary tree. 
   				  You do not necessarily need to follow this format, so please be creative and come up with different 
   				  approaches yourself.
   	Example 1:
   						1
   					   / \
   					  2   3
   					     / \
   					    4   5
   		Input: root = [1,2,3,null,null,4,5]
		Output: [1,2,3,null,null,4,5]
	
	Example 2:
		Input: root = []
		Output: []
		
	Example 3:
		Input: root = [1]
		Output: [1]
		
	Example 4:
		Input: root = [1,2]
		Output: [1,2]
	
	Constraints:
		The number of nodes in the tree is in the range [0, 104].
		-1000 <= Node.val <= 1000
 */
public class SerializeDeserializeBinaryTree {
	
	int index = 0;
	
	// pre order traversal
	private void dfs(TreeNode node, StringBuilder sb) {
		if(node == null) {
			sb.append("#").append(",");
			return;
		}
		int nodeVal = node.val;
		sb.append(nodeVal).append(",");
		dfs(node.left, sb);
		dfs(node.right, sb);
	}
	
	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	StringBuilder sb = new StringBuilder();
    	if(root!=null) {
    		dfs(root, sb);
    	}
        return sb.toString();
    }
    
    /*
     * Time : 12ms
     */
    private TreeNode deserializeDfs(String[] strArr) {
    	if(index>=strArr.length || strArr[index].equals("#")) {
    		return null;
    	}
    	int val = Integer.valueOf(strArr[index]);
    	TreeNode node = new TreeNode(val);
    	index++;
    	node.left = deserializeDfs(strArr);
    	index++;
    	node.right = deserializeDfs(strArr);
    	return node;
    }
    
    /*
     * Time : 9ms
     * using queue so that we can remove first element after processing (FIFO)
     * pre order traversal
     */
    private TreeNode deserializeQueue(Queue<String> queue) {
    	String val = queue.poll();
    	if ("#".equals(val)) {
    		return null;
    	} 
    	TreeNode root = new TreeNode(Integer.valueOf(val));
    	root.left = deserializeQueue(queue);
    	root.right = deserializeQueue(queue);
    	return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	if(data.isEmpty()) {
    		return null;
    	}
    	String[] strArr = data.split(",");
    	/* index=0;
        return deserializeDfs(strArr);*/
    	return deserializeQueue(new LinkedList<>(Arrays.asList(strArr)));
    }
    
    public static void printTree(TreeNode root) {
    	if(root == null) {
    		return;
    	}
    	List<String> printList = new ArrayList<>();
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.add(root);
    	while(!queue.isEmpty()) {
    		TreeNode node = queue.poll();
    		if(node == null) {
    			printList.add("#");
    		} else {
    			printList.add(String.valueOf(node.val));
    			queue.add(node.left);
        		queue.add(node.right);
    		}
    	}
    	for(int i=0;i<printList.size();i++) {
    		System.out.print(printList.get(i));
    		if(i!=printList.size()-1) {
    			System.out.print(",");
    		}
    	}
    	System.out.println();
    }

	public static void main(String[] args) {
		SerializeDeserializeBinaryTree binaryTree = new SerializeDeserializeBinaryTree();
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);
		root1.right.left = new TreeNode(4);
		root1.right.right = new TreeNode(5);
		String serialized1 = binaryTree.serialize(root1);
		System.out.println("Serialized String of root1 : "+serialized1);
		TreeNode root1Deserialized = binaryTree.deserialize(serialized1);
		printTree(root1Deserialized);
	}

}
