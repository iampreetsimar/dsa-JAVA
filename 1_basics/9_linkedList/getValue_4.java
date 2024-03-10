// 1. You are required to complete the body of getFirst, getLast and getAt functions. 
// 1.1 getFirst - should return the data of first element of linked list. If empty, return -1 and print "List is empty".
// 1.2 getLast - should return the data of last element of linked list. If empty, return -1 and print "List is empty".
// 1.3 getAt - should return the data of element available at passed index. 
// If empty, return -1 and print "List is empty". If invalid idx passed, return -1 and print "Invalid arguments".

// Input
// addLast 10
// addLast 20
// addLast 30
// getFirst
// getLast
// getAt 1
// getAt 3
// quit

// Output
// 10
// 30
// 20
// Invalid Arguments


import java.io.*;

public class getValue_4 {
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

        void display()  {
            for(Node temp = head; temp != null; temp = temp.next) {
                System.out.print(temp.data + " ");
            }
            System.out.println();
        }

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
                // list is empty
                System.out.println("List is empty");
                return -1;
            }

            // first item is head -> return data of head
            return head.data;
        }

        int getLast() {
            if(size == 0) {
                // list is empty
                System.out.println("List is empty");
                return -1;
            }

            // last item is tail -> return data of tail
            return tail.data;
        }

        // 0 based indexing of node
        int getAt(int idx) {
            if(size == 0) {
                // list is empty
                System.out.println("List is empty");
                return -1;
            }

            if(idx < 0 || idx >= size) {
                // invalid idx 
                System.out.println("Invalid Arguments");
                return -1;
            }

            // point temp node to head -> this is 0th node
            // move temp to next node idx times to reach idxth node
            Node temp = head;
            for(int i = 0; i < idx; i++) {
                temp = temp.next;
            }

            // reachd idxth node -> return data of temp
            return temp.data;
        }
    }
}
