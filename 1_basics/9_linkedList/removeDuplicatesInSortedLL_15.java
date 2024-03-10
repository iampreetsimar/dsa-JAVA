// 1. You are required to complete the body of removeDuplicates function. 
// 1.1 removeDuplicates - called on a sorted list. Must remove all duplicates from list in O(N) time and O(1) space.

// Constraints
// Time Complexity -> O(N)
// Space Complexity -> O(1)

import java.io.*;

public class removeDuplicatesInSortedLL_15 {
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
            } else if(str.startsWith("removeFirst")) {
                list.removeFirst();
            } else if(str.startsWith("reverseDI")) {
                list.reverseDI();
            } else if(str.startsWith("reversePI")) {
                list.reversePI();
            } else if(str.startsWith("kthFromLast")) {
                int k = Integer.parseInt(str.split(" ")[1]);
                System.out.println(list.kthFromLast(k));
            } else if(str.startsWith("mid")) {
                System.out.println(list.mid());
            } else if(str.startsWith("removeDuplicates")) {
                list.removeDuplicates();
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

        int getFirst() {
            if(size == 0) {
                System.out.println("List is empty");
                return -1;
            }
            return head.data;
        }

        void removeFirst() {
            if(size == 0) {
                System.out.println("List is empty");
            } else if(size == 1) {
                head = tail = null;
                size--;
            } else {
                head = head.next;
                size--;
            }
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
            Node slow = head;
            Node fast = head;

            while(fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow.data;
        }
        
        void removeDuplicates() {
            // new LL object - stores unique nodes
            LinkedList res = new LinkedList();

            while(this.size() > 0) {
                // get first node val of list
                int val = this.getFirst();

                // remove first node from list
                this.removeFirst();

                if(res.size() == 0 || res.tail.data != val) {
                    // if res empty -> add val to res
                    // if data of res tail is not equal to val -> add val to end of res
                    res.addLast(val);
                }
            }

            // list becomes empty
            // res contains unique nodes only
            // need to point list head, tail and size to that of res
            // since this represents list
            this.head = res.head;
            this.tail = res.tail;
            this.size = res.size();
        }
    }
}
