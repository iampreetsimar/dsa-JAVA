// 1. You are required to complete the body of mergeSort function. 
// The function is static and passed head and tail of an unsorted list.
// The functions is expected to return a new sorted list.
// Original list must stay same.

// Sample Input
// 10 2 19 22 3 7 -> list

// Sample Output
// 2 3 7 10 19 22 -> new list
// 10 2 19 22 3 7 -> OG list

import java.io.*;

public class mergeSortLL_26 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList();

        String str = br.readLine();
        while(str.equals("quit") == false) {
            if(str.startsWith("addLast")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addLast(val);
            } else if(str.startsWith("addFirst")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addFirst(val);
            } else if(str.startsWith("addAt")) {
                int idx = Integer.parseInt(str.split(" ")[1]);
                int val = Integer.parseInt(str.split(" ")[2]);
                list.addAt(idx, val);
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
            } else if(str.startsWith("removeFirst")) {
                list.removeFirst();
            } else if(str.startsWith("removeLast")) {
                list.removeLast();
            } else if(str.startsWith("removeAt")) {
                int idx = Integer.parseInt(str.split(" ")[1]);
                list.removeAt(idx);
            } else if(str.startsWith("removeAll")) {
                list.removeAll();
            } else if(str.startsWith("dataIterativeReverse")) {
                list.reverseDI();
            } else if(str.startsWith("pointerIterativeReverse")) {
                list.reversePI();
            } else if(str.startsWith("kthFromLast")) {
                int k = Integer.parseInt(str.split(" ")[1]);
                int val = list.kthFromLast(k);
                if(val != -1) {
                    System.out.println(val);
                }
            } else if(str.startsWith("mid")) {
                int val = list.mid();
                if(val != -1) {
                    System.out.println(val);
                }
            } else if(str.startsWith("removeDuplicates")) {
                list.removeDuplicates();
            } else if(str.startsWith("oddEven")) {
                list.oddEven();
            } else if(str.startsWith("kReverse")) {
                int k = Integer.parseInt(str.split(" ")[1]);
                list.kReverse(k);
            } else if(str.startsWith("reverseDisplay")) {
                list.displayReverse();
            } else if(str.startsWith("pointerRecursiveReverse")) {
                list.reversePR();
            } else if(str.startsWith("dataRecursiveReverse")) {
                list.reverseDR();
            } else if(str.startsWith("isPalindrome")) {
                System.out.println(list.isPalindrome());
            } else if(str.startsWith("fold")) {
                list.fold();
            } else if(str.startsWith("add")) {
                LinkedList l1 = new LinkedList();
                LinkedList l2 = new LinkedList();

                // l1 : 9 8 7
                l1.addLast(9);
                l1.addLast(8);
                l1.addLast(7);

                // l2 : 6 5
                l2.addLast(6);
                l2.addLast(5);

                LinkedList result = list.addLinkedLists(l1, l2);
                result.display();
            } else if(str.startsWith("display")) {
                list.display();
            } else if(str.startsWith("size")) {
                System.out.println(list.size());
            } else if(str.startsWith("mergeTwoSortedLL")) {
                LinkedList l1 = new LinkedList();
                LinkedList l2 = new LinkedList();

                // l1 : 10 20 30 40 50
                l1.addLast(10);
                l1.addLast(20);
                l1.addLast(30);
                l1.addLast(40);
                l1.addLast(50);

                // l2 : 7 9 12 15 37 43 44 48 52 56
                l2.addLast(7);
                l2.addLast(9);
                l2.addLast(12);
                l2.addLast(15);
                l2.addLast(37);
                l2.addLast(43);
                l2.addLast(44);
                l2.addLast(48);
                l2.addLast(52);
                l2.addLast(56);

                LinkedList result = list.mergeTwoSortedLists(l1, l2);
                result.display();
            } else if(str.startsWith("mergeSort")) {
                LinkedList l1 = new LinkedList();

                // l1 : 10 2 19 22 3 7
                l1.addLast(10);
                l1.addLast(2);
                l1.addLast(19);
                l1.addLast(22);
                l1.addLast(3);
                l1.addLast(7);

                LinkedList result = list.mergeSort(l1.head, l1.tail);
                result.display();
            }

            str = br.readLine();
        }
    }

    public static class Node {
        int data;
        Node next;
    }

    public static class LinkedList {
        Node head,tail;
        int size;

        Node left;

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
                System.out.println("Invalid Idx");
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
                System.out.println("List Empty");
                return -1;
            }
            return head.data;
        }

        int getLast() {
            if(size == 0) {
                System.out.println("List Empty");
                return -1;
            }
            return tail.data;
        }

        int getAt(int idx) {
            if(size == 0) {
                System.out.println("List Empty");
                return -1;
            }

            if(idx < 0 || idx >= size) {
                System.out.println("Invalid Idx");
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
                System.out.println("List Empty");
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
                System.out.println("List Empty");
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
                System.out.println("Invalid Idx");
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

                temp.next = temp.next.next;
                size--;
            }
        }

        void removeAll() {
            head = tail = null;
            size = 0;
        }

        private Node getNodeAt(int idx) {
            Node temp = head;
            while(idx > 0) {
                temp = temp.next;
                idx--;
            }
            return temp;
        }

        void reverseDI() {
            int left = 0;
            int right = size - 1;
            while(left < right) {
                Node ln = getNodeAt(left);
                Node rn = getNodeAt(right);

                int temp = ln.data;
                ln.data = rn.data;
                rn.data = temp;

                left++;
                right--;
            }
        }

        void reversePI() {
            if(size <= 1)
                return;

            Node prev = null;
            Node curr = head;

            while(curr != null) {
                Node next = curr.next;
                curr.next = prev;

                prev = curr;
                curr = next;
            }

            Node temp = head;
            head = tail;
            tail = temp;
        }

        int kthFromLast(int k) {
            if(k < 0 || k >= size) {
                System.out.println("Invalid k");
                return -1;
            }

            Node slow = head;
            Node fast = head;

            while(k > 0) {
                fast = fast.next;
                k--;
            }

            while(fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow.data;
        }

        int mid() {
            if(size == 0) {
                System.out.println("List empty");
                return -1;
            }

            Node slow = head;
            Node fast = head;

            while(fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow.data;
        }

        void removeDuplicates() {
            LinkedList res = new LinkedList();

            while(this.size > 0) {
                int val = getFirst();
                removeFirst();

                if(res.size == 0 || res.tail.data != val) {
                    res.addLast(val);
                }
            }

            this.head = res.head;
            this.tail = res.tail;
            this.size = res.size;
        }

        void oddEven() {
            LinkedList odd = new LinkedList();
            LinkedList even = new LinkedList();

            while(this.size > 0) {
                int val = getFirst();
                removeFirst();

                if(val % 2 == 0) {
                    even.addLast(val);
                } else {
                    odd.addLast(val);
                }
            }

            if(odd.size > 0 && even.size > 0) {
                odd.tail.next = even.head;
                this.head = odd.head;
                this.tail = even.tail;
                this.size = odd.size + even.size;
            } else if(odd.size > 0) {
                this.head = odd.head;
                this.tail = odd.tail;
                this.size = odd.size;
            } else {
                this.head = even.head;
                this.tail = even.tail;
                this.size = even.size;
            }
        }

        void kReverse(int k) {
            LinkedList prev = null;

            while(this.size > 0) {
                LinkedList curr = new LinkedList();

                if(this.size >= k) {
                    for(int i = 0; i < k; i++) {
                        int val = this.getFirst();
                        this.removeFirst();
                        curr.addFirst(val);
                    }
                } else {
                    while(this.size > 0) {
                        int val = this.getFirst();
                        this.removeFirst();
                        curr.addLast(val);
                    }
                }

                if(prev == null) {
                    prev = curr;
                } else {
                    prev.tail.next = curr.head;
                    prev.tail = curr.tail;
                    prev.size += curr.size;
                }
            }

            this.head = prev.head;
            this.tail = prev.tail;
            this.size = prev.size;
        }

        private void displayReverseHelper(Node node) {
            if(node == null)
                return;

            displayReverseHelper(node.next);
            System.out.print(node.data + " ");
        }

        void displayReverse() {
            displayReverseHelper(head);
            System.out.println();
        }

        private void reversePRHelper(Node node) {
            if(node.next == null)
                return;

            reversePRHelper(node.next);
            node.next.next = node;
        }

        void reversePR() {
            reversePRHelper(head);
            head.next = null;

            Node temp = head;
            head = tail;
            tail = temp;
        }

        private void reverseDRHelper(Node node, int floor) {
            if(node == null)
                return;

            reverseDRHelper(node.next, floor + 1);
            if(floor >= size / 2) {
                int temp = left.data;
                left.data = node.data;
                node.data = temp;

                left = left.next;
            }
        }

        void reverseDR() {
            left = head;
            reverseDRHelper(head, 0);
        }

        private boolean isPalindromeHelper(Node node) {
            if(node == null)
                return true;

            boolean res = isPalindromeHelper(node.next);
            if(res == false)
                return false;

            if(node.data != left.data)
                return false;

            left = left.next;
            return true;
        }

        boolean isPalindrome() {
            left = head;
            return isPalindromeHelper(head);
        }

        private void foldHelper(Node node, int idx) {
            if(node == null)
                return;

            foldHelper(node.next, idx + 1);

            if(idx > size / 2) {
                Node nbr = left.next;
                left.next = node;
                node.next = nbr;
                left = nbr;
            } else if(idx == size / 2) {
                node.next = null;
                tail = node;
            }
        }

        void fold() {
            left = head;
            foldHelper(head, 0);
        }

        private int addLLHelper(Node node1, int pv1, Node node2, int pv2, LinkedList res) {
            if(node1 == null && node2 == null)
                return 0;

            int data = 0;
            if(pv1 > pv2) {
                int carry = addLLHelper(node1.next, pv1 - 1, node2, pv2, res);
                data = node1.data + carry;
            } else if(pv1 < pv2) {
                int carry = addLLHelper(node1, pv1, node2.next, pv2 - 1, res);
                data = node2.data + carry;
            } else {
                int carry = addLLHelper(node1.next, pv1 - 1, node2.next, pv2 - 1, res);
                data = node1.data + node2.data + carry;
            }

            res.addFirst(data % 10);
            return data / 10;
        }

        LinkedList addLinkedLists(LinkedList l1, LinkedList l2) {
            LinkedList res = new LinkedList();
            int carry = addLLHelper(l1.head, l1.size, l2.head, l2.size, res);
            if(carry > 0) {
                res.addFirst(carry);
            }
            return res;
        }

        // no testing
        int findIntersection(LinkedList one, LinkedList two) {
            Node p1 = one.head;
            Node p2 = two.head;

            if(one.size > two.size) {
                int diff = one.size - two.size;
                while(diff > 0) {
                    p1 = p1.next;
                    diff--;
                }
            } else {
                int diff = two.size - one.size;
                while(diff > 0) {
                    p2 = p2.next;
                    diff--;
                }
            }

            while(p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1.data;
        }

        LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
            LinkedList res = new LinkedList();

            Node one = l1.head;
            Node two = l2.head;

            while(one != null && two != null) {
                if(one.data < two.data) {
                    res.addLast(one.data);
                    one = one.next;
                } else {
                    res.addLast(two.data);
                    two = two.next;
                }
            }

            while(one != null) {
                res.addLast(one.data);
                one = one.next;
            }

            while(two != null) {
                res.addLast(two.data);
                two = two.next;
            }
            return res;
        }

        private Node middleNode(Node head, Node tail) {
            Node slow = head;
            Node fast = head;

            // we find mid node b/w head and tail(NOT ACTUAL head and tail) -> shows current range of items
            while(fast != tail && fast.next != tail) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
        
        LinkedList mergeSort(Node head, Node tail) {
            if(head == tail) {      // BASE CASE
                // only 1 item left
                LinkedList bl = new LinkedList();
                bl.addLast(head.data);
                return bl;
            }

            // mid node b/w head and tail
            Node midNode = middleNode(head,tail);

            // return sorted list b/w first half
            LinkedList firstHalfSortedLL = mergeSort(head, midNode);

            // return sorted list b/w second half
            LinkedList secondHalfSortedLL = mergeSort(midNode.next, tail);

            // return merged sorted list
            return mergeTwoSortedLists(firstHalfSortedLL, secondHalfSortedLL);
        }
    }  
}
