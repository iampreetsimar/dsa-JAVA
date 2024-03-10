// 1. Given GenericTree class, complete size function. 
// The function is expected to count number of nodes in tree and return it.

// Input 
// 10 20 null 30 50 null 60 null null 40 null null

// Output
// 6

import java.util.ArrayList;
import java.util.Stack;

public class sizeOfGT_3 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, null,
                            30, 50, null, 60, null, null,
                            40, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        System.out.println(tree.size(tree.root));
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
                // add number of nodes returned from all children
                count += size(child);
            }

            // add count of node itself with total count from children
            return count + 1;
        }
    }
}
