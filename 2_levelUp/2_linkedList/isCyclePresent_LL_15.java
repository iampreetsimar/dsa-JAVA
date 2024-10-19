// Given a SLL, determine if the list has a cycle in it. 
// There is a cycle in the list if there is some node in list which can be reached again by continuously following the 
// next pointer. 

// INPUT
// 1->2->3->4->5->6->7->8
// 4  -> idx of node to which last node is connected. If -1, last node's next is null

// OUTPUT
// true

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class isCyclePresent_LL_15 {
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

        System.out.println(isCyclePresent(arr[0]));
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;
        ListNode(int data) { this.data = data; }
    }

    public static boolean isCyclePresent(ListNode head) {
        if(head == null || head.next == null) return false; // base case : no cycle in case of 0 or 1(no next node) node

        ListNode slow = head, fast = head;  // fast:slow = 2:1
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) return true;   // meeting point found -> cycle present
        }

        return false;   // fast = null or fast.next = null -> no cycle present
    }
}
