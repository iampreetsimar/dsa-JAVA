// Given an array of k ll, each ll is sorted in increasing order. 
// Merge all the ll into one sorted ll and return its head. 

// INPUT
// 3
// 0->0->0->/
// 0->0->1->1->1->2->2->4->/
// 0->0->0->0->5->5->6->/

// OUTPUT
// 0->0->0->0->0->0->0->0->0->1->1->1->2->2->4->5->5->6->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class mergeKSortedLL_DnQ_7 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        ListNode[] lists = new ListNode[k];
        for(int i = 0; i < k; i++) {
            String[] parts = br.readLine().split(" ");
            lists[i] = createList(parts);
        }

        ListNode head = mergeKSortedLists(lists);
        displayList(head);
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;

        ListNode(int data) {
            this.data = data;
        }
    }

    public static ListNode createList(String[] parts) {
        ListNode head = new ListNode(-1);
        ListNode prev = head;
        for(int i = 0; i < parts.length; i++) {
            prev.next = new ListNode(Integer.parseInt(parts[i]));
            prev = prev.next;
        }

        ListNode temp = head;
        head = head.next;
        temp.next = null;

        return head;
    }

    public static void displayList(ListNode node) {
        while(node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    // DIVIDE and CONQUER approach - use mergeTwoSortedLists()
    public static ListNode mergeKSortedLists(ListNode[] lists) {
        if(lists.length == 0) return null;  // edge case
        return mergeKSortedLists(lists, 0, lists.length - 1);   // startIdx:0, endIdx: arr.length - 1
    }

    // TC: O(NKlogK)
    public static ListNode mergeKSortedLists(ListNode[] lists, int startIdx, int endIdx) {
        if(startIdx > endIdx) return null;  // BASE CASE

        if(startIdx == endIdx) return lists[startIdx];  // BASE CASE

        int midIdx = (startIdx + endIdx) / 2;

        ListNode firstHalf = mergeKSortedLists(lists, startIdx, midIdx);    // returns sorted list of nodes in first half
        ListNode secondHalf = mergeKSortedLists(lists, midIdx + 1, endIdx); // returns sorted list of nodes in second half
        return mergeTwoSortedLists(firstHalf, secondHalf);  // merge both halves sorted list into a SLL
    }

    public static ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;

        ListNode head = new ListNode(-1), tail = head;

        while(l1 != null && l2 != null) {
            if(l1.data < l2.data) {
                tail.next = l1; l1 = l1.next;
            } else {
                tail.next = l2; l2 = l2.next;
            }
            tail = tail.next;
        }

        if(l1 != null) tail.next = l1;
        if(l2 != null) tail.next = l2;

        ListNode temp = head;
        head = head.next;
        temp.next = null;
        return head;
    }
}
