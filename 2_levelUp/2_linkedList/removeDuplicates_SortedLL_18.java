// Given a sorted SLL, remove all duplicate nodes such that they appear only once and return the remaining ll. 

// INPUT
// 1->1->1->4->5->6->6->7->8->9->9->9->/

// OUTPUT
// 1->4->5->6->7->8->9->/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class removeDuplicates_SortedLL_18 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        ListNode head = createList(parts);

        // removeDuplicates(head);
        // displayList(head);

        head = removeDuplicatesUsingAddLast(head);
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

    public static void removeDuplicates(ListNode head) {
        if(head == null || head.next == null) return;   // base case
        ListNode curr = head.next, prev = head;
        while(curr != null) {
            ListNode nbr = curr.next;   
            if(curr.data != prev.data) {   // curr node's val != prev node's val
                prev.next = curr;   // add curr node to prev's next
                prev = prev.next;   // update prev
            } else {
                curr.next = null;   // can ignore(taken care by GC) or point to null
            }
            curr = nbr; // move curr forward
        }
        prev.next = curr;   // last valid node: prev may/may not point to null -> point it to curr(which is null now)
    }

    public static ListNode removeDuplicatesUsingAddLast(ListNode head) {
        if(head == null || head.next == null) return head;  // base case
        ListNode curr = head, tempHead = null, tempTail = null;  // for o/p list
        while(curr != null) {
            ListNode nbr = curr.next;   // store curr's next as nbr
            curr.next = null;   // remove curr's next link
            if(tempTail == null || tempTail.data != curr.data) {   // add last curr node to o/p list
                if(tempTail == null)    // if o/p list empty -> set both head and tail to node
                    tempHead = tempTail = curr;
                else {      // otherwise, point o/p list's tail's next to node and update tail
                    tempTail.next = curr;
                    tempTail = tempTail.next;
                }
            }
            curr = nbr; // move curr to nbr to traverse i/p list
        }
        return tempHead;    // return head of o/p list
    }
}
