// 1. Given GenericTree class, complete diameter function. 
// The function is expected to find and print diameter of the tree.
// DIAMETER - maximum number of edges b/w any two nodes of the tree.
// Use "Travel and Change" strategy.

// Input 
// 10, 20, -50, null, -60, null, null, 30, -70, null, 80, -110, null, 120, null, null, 90, null, null,
// 40, -100, null, null, null -> tree input

// Output
//  -> diameter

import java.util.ArrayList;
import java.util.Stack;

public class diameterOfGT_26 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, -50, null, -60, null, null,
                            30, -70, null, 80, -110, null, 120, null, null, 90, null, null,
                            40, -100, null, null,
                            null }; 

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        tree.returnHeightAndFindDiameter(tree.root);
        System.out.println(tree.diameter); 
    }

    public static class Node {
        int data;
        ArrayList<Node> children;

        Node() {
            children = new ArrayList<>();
        }

        Node(int val) {
            this();
            data = val;
        }
    }

    public static class GenericTree {
        Node root;
        int diameter;

        GenericTree() {
            root = null;
            diameter = 0;
        }

        GenericTree(Integer[] input) {
            this();
            constructTree(input);
        }

        void constructTree(Integer[] input) {
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
        }

        void display(Node node) {
            System.out.print(node.data + " -> ");
            for(Node child : node.children) {
                System.out.print(child.data + " ");
            }
            System.out.println(".");

            for(Node child : node.children) {
                display(child);
            }
        }

        // Use Travel and Change for diameter
        // Initial value of diameter : 0
        // Initial value of node : tree.root
        // For each node, find its deepest and second deepest height -> find potential diameter via node
        // return height of node by incrementing 1 to deepest height
        int returnHeightAndFindDiameter(Node node) {
            int deepestChildHeight = -1;
            int secondDeepestChildHeight = -1;

            // recursive calls to node's children
            for(Node child : node.children) {
                // return height of child
                int childHeight = returnHeightAndFindDiameter(child);

                if(childHeight > deepestChildHeight) {  // node's deepest need to update
                    secondDeepestChildHeight = deepestChildHeight;  // add second deepest to deepest first
                    deepestChildHeight = childHeight;
                } else if(childHeight > secondDeepestChildHeight) {
                    secondDeepestChildHeight = childHeight;     // node's second deepest need to update
                }
            }

            // potential diameter via node
            int potentialDiameterViaNode = deepestChildHeight + secondDeepestChildHeight + 2;
            if(potentialDiameterViaNode > diameter) { 
                diameter = potentialDiameterViaNode;    // update diameter with potential diameter
            }

            return deepestChildHeight + 1;  // return height of node by incrementing 1 to deepest height
        }
    }
}
