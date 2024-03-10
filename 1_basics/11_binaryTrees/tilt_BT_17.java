// 1. Complete tilt function. 
// The function is expected to set the value of data member "tilt".
// Tilt of a NODE is the absolute value of difference b/w the sum of nodes in its left and right subtree.
// Tilt of whole TREE is represented as the sum of tilts of all its nodes.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null 

// Output
// 390 -> tilt

import java.util.Stack;

public class tilt_BT_17 {
    public static void main(String[] args) {
        Integer input[] = { 50, 
                            25, 12, null, null, 37, 30, null, null, null, 
                            75, 62, null, 70, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);

        tiltOfTree = 0;
        tilt(root);
        System.out.println(tiltOfTree);
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
        Pair rtp = new Pair(root, 1);
        Stack<Pair> s = new Stack<>();
        s.push(rtp);
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

    // static variable represents tilt of tree
    public static int tiltOfTree;

    // TRAVEL AND CHANGE APPROACH - return sum of nodes and change tilt of tree
    // Initial value of node : root
    public static int tilt(Node node) {
        if(node == null)    // BASE CASE - no node -> return 0
            return 0;

        int leftSum = tilt(node.left);  // returns sum of left subtree nodes
        int rightSum = tilt(node.right);    // return sum of right subtree nodes

        int nodeTilt = Math.abs(leftSum - rightSum);    // calculates tilt of current node
        tiltOfTree += nodeTilt;   // add current node tilt to tilt of tree

        return leftSum + rightSum + node.data;  // return sum of nodes in current node subtree
    }
}
