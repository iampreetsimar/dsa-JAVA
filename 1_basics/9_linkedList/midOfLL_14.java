// 1. You are required to complete the body of mid function. 
// 1.1 mid - should be an iterative function and should return the middle node linked list. If ll is odd sized, 
// return end node of the first half.
// Do not use size data member directly or indirectly(by traversing to know size).

// Constraints
// 1. Size data member should be used directly or indirectly. 
// 2. Constant time, single traversal is expected. 
// 3. Iterative solution(no recursion) is expected.

import java.io.*;

public class midOfLL_14 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList();

        String str = br.readLine();
        while(str.equals("quit") == false) {
            if(str.startsWith("addLast")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addLast(val);
            } else if(str.startsWith("display")) {
                list.display();
            } else if(str.startsWith("size")) {
                System.out.println(list.size());
            } else if(str.startsWith("reverseDI")) {
                list.reverseDI();
            } else if(str.startsWith("reversePI")) {
                list.reversePI();
            } else if(str.startsWith("kthFromLast")) {
                int k = Integer.parseInt(str.split(" ")[1]);
                System.out.println(list.kthFromLast(k));
            } else if(str.startsWith("mid")) {
                System.out.println(list.mid());
            }

            str = br.readLine();
        }
    }

    public static class Node {
        int data;
        Node next;
    }

    public static class LinkedList {
        Node head, tail;
        int size;

        int size() {
            return size;
        }

        void display() {
            for(Node temp = head; temp != null; temp = temp.next) {
                System.out.print(temp.data + " ");
            }
            System.out.println();
        }

        void addLast(int val) {
            Node node = new Node();
            node.data = val;

            if(size == 0) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size++;
        }

        private Node getNodeAt(int idx) {
            Node temp = head;
            while(idx > 0) {
                temp = temp.next;
                idx--;
            }
            return temp;
        }

        void reverseDI() {
            int l = 0;
            int r = size - 1;

            while(l < r) {
                Node ln = getNodeAt(l);
                Node rn = getNodeAt(r);

                int temp = ln.data;
                ln.data = rn.data;
                rn.data = temp;

                l++;
                r--;
            }
        }

        void reversePI() {
            if(size <= 1)
                return;

            Node prev = null;
            Node curr = head;

            while(curr != null) {
                Node next = curr.next;
                curr.next = prev;

                prev = curr;
                curr = next;
            }

            Node temp = head;
            head = tail;
            tail = temp;
        }

        int kthFromLast(int k) {
            Node slow = head;
            Node fast = head;

            while(k > 0) {
                fast = fast.next;
                k--;
            }

            while(fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow.data;
        }

        int mid() {
            // no empty list -> if empty case -> return -1;

            // initial slow and fast point to head
            Node slow = head;
            Node fast = head;

            // slow moves forward by 1
            // fast moves forward by 2
            // move forward till fast reaches last node(odd LL case) and 
            // fast reaches 2nd last node(even LL case)
            // when loop stops -> slow on mid node
            while(fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            // slow on mid node of LL
            return slow.data;
        }
    }
}
