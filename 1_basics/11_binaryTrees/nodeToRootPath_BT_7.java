// 1. Complete nodeToRootPath function for a BT. 
// The function is expected to return in the form of AL the path from data to root, if data is found.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null 
// 70 -> data

// Output
// [70, 62, 75, 50]

import java.util.ArrayList;
import java.util.Stack;

public class nodeToRootPath_BT_7 {
    public static void main(String[] args) {
        Integer input[] = { 50,
                            25, 12, null, null, 37, 30, null, null, null,
                            75, 62, null, 70, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        System.out.println(nodeToRootPath(root, 30));
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node constructTree(Integer[] input) {
        Node root = new Node(input[0]);
        Pair rootPair = new Pair(root, 1);
        Stack<Pair> s = new Stack<>();
        s.push(rootPair);
        int idx = 0;
        while(s.size() > 0) {
            Pair top = s.peek();
            if(top.state == 1) {
                if(input[++idx] != null) {
                    top.node.left = new Node(input[idx]);
                    Pair lp = new Pair(top.node.left, 1);
                    s.push(lp);
                }
                top.state++;
            } else if(top.state == 2) {
                if(input[++idx] != null) {
                    top.node.right = new Node(input[idx]);
                    Pair rp = new Pair(top.node.right, 1);
                    s.push(rp);
                }
                top.state++;
            } else {
                s.pop();
            }
        }
        return root;
    }

    public static void displayTree(Node node) {
        if(node == null)
            return;

        String res = (node.left == null) ? "." : node.left.data + "";
        res += " <- " + node.data + " -> ";
        res += (node.right == null) ? "." : node.right.data + "";
        System.out.println(res);

        displayTree(node.left);
        displayTree(node.right);
    }

    // Initial value of node : root
    // Similar to nodeToRootPath in Generic Tree
    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        if(node == null)    // BASE CASE -> null node reached
            return new ArrayList<Integer>();    // return empty AL

        if(node.data == data) {     // data found -> return AL with current node data as element
            ArrayList<Integer> bl = new ArrayList<>();
            bl.add(node.data);
            return bl;
        }

        ArrayList<Integer> pathTillLeftChild = nodeToRootPath(node.left, data);
        if(pathTillLeftChild.size() > 0) {  // left child returns a path -> current node added to path and returned
            pathTillLeftChild.add(node.data);
            return pathTillLeftChild;
        }

        ArrayList<Integer> pathTillRightChild = nodeToRootPath(node.right, data);
        if(pathTillRightChild.size() > 0) { // right child returns a path -> current node added to path and returned
            pathTillRightChild.add(node.data);
            return pathTillRightChild;
        }
        
        return new ArrayList<Integer>();    // data not found in node subtree -> return empty AL
    }
}
