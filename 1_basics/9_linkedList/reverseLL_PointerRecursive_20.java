// 1. You are required to complete the body of reversePR and reversePRHelper functions. 
// The functions are expected to reverse the list using RECURSION and changing next data member of nodes.

// Constraints
// Time Complexity -> O(N)
// Space Complexity -> O(N)

// Sample Input
// 1 2 3 4 5 6 -> list

// Sample Output
// 1 2 3 4 5 6 -> list
// 6 5 4 3 2 1 -> after reversePR

import java.io.*;

public class reverseLL_PointerRecursive_20 {
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
            } else if(str.startsWith("reversePR")) {
                list.reversePR();
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

        private void reversePRHelper(Node node) {
            if(node.next == null)   // node is tail - BASE CASE
                return;

            reversePRHelper(node.next);

            // point next of curr node.next to node itself - this reverses the link of node next to curr node
            // in postorder
            node.next.next = node;
        }

        void reversePR(){
            // send head as initial node to recursion helper
            reversePRHelper(head);

            // links are reversed in list except head
            // point next of head to null
            head.next = null;

            // list links are reversed but head and tail are same
            // swap
            Node temp = head;
            head = tail;
            tail = temp;
        }
    }
}
