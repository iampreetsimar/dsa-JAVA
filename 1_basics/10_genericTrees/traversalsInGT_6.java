// 1. Given GenericTree class, complete traversals function. 
// The function is expected to visit every node. While traversing, the function must print: 
// -> when control reaches a node for the first time - "Node Pre" node.data
// -> Before the control leaves for a child node from node - "Edge Pre" node.data -- child.data
// -> After the control comes back to a node from a child - "Edge Post" node.data -- child.data
// -> When the control is about to leave the node - "Node Post" node.data


// Input 
// 10 20 null 30 50 null 60 null null 40 null null

import java.util.ArrayList;
import java.util.Stack;

public class traversalsInGT_6 {
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
        System.out.println("Height : " + tree.height(tree.root));
        tree.traversals(tree.root);
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
            int height = -1;
            for(Node child : node.children) {
                height = Math.max(height, height(child));
            }
            return height + 1;
        }

        void traversals(Node node) {
            // pre - on reaching node
            System.out.println("Node Pre " + node.data);

            for(Node child : node.children) {
                // edge pre - on going into recursion
                System.out.println("Edge Pre " + node.data + "--" + child.data);
                traversals(child);

                // edge post - on coming out from recursion
                System.out.println("Edge Post " + node.data + "--" + child.data);
            }

            // post - on leaving node
            System.out.println("Node Post " + node.data);
        }
    }
}
