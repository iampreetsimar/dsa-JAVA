// Given a pointer to the head node of a linked list, reverse the linked list by changing the pointers b/w nodes. 

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class reverseLL_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        ListNode dummyNode = new ListNode(-1);
        ListNode prev = dummyNode;
        for(int i = 0; i < parts.length; i++) {
            prev.next = new ListNode(Integer.parseInt(parts[i]));
            prev = prev.next;
        }

        ListNode reversedHead = reverseLL(dummyNode.next);
        displayLL(reversedHead);
    }

    public static void displayLL(ListNode node) {
        while(node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;
        ListNode(int data) { this.data = data; }
    }

    public static ListNode reverseLL(ListNode node) {
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
}
