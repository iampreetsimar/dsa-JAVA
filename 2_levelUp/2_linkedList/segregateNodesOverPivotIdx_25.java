// Given a SLL, segregate list over pivot idx and return pivot node of list and return starting node of list. 
// Pivot will be any random idx from range: 0 to length of list
// After segregation, pivot node should have to be present at correct idx as in sorted list. 

// INPUT
// 1->5->2->9->5->14->11->1->10->10->1->3->/
// 11

// OUTPUT
// 1->2->1->1->3->5->9->5->14->11->10->10->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class segregateNodesOverPivotIdx_25 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        int pivotIdx = Integer.parseInt(br.readLine());
        if(pivotIdx < 0 || pivotIdx >= parts.length) {
            System.out.println("Invalid Pivot Idx");
            return;
        }

        ListNode head = createList(parts);

        head = segragateOverPivotIdx(head, pivotIdx);
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
    
    public static int getPivotData(ListNode node, int pivotIdx) {   // returns value of node at pivotIdx
        while(pivotIdx-- > 0) { node = node.next; }
        return node.data;
    }

    // given valid pivotIdx : 0 to list.length() - 1
    // in addition to approach used in segregation over last idx, use a pivot node w/c point to node at pivot idx
    // pivot node is not the last node in smaller nodes list now
    public static ListNode segragateOverPivotIdx(ListNode head, int pivotIdx) {
        if(head == null || head.next == null) return head;  // base case

        int pivotData = getPivotData(head, pivotIdx);   // fetch value of node at pivot idx for comparisons

        ListNode smallerPartHead = new ListNode(-1), prevSmaller = smallerPartHead;
        ListNode largerPartHead = new ListNode(-1), prevLarger = largerPartHead;
        ListNode pivot = null; int idx = 0;

        ListNode curr = head;
        while(curr != null) {
            if(idx == pivotIdx) {   // node at pivot idx becomes pivot
                pivot = curr;
            } else if(curr.data <= pivotData) {     // nodes <= pivot value in smaller list
                prevSmaller.next = curr; prevSmaller = prevSmaller.next;
            } else {    // nodes > pivot value in greater list
                prevLarger.next = curr; prevLarger = prevLarger.next;
            } 
            curr = curr.next; idx++;    // curr for node traversal | idx for detecting pivotIdx
        }

        pivot.next = largerPartHead.next;   // pivot becomes at joining point for both list
        prevSmaller.next = pivot;
        prevLarger.next = null;     // indicates end of list

        return smallerPartHead.next;    // new head of resulting list
    }
}
