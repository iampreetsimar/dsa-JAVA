// 1. Given GenericTree class, complete levelOrderLineWise function. 
// The function is expected to visit every node in level order fashion.
// All nodes on same level should be separated by a space.
// Different level nodes on separate lines.

// Input 
// 10 20 null 30 50 null 60 null null 40 null null

// Output
// 10
// 20 30 40
// 50 60

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class levelOrderLineWise_8 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, null,
                            30, 50, null, 60, 70, null, null, null,
                            40, 80, null, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        tree.levelOrderLineWise(tree.root);
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

        void levelOrderLineWise(Node node) {
            // mainQ represents nodes at level i
            Queue<Node> mainQ = new ArrayDeque<>();

             // childQ represents nodes at leve i + 1
            Queue<Node> childQ = new ArrayDeque<>();

            // add root node to mainQ
            mainQ.add(node);

            while(mainQ.size() > 0) {
                // 1. remove from front of mainQ
                Node f = mainQ.remove();

                // 2. print node removed from front
                System.out.print(f.data + " ");

                // 3. add children of node from front of mainQ to back of childQ 
                for(Node child : f.children) {
                    childQ.add(child);
                }

                if(mainQ.size() == 0) {
                    // marks that mainQ is empty -> nodes at level i has been read
                    // move to nodes at level i + 1 
                    // -> make mainQ as childQ and childQ becomes empty to repeat the process
                    Queue<Node> temp = mainQ;
                    mainQ = childQ;
                    childQ = temp;

                    // add new line before reading nodes at level i + 1
                    System.out.println();
                }
            }
        }
    }
}
