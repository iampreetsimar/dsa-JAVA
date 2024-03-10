// 1. You are required to complete the body of reversePI function. 
// 1.1 reversePI - should be an iterative function and should reverse the content of linked list by
// changing next property of nodes.

import java.io.*;

public class reverseLL_PointerIterative_10 {
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
            } else if(str.startsWith("reverseDI")) {
                list.reverseDI();
            } else if(str.startsWith("reversePI")) {
                list.reversePI();
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
                System.out.println("Invalid arguments");
            } else if(idx == 0) {
                addFirst(val);
            } else if(idx == size) {
                addLast(val);
            } else {
                Node node = new Node();
                node.data = val;

                Node temp = head;
                while(idx > 1) {
                    temp = temp.next;
                    idx--;
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
            while(idx > 0) {
                temp = temp.next;
                idx--;
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
                while(temp.next.next != null) {
                    temp = temp.next;
                }

                temp.next = null;
                tail = temp;
                size--;
            }
        }

        void removeAt(int idx) {
            if(idx < 0 || idx >= size) {
                System.out.println("Invalid arguments");
            } else if(idx == 0) {
                removeFirst();
            } else if(idx == size - 1) {
                removeLast();
            } else {
                Node temp = head;
                while(idx > 1) {
                    temp = temp.next;
                    idx--;
                }

                Node toDelete = temp.next;
                temp.next = toDelete.next;
                toDelete.next = null;
                size--;
            }
        }

        private Node getNodeAt(int idx) {
            Node temp = head;
            for(int i = 0; i < idx; i++) {
                temp = temp.next;
            }
            return temp;
        }

        void reverseDI() {
            int l = 0;
            int r = size - 1;

            while(l< r) {
                Node li = getNodeAt(l);
                Node ri = getNodeAt(r);

                int temp = li.data;
                li.data = ri.data;
                ri.data = temp;
            }
        }

        // reverses LL by actually reversing nodes -> changing next member of nodes
        void reversePI() {
            if(size <= 1) {
                // either list empty or only 1 element -> no need to reverse
                return;
            }

            Node prev = null;
            Node curr = head;

            while(curr != null) {
                // store next node of curr before curr cuts connection to it
                Node next = curr.next;

                // point next of curr to prev -> reverses connection to prev node
                curr.next = prev;

                // move prev and curr forward
                prev = curr;
                curr = next;
            }

            // swap head and tail
            Node temp = head;
            head = tail;
            tail = temp;
        }
    }
}
