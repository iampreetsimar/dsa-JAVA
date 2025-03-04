// Given a SLL, reverse the nodes of the given list in groups of size k at a time and return the modified list. 
// If number of nodes in the group are k(multiple of k), reverse the nodes, otherwise don't reverse.

// INPUT
// 3
// 1->2->3->4->5->6->7->8->9->10->11->/

// OUTPUT
// 3->2->1->6->5->4->9->8->7->10->11->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class reverseNodesInKGroup_12 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        head = reverseInKGroup(head, k);
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

    public static int getListSize(ListNode node) {
        int size = 0;
        while(node != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    static ListNode tempHead, tempTail;
    public static void addFirst(ListNode node) {
        if(tempHead == null)
            tempHead = tempTail = node;
        else {
            node.next = tempHead;
            tempHead = node;
        }
    }
    
    // TC: O(N) | SC: constant
    // almost similar to BASICS approach
    public static ListNode reverseInKGroup(ListNode node, int k) {
        if(node == null || node.next == null || k == 0) return node;    // edge case

        ListNode head = null, tail = null;
        tempHead = tempTail = null;

        int size = getListSize(node);

        if(size < k) return node;   // edge case

        while (size >= k) {     // n/k times
            for(int i = 0; i < k; i++) {    // removeFirst + addFirst: k times
                ListNode nbr = node.next;
                node.next = null;
                addFirst(node);
                node = nbr;
            }

            if(head == null) {      // final list is empty
                head = tempHead; tail = tempTail;
            } else {        // final list not empty
                tail.next = tempHead;   // point final list tail to temp list head
                tail = tempTail;    // temp tail becomes final tail
            } 

            tempHead = null; tempTail = null;   // temp tail and head set to null for next iteration
            size -= k;  // decrement size by k
        }

        tail.next = node;  // either final list complete or < k nodes still left (point final tail to next node)
        return head;
    }
}
