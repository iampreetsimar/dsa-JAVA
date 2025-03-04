// Given the head of a SLL, return the list after sorting it in increasing order using QUICK SORT algorithm. 
// TC: O(NlogN) | SC: constant

// INPUT 
// 1 7 2 6 3 5 4

// OUTPUT 
// 1->2->3->4->5->6->7->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class quickSortLL_26 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        head = quickSort(head)[0];  // returned {head, tail} of sorted list
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

    public static ListNode getPivotNode(ListNode node, int pivotIdx) {
        while(pivotIdx-- > 0) { node = node.next; }
        return node;
    }

    // TC: O(NlogN) in best case and O(N^2) in worst case | SC: constant as no extra space other than recursive stack
    public static ListNode[] quickSort(ListNode head) {
        if(head == null || head.next == null) return new ListNode[] { head, head };

        // if pivot idx is last node, need to traverse list to get its idx
        ListNode[] segregatedNodes = partitionOverPivotIdx(head, 0);    // TC: O(N) 

        ListNode[] leftSortedPartition = quickSort(segregatedNodes[0]);  // recursive call on head of smaller list - TC: O(N/2) in best case
        ListNode[] rightSortedPartition = quickSort(segregatedNodes[2]); // recursive call on head of greater list - TC: O(N/2) in best case
        return mergeSortedPartitionsWithPivot(leftSortedPartition, segregatedNodes[1], rightSortedPartition);   // TC: O(1)
    }

    // segregates list over pivotIdx/head and returns head of smaller nodes list, pivot node and head of greater node list
    // TC: O(N)
    public static ListNode[] partitionOverPivotIdx(ListNode head, int pivotIdx) {  

        ListNode pivot = head;  // TC: O(1) but if we use getPivotNode(head, pivotIdx) - TC: O(N)

        ListNode headS = new ListNode(-1), prevS = headS;
        ListNode headL = new ListNode(-1), prevL = headL;

        ListNode curr = head;
        while(curr != null) {   // segregates smaller nodes, greater nodes and pivot node
            if(curr != pivot) {
                if(curr.data <= pivot.data) {   // point to smaller list
                    prevS.next = curr;
                    prevS = prevS.next;
                } else {        // point to greater list
                    prevL.next = curr;
                    prevL = prevL.next;
                }
            }
            curr = curr.next;
        }

        // point all last nodes of 3 parts to null to terminate existing connections
        prevL.next = null;  
        prevS.next = null;
        pivot.next = null;

        return new ListNode[] { headS.next, pivot, headL.next } ;
    }

    // merges left and right sorted portions with pivot node in the middle
    // returns {head, tail} of merged list
    // TC: O(1)
    public static ListNode[] mergeSortedPartitionsWithPivot(ListNode[] leftSortedPartition, ListNode pivot, 
                                                            ListNode[] rightSortedPartition) {
        ListNode head = null, tail = null;
        if(leftSortedPartition[0] != null && rightSortedPartition[0] != null) {     // if both portions are present
            pivot.next = rightSortedPartition[0];
            leftSortedPartition[1].next = pivot;
            head = leftSortedPartition[0];
            tail = rightSortedPartition[1];
        } else if(leftSortedPartition[0] != null) {     // if left portion is present
            leftSortedPartition[1].next = pivot;
            head = leftSortedPartition[0];
            tail = pivot;
        } else if(rightSortedPartition[0] != null) {    // if right portion is present
            pivot.next = rightSortedPartition[0];
            head = pivot;
            tail = rightSortedPartition[1];
        } else {        // if both portions are null
            head = tail = pivot;
        }
        return new ListNode[] { head, tail };
    }
}
