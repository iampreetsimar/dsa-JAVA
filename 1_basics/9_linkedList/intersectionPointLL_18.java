// 1. You are required to complete the body of findIntersection function. 
// The function is passed two linked list which start separately but merge at a node and become common
// thereafter. The functions is expected to find the point where these two ll merge.
// Not allowed to use arrays.

// Constraints
// Time Complexity -> O(N)
// Space Complexity -> O(1)

// Can't execute this function in local **********
// List1 : 10 -> 20 -> 30 -> 40 -> 50
// List2 : 100 -> 200 -> 300 -> 30(address same as List1) -> 40(address same as List1) -> 50(address same as List1)
// Intersection Point : 30

import java.io.*;

public class intersectionPointLL_18 {
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

        // Cannot test this
        int findIntersection(LinkedList one, LinkedList two) {
            // initial starting point of one and two
            Node p1 = one.head;
            Node p2 = two.head;

            if(one.size() > two.size()) {
                // one has sizeDiff more items than two before instersection point
                // after intersection, number of items will be same
                int sizeDiff = one.size() - two.size();

                // move p1 sizeDiff times so that their p1 and p2 starting point is balanced
                while(sizeDiff > 0) {
                    p1 = p1.next;
                    sizeDiff--;
                }
            } else {
                // two has sizeDiff more items than one before instersection point
                // this will also handle equal size -> starting point is already balanced -> no move
                // after intersection, number of items will be same
                int sizeDiff = two.size() - one.size();

                // move p2 sizeDiff times so that their p1 and p2 starting point is balanced
                while(sizeDiff > 0) {
                    p2 = p2.next;
                    sizeDiff--;
                }
            }

            // move p1 and p2 until both are same
            while(p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }

            // p1 and p2 at same point - intersection point
            return p1.data;
        }
    }
}
