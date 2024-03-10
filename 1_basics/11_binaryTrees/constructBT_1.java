// Construct a binary tree using given input in input array

import java.util.Stack;

public class constructBT_1 {
    public static void main(String[] args) {
        Integer input[] = { 10,
                            20, 40, null, null, 50, 80, null, null, null,
                            30, 60, null, 90, null, null, 70, null, null };

        Node root = constructBinaryTree(input);
        System.out.println(root.data);
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node constructBinaryTree(Integer[] input) {
        Node root = new Node(input[0]); 
        Pair rootPair = new Pair(root, 1); 
        Stack<Pair> s = new Stack<>();
        s.push(rootPair);
        int idx = 0;    // to traverse input array

        while(s.size() > 0) {
            Pair top = s.peek();

            if(top.state == 1) {        // need to add left child of top node
                idx++;      // since idx was at previous idx
                Integer val = input[idx];
                if(val != null) {   
                    top.node.left = new Node(val);      // add new node top top's left
                    Pair leftPair = new Pair(top.node.left, 1); 
                    s.push(leftPair);
                }

                // if val is null, no need to do anything
                // as default value of a node's left is null

                top.state++;    // increment top state as left has been added
            } else if (top.state == 2) {    // need to add right child of top node
                idx++;  
                Integer val = input[idx];
                if(val != null) {
                    top.node.right = new Node(val);     // add new node to top's right
                    Pair rightPair = new Pair(top.node.right, 1);
                    s.push(rightPair);
                }

                // if val is null, no need to do anything
                // as default value of a node's right is null

                top.state++;    // increment top state as right has been added
                
            } else {       
                s.pop();    // top.state is 3 - both childs are added - pop top node
            }
        }

        return root;    
    }
}
