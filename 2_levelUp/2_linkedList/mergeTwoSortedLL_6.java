// Given two sorted ll's(sorted in increasing order) heads, merge both ll and return head of a single sorted ll. 

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class mergeTwoSortedLL_6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts1 = br.readLine().split(" ");
        String[] parts2 = br.readLine().split(" ");
        ListNode head1 = createList(parts1);
        ListNode head2 = createList(parts2);

        ListNode mergedLLHead = mergeTwoSortedLists(head1, head2);
        displayList(mergedLLHead);
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;

        ListNode(int data) {
            this.data = data;
        }
    }

    public static ListNode createList(String[] input) {
        ListNode head = new ListNode(-1);
        ListNode prev = head;
        for(int i = 0; i < input.length; i++) {
            prev.next = new ListNode(Integer.parseInt(input[i]));
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

    // TC: O(list1.size + list2.size) | SC: constant
    public static ListNode mergeTwoSortedLists(ListNode h1, ListNode h2) {
        if(h1 == null && h2 == null) return null;   // edge case: if both lists are null -> no merging required

        ListNode p1 = h1, p2 = h2;  // pointers to traverse list1 and list2
        ListNode resHead = new ListNode(-1), tail = resHead;    // use a dummy node for mergedList with its tail pointer

        while(p1 != null && p2 != null) {  // loop stops if any of the two lists reaches null - points tail to next smallest node
            if(p1.data < p2.data) {     // if node in list1 is smaller -> points tail's next to this node
                tail.next = p1; p1 = p1.next;
            } else {    // if node in list2 is smaller -> points tail's next to this node 
                tail.next = p2; p2 = p2.next;
            }
            tail = tail.next;   // tail also gets updated in both case
        }

        if(p1 != null) tail.next = p1;  // if list1 still remains -> list2 reached null -> point tail to rest of the nodes from list1
        if(p2 != null) tail.next = p2;  // if list2 still remains -> list1 reached null -> point tail to rest of the nodes from list2

        ListNode temp = resHead;
        resHead = resHead.next; // actual merged list head should be node next of dummy node
        temp.next = null;
        return resHead;
    }
}
