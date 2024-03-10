// 1. Given GenericTree class, complete max function. 
// The function is expected to return the max element in the tree.

// Input 
// 10 20 null 30 50 null 60 null null 40 null null

// Output
// 60

import java.util.ArrayList;
import java.util.Stack;

public class maxInGT_4 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, null,
                            30, 50, null, 60, null, null,
                            40, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        System.out.println("Size : " + tree.size(tree.root));
        System.out.println("Max : " + tree.max(tree.root));
    }

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

    public static class GenericTree {
        Node root;

        GenericTree() {
            root = null;
        }

        GenericTree(Integer[] input) {
            this();
            this.constructTree(input);
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

        int size(Node node) {
            int count = 0;
            for(Node child : node.children) {
                count += size(child);
            }
            return count + 1;
        }

        int max(Node node) {
            // set max as node data
            int max = node.data;

            for(Node child : node.children) {
                // compare and set max to max from child node for all children
                max = Math.max(max, max(child));
            }

            // return max node from node and its descendents
            return max;
        }
    }
}
