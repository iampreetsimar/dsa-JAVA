// Given a SLL with an additional pointer w/c could point to any node in the list or NULL. 
// Return a deep copy(occupies different space from OG) of the list without using extra space.

// INPUT
// 1 2 3 4 5 6 7 8
// 0 3 0 -1 3 3 -1 4

// OUTPUT
// (1, 1), (2, 4), (3, 1), (4, -1), (5, 4), (6, 4), (7, -1), (8, 5)

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class copyLLRandomPointers_ConstantSpace_14 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] partsVal = br.readLine().split(" ");
        String[] partsRdmIdx = br.readLine().split(" ");
        
        ListNode[] arr = new ListNode[partsVal.length];
        ListNode prev = null;
        for(int i = 0; i < arr.length; i++) {
            arr[i] = new ListNode(Integer.parseInt(partsVal[i]));
            if(prev != null) 
                prev.next = arr[i];
            prev = arr[i];
        }

        for(int i = 0; i < arr.length; i++) {
            int rdmIdx = Integer.parseInt(partsRdmIdx[i]);
            if(rdmIdx != -1)
                arr[i].random = arr[rdmIdx];
        }

        ListNode copyHead = copyRandomList(arr[0]);

        while(copyHead != null) {
            System.out.print("(" + copyHead.data + ", " + (copyHead.random != null ? copyHead.random.data : -1) + ")-->");
            copyHead = copyHead.next;
        }
        System.out.println("/");
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null; ListNode random = null; ListNode(int data) { this.data = data; }
    }

    public static ListNode copyRandomList(ListNode head) {  // TC: O(N) | SC: constant
        if(head == null) return null;   // edge case
        copyList(head); copyRandomPointers(head);
        return extractDeepCopyList(head);
    }

    public static void copyList(ListNode head) {    // adds deep copy of each node to its next
        ListNode curr = head;
        while(curr != null) {
            ListNode node = new ListNode(curr.data);
            ListNode nbr = curr.next; curr.next = node; node.next = nbr; curr = nbr;
        }
    }

    public static void copyRandomPointers(ListNode head) {  // copies random pointers from OG nodes
        ListNode curr = head;
        while(curr != null) {
            ListNode copy = curr.next;
            copy.random = curr.random != null ? curr.random.next : null; curr = copy.next;
        }
    }

    public static ListNode extractDeepCopyList(ListNode head) { // extracts copy list from OG list
        ListNode curr = head, newHead = new ListNode(-1), prev = newHead;
        while(curr != null) {
            prev.next = curr.next; curr.next = curr.next.next;
            prev = prev.next; curr = curr.next;
        }
        ListNode temp = newHead; newHead = newHead.next; temp.next = null;
        return newHead;
    }
}
