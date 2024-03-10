// 1. You are required to complete the body of oddEven function. 
// 1.1 oddEven - is expected to tweak the linked list such that all odd values are followed by all 
// even values. The relative order of elements should not change.
// Make sure of the case when there are no odd or no even elements.
// Make sure to properly set head, tail and size properties.

// Constraints
// Time Complexity -> O(N)
// Space Complexity -> O(1)

// Sample Input
// 7 -> N
// 2 8 9 1 5 4 3 -> list
// 10 -> addFirst
// 100 -> addLast

// Sample Output
// 2 8 9 1 5 4 3 -> list
// 9 1 5 3 2 8 4 -> after oddEven()
// 10 9 1 5 3 2 8 4 100

import java.io.*;

public class oddEvenLL_16 {
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
            } else if(str.startsWith("removeDuplicates")) {
                list.removeDuplicates();
            } else if(str.startsWith("oddEven")) {
                list.oddEven();
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

        void removeDuplicates() {
            LinkedList res = new LinkedList();

            while(this.size() > 0) {
                int val = this.getFirst();
                this.removeFirst();

                if(res.size() == 0 || res.tail.data != val) {
                    res.addLast(val);
                }
            }

            this.head = res.head;
            this.tail = res.tail;
            this.size = res.size();
        }

        void oddEven() {
            // creating two LL objects
            LinkedList odd = new LinkedList();
            LinkedList even = new LinkedList();

            // traversing list
            while(this.size > 0) {
                // get node value from front of list
                int val = this.getFirst();

                // remove node from front of list
                this.removeFirst();

                // addLast val removed from list to odd/even
                if(val % 2 == 0) {
                    even.addLast(val);
                } else {
                    odd.addLast(val);
                }
            }

            if(odd.size > 0 && even.size > 0) {
                // both odd and even has items
                // merge odd and even
                // update head, tail and size of list
                odd.tail.next = even.head;
                this.head = odd.head;
                this.tail = even.tail;
                this.size = odd.size + even.size;
            } else if(odd.size > 0) {
                // only odd has items
                // update head, tail and size of list
                this.head = odd.head;
                this.tail = odd.tail;
                this.size = odd.size;
            } else {
                // only even has items
                // update head, tail and size of list
                this.head = even.head;
                this.tail = even.tail;
                this.size = even.size;
            }
        }
    }
}
