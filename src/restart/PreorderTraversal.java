package restart;

import java.util.ArrayList;
import java.util.List;

public class PreorderTraversal {

    public List<Integer> morrisPreorderTraversal(TreeNode root) {
        List<Integer> preorderList = new ArrayList<>();
        TreeNode node = root;
        while(node!=null){
            if(node.left==null){
                preorderList.add(node.val);
                node = node.right;
            } else {
                TreeNode temp = node.left;
                while(temp.right!=null || temp.left!=null){
                    if(temp.right!=null) {
                        temp = temp.right;
                    } else {
                        temp = temp.left;
                    }
                }
                temp.right = node.right;
                node.right = null;
                preorderList.add(node.val);
                TreeNode curr = node.left;
                node = curr;
            }
        }
        return preorderList;
    }

    public static void printList(List<Integer> preOrderList) {
        for(int val : preOrderList) {
            System.out.print(val+" ");
        }
    }

    public static void main(String[] args) {
        PreorderTraversal preorderTraversal = new PreorderTraversal();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.right = new TreeNode(3);
        printList(preorderTraversal.morrisPreorderTraversal(root));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}
