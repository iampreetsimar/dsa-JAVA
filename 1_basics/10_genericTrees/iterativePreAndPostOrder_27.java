// 1. Given GenericTree class, complete iterativePreAndPostOrder function. 
// The function does not use recursion and is expected to print preorder and postorder of the tree

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null -> tree input

// Output
// 10 20 50 60 30 70 80 110 120 90 40 100 -> preorder
// 50 60 20 70 110 120 80 90 30 100 40 10 -> postorder

import java.util.ArrayList;
import java.util.Stack;

public class iterativePreAndPostOrder_27 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null }; 

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        tree.iterativePreAndPostOrder(tree.root);
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

        private class Pair {
            Node node;
            int state;

            Pair(Node node, int state) {
                this.node = node;
                this.state = state;
            }
        }

        // Initial value of node : tree.root
        // Stack imitates recursive stack with help of state
        // State: -1 represents node added first time and can be read in preorder, 
        // State: node.children.size() represents node can be read in postorder,
        // State: 0 -> node.children.size() - 1 represents need to read node's child at idx(state)
        void iterativePreAndPostOrder(Node node) {
            Stack<Pair> s = new Stack<>();
            s.push(new Pair(node, -1));     // add root as pair object with state: -1
            String preOrder = "";
            String postOrder = "";

            while(s.size() > 0) {
                Pair top = s.peek();
                if(top.state == -1) {   // top in preorder
                    preOrder += top.node.data + " ";    // preorder print
                    top.state++;    // increment state to move to its child
                } else if(top.state == top.node.children.size()) {
                    // top in postorder
                    postOrder += top.node.data + " ";   // postorder print
                    s.pop();    // pop top after reading
                } else {
                    // top in state : 0 -> top.children.size() - 1
                    // fetch top's child at idx(state) 
                    Pair childPair = new Pair(top.node.children.get(top.state), -1);
                    top.state++;    // increment top state - to move to its next child the next time top is read
                    s.push(childPair);  // -> add child to stack to be read in preorder
                }
            }

            System.out.println(preOrder);
            System.out.println(postOrder);
        }
    }
}
