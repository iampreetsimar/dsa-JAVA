// Given a SLL, reverse the list by changing the links b/w nodes. 

// INPUT
// 1->2->3->4->5->/

// OUTPUT
// 5->4->3->2->1->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class reverseLL_AddFirst_11 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        head = reverseListUsingAddFirst(head);
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

    // TC: O(1), SC: constant
    public static ListNode addFirst(ListNode newHead, ListNode node) {
        if(newHead == null)     // if new list has 0 nodes, new node becomes head
            newHead = node;
        else {      // otherwise add new node in front of new list's head
            node.next = newHead; newHead = node;     // new node becomes head
        }
        return newHead;
    }

    // similar to removeFirst() + addFirst()
    // TC: O(N) | SC: constant as no new node created, only links are changed
    public static ListNode reverseListUsingAddFirst(ListNode head) {
        ListNode newHead = null, curr = head;

        while(curr != null) {
            ListNode nbr = curr.next;
            curr.next = null;      // delink curr node
            newHead = addFirst(newHead, curr);      // add first curr node to new list using addFirst()
            curr = nbr;     // move curr fwd
        }

        return newHead;     // head of new list
    }
}
