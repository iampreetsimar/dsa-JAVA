// Complete implementation of a Doubly Linked List. 

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class doublyLL_27 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        DoublyLinkedList list = new DoublyLinkedList(parts);

        String cmd = br.readLine();
        while(!cmd.contains("exit")) {
            if(cmd.contains("get head")) {
                ListNode res = list.getHead();
                if(res != null) System.out.println(res.data);
            } else if(cmd.contains("get tail")) {
                ListNode res = list.getTail();
                if(res != null) System.out.println(res.data);
            } else if(cmd.contains("size")){
                System.out.println(list.getSize());
            } else if(cmd.contains("display")) {
                list.display();
            } else if(cmd.contains("print fwd")) {
                list.displayForward();
            } else if(cmd.contains("print rev")) {
                list.displayBackward();
            } else if(cmd.contains("add first")) {
                list.addFirst(Integer.parseInt(cmd.split(" ")[2]));
            } else if(cmd.contains("add last")) {
                list.addLast(Integer.parseInt(cmd.split(" ")[2]));
            } else if(cmd.contains("add at")) {
                String[] inp = cmd.split(" ");
                list.addAt(Integer.parseInt(inp[2]), Integer.parseInt(inp[3]));
            } else if(cmd.contains("add before")) {
                String[] inp = cmd.split(" ");
                list.addBefore(Integer.parseInt(inp[2]), Integer.parseInt(inp[3]));
            } else if(cmd.contains("add after")) {
                String[] inp = cmd.split(" ");
                list.addAfter(Integer.parseInt(inp[2]), Integer.parseInt(inp[3]));
            } else if(cmd.contains("remove first")) {
                ListNode res = list.removeFirst();
                if(res != null) System.out.println(res.data);
            } else if(cmd.contains("remove last")) {
                ListNode res = list.removeLast();
                if(res != null) System.out.println(res.data);
            } else if(cmd.contains("remove at")) {
                ListNode res = list.removeAt(Integer.parseInt(cmd.split(" ")[2]));
                if(res != null) System.out.println(res.data);
            } else if(cmd.contains("remove before")) {
                ListNode res = list.removeBefore(Integer.parseInt(cmd.split(" ")[2]));
                if(res != null) System.out.println(res.data);
            } else if(cmd.contains("remove after")) {
                ListNode res = list.removeAfter(Integer.parseInt(cmd.split(" ")[2]));
                if(res != null) System.out.println(res.data);
            } else if(cmd.contains("remove node")) {
                ListNode res = list.removeGivenNode(Integer.parseInt(cmd.split(" ")[2]));
                if(res != null) System.out.println(res.data);
            } else if(cmd.contains("get first")) {
                ListNode res = list.getFirst();
                if(res != null) System.out.println(res.data);
            } else if(cmd.contains("get last")) {
                ListNode res = list.getLast();
                if(res != null) System.out.println(res.data);
            } else if(cmd.contains("get at")) {
                ListNode res = list.getAt(Integer.parseInt(cmd.split(" ")[2]));
                if(res != null) System.out.println(res.data);
            }
            cmd = br.readLine();
        }
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;
        ListNode prev = null;

        ListNode(int data) { this.data = data; }
        ListNode(int data, ListNode next, ListNode prev) { this.data = data; this.next = next; this.prev = prev; }
    }

    public static class DoublyLinkedList {
        private ListNode head = null;
        private ListNode tail = null;
        private int size = 0;

        DoublyLinkedList(ListNode head, ListNode tail) { this.head = head; this.tail = tail; }
        DoublyLinkedList(String[] input) { createList(input); }

        public ListNode getHead() { return this.head; }
        public ListNode getTail() { return this.tail; }
        public int getSize() { return this.size; }

        private void createList(String[] input) {
            head = new ListNode(Integer.parseInt(input[0]));
            ListNode temp = head;
            for(int i = 1; i < input.length; i++) {
                ListNode newNode = new ListNode(Integer.parseInt(input[i]));
                temp.next = newNode;
                newNode.prev = temp;
                temp = newNode;
            }
            tail = temp;
            size = input.length;
        }

        private boolean isIdxInvalid(int idx, int start, int end) {
            if(idx < start || idx > end) {
                System.out.println("Invalid arguments");
                return true;
            }
            return false;
        }

        private boolean isListEmpty() {
            if(size == 0) {
                System.out.println("List is empty!");
                return true;
            }
            return false;
        }

        public void display() {
            if(isListEmpty()) return;

            StringBuilder sb = new StringBuilder();
            sb.append("[ ");

            ListNode curr = head;
            while(curr != null) {
                sb.append(curr.data);
                if(curr.next != null) sb.append("<=>");
                curr = curr.next;
            }

            sb.append(" ]");
            System.out.println(sb.toString());
        }

        public void displayForward() {
            display();
        }

        public void displayBackward() {
            if(isListEmpty()) return;

            StringBuilder sb = new StringBuilder();
            sb.append("[ ");

            ListNode curr = tail;
            while(curr != null) {
                sb.append(curr.data);
                if(curr.prev != null) sb.append("<=>");
                curr = curr.prev;
            }

            sb.append(" ]");
            System.out.println(sb.toString());
        }

        public void addFirst(int data) {
            ListNode node = new ListNode(data);

            if(head == null) {
                head = tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
            size++;
        }

        public void addLast(int data) {
            ListNode node = new ListNode(data);

            if(tail == null) {
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        public void addAt(int data, int idx) {
            if(isIdxInvalid(idx, 0, size)) {
                return;
            } else if(idx == 0) {
                addFirst(data);
            } else if(idx == size) {
                addLast(data);
            } else {
                ListNode node = new ListNode(data);
                ListNode curr = getAt(idx);
                node.next = curr;
                node.prev = curr.prev;
                node.prev.next = node;
                curr.prev = node;
                size++;
            }
        }

        public void addBefore(int idx, int data) {
            if(isListEmpty()) return;
            else if(isIdxInvalid(idx, 0, size - 1)) return;
            else {
                ListNode node = new ListNode(data);
                ListNode refNode = getAt(idx);
                addBefore(refNode, node);
            }
        }

        private void addBefore(ListNode refNode, ListNode node) {
            if(refNode == head) {
                node.next = head;
                head.prev = node;
                head = node;
            } else {
                ListNode prev = refNode.prev;
                node.next = refNode;
                refNode.prev = node;
                prev.next = node;
                node.prev = prev;
            }
            size++;
        } 

        public void addAfter(int idx, int data) {
            if(isListEmpty()) return;
            else if(isIdxInvalid(idx, 0, size - 1)) return;
            else {
                ListNode refNode = getAt(idx);
                ListNode node = new ListNode(data);
                addAfter(refNode, node);
            }
        }

        private void addAfter(ListNode refNode, ListNode node) {
            if(refNode == tail) {
                tail.next = node;
                node.prev = tail;
                tail = node;
            } else {
                node.next = refNode.next;
                refNode.next.prev = node;
                refNode.next = node;
                node.prev = refNode;
            }
            size++;
        }

        public ListNode removeFirst() {
            ListNode temp;
            if(isListEmpty()) return null; 
            else if(head.next == null) {
                temp = head;
                head = tail = null;
            } else {
                temp = head;
                ListNode nbr = head.next;
                head.next = null;
                nbr.prev = null;
                head = nbr;
            }
            size--;
            return temp;
        }

        public ListNode removeLast() {
            ListNode temp;
            if(isListEmpty()) return null; 
            else if(head.next == null) {
                temp = head;
                head = tail = null;
            } else {
                temp = tail;
                ListNode prevNbr = tail.prev;
                tail.prev = null;
                prevNbr.next = null;
                tail = prevNbr;
            }
            size--;
            return temp;
        }

        public ListNode removeAt(int idx) {
            if(isListEmpty()) return null; 
            else if(isIdxInvalid(idx, 0, size - 1)) return null;
            else if(idx == 0) return removeFirst();
            else if(idx == size - 1) return removeLast();
            else {
                ListNode curr = getAt(idx);
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                curr.next = curr.prev = null;
                size--;
                return curr;
            }
        }

        public ListNode removeBefore(int idx) {
            if(isListEmpty()) return null;
            else if(isIdxInvalid(idx, 0, size - 1)) return null;
            else {
                ListNode refNode = getAt(idx);
                return removeBefore(refNode);
            }
        }

        private ListNode removeBefore(ListNode refNode) {
            ListNode toBeDeleted;
            if(refNode == head) {
                System.out.println("Location Invalid");
                return null;
            } else if(refNode.prev == head) {
                toBeDeleted = head;
                refNode.prev = null;
                toBeDeleted.next = null;
                head = refNode;
            } else {
                toBeDeleted = refNode.prev;
                refNode.prev = toBeDeleted.prev;
                toBeDeleted.prev.next = refNode;
                toBeDeleted.next = null;
                toBeDeleted.prev = null;
            }
            size--;
            return toBeDeleted;
        } 

        public ListNode removeAfter(int idx) {
            if(isListEmpty()) return null;
            else if(isIdxInvalid(idx, 0, size - 1)) return null;
            else {
                ListNode refNode = getAt(idx);
                return removeAfter(refNode);
            }
        }

        private ListNode removeAfter(ListNode refNode) {
            ListNode toBeDeleted;
            if(refNode == tail) {
                System.out.println("Location Invalid");
                return null;
            } else if(refNode.next == tail) {
                toBeDeleted = tail;
                refNode.next = null;
                toBeDeleted.prev = null;
                tail = refNode;
            } else {
                toBeDeleted = refNode.next;
                refNode.next = toBeDeleted.next;
                toBeDeleted.next.prev = refNode;
                toBeDeleted.next = null;
                toBeDeleted.prev = null;
            }
            size--;
            return toBeDeleted;
        }

        public ListNode removeGivenNode(int idx) {
            if(isListEmpty()) return null;
            else if(isIdxInvalid(idx, 0, size - 1)) return null;
            else {
                ListNode refNode = getAt(idx);
                return removeGivenNode(refNode);
            }
        }

        private ListNode removeGivenNode(ListNode refNode) {
            if(refNode == head && refNode == tail) {
                head = tail = null;
            } else if(refNode == head) {
                ListNode nbr = refNode.next;
                refNode.next = null;
                nbr.prev = null;
                head = nbr;
            } else if(refNode == tail) {
                ListNode prev = refNode.prev;
                prev.next = null;
                refNode.prev = null;
                tail = prev;
            } else {
                refNode.prev.next = refNode.next;
                refNode.next.prev = refNode.prev;
                refNode.next = null;
                refNode.prev = null;
            }
            size--;
            return refNode;
        }

        public ListNode getFirst() {
            if(isListEmpty()) return null;
            return head;
        }

        public ListNode getLast() {
            if(isListEmpty()) return null;
            return tail;
        }

        public ListNode getAt(int idx) {
            if(isListEmpty()) return null;
            else if(isIdxInvalid(idx, 0, size - 1)) return null;
            else if(idx == 0) return getFirst(); 
            else if(idx == size - 1) return getLast();
            else {
                ListNode curr = head;
                while(idx-- > 0) { curr = curr.next; }
                return curr;
            }
        }
    }
}
