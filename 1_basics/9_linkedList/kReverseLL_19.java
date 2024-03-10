// 1. You are required to complete the body of kReverse function. 
// 1.1 kReverse - The function is expected to tweak the list such that all groups of k elements
// in the list get reversed and linked. If last set has less than k elements, 
// leave it as it is(don't reverse last group).

// Constraints
// Time Complexity -> O(N)
// Space Complexity -> O(1)

// Sample Input
// 1 2 3 4 5 6 7 8 9 10 11 -> list
// 3 -> k

// Sample Output
// 1 2 3 4 5 6 7 8 9 10 11 -> list
// 3 2 1 6 5 4 9 8 7 10 10 -> after kReverse

import java.io.*;

public class kReverseLL_19 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList();

        String str = br.readLine();
        while(str.equals("quit") == false) {
            if(str.startsWith("addLast")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addLast(val);
            } else if(str.startsWith("addFirst")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addLast(val);
            } else if(str.startsWith("display")) {
                list.display();
            } else if(str.startsWith("removeFirst")) {
                list.removeFirst();
            } else if(str.startsWith("size")) {
                System.out.println(list.size());
            } else if(str.startsWith("kReverse")) {
                int k = Integer.parseInt(str.split(" ")[1]);
                list.kReverse(k);
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

        void removeFirst() {
            if(size == 0) {
                System.out.println("List Empty");
            } else if(size == 1) {
                head = tail = null;
                size--;
            } else {
                head = head.next;
                size--;
            }
        }

        int getFirst() {
            if(size == 0) {
                System.out.println("List Empty");
                return -1;
            }
            return head.data;
        }

        void kReverse(int k) {
            // valid k

            // prev LL object - store reversed k groups
            LinkedList prev = null;

            // loop till list not empty
            while(this.size() > 0) {
                // curr LL object - store current reversed k elements
                LinkedList curr = new LinkedList();

                // check if k items present in list
                if(this.size() >= k) {
                    // k elements present in list -> can be reversed
                    // loop k times
                    for(int i = 0; i < k; i++) {
                        int val = this.getFirst();
                        this.removeFirst();
                        curr.addFirst(val);        // addFirst reverses the order of items from list
                    }
                } else {
                    // < k items present in list
                    // cannot form k subgroup -> don't reverse
                    while(this.size() > 0) {
                        int val = this.getFirst();
                        this.removeFirst();
                        curr.addLast(val);      // addLast adds items to curr in same order as list
                    }
                }

                if(prev == null) {
                    // no k subgroup has been made yet
                    // assign prev to curr
                    // curr contains reversed k subgroup
                    prev = curr;
                } else {
                    // prev already has reversed subgroup
                    // join prev to curr which has next k subgroup
                    // update prev tail and size
                    prev.tail.next = curr.head;
                    prev.tail = curr.tail;
                    prev.size += curr.size;
                }
            }

            // list is empty now
            // prev contains final reversed k subgroups
            // point list head, tail and size to prev
            this.head = prev.head;
            this.tail = prev.tail;
            this.size = prev.size;
        }
    }
}
