package restart;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BoundaryTraversalTree {

    public List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> boundaryList = new ArrayList<>();
        TreeNode node = root;
        if(!isLeaf(node)){
            boundaryList.add(node.val);
        }
        node = node.left;
        while(node!=null){
            if(!isLeaf(node)) {
                boundaryList.add(node.val);
            }
            node = node.left == null ? node.right : node.left;
        }
        getLeaves(root, boundaryList);
        LinkedList<Integer> linkedList = new LinkedList<>();
        TreeNode rightNode = root.right;
        while(rightNode!=null){
            if(!isLeaf(rightNode)) {
                linkedList.addFirst(rightNode.val);
            }
            rightNode = rightNode.right!=null ? rightNode.right : rightNode.left;
        }
        boundaryList.addAll(linkedList);
        return boundaryList;
    }

    private void getLeaves(TreeNode node, List<Integer> list){
        if(node == null){
            return;
        }
        if(isLeaf(node)){
            list.add(node.val);
            return;
        }
        getLeaves(node.left,list);
        getLeaves(node.right, list);
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public static void printList(List<Integer> list) {
        for(int num:list){
            System.out.print(num +" ");
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.right.left = new TreeNode(6);
        root.right.left.left= new TreeNode(9);
        root.right.left.right = new TreeNode(10);
        BoundaryTraversalTree boundaryTraversalTree = new BoundaryTraversalTree();
        printList(boundaryTraversalTree.boundaryTraversal(root));
    }
}
