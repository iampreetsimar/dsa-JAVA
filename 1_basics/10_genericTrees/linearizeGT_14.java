// 1. Given GenericTree class, complete linearize function. 
// The function is expected to create a linear tree. Every node will have a single child.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null 

// Output
// 10 -> 20 .
// 20 -> 50 .
// 50 -> 60 . 
// 60 -> 30 .
// 30 -> 70 .
// 70 -> 80 .
// 80 -> 110 .
// 110 -> 120 .
// 120 -> 90 .
// 90 -> 40 .
// 40 -> 100 .
// 100 -> .

import java.util.ArrayList;
import java.util.Stack;

public class linearizeGT_14 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);    // Before linearize
        // tree.linearize(tree.root);
        tree.linearizeEfficient(tree.root);
        tree.display(tree.root);    // After linearize
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

        private Node getTailNode(Node node) {
            // since node is linearized due to recursion
            // all nodes have only 1 child
            // we need to traverse till last node by fetching 0th idx child at each step
            while(node.children.size() > 0) {
                node = node.children.get(0);
            }
            return node;
        }
        
        void linearize(Node node) {
            // recursive call to all children of node
            for(Node child : node.children) {
                linearize(child);
            }

            // faith that all nodes from node children onwards are linearized
            // Post order - need to linearize node with its children
            // when node has only 1 children, loop will stop -> node will be linearized
            while(node.children.size() > 1) {
                // remove last child of node from children AL
                Node lastChild = node.children.remove(node.children.size() - 1);

                // get secondLastChild of node -> secondLastChild becomes last after removal of last
                // we have faith that all children of nodes are linearized
                Node secondLastChild = node.children.get(node.children.size() - 1);

                // get tail node of second last child
                Node tail = getTailNode(secondLastChild);

                // add last child to children of tail node
                tail.children.add(lastChild);
            }
        }

        Node linearizeEfficient(Node node) {
            if(node.children.size() == 0)   // BASE CASE
                return node;    // node has 0 child -> return node itself as tail

            // recursive call to last child of node -> return tail of last child(linearized now)
            Node lastChildTailNode = linearizeEfficient(node.children.get(node.children.size() - 1));

            // loop stops when node has 1 child 
            while(node.children.size() > 1) {

                //remove last child from node.children
                Node lastChild = node.children.remove(node.children.size() - 1);

                // second last child becomes last after removal
                // recursive call to second last child -> returns tail of second last child(linearized)
                Node secondLastChildTailNode = linearizeEfficient(node.children.get(node.children.size() - 1));

                // add last child to children of tail of second last child
                secondLastChildTailNode.children.add(lastChild);
            }

            // node becomes linearized -> has only 1 child now
            // tail of last child becomes tail of node now
            // return tail
            return lastChildTailNode;
        }
    }
}
