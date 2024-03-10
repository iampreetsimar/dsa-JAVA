// Construct a generic tree using given input in input array

import java.util.ArrayList;
import java.util.Stack;

public class constructTree_1 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };

        Node root = constructTree(input);
        System.out.println(root.data);
    }

    public static class Node {
        int data;
        ArrayList<Node> children;

        Node() {
            children = new ArrayList<>();
        }

        Node(int val) {
            this();     // -> invokes default constructor (process is CONSTRUCTOR CHAINING)
            this.data = val;
        }
    }

    public static Node constructTree(Integer[] input) {
        Node root = null;
        Stack<Node> s = new Stack<>();

        // traverse input array
        for(int i = 0; i < input.length; i++) {
            if(input[i] == null) {
                // pop node from stack
                s.pop();
            } else {
                // create new node with data val
                Node node = new Node(input[i]);

                if(s.size() > 0) {
                    // link new node as child of its parent node(on top of stack)
                    s.peek().children.add(node);
                } else {
                    // stack empty -> assign root to new node
                    root = node;
                }

                // add new node to stack
                s.push(node);
            }
        }
        
        return root;
    }
}
