// 1. Given GenericTree class, complete height function. 
// The function is expected to return the height of the tree.
// Depth of node - number of edges it is away from root(depth of root is 0).
// Height of tree - depth of deepest node(distance, i.e., number of edges b/w root and its deepest node)

// Input 
// 10 20 null 30 50 null 60 70 null null null 40 null null

// Output
// 3

import java.util.ArrayList;
import java.util.Stack;

public class heightOfGT_5 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, null,
                            30, 50, null, 60, 70, null, null, null,
                            40, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        System.out.println("Height : " + tree.height(tree.root));
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
            int max = node.data;
            for(Node child : node.children) {
                max = Math.max(max, max(child));
            }
            return max;
        }

        int height(Node node) {
            // BASE CASE LOGIC
            // Assume only root node present
            // Height of that tree should be 0. Since we add 1 while returning, adding 1 to (-1)
            // will result height of tree to be 0
            int height = -1;

            // assign max result to height to get depth of the deepest node
            for(Node child : node.children) {
                height = Math.max(height, height(child));
            }

            // add 1 to height to include edge from node to its child
            return height + 1;
        }
    }
}
