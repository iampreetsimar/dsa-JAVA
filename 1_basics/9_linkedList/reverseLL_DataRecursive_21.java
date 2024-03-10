// 1. You are required to complete the body of reverseDR function. 
// The function is expected to reverse the list using RECURSION and changing 'data' data member of nodes.

// Constraints
// Time Complexity -> O(N)
// Space Complexity -> O(N)

// Sample Input
// 1 2 3 4 5 6 -> list

// Sample Output
// 1 2 3 4 5 6 -> list
// 6 5 4 3 2 1 -> after reverseDR(only data is changed, head and tail are same)

import java.io.*;

public class reverseLL_DataRecursive_21 {
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
            } else if(str.startsWith("reverseDR")) {
                list.reverseDR();
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

        private void reverseDRHelper(Node node, int floor) {
            if(node == null)
                return;     // BASE CASE : node next to tail

            // floor : 0 -> size
            reverseDRHelper(node.next, floor + 1);      // going up the stack

            // going down the stack
            if(floor >= size / 2) {     // no swap for floor < size/2 -> no prevent LL becoming same as original
                // swap data of left and node
                int temp = left.data;
                left.data = node.data;
                node.data = temp;

                // move left forward
                left = left.next;
            }
        }

        Node left;
        void reverseDR(){
            left = head;    // assign left to head

            // pass head as initial node and 0th floor
            reverseDRHelper(head, 0);
        }
    }
}
