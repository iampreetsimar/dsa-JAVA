// Given the heads of two SLL headA and headB, return the node at w/c the two lists intersect. 
// If the two ll have no intersection node, return null. 

// INPUT
// 1->2->3->4->5->6->7->8->/
// 10->20->30
// 3   -> idx of node in list 1 where list 2 joins it. 

// OUTPUT
// 4

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class intersectionNode_FloydCycle_17 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts1 = br.readLine().split(" ");
        String[] parts2 = br.readLine().split(" ");
        int idx = Integer.parseInt(br.readLine());

        ListNode[] arr1 = new ListNode[parts1.length];
        ListNode[] arr2 = new ListNode[parts2.length];
        ListNode prev1 = null, prev2 = null;
        for(int i = 0; i < arr1.length; i++) {
            arr1[i] = new ListNode(Integer.parseInt(parts1[i]));
            if(prev1 != null) 
                prev1.next = arr1[i];
            prev1 = arr1[i];
        }

        for(int i = 0; i < arr2.length; i++) {
            arr2[i] = new ListNode(Integer.parseInt(parts2[i]));
            if(prev2 != null) 
                prev2.next = arr2[i];
            prev2 = arr2[i];
        }

        if(idx != -1) arr2[arr2.length - 1].next = arr1[idx];

        ListNode intersectionNode = solve(arr1[0], arr2[0]);
        System.out.println(intersectionNode != null ? intersectionNode.data : "no intersection node present");
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;
        ListNode(int data) { this.data = data; }
    }

    public static ListNode solve(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null; // base case: no intersection if any list is null

        ListNode tailA = headA;
        while(tailA.next != null) { // reach list A's tail
            tailA = tailA.next;
        }

        tailA.next = headB;     // point tailA to listB head so that a cycle is formed if lists are intersecting at some point
        ListNode intersectionNode = getCycleNode(headA);    // cycle node is the intersecting point where listB merges with listA
        tailA.next = null;      // unset listA's tail as before
        return intersectionNode;    // return instersecting node
    }

    public static ListNode getCycleNode(ListNode head) {    // return cycle node if cycle present, else returns null
        if(head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next; fast = fast.next.next;
            if(slow == fast) break;
        }
        if(slow != fast) return null;
        slow = head;
        while(slow != fast) { slow = slow.next; fast = fast.next; }
        return slow;
    }
}
