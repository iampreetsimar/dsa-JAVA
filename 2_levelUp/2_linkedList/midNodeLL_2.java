// Given a SLL with a head node, return the middle node of the ll. If ll has 2 mid nodes(for even sized ll), 
// return the first mid node.

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class midNodeLL_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        ListNode dummyNode = new ListNode(-1);
        ListNode prev = dummyNode;
        for(int i = 0; i < parts.length; i++) {
            prev.next = new ListNode(Integer.parseInt(parts[i]));
            prev = prev.next;
        }

       ListNode midNode = midNodeOfLL(dummyNode.next);
       System.out.println(midNode.data);
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;
        ListNode(int data) { this.data = data; }
    }

    // same approach as discussed in BASICS
    // to get first mid node as mid(amongst 2 mid nodes) in case of even nodes, we'll check (fast.next.next != null)
    // to get second mid node as mid(amongst 2 mid nodes) in case of even nodes, we'll check (fast != null)
    public static ListNode midNodeOfLL(ListNode node) {
        if(node == null) return node;   // edge case on list size: 0

        ListNode slow = node, fast = node;
        while(fast.next != null && fast.next.next != null) { // stopping condition handles even/odd # of nodes in list + list size: 1,2
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
