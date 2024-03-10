// 1. Complete diameter function. 
// The function is expected to return diameter of BT. 
// Diameter - max edges b/w any two nodes of BT

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null 

// Output
// 6

import java.util.Stack;

public class diameter_BT_16 {
    public static void main(String[] args) {
        Integer input[] = { 50, 
                            25, 12, null, null, 37, 30, null, null, null, 
                            75, 62, null, 70, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        System.out.println(diameter(root));
        System.out.println(diameterEfficient(root).diameter);
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
 
    public static int height(Node node) {
        if(node == null)
            return -1;

        return 1 + Math.max(height(node.left), height(node.right));
    }
    
    // Similar to diameter approach in Generic Tree
    // Instead of travel and change, we invoke height() at each call and return potential diameter
    // TC = O(N * h), h is TC for finding height of tree(O(N) or O(logN))
    // Initial value of node : tree
    public static int diameter(Node node) {
        if(node == null)    // BASE CASE - 0 edges - return 0
            return 0;

        // gets diameter from left child subtree - both nodes in left subtree
        int leftChildDiameter = diameter(node.left);

        // get diameter from right child subtree - both nodes in right subtree
        int rightChildDiameter = diameter(node.right);

        // gets height of left and right childs from their deepest nodes
        int leftChildHeight = height(node.left);
        int rightChildHeight = height(node.right);

        // diameter passing via current node - 1 node in left subtree, 1 in right subtree, 
        // 2 for adding edges from node to left and right childs
        int diameterViaNode = leftChildHeight + rightChildHeight + 2;

        // return max of the 3 potential diameter of tree
        return Math.max(diameterViaNode, Math.max(leftChildDiameter, rightChildDiameter));
    }


    public static class DHPair {
        int diameter;
        int height;

        DHPair(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
    }

    // ALTERNATIVE APPROACH : use DHPair class
    // instead of returning only diameter, return diameter and height in same call
    // TC = O(N) as other than recursive calls, only O(1) work for each call
    // Initial value of node : root
    public static DHPair diameterEfficient(Node node) {
        if(node == null)    // BASE CASE - as 0 edges, return 0: diameter, -1: height
            return new DHPair(0, -1);

        // return diameter of left subtree and height of left child
        DHPair leftChildPair = diameterEfficient(node.left);

        // return diameter of right subtree and height of right child
        DHPair rightChildPair = diameterEfficient(node.right);

        // diamter passing via current node
        int diameterViaNode = leftChildPair.height + rightChildPair.height + 2;

        // height of current node from its deepest descendent
        int nodeHeight = Math.max(leftChildPair.height, rightChildPair.height) + 1;

        // return max of 3 potential diameter and height of current node
        return new DHPair(Math.max(diameterViaNode, Math.max(leftChildPair.diameter, rightChildPair.diameter)), 
        nodeHeight);
    }
}
