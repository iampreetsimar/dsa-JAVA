// 1. Given GenericTree class, complete nodeToRootPath function. 
// The function is expected to return in the form of AL the path from element to root, if element with data is found.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null 
// 120 -> data

// Output
// [120, 80, 30, 10] -> ArrayList of Integer

import java.util.ArrayList;
import java.util.Stack;

public class nodeToRootPath_15 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);  
        int data = 120;
        ArrayList<Integer> nodeToRootPath = tree.nodeToRootPath(tree.root, data);
        System.out.println("Node to root path for data " + data +  " -- " + nodeToRootPath);
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

        ArrayList<Integer> nodeToRootPath(Node node, int data) {
            if(data == node.data) {
                // BASE CASE
                // data present at node -> add node.data to AL and return
                ArrayList<Integer> baseList = new ArrayList<>();
                baseList.add(node.data);
                return baseList;
            }

            for(Node child : node.children) {
                // recursive call to node children -> return AL
                ArrayList<Integer> pathTillChild = nodeToRootPath(child, data);

                if(pathTillChild.size() > 0) {
                    // returned AL has items -> data found in tree after node(from child onwards)
                    // add node to path till now
                    pathTillChild.add(node.data);

                    // return updated AL path
                    return pathTillChild;
                }
            }

            // data not found at node or its children
            // return empty AL
            return new ArrayList<Integer>();
        }
    }
}
