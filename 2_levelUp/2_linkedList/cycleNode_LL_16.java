// Given a SLL, return the node where cycle begins. If there is no cycle present, return null. 
// There is a cycle in the list if there is some node in list which can be reached again by continuously following the 
// next pointer. 

// INPUT
// 1->2->3->4->5->6->7->8->9
// 4  -> idx of starting node of cycle

// OUTPUT
// 5

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class cycleNode_LL_16 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int idx = Integer.parseInt(br.readLine());
        ListNode[] arr = new ListNode[parts.length];
        ListNode prev = null;
        for(int i = 0; i < arr.length; i++) {
            arr[i] = new ListNode(Integer.parseInt(parts[i]));
            if(prev != null) 
                prev.next = arr[i];
            prev = arr[i];
        }
        if(idx != -1) arr[arr.length - 1].next = arr[idx];

        ListNode cycleNode = getCycleNode(arr[0]);
        System.out.println((cycleNode != null ? cycleNode.data : "no cycle present"));
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;
        ListNode(int data) { this.data = data; }
    }

    public static ListNode getCycleNode(ListNode head) {
        if(head == null || head.next == null) return null;  // base case

        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {  // detects if cycle present
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) break;     // cycle present -> find cycle node now
        }

        if(slow != fast) return null;  // no cycle present -> return null

        slow = head; // and fast = fast; | slow(from head again) and fast at same speed

        while(slow != fast) {   // meet at cycle node
            slow = slow.next;
            fast = fast.next;
        }

        return slow;    // slow and fast at cycle node
    }
}
