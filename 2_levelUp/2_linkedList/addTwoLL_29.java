// Given 2 SLL of digits, the most significant digit comes first and each of their nodes contain a single digit. 
// Add the two numbers and return it as a ll. 
// Assume the two numbers does not contain leading zero, except the number 0 itself. 

// INPUT
// 1->2->3->4->5->6->7->/
// 7->8->9->/

// 9->8->9->/
// 1->2->/

// OUTPUT 
// 1->2->3->5->3->5->6->/

// 1->0->0->1->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class addTwoLL_29 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts1 = br.readLine().split(" ");
        String[] parts2 = br.readLine().split(" ");
        ListNode head1 = createList(parts1);
        ListNode head2 = createList(parts2);
        ListNode result = addTwoLists(head1, head2);
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
    
    public static ListNode addTwoLists(ListNode head1, ListNode head2) {
        if(head1 == null || head2 == null) return (head1 != null) ? head1 : head2; // edge cases
        
        ListNode rev1 = reverseList(head1), prev1 = rev1;   // reverse both given SLLs
        ListNode rev2 = reverseList(head2), prev2 = rev2;

        ListNode resHead = new ListNode(-1), resCurr = resHead; // new dummy node for result list

        int carry = 0;
        while(carry != 0 || prev1 != null || prev2 != null) {   // both pointers must be null and carry to be 0 to stop loop
            int nodesSum = carry;   // if carry is not 0, add it to sum
            nodesSum += (prev1 != null) ? prev1.data : 0;   // if node from list1 is not null, add it to sum
            nodesSum += (prev2 != null) ? prev2.data : 0;   // if node from list2 is not null, add it to sum
            
            resCurr.next = new ListNode(nodesSum % 10);     // add new node with remainder of sum as digit
            
            if(prev1 != null) prev1 = prev1.next;   // move pointers of list1 and list2 forward if possible
            if(prev2 != null) prev2 = prev2.next;
            resCurr = resCurr.next; // move pointer of result list forward
            carry = nodesSum / 10;  // carry becomes quotient of sum
        }

        // if(carry > 0) {      // since added (carry != 0) condition in while loop itself
        //     resCurr.next = new ListNode(carry);
        //     resCurr = resCurr.next;
        // }

        ListNode temp = resHead;    // remove dummy node from result list
        resHead = resHead.next;     // the first node in result list becomes head
        temp.next = null;

        head1 = reverseList(rev1);  // reverse all 3 SLLs
        head2 = reverseList(rev2);
        return reverseList(resHead);    // result list output now
    }
}
