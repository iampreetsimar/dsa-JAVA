// Given a SLL, segregate list over node at last idx and return pivot node of list. 
// Pivot is always the last idx of list!! 
// After segregation, pivot node should have to be present at correct idx as in sorted list. 

// INPUT
// 1->5->2->9->5->14->11->1->10->10->1->3->/

// OUTPUT
// 3->5->9->5->14->11->10->10->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class segregateNodesOverLastIdx_24 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        head = segragateOverLastIdx(head);
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

    public static ListNode getTailNode(ListNode head) {     // returns tail node of list
        if(head == null) return head;
        ListNode tail = head;
        while(tail.next != null) { tail = tail.next; }
        return tail;
    }
    
    // use 2 dummy nodes list: one has all nodes <= pivot node | other has all nodes > pivot node
    // nodes in both lists are in their respective i/p order
    // use 2 pointers to track last node of the list
    // the last node of smaller value list will always be the pivot node(since it is always present and is the last node of given list)
    public static ListNode segragateOverLastIdx(ListNode head) {
        if(head == null || head.next == null) return head;  // base case

        ListNode tail = getTailNode(head);  // tail becomes the pivot node

        ListNode partitionSmallerHead = new ListNode(-1), prevSmaller = partitionSmallerHead;
        ListNode paritionLargerHead = new ListNode(-1) , prevLarger = paritionLargerHead;

        ListNode curr = head;
        while(curr != null) {
            if(curr.data <= tail.data) {
                prevSmaller.next = curr; prevSmaller = prevSmaller.next;
            } else {
                prevLarger.next = curr; prevLarger = prevLarger.next;
            }
            curr = curr.next;
        }   // prevSmaller at pivot node

        prevSmaller.next = paritionLargerHead.next; // point pivot node's next to first larger node
        prevLarger.next = null;     // last larger node's next to null to indicate end of list

        return prevSmaller;     // only pivot node at correct sorted pos
    }
}
