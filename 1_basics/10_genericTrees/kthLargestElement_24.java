// 1. Given GenericTree class, complete kthLargest function. 
// The function is given k and expected to find and print kth largest element in the tree.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null -> tree input
// 3 -> k

// Output
// 100 -> kth largest

import java.util.ArrayList;
import java.util.Stack;

public class kthLargestElement_24 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null }; 

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        int k = 3; 
        System.out.println(tree.kthLargest(tree.root, k));
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

        void ceilAndFloor(Node node, int data) {
            if(node.data > data) {
                if(node.data < ceil) {
                    ceil = node.data;
                }
            }

            if(node.data < data) {
                if(node.data > floor) {
                    floor = node.data;
                }
            }
            
            for(Node child : node.children) {
                ceilAndFloor(child, data);
            }
        }

        int kthLargest(Node node, int k) {
            // initial data value - +infinity
            // floor(data) -> 1st largest element
            int data = Integer.MAX_VALUE;

            // loop k times for kth largest
            for(int i = 0; i < k; i++) {
                // set floor for each iteration so that previous floor value doesn't affect
                // floor is in heap memory
                floor = Integer.MIN_VALUE;

                // sets floor value of data
                ceilAndFloor(node, data);

                // update data as floor value 
                data = floor;
            }

            // data contains kth largest element
            return data;
        }
    }
}
