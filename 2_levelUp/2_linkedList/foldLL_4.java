// Given a SLL, fold  the ll. 

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class foldLL_4 {
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
        foldList(head);
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

    public static ListNode getMidNode(ListNode node) {
        if(node == null || node.next == null) return node;

        ListNode slow = node, fast = node;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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

    // TC: O(N) | SC: constant
    public static void foldList(ListNode node) {
        if(node == null || node.next == null) return;   // edge case: list size:0/1

        ListNode mid = getMidNode(node);    // returns mid node of list ~ O(N)
        ListNode newHead = mid.next;
        mid.next = null;    // detach two halves

        newHead = reverseList(newHead);     // reverse second half | T(N/2) ~ O(N)

        ListNode p1 = node, p2 = newHead;
        while(p2 != null) {  // second half list will reach null faster or at same time as first half list | T(N/2) ~ O(N)
            ListNode nbr1 = p1.next, nb2 = p2.next;
            p1.next = p2;   
            p2.next = nbr1;
            p1 = nbr1;
            p2 = nb2;
        }
    }
}
