// 1. Given GenericTree class, complete multisolver function. 
// The function is expected to set size, max element, min element, height of the tree without actually returning
// any value from function.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null -> tree input

// Output
// 12 -> size
// 120 -> max
// 10 -> min
// 3 -> height

import java.util.Stack;
import java.util.ArrayList;

public class multisolverGT_21 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };   

        GenericTree tree = new GenericTree(input);

        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root); 
        tree.multisolver(tree.root, 0);
        System.out.println("Size : " + tree.size);
        System.out.println("Max : " + tree.max);
        System.out.println("Min : " + tree.min);
        System.out.println("Height : " + tree.height);
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
        int size;
        int max;
        int min;
        int height;

        GenericTree() {
            root = null;
            size = 0;
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            height = 0;
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

        // TRAVEL AND CHANGE APPROACH 
        // - travelling using recursive calls and changing property in heap instead of returning result
        // Initial value of node : tree.root
        // Initial value of depth : 0(for root node)
        void multisolver(Node node, int depth) {
            // size, max, min, height are data members stored in heap memory 
            // - changes dones to them remain after function call as well
            size++;
            max = Math.max(max, node.data);
            min = Math.min(min, node.data);
            height = Math.max(height, depth);

            for(Node child : node.children) {
                // depth is in stack memory - changes remain until that recursive call only
                multisolver(child, depth + 1);
            }
        }
    }
}
