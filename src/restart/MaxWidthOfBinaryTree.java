package restart;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.List;

public class MaxWidthOfBinaryTree {

    int max = 0;
    public int widthOfBinaryTree(TreeNode root) {
        postOrder(root, 0, 1, new ArrayList<>());
        return max;
    }

    private void postOrder(TreeNode cur, int depth, int index, List<Integer> list) {
        if (cur == null) {
            return;
        }
        if (depth == list.size()) {
            list.add(index);
        }
        max = Math.max(max, index - list.get(depth) + 1);
        postOrder(cur.left, depth+1, index*2, list);
        postOrder(cur.right, depth+1, index*2+1, list);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
            left =right = null;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left =new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        MaxWidthOfBinaryTree maxWidthOfBinaryTree = new MaxWidthOfBinaryTree();
        System.out.println(maxWidthOfBinaryTree.widthOfBinaryTree(root));
    }
 }
