// 1. You are required to complete the body of isPalindrome function. 
// The function is expected to check if the list is a palindrome or not using RECURSION

// Constraints
// Time Complexity -> O(N)
// Space Complexity -> O(N)

// Sample Input
// 1 2 3 2 1 -> list

// Sample Output
// true

import java.io.*;

public class isLLPalindrome_Recursive_22 {
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
            } else if(str.startsWith("isPalindrome")) {
                System.out.println(list.isPalindrome());
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

        private boolean isPalindromeHelper(Node node) {
            if(node == null)        // BASE CASE : node next to tail
                return true;        // Potential Palindrome LL 

            boolean res = isPalindromeHelper(node.next);
            if(!res)    // res from above is false -> data mismatch -> not palindrome
                return false;

            // NOTE : we can add a check floor >= size/2 to avoid unnecessary comparisons    
            // data from above matched -> check data of current node with left
            if(left.data != node.data)
                return false;       // data mismatch -> not palindrome

            // NOTE : we can add a check floor >= size/2 to avoid unnecessary comparisons   
            // data matched for current node -> move left forward
            left = left.next;

            // data from above till current node matched
            return true;
        }

        Node left;
        boolean  isPalindrome() {
            // assign left to head
            left = head;
            return isPalindromeHelper(head);
        }
    }
}
