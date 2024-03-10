// Display constructed generic tree using given input in input array

// Output Format
// print (node -> node's children)

// Output
// 10 -> 20 30 40 .
// 20 -> 50 60 .
// 50 -> .
// 60 -> .
// 30 -> 70 80 90 .
// 70 -> .
// 80 -> 110 120 .
// 110 -> .
// 120 -> .
// 90 -> .
// 40 -> 100 .
// 100 -> .

import java.util.ArrayList;
import java.util.Stack;

public class displayTree_2 {

    public static class Node {
        int data;
        ArrayList<Node> children;

        Node() {
            children = new ArrayList<>();
        }

        Node(int val) {
            this();
            this.data = val;
        }
    }

    public static Node constructTree(Integer[] input) {
        Node root = null;
        Stack<Node> s = new Stack<>();

        for(int i = 0; i < input.length; i++) {
            if(input[i] == null) {
                s.pop();
            } else {
                Node node = new Node(input[i]);
                if(s.size() > 0) {
                    s.peek().children.add(node);
                } else {
                    root = node;
                }

                s.push(node);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };

        Node root = constructTree(input);
        displayTree(root);
    }

    public static void displayTree(Node node) {
        System.out.print(node.data + " -> ");    // print node itself

        for(Node child : node.children) {   // print all children of node
            System.out.print(child.data + " ");
        }
        System.out.println(".");

        // recursive call to all children of node
        // if node has 0 child - no loop will run
        for(Node child : node.children) {
            displayTree(child);
        }
    }
}
