import java.io.BufferedReader;
import java.io.InputStreamReader;

public class removeKthNodeFromEnd_28 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int k = Integer.parseInt(br.readLine());
        ListNode head = createList(parts);
        head = removeKthNodeFromEnd(head, k);
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
        System.out.print("[ ");
        while(node != null) {
            System.out.print(node.data);
            if(node.next != null) System.out.print("->");
            node = node.next;
        }
        System.out.println(" ]");
    }
    
    public static ListNode removeKthNodeFromEnd(ListNode head, int k) {
        if(head == null || k <= 0) return head;     // edge cases

        ListNode fast = head;   
        while(fast != null && k-- > 0) {  // move fast pointer k times(1-based indexing), if possible
            fast = fast.next; 
        }  // fast can reach null or k can become zero or both

        if(k > 0) return head;  // only fast reached null -> list does not have k nodes

        if(fast == null) {  //  fast reached null and k == 0 -> head is kth node | list.size == k
            ListNode nbr = head.next;   // delete head node and return list
            head.next = null;
            return nbr;
        }

        ListNode slow = head;   // fast not null yet but k == 0 -> use slow pointer(gap of k nodes b/w both pointers now)
        while(fast.next != null) {  // loop stops when fast at last node -> slow at (k+1)th node from end
            slow = slow.next; fast = fast.next;
        }

        ListNode toBeDeleted = slow.next;   // delete kth node which is next node to slow
        slow.next = toBeDeleted.next;
        toBeDeleted.next = null;

        return head;
    }
}
