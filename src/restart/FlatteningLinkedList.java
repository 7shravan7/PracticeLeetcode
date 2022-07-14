package restart;

public class FlatteningLinkedList {

    public Node flatten(Node root){
        if(root.next == null){
            return root;
        }
        Node resultNode = flatten(root.next);
        return merge(root, resultNode);
    }

    private Node merge(Node node1, Node node2){
        Node dummyHead = new Node(-1);
        Node temp = dummyHead;
        while(node1!=null && node2!=null){
            if(node1.data<= node2.data){
                temp.bottom = node1;
                node1 = node1.bottom;
            } else {
                temp.bottom = node2;
                node2 = node2.bottom;
            }
            temp = temp.bottom;
        }
        while(node1!=null){
            temp.bottom = node1;
            node1 = node1.bottom;
            temp = temp.bottom;
        }
        while(node2!=null){
            temp.bottom = node2;
            node2 = node2.bottom;
            temp = temp.bottom;
        }
        return dummyHead.bottom;
    }

    public static void printList(Node head){
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data+"->");
            temp = temp.bottom;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FlatteningLinkedList flatteningLinkedList = new FlatteningLinkedList();
        Node head = new Node(5);
        head.bottom = new Node(7);
        head.bottom.bottom = new Node(8);
        head.bottom.bottom.bottom = new Node(30);
        head.next = new Node(10);
        head.next.bottom = new Node(20);
        head.next.next = new Node(19);
        head.next.next.bottom = new Node(22);
        head.next.next.bottom.bottom = new Node(50);
        head.next.next.next = new Node(28);
        head.next.next.next.bottom = new Node(35);
        head.next.next.next.bottom.bottom = new Node(40);
        head.next.next.next.bottom.bottom.bottom = new Node(45);
        Node flattenList = flatteningLinkedList.flatten(head);
        printList(flattenList);
    }
}

class Node {
    int data;
    Node next;
    Node bottom;
    public Node(int d){
        data = d;
        next = bottom = null;
    }
}
