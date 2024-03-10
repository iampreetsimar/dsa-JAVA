// 1. You are required to complete the body of displayReverse and displayReverseHelper functions. 
// The functions are expected to print in reverse the linked list without actually reversing it.

// Constraints
// Time Complexity -> O(N)
// Space Complexity -> O(N)

// Sample Input
// 7 -> N
// 10 20 30 40 50 60 70 -> list

// Sample Output
// 70 60 50 40 30 20 10 -> print
// 10 20 30 40 50 60 70 -> list

import java.io.*;

public class displayReverseLL_Recursive_17 {
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
            } else if(str.startsWith("reverseDisplay")) {
                list.displayReverse();
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

        // RECURSIVE Function
        private void displayReverseHelper(Node node) {
            if(node == null)
                return;     // BASE CASE - node next to tail

            // moving up the stack with next node
            displayReverseHelper(node.next);

            // printing current node while going down the stack in postorder
            System.out.print(node.data + " ");
        }

        void displayReverse() {
            // prints list in reverse using recursion
            // not actual reversing
            displayReverseHelper(head);
            System.out.println();
        }
    }
}
