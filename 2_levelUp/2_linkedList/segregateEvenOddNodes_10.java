// Given a SLL, modify the given list such that all the even nodes appear before all the odd nodes in the modified ll. 
// The order of appearance of numbers within each segregation should be same as that in the OG list. 

// INPUT
// 1->7->2->6->3->5->4->/

// OUTPUT
// 2->6->4->1->7->3->5->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class segregateEvenOddNodes_10 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        head = segregateEvenOdd(head);
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

    // TC: O(N) | SC: constant
    public static ListNode segregateEvenOdd(ListNode node) {
        if(node == null || node.next == null) return node;      // edge cases: 0/1 nodes -> no change needed

        ListNode oddHead = new ListNode(-1), oddTail = oddHead;
        ListNode evenHead = new ListNode(-1), evenTail = evenHead;

        while(node != null) {
            if(node.data % 2 == 0) {        // node: even -> add it to even tail and update even tail
                evenTail.next = node; 
                evenTail = evenTail.next;
            } else {                        // node: odd -> add it to odd tail and update odd tail
                oddTail.next = node; 
                oddTail = oddTail.next;
            }
            node = node.next;
        }

        ListNode temp = oddHead;
        oddHead = oddHead.next;     // odd actual head is node next to dummy node
        temp.next = null;

        temp = evenHead;            // even actual head is node next to dummy node
        evenHead = evenHead.next; 
        temp.next = null;

        evenTail.next = oddHead;      // add odd head to even tail
        oddTail.next = null;          // no node should be after odd tail

        return evenHead;        // even head becomes list head
    }
}
