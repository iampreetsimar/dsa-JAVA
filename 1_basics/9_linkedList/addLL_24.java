// 1. You are required to complete the body of addLinkedLists function. 
// The function is passed two ll which represent two numbers, the first element in list is most significant digit
// and the last element in list is the least significant one.
// The functions is expected to add two ll and return result in a new ll.
// 
// NOT ALLOWED -
// 1. Reverse ll to access them from least to most significant digits.
// 2. Use arrays or explicit extra memory.
// 3. Convert ll to numbers, add them and convert result to a new ll.

// Constraints
// Time Complexity -> O(N)
// Space Complexity -> O(N)

// Sample Input
// 9 9 9 9  -> list
// 1 1 -> list

// Sample Output
// 1 0 0 1 0

import java.io.*;

public class addLL_24 {
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
            } else if(str.startsWith("add")) {
                LinkedList l1 = new LinkedList();
                LinkedList l2 = new LinkedList();

                // l1 : 9 8 7
                l1.addLast(9);
                l1.addLast(8);
                l1.addLast(7);

                // l2 : 6 5
                l2.addLast(6);
                l2.addLast(5);

                LinkedList result = list.addLinkedLists(l1, l2);
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

        // RECURSIVE 
        // node1 - node from list 1
        // pv1 - placeholder value of node1 in list 1
        // node2 - node from list 2
        // pv2 - placeholder value of node2  in list 2
        // res - result of adding list 1 and list 2
        private int addLLHelper(Node node1, int pv1, Node node2, int pv2, LinkedList res) {
            if(node1 == null && node2 == null) {        // BASE CASE
                // pv1 = 0 and pv2 = 0 -> return carry 0 as no addition
                return 0;
            }

            int data = 0;
            if(pv1 > pv2) {     // no item in list 2 at pv1
                int carry = addLLHelper(node1.next, pv1 - 1, node2, pv2, res);

                // add digit from list1 with carry
                data = node1.data + carry;

            } else if(pv1 < pv2) {         // no item in list 1 at pv2
                int carry = addLLHelper(node1, pv1, node2.next, pv2 - 1, res);

                // add digit from list2 with carry
                data = node2.data + carry;

            } else {        // item present in both list 1 and 2 at pv1 == pv2
                int carry = addLLHelper(node1.next, pv1 - 1, node2.next, pv2 - 1, res);

                // add digits from list1 and list2 with carry
                data = node1.data + node2.data + carry;
            }

            // add remainder of digit sum to front of res
            // return quotient of digit sum as carry
            res.addFirst(data % 10);
            return data / 10;
        }

        LinkedList addLinkedLists(LinkedList l1, LinkedList l2) {
            LinkedList res = new LinkedList();
            int carry = addLLHelper(l1.head, l1.size, l2.head, l2.size, res);
            if(carry > 0) {
                // if carry present -> add to front of res
                res.addFirst(carry);
            }

            return res;
        }
    }
}
