// 1. Given GenericTree class, complete ceilAndFloor function. 
// The function is expected to find the ceil and floor of a given element.
// If element is largest, ceil is largest integer value and if element is smallest, floor is smallest integer value.
// It doesn't matter if element in present in tree or not.
// Ceil is just next larger to given element (smallest among larger items).
// Floor is just previous smaller to given element (largest among smaller items).
// Use "Travel and Change" strategy.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null -> tree input
// 65 -> element

// Output
// 70 -> ceil
// 60 -> floor

import java.util.ArrayList;
import java.util.Stack;

public class ceilAndFloor_23 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null }; 

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        int data = 65; 
        tree.ceilAndFloor(tree.root, data);
        System.out.println("Ceil of " + data + " : " + tree.ceil);
        System.out.println("Floor of " + data + " : " + tree.floor);
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
        int ceil;
        int floor;

        GenericTree() {
            root = null;
            ceil = Integer.MAX_VALUE;   // set to +infinity
            floor = Integer.MIN_VALUE;  // set to -infinity
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

        // Use Travel and Change Approach
        // Initial value of ceil : Integer.MAX_VALUE (+infinity) - as we are finding SMALLEST amoungst largest
        // Initial value of floor : Integer.MIN_VALUE (-infinity) - as we are finding LARGEST amoungst smallest
        // Initial value of node : tree.root
        void ceilAndFloor(Node node, int data) {
            if(node.data > data) {
                // potential for ceil
                if(node.data < ceil) {
                    // if node is smaller than current ceil
                    ceil = node.data;
                }
            }

            if(node.data < data) {
                // potential for floor
                if(node.data > floor) {
                    // if node is larger than current floor
                    floor = node.data;
                }
            }

            // recursive calls to node's children
            for(Node child : node.children) {
                ceilAndFloor(child, data);
            }
        }
    }
}
