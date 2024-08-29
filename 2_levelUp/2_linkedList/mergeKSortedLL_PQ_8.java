// Given an array of k ll, each ll is sorted in increasing order. 
// Merge all the ll into one sorted ll and return its head. 

// INPUT
// 3
// 0->0->0->/
// 0->0->1->1->1->2->2->4->/
// 0->0->0->0->5->5->6->/

// OUTPUT
// 0->0->0->0->0->0->0->0->0->1->1->1->2->2->4->5->5->6->/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class mergeKSortedLL_PQ_8 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        ListNode[] lists = new ListNode[k];
        for(int i = 0; i < k; i++) {
            String[] parts = br.readLine().split(" ");
            lists[i] = createList(parts);
        }

        ListNode head = mergeKSortedLists(lists);
        displayList(head);
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;

        ListNode(int data) {
            this.data = data;
        }
    }

    public static ListNode createList(String[] parts) {
        ListNode head = new ListNode(-1);
        ListNode prev = head;
        for(int i = 0; i < parts.length; i++) {
            prev.next = new ListNode(Integer.parseInt(parts[i]));
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

    // use PRIORITY QUEUE approach | TC: O(NlogK)
    // for mmin priority: this - other | for max priority: other - this 
    public static ListNode mergeKSortedLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(   // PQ with comparator priority given to min node
            (a, b) -> { return a.data - b.data;}
        );

        for(ListNode node: lists) {     // add head nodes of k lists | PQ size -> k | TC: KlogK
            if(node != null) pq.add(node);
        }

        ListNode head = new ListNode(-1), prev = head;   // dummy node for final result list  
        while(pq.size() > 0) {  // loop runs till pq has a item node in it | T: 2NlogK as all nodes will be added and removed from PQ at some point
            ListNode node = pq.remove();   // node with min priority(min nodes out of all) is removed | T: (NlogK)
            prev.next = node;   // add removed node to next of last node of final list
            prev = prev.next;   // move last node of final list forward
            node = node.next;   // the removed node also moves forward in its list
            if(node != null) pq.add(node);  // if the new removed node pointer is not null, add it to PQ for comparing | T: (N-KlogK)
        }

        ListNode temp = head;
        head = head.next;   // node next to dummy node is head of final result list
        temp.next = null;
        return head;
    }
}
