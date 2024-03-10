// 1. Given GenericTree class, complete levelOrder function. 
// The function is expected to visit every node level wise and print it.

// Input 
// 10 20 null 30 50 null 60 null null 40 null null

// Output
// 10 20 30 40 50 60 .

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class levelOrderTraversal_7 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, null,
                            30, 50, null, 60, null, null,
                            40, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        tree.levelOrder(tree.root);
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

        void levelOrder(Node node) {
            // use queue due to its FIFO discipline
            // node at level i + 1 will be added to back and read when all nodes at level i will be read
            Queue<Node> q = new ArrayDeque<>();
            q.add(node);    // add root

            while(q.size() > 0) {
                // 3 steps - remove(level i node), print(level i node), add children(level i + 1 nodes)

                // remove from front of queue
                Node front = q.remove();

                // print
                System.out.print(front.data + " ");

                // add children
                for(Node child : front.children) {
                    q.add(child);
                }
            }

            // queue is empty now -> all nodes read level wise
            System.out.println(".");
        }
    }
}
