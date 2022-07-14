package restart;


public class ReverseKLinkedList {

        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode temp = head;
            ListNode prev = null;
            ListNode resultHead = null;
            ListNode startTemp = head;
            ListNode prevStart = null;
            int count = 0;
            while(temp!=null){
                prev = temp;
                temp = temp.next;
                count++;
                if(count%k == 0) {
                    ListNode t = reverse(startTemp, prev);
                    if(resultHead == null){
                        resultHead = t;
                    } else{
                        prevStart.next = t;
                    }
                    prevStart = startTemp;
                    startTemp.next = temp;
                    startTemp = temp;
                }
            }
            return resultHead;
        }

            private ListNode reverse(ListNode startNode, ListNode endNode) {
                ListNode prev = null;
                ListNode curr = startNode;
                ListNode next = null;
                while(curr!=endNode){
                    next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }
                endNode.next = prev;
                return endNode;
            }

            public static void printLinkedList(ListNode head){
                ListNode temp =head;
                while(temp!=null){
                    System.out.print(temp.val+"->");
                    temp = temp.next;
                }
                System.out.print("null");
                System.out.println();
            }

        public static void main(String[] args) {
            ReverseKLinkedList reversekLinkedList = new ReverseKLinkedList();
            ListNode head = new ListNode(1);
            head.next = new ListNode(2);
            head.next.next = new ListNode(3);
            head.next.next.next = new ListNode(4);
            head.next.next.next.next = new ListNode(5);
            head.next.next.next.next.next = new ListNode(6);
            ListNode reversedList = reversekLinkedList.reverseKGroup(head,2);
            System.out.println();
            printLinkedList(reversedList);
        }
}

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
