// Given 2 SLL of digits, the most significant digit comes first and each of their nodes contain a single digit. 
// Subtract the two numbers and return it as a ll. 
// Assume the two numbers does not contain leading zero, except the number 0 itself. 
// Assume list1 > list2!!

// INPUT 
// 1->2->3->4->5->6->7->/
// 7->8->9->/

// 1->0->0->1->/
// 1->2->/

// OUTPUT 
// 1->2->3->3->7->7->8->/

// 9->8->9->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class subtractTwoLL_30 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts1 = br.readLine().split(" ");
        String[] parts2 = br.readLine().split(" ");
        ListNode head1 = createList(parts1);
        ListNode head2 = createList(parts2);
        ListNode result = subtractTwoLists(head1, head2);  // head1 - head2
        displayList(result);
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
        System.out.print("[ ");
        while(node != null) {
            System.out.print(node.data);
            if(node.next != null) System.out.print("->");
            node = node.next;
        }
        System.out.println(" ]");
    }

    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode curr = head, prev = null;
        while(curr != null) {
            ListNode nbr = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nbr;
        }
        return prev;
    }
    
    public static ListNode subtractTwoLists(ListNode head1, ListNode head2) {
        if(head2 == null) return head1; // edge case

        // reverse given lists and get traversal pointers for both
        head1 = reverseList(head1); ListNode prev1 = head1; 
        head2 = reverseList(head2); ListNode prev2 = head2;

        ListNode resHead = new ListNode(-1), resItr = resHead;  // dummy result node with its traversal pointer
        int carry = 0;  // initia borrow

        while(prev1 != null) {  // terminating condition on only list1 node as list1 > list2
            int diff = carry + prev1.data - ((prev2 != null) ? prev2.data : 0);
            if(diff < 0)  { // if diff is < 0 -> add 10 to diff, and carry -1 as borrow from next digits
                diff += 10; carry = -1;
            } else 
                carry = 0;
            
            resItr.next = new ListNode(diff);   // point result lists traversal pointers' next node as new node with val: diff
            resItr = resItr.next;   // move traversal forward

            prev1 = prev1.next; 
            if(prev2 != null) prev2 = prev2.next;   // move traversal forward if possible
        }

        head1 = reverseList(head1);     // reverse all 3 lists
        head2 = reverseList(head2);
        resHead = reverseList(resHead.next);    
        while(resHead.data == 0) { resHead = resHead.next; }    // remove any leading 0 if present in result list
        return resHead; 
    }
}
