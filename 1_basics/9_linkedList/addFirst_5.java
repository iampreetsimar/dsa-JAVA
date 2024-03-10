// 1. You are required to complete the body of addFirst function. 
// 1.1 addFirst - should add the element to the beginning of the linked list and appropriately update head, 
// tail and size.

// Input
// addFirst 10
// addFirst 20
// addFirst 30
// display
// size
// quit

// Output
// 30 20 10
// 3

import java.io.*;

public class addFirst_5 {
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
            } else if(str.startsWith("getFirst")) {
                int val = list.getFirst();
                if(val != -1) {
                    System.out.println(val);
                }
            } else if(str.startsWith("getLast")) {
                int val = list.getLast();
                if(val != -1) {
                    System.out.println(val);
                }
            } else if(str.startsWith("getAt")) {
                int idx = Integer.parseInt(str.split(" ")[1]);
                int val = list.getAt(idx);
                if(val != -1) {
                    System.out.println(val);
                }
            } else if(str.startsWith("addFirst")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addFirst(val);
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

        void removeFirst() {
            if(size == 0) {
                System.out.println("List is empty");
            } else if(size == 1) {
                head = tail = null;
                size--;
            } else {
                head = head.next;
                size--;
            }
        }

        int getFirst() {
            if(size == 0) {
                System.out.println("List is empty");
                return -1;
            }

            return head.data;
        }

        int getLast() {
            if(size == 0) {
                System.out.println("List is empty");
                return -1;
            }

            return tail.data;
        }

        int getAt(int idx) {
            if(size == 0) {
                System.out.println("List is empty");
                return -1;
            }

            if(idx < 0 || idx >= size) {
                System.out.println("Invalid Arguments");
                return -1;
            }

            Node temp = head;
            for(int i = 0; i < idx; i++) {
                temp = temp.next;
            }

            return temp.data;
        }

        void addFirst(int val) {
            Node newNode = new Node();
            newNode.data = val;

            if(size == 0) {
                // list empty -> head and tail point to null
                // point head an tail to newNode
                head = tail = newNode;
            } else {
                // point next of newNode to head
                // update head by assigning to newNode
                newNode.next = head;
                head = newNode;
            }

            // increment size of list
            size++;
        }
    }
}
