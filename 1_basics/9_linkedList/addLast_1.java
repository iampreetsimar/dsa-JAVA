// 1. You are required to complete the body of addLast function. This function is supposed to add an element
// to the end of Linked list. You are required to update head, tail and size as required.

// Input
// addLast 10
// addLast 20
// addLast 30
// addLast 40
// quit

// Output
// 10
// 20
// 30
// 40

import java.io.*;

public class addLast_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList();

        String str = br.readLine();
        while(str.equals("quit") == false) {
            if(str.startsWith("addLast")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addLast(val);
            }

            str = br.readLine();
        }

        testList(list);
    }

    public static void testList(LinkedList list) {
        for(Node temp = list.head; temp != null; temp = temp.next) {
            System.out.println(temp.data);
        }

        System.out.println(list.size);

        if(list.size > 0) {
            System.out.println(list.tail.data);
        }
    }

    public static class Node {
        int data;
        Node next;
    }

    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        void addLast(int val) {
            // create new node with val as data
            Node temp = new Node();
            temp.data = val;

            if(size == 0) {
                // list empty -> head and tail point to null
                // point head and tail to temp
                head = tail = temp;
            } else {
                // list not empty
                // point next of tail to temp
                // assign tail to temp -> since temp will be last node of list now
                tail.next = temp;
                tail = temp;
            }

            // increment list size by 1
            size++;
        }
    }
}
