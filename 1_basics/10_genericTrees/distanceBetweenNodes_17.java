// 1. Given GenericTree class, complete distanceBetweenNodes function. 
// The function is expected to return the distance(in terms of number of edges) between two nodes in GT.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null 
// 100 -> d1
// 110 -> d2

// Output
// 5 -> number of edges between 100 and 110

import java.util.Stack;
import java.util.ArrayList;

public class distanceBetweenNodes_17 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root); 

        // distance b/w nodes in terms of edges
        int d1 = 50;
        int d2 = 100;
        int distance = tree.distanceBetweenNodes(tree.root, d1, d2);
        System.out.println("Distance b/w " + d1 + " and " + d2 + " - " + distance);
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

        ArrayList<Integer> nodeToRootPath(Node node, int data) {
            if(data == node.data) {
                ArrayList<Integer> bl = new ArrayList<>();
                bl.add(node.data);
                return bl; 
            }

            for(Node child : node.children) {
                ArrayList<Integer> path = nodeToRootPath(child, data);
                if(path.size() > 0) {
                    path.add(node.data);
                    return path;
                }
            }

            return new ArrayList<Integer>();
        }

        // valid d1 and d2 - both present in tree
        int distanceBetweenNodes(Node node, int d1, int d2) {
            // return node to root path for d1
            ArrayList<Integer> pathForD1 = nodeToRootPath(node, d1);

            // return node to root path for d2
            ArrayList<Integer> pathForD2 = nodeToRootPath(node, d2);

            // traverse from back of paths
            int i = pathForD1.size() - 1;
            int j = pathForD2.size() - 1;

            // find lca for both nodes
            while(i >= 0 && j >= 0 && pathForD1.get(i) == pathForD2.get(j)) {
                i--;
                j--;
            }

            // lca is the data prior
            i++; j++;

            // d1(at idx : 0) is i distance away from lca
            // d2(at idx : 0) is j distance away from lca
            // adding both distances -> distance b/w both nodes in terms of edges
            return i + j;
        }
    }
}
