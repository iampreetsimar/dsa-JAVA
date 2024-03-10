// 1. You are required to complete the body of fold function. 
// The function is expected to place last element after 1st element, 2nd last element after 2nd element and so on.

// Constraints
// Time Complexity -> O(N)
// Space Complexity -> O(N)

// Sample Input
// 1 2 3 4 5 -> list
// 1 2 3 4 5 6 -> list

// Sample Output
// 1 5 2 4 3 -> after fold
// 1 6 2 5 3 4 -> after fold

import java.io.*;

public class foldLL_Recursive_23 {
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
            } else if(str.startsWith("fold")) {
                list.fold();
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

        private void foldHelper(Node node, int idx) {
            if(node == null)        //  BASE CASE
                return;     // node after tail

            foldHelper(node.next, idx + 1);     // idx : 0(head node) -> size(for null node after tail)

            // Condition similar to Reverse LL - Data Recursive
            // fold nodes when idx > size/2 -> idx == size/2 => last node
            // do not do anything idx < size/2
            if(idx > size / 2) {
                // fold nodes
                // store next of left as nbr(as connection to nbr will break) -> point next of left to node
                // point next of node to nbr
                // move left forward to nbr
                Node nbr = left.next;
                left.next = node;
                node.next = nbr;
                left = nbr;
            } else if(idx == size / 2) {
                // last node
                // point next of node to null
                // assign node as tail
                node.next = null;
                tail = node;
            }
        }

        Node left;
        void fold() {
            // initial left = head
            left = head;

            // pass head as list starting point and idx = 0
            foldHelper(head, 0);
        }
    }
}
