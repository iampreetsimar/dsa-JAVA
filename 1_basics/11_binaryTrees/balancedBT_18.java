// 1. Complete isBTBalanced function. 
// The function is expected to check if given BT is balanced or not. 
// A BT is balanced if for every node of the BT, the gap b/w height of its left and right subtree is not more than 1

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, 51, null, null, 75, 62, 60, null, null, 70, null, null, null 

// Output
// false

import java.util.Stack;

public class balancedBT_18 {
    public static void main(String[] args) {
        Integer input[] = { 50, 
                            25, 12, null, null, 37, 30, null, null, 51, null, null, 
                            75, 62, 60, null, null, 70, null, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        isBTBalanced(root);
        System.out.println(isBalanced);
        // System.out.println(isBTBalanced(root).isBalanced);
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

    // represents if given BT is balanced or not
    public static boolean isBalanced = true;

    // Use Travel and Change Approach - return height and change isBalanced
    // Initial value of node : root
    // Assume tree is balanced with default isBalanced - true
    // If we get balancedFactor > 1 for any node, we set isBalanced to false
    public static int isBTBalanced(Node node) {
        if(node == null)    // BASE CASE
            return -1;

        // recursive call to left and right childs to get their heights
        int leftChildHeight = isBTBalanced(node.left);
        int rightChildHeight = isBTBalanced(node.right);

        // tree not balanced: absolute value of left and right subtree's height diff > 1 even for a single node
        // checks at node level
        int balancedFactor = Math.abs(leftChildHeight - rightChildHeight);
        if(balancedFactor > 1)
            isBalanced = false;

        // return node subtree height
        return Math.max(leftChildHeight, rightChildHeight) + 1;
    }

    public static class BalPair {
        boolean isBalanced;
        int height;

        BalPair(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    // ALTERNATIVE APPROACH : use BalPair class
    // instead of travel and change approach, return both height and isBalanced values
    // public static BalPair isBTBalanced(Node node) {
    //     if(node == null)    // BASE CASE
    //         return new BalPair(true, -1);

    //     // recursive calls to left and right childs - return their heights and if their subtree is balanced or not
    //     BalPair lp = isBTBalanced(node.left);
    //     BalPair rp = isBTBalanced(node.right);

    //     // checks at tree level - if both left and right subtree are balanced or not
    //     // checks at node level as well - if current node is balanced or not
    //     boolean isNodeSubtreeBalanced = lp.isBalanced && rp.isBalanced && Math.abs(lp.height - rp.height) <= 1;
    //     int nodeHeight = Math.max(lp.height, rp.height) + 1;    // calculates current node's height

    //     // returns if current node subtree is balanced or not and current node's height
    //     return new BalPair(isNodeSubtreeBalanced, nodeHeight);
    // }
}
