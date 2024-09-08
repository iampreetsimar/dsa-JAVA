// Given a SLL with an additional pointer w/c could point to any node in the list or NULL. 
// Return a deep copy(occupies different space from OG) of the list. 

// INPUT
// 1 2 3 4 5 6 7 8
// 0 3 0 -1 3 3 -1 4

// OUTPUT
// (1, 1), (2, 4), (3, 1), (4, -1), (5, 4), (6, 4), (7, -1), (8, 5)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class copyLLRandomPointers_13 {
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

        ListNode copyHead = copyList(arr[0]);

        while(copyHead != null) {
            System.out.print("(" + copyHead.data + ", " + (copyHead.random != null ? copyHead.random.data : -1) + ")-->");
            copyHead = copyHead.next;
        }
        System.out.println("/");
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null; ListNode random = null;
        ListNode(int data) { this.data = data; }
    }

    // TC: O(N) | SC: O(N)
    public static ListNode copyList(ListNode head) {
        if(head == null) return null;   // edge case
        HashMap<ListNode, ListNode> map = new HashMap<>();
        ListNode curr = head, newHead = new ListNode(-1), prev = newHead;
        while (curr != null) {    // 1st pass : create deep copy list with valid next pointers | map node:copyNode as key:value pair
            ListNode node = new ListNode(curr.data);
            prev.next = node;
            map.put(curr, node);
            prev = prev.next; curr = curr.next;
        }

        curr = head;    // set curr for next pass
        ListNode temp = newHead; newHead = newHead.next; temp.next = null;

        // while(curr != null) {    // 2nd pass: approach1: map node's random pointers' copy node to copy node's random pointer
        //     ListNode currCopy = map.get(curr);   // get copy node from map
        //     ListNode randomCopy = curr.random != null ? map.get(curr.random): null;
        //     currCopy.random = randomCopy;
        //     curr  = curr.next;
        // }

        ListNode currCopy = newHead;    // copy node also traverses copy list
        while(curr != null) {   // 2nd pass: approach2: map node's random pointers' copy node to copy node's random pointer
            currCopy.random = curr.random != null ? map.get(curr.random) : null;
            curr  = curr.next; currCopy = currCopy.next;
        }

        return newHead;     // return head of copy list
    }
}
