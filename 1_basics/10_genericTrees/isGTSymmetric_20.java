// 1. Given GenericTree class, complete isSymmetric function. 
// The function is expected to return true if tree is symmetric, else false.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null -> tree input

// Output
// false -> if tree is not symmetric

import java.util.Stack;
import java.util.ArrayList;

public class isGTSymmetric_20 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };     // Non Symmetric input
        
        // Integer input[] = { 10, 
        //                     20, 50, null, 60, null, null,
        //                     30, 70, null, 80, null, 90, null, null,
        //                     40, 100, null, 110, null, null,
        //                     null };     // Symmetric input

        GenericTree tree = new GenericTree(input);

        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root); 

        // return true if tree is symmetric
        // System.out.println(tree.isSymmetric(tree.root));
        System.out.println(tree.isSymmetricEfficient(tree.root, tree.root));
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

        // Initial value of node1(left to right traversal) - tree1.root
        // Inital value of node2(right to left traversal) - tree2.root
        boolean areMirror(Node node1, Node node2) {
            if(node1.children.size() != node2.children.size())
                return false;

            for(int i = 0; i < node1.children.size(); i++) {
                int j = node2.children.size() - 1 - i;

                Node childLeft = node1.children.get(i);
                Node childRight = node2.children.get(j);

                if(areMirror(childLeft, childRight) == false)
                    return false;
            }

            return true;
        }
        
        // Initial value of node - tree.root
        boolean isSymmetric(Node node) {
            // 1st argument initial value - tree.root(left to right traversal)
            // 2nd argument initial value - tree.root(right to left traversal)

            // Tree is symmetric if when divided from centre generates two mirror images
            // We can use areMirror() with same root passed for left to right and right to left traversals
            return areMirror(node, node);
        }

        // Initial value of node1 - tree.root(left to right traversal)
        // Initial value of node2 - tree.root(right to left traversal)
        // Similar to areMirror implementation - but stopping before duplicate traversals
        // Odd nodes stop at i <= j - after that already compared
        // Even nodes stop at i < j
        boolean isSymmetricEfficient(Node node1, Node node2) {
            if(node1.children.size() != node2.children.size())
                return false;

            int i = 0; 
            int j = node2.children.size() - 1;

            while(i <= j) {
                Node childLeft = node1.children.get(i);
                Node childRight = node2.children.get(j);

                if(isSymmetricEfficient(childLeft, childRight) == false)
                    return false;

                i++;
                j--;
            }

            return true;
        }
    }
}
