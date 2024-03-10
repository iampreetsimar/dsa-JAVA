// 1. You are required to complete the body of removeAt function. 
// 1.1 removeLast - should remove the element available at the idx passed as parameter. 
// If list is empty, print "List is empty". If size is 1, should set both head and tail to null.
// If idx is invalid, print "Invalid Arguments".

import java.io.*;

public class removeAtIndex_8 {
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
            } else if(str.startsWith("removeLast")) {
                list.removeLast();
            } else if(str.startsWith("removeAt")) {
                int idx = Integer.parseInt(str.split(" ")[1]);
                list.removeAt(idx);
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

        void addAt(int idx, int val) {
            if(idx < 0 || idx > size) {
                System.out.println("Invalid Arguments");
            } else if(idx == 0) {
                addFirst(val);
            } else if(idx == size) {
                addLast(val);
            } else {
                Node node = new Node();
                node.data = val;

                Node temp = head;
                for(int i = 0; i < idx - 1; i++) {
                    temp = temp.next;
                }

                node.next = temp.next;
                temp.next = node;
                size++;
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
                System.out.println("Invalid Arguments");
                return -1;
            }

            Node temp = head;
            for(int i = 0; i < idx; i++) {
                temp = temp.next;
            }
            return temp.data;
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

        void removeLast() {
            if(size == 0) {
                System.out.println("List empty");
            } else if(size == 1) {
                head = tail = null;
                size--;
            } else {
                Node temp = head;
                for(int i = 0; i < size - 2; i++) {
                    temp = temp.next;
                }

                temp.next = null;
                tail = temp;
                size--;
            }
        }

        void removeAt(int idx) {
            if(idx < 0 || idx >= size) {
                // invalid idx
                System.out.println("Invalid Arguments");
            } else if(idx == 0) {
                // remove from beginning of list
                removeFirst();
            } else if(idx == size - 1) {
                // remove from end of list
                removeLast();
            } else {
                // idx : 0 -> < size - 1

                Node temp = head;
                // reach node at idx - 1
                for(int i = 0; i < idx - 1; i++) {
                    temp = temp.next;
                }

                // point next of node at idx - 1 to next of node at idx -> node(idx - 1) point to node(idx + 1)
                // connection from node(idx) to node(idx + 1) will be removed by G.C.
                temp.next = temp.next.next;

                // ALTERNATIVE APPROACH
                // while(idx > 1) {
                //     // runs idx - 1 times
                //     temp = temp.next;
                // }
                // // temp at idx - 1
                // Node toBeDeleted =  temp.next;      // toBeDeleted - node at idx
                // temp.next = toBeDeleted.next;       // node(idx - 1) points to node(idx + 1)
                // toBeDeleted.next = null;        // connection from node(idx) to node(idx + 1) is removed

                // decrement size
                size--;
            }
        }
    }
}
