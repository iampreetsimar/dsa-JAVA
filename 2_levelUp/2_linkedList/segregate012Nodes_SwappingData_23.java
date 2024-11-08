// Given a SLL, segragate 012 nodes of the list by SWAPPING DATA. 
// After segregation nodes with 0 data should come first, followed by nodes with 1 data and atlast nodes with 2 data. 
// NOTE: next pointer of nodes must not be changed!!

// INPUT
// 1->2->2->0->1->0->2->2->0->1->1->1->2->1->1->/

// OUTPUT
// 0->0->0->1->1->1->1->1->1->1->2->2->2->2->2->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class segregate012Nodes_SwappingData_23 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        segragate012Data(head);
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

    // similar to SORT 012
    // divide list into 4 regions:
    // 0:j-1 -> 0 nodes | j:k-1 -> 1 nodes | k:i-1 -> 2 nodes | i:end -> unknown nodes
    public static void segragate012Data(ListNode head) {
        if(head == null || head.next == null) return;   // base case

        ListNode start1 = head, start2 = head, curr = head; // start1 - first 1 node, start2 - first 2 node, curr - first unknown node

        while(curr != null) {
            if(curr.data == 0) {
                swapData(curr, start2);     // swap nodes(ith, kth) - kth node has 0 now, ith node has 2 now
                swapData(start2, start1);   // swap nodes(kth, jth) - jth node has 0 now, kth node has 1 now
                start1 = start1.next;       // first 1 node at j+1
                start2 = start2.next;       // first 2 node at k+1
            } else if(curr.data == 1) {
                swapData(curr, start2);     // swap nodes(ith, kth) - kth node has 1 now, ith node has 0 
                start2 = start2.next;       // first 2 node at k+1
            }

            curr = curr.next;       // first unknown node at i+1
        }
    }

    public static void swapData(ListNode node1, ListNode node2) {   // swaps data b/w 2 nodes
        int temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }
}
