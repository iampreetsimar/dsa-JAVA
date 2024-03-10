// 1. You are required to complete the body of mergeTwoSortedLists function. 
// The function is static and passed two ll which are sorted.
// The functions is expected to return a new sorted list containing elements of both lists.
// Originals lists must stay same.

// Constraints
// Time Complexity -> O(N)
// Space Complexity -> O(N)

// Sample Input
// 10 20 30 40 50 -> list1
// 7 9 12 15 37 43 44 48 52 56 -> list2

// Sample Output
// 7 9 10 12 15 20 30 37 40 43 44 48 50 52 56

import java.io.*;

public class mergeTwoSortedLL_25 {
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
            } else if(str.startsWith("mergeTwoSortedLL")) {
                LinkedList l1 = new LinkedList();
                LinkedList l2 = new LinkedList();

                // l1 : 10 20 30 40 50
                l1.addLast(10);
                l1.addLast(20);
                l1.addLast(30);
                l1.addLast(40);
                l1.addLast(50);

                // l2 : 7 9 12 15 37 43 44 48 52 56
                l2.addLast(7);
                l2.addLast(9);
                l2.addLast(12);
                l2.addLast(15);
                l2.addLast(37);
                l2.addLast(43);
                l2.addLast(44);
                l2.addLast(48);
                l2.addLast(52);
                l2.addLast(56);

                LinkedList result = list.mergeTwoSortedLists(l1, l2);
                result.display();
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

        LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
            Node one = l1.head;     // one traverses l1
            Node two = l2.head;     // two traverses l2

            LinkedList res = new LinkedList();

            while(one != null && two != null) {
                // loop stops if either l1 traversal complete or l2 traversal complete
                if(one.data < two.data) {
                    // add one.data to last of res -> move one forward
                    res.addLast(one.data);
                    one = one.next;
                } else {
                    // add two.data to last of res -> move two forward
                    res.addLast(two.data);
                    two = two.next;
                }
            }

            while(one != null) {
                // l2 traversal complete -> but l1 traversal remaining
                // add all l1 remaining items to res
                res.addLast(one.data);
                one = one.next;
            }

            while(two != null) {
                // l1 traversal complete -> but l2 traversal remaining
                // add all l2 remaining items to res
                res.addLast(two.data);
                two = two.next;
            }

            return res;     // res contains all items of l1 and l2 in sorted manner
        }
    }
}
