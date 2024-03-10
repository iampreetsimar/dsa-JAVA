// 1. You are required to complete the body of removeFirst function. 
// 1.1 removeFirst - should remove the first element of linked list.
// If there is only one element in list, point head and tail both to null.
// If there is no element in list, print "List is empty"

// Input
// addLast 10
// addLast 20
// addLast 30
// removeFirst
// display
// size
// removeFirst
// display
// size
// removeFirst
// display
// size
// removeFirst
// size
// quit

// Output
// 10 20
// 2
// 10
// 1
// 0
// List is empty
// 0

import java.io.*;

public class removeFirst_3 {
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
            } else if(str.startsWith("removeFirst")) {
                list.removeFirst();
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

            if(size() == 0) {
                head = tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }
            size++;
        }

        void display() {
            Node temp = head;
            while(temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }

        int size() {
            return size;
        }

        void removeFirst() {
            // remove first element of list
            if(size() == 0) {
                // empty list
                System.out.println("List is empty");
            } else if(size() == 1) {
                // one element is list
                // on removing -> head and tail point to null
                // decrement size
                head = tail = null;
                size--;
            } else {
                // on removing -> head will point to 2nd element of list which is next of original head
                // decrement size
                // original head node still pointing to new head node 
                // since nothing points to original head node now -> taken care by GARBAGE COLLECTOR
                head = head.next;
                size--;


                // ALTERNATIVE APPROACH - Without Garbage Collection
                // 1. point temp to 2nd element which is next of original head
                // 2. point next of original head to null -> unreachable connection pointed to NULL
                // 3. point head to temp -> temp becomes new head
                // 4. decrement size
                // Node temp = head.next;
                // head.next = null;
                // head = temp;
                // size--;
            }
        }
    }
}
