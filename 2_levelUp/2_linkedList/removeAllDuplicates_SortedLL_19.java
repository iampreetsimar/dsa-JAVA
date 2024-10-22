// Given a sorted SLL, remove all duplicate nodes leaving only distinct nodes from OG list and return the remaining ll. 

// INPUT
// 1->1->1->4->5->6->6->7->8->9->9->9->10/

// OUTPUT
// 4->5->7->8->10/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class removeAllDuplicates_SortedLL_19 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        head = removeAllDuplicates(head);
        displayList(head);
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;
        ListNode(int data) { this.data = data; }
    }

    public static ListNode createList(String[] input) {
        ListNode head = new ListNode(-1);
        ListNode prev = head;
        for(int i = 0; i < input.length; i++) {
            prev.next = new ListNode(Integer.parseInt(input[i]));
            prev = prev.next;
        }
        return head.next;
    }

    public static void displayList(ListNode node) {
        while(node != null) {
            System.out.print(node.data + "->");
            node = node.next;
        }
        System.out.println("/");
    }

    public static ListNode removeAllDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;  // base case

        ListNode newHead = new ListNode(-1);    // dummy node
        ListNode itr = newHead;     // itr : nodes till iter node are actual valid nodes
        itr.next = head;  // iter.next : potential valid nodes
        
        ListNode curr = head.next;
        while(curr != null) {
            boolean isItrNextNotDistinct = false;   // to determine if loop to skip duplicates ran or not
            while(curr != null && itr.next.data == curr.data) {     // skips duplicate nodes w/c are equal to itr's next node's val
                curr = curr.next;
                isItrNextNotDistinct = true;
            }

            if(isItrNextNotDistinct) // if loop executed, curr stops at a value not equal to iter.next.val
                itr.next = curr;    // we get a new potential valid node at curr, old one was a duplicate
            else    // if loop is not executed, curr iter.next node is distinct
                itr = itr.next;     // iter.next is actual valid node, move iter forward

            if(curr != null) curr = curr.next;  // move curr forward to traverse but keep check as curr can reach null from while loop
        }

        return newHead.next;    // dummy node's next node becomes head of o/p list
    }
}
