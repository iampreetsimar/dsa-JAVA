// Given a SLL, segragate 012 nodes of the list and return pivot node of the list. 
// After segregation 0 nodes should come first, followed by 1 nodes and at last all 2 nodes. 

// INPUT
// 1->0->2->1->0->2->2->0->1->1->2->1->1->2->1->2->/

// OUTPUT
// 0->0->0->1->1->1->1->1->1->1->2->2->2->2->2->2->/


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class segregate012Nodes_SwappingNodes_22 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        head = segragate012Nodes(head);
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

    // similar to segregate 01 nodes - swapping nodes
    // use 3 dummy nodes for each list and 3 pointers to track last nodes of each list
    public static ListNode segragate012Nodes(ListNode head) {
        if(head == null || head.next == null) return head;  // base case

        ListNode head0 = new ListNode(-1), tail0 = head0;
        ListNode head1 = new ListNode(-1), tail1 = head1;
        ListNode head2 = new ListNode(-1), tail2 = head2;

        ListNode curr = head;
        while(curr != null) {
            if(curr.data == 0) {
                tail0.next = curr;
                tail0 = tail0.next;
            } else if(curr.data == 1) {
                tail1.next = curr;
                tail1 = tail1.next;
            } else {
                tail2.next = curr;
                tail2 = tail2.next;
            }
            curr = curr.next;
        }   // after traversing, all nodes in their respective lists

        //  join lists such that nodes are in required order
        // joining order is VERY IMP!!! (takes care of all edge case where any of the nodes are not present)
        tail1.next = head2.next;    // point last 1's node's next to first 2's node(if no 2's node, it points to null)
        tail0.next = head1.next;    // point last 0's node's next to first 1's node(if no 1's node, it point to 2's first node)
        tail2.next = null;         // point last 2's node's next to null to indicate end of required list

        return head0.next;  // new head of required list
    }
}
