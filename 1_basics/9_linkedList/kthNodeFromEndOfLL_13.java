// 1. You are required to complete the body of kthFromLast function. 
// 1.1 kthFromLast - should be an iterative function and should return the kth node from end of linked list.
// Do not use size data member directly or indirectly(by traversing to know size).
// k is 0 based index. Assume only valid values of k is passed

// Constraints
// 1. Size data member should be used directly or indirectly. 
// 2. Constant time, single traversal is expected. 
// 3. Iterative solution(no recursion) is expected. 

import java.io.*;

public class kthNodeFromEndOfLL_13 {
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
            } else if(str.startsWith("getFirst")) {
                int val = list.getFirst();
                if(val != -1) {
                    System.out.println(val);
                }
            } else if(str.startsWith("getLast")) {
                int val = list.getLast();
                if(val != -1) {
                    System.out.println(val);
                }
            } else if(str.startsWith("getAt")) {
                int idx = Integer.parseInt(str.split(" ")[1]);
                int val = list.getAt(idx);
                if(val != -1) {
                    System.out.println(val);
                }
            } else if(str.startsWith("addFirst")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addFirst(val);
            } else if(str.startsWith("addAt")) {
                int idx = Integer.parseInt(str.split(" ")[1]);
                int val = Integer.parseInt(str.split(" ")[2]);
                list.addAt(idx, val);
            } else if(str.startsWith("removeLast")) {
                list.removeLast();
            } else if(str.startsWith("removeAt")) {
                int idx = Integer.parseInt(str.split(" ")[1]);
                list.removeAt(idx);
            } else if(str.startsWith("reverseDI")) {
                list.reverseDI();
            } else if(str.startsWith("reversePI")) {
                list.reversePI();
            } else if(str.startsWith("kthFromLast")) {
                int k = Integer.parseInt(str.split(" ")[1]);
                System.out.println(list.kthFromLast(k));
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

        void addFirst(int val) {
            Node node = new Node();
            node.data = val;

            node.next = head;
            head = node;

            if(size == 0) {
                tail = node;
            }
            size++;
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

        void addAt(int idx, int val) {
            if(idx < 0 || idx > size) {
                System.out.println("Invalid arguments");
            } else if(idx == 0) {
                addFirst(val);
            } else if(idx == size) {
                addLast(val);
            } else {
                Node node = new Node();
                node.data = val;

                Node temp = head;
                while(idx > 1) {
                    temp = temp.next;
                    idx--;
                }

                node.next = temp.next;
                temp.next = node;
                size++;
            }
        }

        int getFirst() {
            if(size == 0) {
                System.out.println("List empty");
                return -1;
            }
            return head.data;
        }

        int getLast() {
            if(size == 0) {
                System.out.println("List empty");
                return -1;
            }
            return tail.data;
        }

        int getAt(int idx) {
            if(size == 0) {
                System.out.println("List empty");
                return -1;
            }

            if(idx < 0 || idx >= size) {
                System.out.println("Invalid arguments");
                return -1;
            }

            Node temp = head;
            for(int i = 0; i < idx; i++) {
                temp = temp.next;
            }
            return temp.data;
        }

        void removeFirst() {
            if(size == 0) {
                System.out.println("List empty");
            } else if(size == 1) {
                head = tail = null;
                size--;
            } else {
                head = head.next;
                size--;
            }
        }

        void removeLast() {
            if(size == 0) {
                System.out.println("List empty");
            } else if(size == 1) {
                head = tail = null;
                size--;
            } else {
                Node temp = head;
                for(int i = 0; i < size - 2; i++) {
                    temp = temp.next;
                }
                temp.next = null;
                tail = temp;
                size--;
            }
        }

        void removeAt(int idx) {
            if(idx < 0 || idx >= size) {
                System.out.println("Invalid arguments");
            } else if(idx == 0) {
                removeFirst();
            } else if(idx == size - 1) {
                removeLast();
            } else {
                Node temp = head;
                while(idx > 1) {
                    temp = temp.next;
                    idx--;
                }

                temp.next = temp.next.next;
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
            // only valid k
            // initial point
            Node slow = head;
            Node fast = head;

            // Move fast k times - to get a gap of k b/w slow and fast
            while(k > 0) {
                fast = fast.next;
                k--;
            }

            // Move slow, fast till fast reaches last node(k = 0)
            // Since fast at k = 0 -> slow would be at kth node from last 
            // as there is a gap of k b/w slow and fast
            while(fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }

            // slow at kth node from last
            return slow.data;
        }
    }
}
