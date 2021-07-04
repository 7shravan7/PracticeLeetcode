package codes.LeetCode.may2020Challenges.week_4_22to28;

public class ConstructBSTFromPreorder {

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

	public int currIndex=0;

	private TreeNode getTreeNode(int[] preorder, int max){
		if(currIndex>=preorder.length || preorder[currIndex]>max){
			return null;
		}
		int data = preorder[currIndex];
		currIndex++;
		TreeNode newNode = new TreeNode(data);
		newNode.left = getTreeNode(preorder, data);
		newNode.right = getTreeNode(preorder, max);
		return newNode;
	}

	public TreeNode bstFromPreorder(int[] preorder) {
		return getTreeNode(preorder, Integer.MAX_VALUE);
	}

	public static void main(String[] args) {
		ConstructBSTFromPreorder c =new ConstructBSTFromPreorder();
		int[] preOrderlist = {8,5,1,7,10,12};
		TreeNode head = c.bstFromPreorder(preOrderlist);
		System.out.println("");
	}

}
