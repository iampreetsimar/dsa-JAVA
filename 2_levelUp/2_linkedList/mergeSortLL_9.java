// Given a ll, sort it in increasing order using Merge Sort algorithm(divide and conquer). 
// NOTE: TC: O(NlogN), SC: constant

// INPUT
// 1->7->2->6->3->5->4->/

// OUTPUT
// 1->2->3->>4->5->6->7->/


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class mergeSortLL_9 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        head = mergeSort(head);
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

    public static ListNode getMidNode(ListNode node) {
        if(node == null || node.next == null) return node;

        ListNode slow = node, fast = node;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    public static ListNode mergeTwoSortedLists(ListNode head1, ListNode head2) {
        if(head1 == null && head2 == null) return null;

        if(head1 == null || head2 == null) return head1 != null ? head1 : head2;

        ListNode head = new ListNode(-1);
        ListNode prev = head;

        while(head1 != null && head2 != null) {
            if(head1.data < head2.data) {
                prev.next = head1;
                head1 = head1.next;
            } else {
                prev.next = head2;
                head2 = head2.next;
            }
            prev = prev.next;
        }

        if(head1 != null) prev.next = head1;

        if(head2 != null) prev.next = head2;

        ListNode temp = head;
        head = head.next;
        temp.next = null;

        return head;
    }
    
    // TC: O(NlogN) | SC: O(1) as no new node are formed and OG list itself is sorted
    public static ListNode mergeSort(ListNode node) {
        if(node == null) return null;
        
        if(node.next == null) return node;  // for constant space | for new list, return new ListNode(node.data)

        ListNode mid = getMidNode(node);       
        ListNode fwd = mid.next;
        mid.next = null;

        ListNode firstHalfSortedHead = mergeSort(node);    
        ListNode secondHalfSortedHead = mergeSort(fwd);   
        ListNode mergedSortedHead = mergeTwoSortedLists(firstHalfSortedHead, secondHalfSortedHead);    

        // mid.next = fwd;  // if new list(extra space), changes in OG list must be removed
        return mergedSortedHead;
    }
}
