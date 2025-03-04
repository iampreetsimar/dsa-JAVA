// Given a SLL, unfold  the ll. 

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class unfoldLL_5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode dummyNode = new ListNode(-1);
        ListNode prev = dummyNode;
        for(int i = 0; i < parts.length; i++) {
            prev.next = new ListNode(Integer.parseInt(parts[i]));
            prev = prev.next;
        }

        ListNode head = dummyNode.next;
        unfoldList(head);
        displayList(head);
    }

    public static void displayList(ListNode node) {
        while(node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;

        ListNode(int data) {
            this.data = data;
        }
    }

    public static ListNode reverseList(ListNode node) {
        if(node == null || node.next == null) return node;

        ListNode prev = null, curr = node;
        while(curr != null) {
            ListNode nbr = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nbr;
        }
        return prev;
    }

    // use reverseList()
    // TC: O(N) | SC: constant
    public static void unfoldList(ListNode node) {
        if(node == null || node.next == null || node.next.next == null) return; // edge cases: list size 0/1/2

        ListNode newHead = node.next;   // 1st node as start of second half list

        ListNode p1 = node, p2 = newHead;
        while(p2 != null && p2.next != null) {  // since p2 reaches null faster in case of odd size list, check p2.next instead of p2
            p1.next = p1.next.next;
            p2.next = p2.next.next;
            p1 = p1.next;
            p2 = p2.next;
        }   // ~ T(N)

        newHead = reverseList(newHead); // reverse second half list starting from newHead | ~T(N/2)
        p1.next = newHead;  // merge both halves | OG list head remains same
    }
}
