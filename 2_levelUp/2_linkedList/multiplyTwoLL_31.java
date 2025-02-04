// Given 2 SLL of digits, the most significant digit comes first and each of their nodes contain a single digit. 
// Multiply the two numbers and return it as a ll. 
// Assume the two numbers does not contain leading zero, except the number 0 itself. 

// INPUT 
// 1->2->3->4->5->/
// 7->8->9->/

// OUTPUT 
// 9->7->4->0->2->0->5->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class multiplyTwoLL_31 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts1 = br.readLine().split(" ");
        String[] parts2 = br.readLine().split(" ");
        ListNode head1 = createList(parts1);
        ListNode head2 = createList(parts2);
        ListNode result = multiplyTwoLists(head1, head2); 
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

    public static ListNode multiplyTwoLists(ListNode head1, ListNode head2) {
        if(head1  == null || head2 == null) return null;    // edge case

        head1 = reverseList(head1);
        head2 = reverseList(head2); ListNode prev2 = head2;

        ListNode resHead = new ListNode(-1), resItr = resHead;

        while(prev2 != null) {  // traverse on list 2 digits
            ListNode prod = multiplyListWithDigit(prev2, head1);  // multiply curr list 2 digit with entire list 1
            addTwoLists(prod, resItr);  // add prod list returned with result list
            resItr = resItr.next;   // move res_itr forward - needed to add both lists in correct way
            prev2 = prev2.next;     // move to next list 2 digit
        }

        head1 = reverseList(head1);     // reverse 3 lists again
        head2 = reverseList(head2);
        resHead = reverseList(resHead.next);    
        while(resHead.next != null && resHead.data == 0) { resHead = resHead.next; }    // remove any leading zero from result list
        return resHead; // return result list
    }

    // returns prod list of list1 with list 2 node
    public static ListNode multiplyListWithDigit(ListNode node, ListNode head1) {
        ListNode prev1 = head1;
        ListNode head = new ListNode(-1), itr = head;
        int carry = 0;

        while(prev1 != null || carry != 0) {    
            int data = carry + ((prev1 != null ? prev1.data : 0) * node.data);
            itr.next = new ListNode(data % 10);
            carry = data / 10;

            if(prev1 != null) prev1 = prev1.next;
            itr = itr.next;
        }

        return head.next;
    }

    // sums prod list with result list
    public static void addTwoLists(ListNode head, ListNode itr) {
        ListNode prev = head;
        int carry = 0;

        // terminating condition on prod as prod will always have >= count of nodes than result list
        // sum starts from itr.next of result list and first node of prod list -> to follow multiplication addition rule
        while(prev != null || carry != 0) { 
            int data = carry + (prev != null ? prev.data : 0) + (itr.next != null ? itr.next.data : 0);
            carry = data / 10;

            if (itr.next != null) itr.next.data = data % 10;
            else itr.next = new ListNode(data % 10);

            itr = itr.next;
            if(prev != null) prev = prev.next;
        }
    }
}
