// 1. Given GenericTree class, complete areMirror function. 
// The function is passed two GT roots and expected to return true if both trees are mirrored, else false.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null -> tree1 input

// 1, 2, 5, null, 6, null, null, 3, 7, null, 8, 11, null, 12, null, null, 9, null, null,
// 4, 10, null, null, null  -> tree2 input

// Output
// false -> tree1 and tree2 are not mirrored

import java.util.Stack;
import java.util.ArrayList;

public class areTreesMirrorInShape_19 {
    public static void main(String[] args) {
        Integer input1[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };
        
        Integer input2[] = { 100, 
                            200, 500, null, 600, null, null,
                            300, 700, null, 800, 1100, null, 1200, null, null, 900, null, null,
                            400, 1000, null, 1300, null, null,
                            null };

        GenericTree tree1 = new GenericTree(input1);
        GenericTree tree2 = new GenericTree(input2);

        System.out.println("Tree 1 root : " + tree1.root.data);
        tree1.display(tree1.root); 

        System.out.println("Tree 2 root : " + tree2.root.data);
        tree2.display(tree2.root); 

        // return true if tree1 and tree2 are mirrored in shape
        System.out.println(tree1.areMirror(tree1.root, tree2.root));
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

        // initial value of node1 : tree1.root
        // initial value of node2 : tree2.root
        boolean areMirror(Node node1, Node node2) {
            // BASE CASE : on reaching a node, check node1(tree1) and node2(tree2) number of children
            // if different -> not mirror in shape -> return false -> control does not move forward
            if(node1.children.size() != node2.children.size())
                return false;

            // node1 and node2 have same number of children
            // recursive calls to all children 
            // -> left to right for node1 children -> right to left for node2 children
            // if result from any of the child is false -> child1 and child2 at that point are not mirrored
            // return false -> control does move to next child    
            int i = 0;
            int j = node2.children.size() - 1;
            while(i < node1.children.size() && j >= 0) {
                // child1 - node1(tree1) child
                // child2 - node2(tree2) child
                Node child1 = node1.children.get(i);
                Node child2 = node2.children.get(j);

                if(areMirror(child1, child2) == false)
                    return false;


                i++;   //  move forward left to right traversal
                j--;   // move forward right to left traversal
            }

            // node1 and node2 are mirrored
            // children of node1 and that of node2 are also mirrored
            // return true -> everything from node1 and node2 onwards is mirrored
            return true;
        }
    }
}
