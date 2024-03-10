// 1. Given GenericTree class, complete removeLeaves function. 
// The function is expected to remove all leaf nodes from the tree. 

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null 

// Output
// 10 -> 20 30 40 .
// 20 -> .
// 30 -> 80 .
// 80 -> .
// 40 -> .

import java.util.ArrayList;
import java.util.Stack;

public class removeLeavesGT_13 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);    // Before removeLeaves
        tree.removeLeaves(tree.root);
        tree.display(tree.root);    // After removeLeaves
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

        GenericTree() {
            root = null;
        }

        GenericTree(Integer[] input) {
            this();
            constructTree(input);
        }

        void constructTree(Integer[] input) {
            Stack<Node> s = new Stack<>();
            for(int  i = 0; i < input.length; i++) {
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

        void removeLeaves(Node node) {
            // Pre-order removal of leaves
            // Since children in arraylist, we'll remove from back
            // so that we do not need to track idx
            for(int i = node.children.size() - 1; i >= 0; i--) {
                Node child = node.children.get(i);

                // check if child nodes has 0 children 
                if(child.children.size() == 0) {
                    // remove child node from node.children
                    node.children.remove(child);
                }

            }

            // recursive call to remaining child
            for(Node child : node.children) {
                removeLeaves(child);
            }
        }
    }
}
