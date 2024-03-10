// 1. You are required to complete the body of display and size function. 
// 1.1 display - should print the elements of linked list from front to end separated by a space.
// 1.2 size - should return number of elements in the linked list.

// Input
// addLast 10
// addLast 20
// addLast 30
// addLast 40
// display
// size
// addLast 50
// addLast 60
// display
// size
// quit

// Output
// 10 20 30 40
// 4
// 10 20 30 40 50 60
// 6

import java.io.*;

public class displayAndSize_2 {
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

        void addLast(int val) {
            Node temp = new Node();
            temp.data = val;

            if(size == 0) {
                head = tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }
            size++;
        }

        void display() {
            // print all elements from head to tail
            // starting from head, move till temp becomes null -> NULL is when loop stops
            for(Node temp = head; temp != null; temp = temp.next) {
                System.out.print(temp.data + " ");
            }
            System.out.println();
        }

        int size() {
            // returns total element in list
            return size;
        }
    }
}
