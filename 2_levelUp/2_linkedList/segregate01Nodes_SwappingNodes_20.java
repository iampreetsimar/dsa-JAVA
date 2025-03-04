// Given a SLL, segragate 01 nodes of the list and return pivot node of the list. 
// After segregation 0 nodes should come first, followed by 1 nodes. 

// INPUT
// 1->0->1->0->0->1->1->1->1->1->/

// OUTPUT
// 0->0->0->1->1->1->1->1->1->1->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class segregate01Nodes_SwappingNodes_20 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        head = segragate01Nodes(head);
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

    // use 2 dummy nodes for 0 nodes list and 1 nodes list
    // use 2 pointers to track last 0 and last 1 node in their respective dummy nodes list
    public static ListNode segragate01Nodes(ListNode head) {
        if(head == null || head.next == null) return head;  // base case

        ListNode head1 = new ListNode(-1);
        ListNode tail1 = head1;

        ListNode head0 = new ListNode(-1);
        ListNode tail0 = head0;

        ListNode curr = head;
        while(curr != null) {
            if(curr.data == 0) {
                tail0.next = curr;
                tail0 = tail0.next;
            } else {
                tail1.next = curr;
                tail1 = tail1.next;
            }
            curr = curr.next;
        }   // all nodes are in their respective nodes list but there may be some interconnected pointers at both tails

        tail0.next = head1.next;    // point last 0 node's next to first 1 node
        tail1.next = null;          // point last 1 node's nnext to null indicating end of list

        return head0.next;  // return 1st 0 node as head of resultant list
    }
}
