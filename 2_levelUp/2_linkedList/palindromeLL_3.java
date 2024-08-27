// Given a SLL, determine if it is a palindrome or not.

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class palindromeLL_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        ListNode dummyNode = new ListNode(-1);
        ListNode prev = dummyNode;
        for(int i = 0; i < parts.length; i++) {
            prev.next = new ListNode(Integer.parseInt(parts[i]));
            prev = prev.next;
        }

       System.out.println(isPalindrome(dummyNode.next));
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;
        ListNode(int data) { this.data = data; }
    }

    // levelup approach: use getMidNode() and reverseList()
    public static boolean isPalindrome(ListNode node) {
        if(node == null || node.next == null) return true;  // edge case on list size:0,1

        ListNode mid = getMidNode(node);    // get mid node
        ListNode secondHead = mid.next;     // node after mid becomes new head for 2nd half list
        mid.next = null;                    // delink both halves

        secondHead = reverseList(secondHead);       // reverse second half

        ListNode p1 = node, p2 = secondHead;
        boolean isPalindrome = true;
        while(p2 != null) {  // check till second half node reaches null as incase of odd nodes, first half list will have an extra node always
            if(p1.data != p2.data) {        // if any data is different -> ll not a palindrome
                isPalindrome = false;
                break;                  // no point in checking further
            }
            p1 = p1.next; p2 = p2.next;
        }

        secondHead = reverseList(secondHead);   // reverse second half again so that OG list could be made again
        mid.next = secondHead;          // link both halves - OG list restored
        return isPalindrome;        // return stored result
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

    public static ListNode reverseList(ListNode node) {
        if(node == null || node.next == null) return node;

        ListNode prev = null, curr = node;
        while(curr != null) {
            ListNode nbr = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nbr;
        }
        return prev;
    }
}
