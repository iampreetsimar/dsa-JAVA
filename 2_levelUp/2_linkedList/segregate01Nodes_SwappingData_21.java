// Given a SLL, segragate 01 nodes of the list by SWAPPING DATA. 
// After segregation nodes with 0 data should come first, followed by nodes with 1 data. 
// NOTE: next pointer of nodes must not be changed!!

// INPUT
// 1->0->1->0->0->1->1->1->1->1->/

// OUTPUT
// 0->0->0->1->1->1->1->1->1->1->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class segregate01Nodes_SwappingData_21 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        segragate01Data(head);
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

    // similar to Partition an Array and Sort 01
    // 3 regions: 0:(j-1) -> all 0's | j:(i-1) -> all 1's | i:end -> unknown
    // only values of nodes are changed
    // jth node's value will always be 1 - can be said its the pivot
    public static void segragate01Data(ListNode head) {
        if(head == null || head.next == null) return;   // base case

        ListNode curr = head, zero = head;
        while(curr != null) {
            if(curr.data == 0) {
                int temp = curr.data;
                curr.data = zero.data;
                zero.data = temp;
                zero = zero.next;
            }

            curr = curr.next;
        }
    }
}
