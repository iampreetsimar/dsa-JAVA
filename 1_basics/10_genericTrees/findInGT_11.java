// 1. Given GenericTree class, complete find function. 
// The function is expected to find the given data in tree. If found, print true, else false.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null 

// 120 -> key
// 95 -> key

// Output
// true
// false

import java.util.ArrayList;
import java.util.Stack;

public class findInGT_11 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        System.out.println(tree.find(tree.root, 120));
        System.out.println(tree.find(tree.root, 95));
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

        boolean find(Node node, int key) {
            if(node.data == key)    // if key found -> no need to make recursive calls further
                return true;        // move back and return true

            // key not found at node level -> move to children
            for(Node child : node.children) {
                // as soon as key found at a child -> move back and return true
                // no need to make further call to remaining children
                if(find(child, key))
                    return true;
            }

            // key not found at node and its children -> return false
            return false;
        }
    }
}
