// 1. You are required to complete the body of addAt function. 
// 1.1 addAt - should add the element at the index mentioned in parameter and appropriately update head, 
// tail and size. If idx is invalid, print "Invalid arguments"

// Input
// addAt 0 10
// addAt 1 20
// addAt 0 40
// display
// size
// quit

// Output
// 40 10 20
// 3

import java.io.*;

public class addAtIndex_6 {
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
            } else if(str.startsWith("addAt")) {
                int idx = Integer.parseInt(str.split(" ")[1]);
                int val = Integer.parseInt(str.split(" ")[2]);
                list.addAt(idx, val);
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
                System.out.println("List empty");
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
                System.out.println("List empty");
                return -1;
            }

            return head.data;
        }

        int getLast() {
            if(size == 0) {
                System.out.println("List empty");
                return -1;
            }

            return tail.data;
        }

        int getAt(int idx) {
            if(size == 0) {
                System.out.println("List empty");
                return -1;
            }

            if(idx < 0 || idx >= size) {
                System.out.println("Invalid arguments");
                return -1;
            }

            Node temp = head;
            for(int i = 0; i < idx; i++) {
                temp = temp.next;
            }

            return temp.data;
        }

        void addAt(int idx, int val) {
            if(idx < 0 || idx > size) {
                // invalid idx
                System.out.println("Invalid Arguments");
            } else if(idx == 0) {
                // add at beginning of list
                addFirst(val);
            } else if(idx == size) {
                // add at last of list
                addLast(val);
            } else {
                // add from idx : 1 -> < size
                // create a new node
                Node node = new Node();
                node.data = val;

                // starting from head, reach node at idx - 1
                Node temp = head;
                for(int i = 0; i < idx - 1; i++) {
                    // loop runs idx - 1 times
                    temp = temp.next;
                }

                // point next of new node to node currently at idx
                node.next = temp.next;

                // point node at idx - 1 to new node
                temp.next = node;

                // increment size
                size++;
            }
        }
    }
}
