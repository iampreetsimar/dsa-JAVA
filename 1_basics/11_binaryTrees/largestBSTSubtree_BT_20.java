// 1. Complete multisolverBST function. 
// The function is expected to find the root of largest subtree which is also a BST. 
// Also, find the number of nodes in that subtree.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, 40, null, null, 75, 62, 60, null, null, 77, null, null, 87, null, null

// Output
// 25@5

import java.util.Stack;

public class largestBSTSubtree_BT_20 {
    public static void main(String[] args) {
        Integer input[] = { 50, 
                            25, 12, null, null, 37, 30, null, null, 40, null, null, 
                            75, 62, 60, null, null, 77, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        BSTPair result = multisolverBST(root);
        System.out.println("is Tree Root BST - " + result.isBST);
        System.out.println(result.largestBSTRoot.data + "@" + result.largestBSTSize);
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

    public static class BSTPair {
        boolean isBST;
        int min;
        int max;
        Node largestBSTRoot;
        int largestBSTSize;

        BSTPair(boolean isBST, int min, int max, Node largestBSTRoot, int largestBSTSize) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.largestBSTRoot = largestBSTRoot;
            this.largestBSTSize = largestBSTSize;
        }
    }

    public static BSTPair multisolverBST(Node node) {
        if(node == null) {      // BASE CASE -> node is null
            return new BSTPair(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 
            null, 0); // no largest BST root -> root: null, size: 0
        }

        // ******* Same as isBST logic *******
        BSTPair lp = multisolverBST(node.left);
        BSTPair rp = multisolverBST(node.right);

        boolean isNodeBST = node.data >= lp.max && node.data <= rp.min;
        boolean isNodeSubtreeBST = lp.isBST && rp.isBST && isNodeBST;

        int min = Math.min(node.data, Math.min(lp.min, rp.min));
        int max = Math.max(node.data, Math.max(lp.max, rp.max));
        // ******* End of isBST logic *******

        Node largestBSTRoot;    // stores root of largest BST subtree
        int largestBSTSize;     // stores size of largest BST subtree

        if(isNodeSubtreeBST) {  // current node subtree is BST
            largestBSTRoot = node;
            largestBSTSize = lp.largestBSTSize + rp.largestBSTSize + 1; // +1 for current node itself
        } else if(lp.largestBSTSize > rp.largestBSTSize) { // current node subtree is not BST -> largest BST in left
            largestBSTRoot = lp.largestBSTRoot;     
            largestBSTSize = lp.largestBSTSize;
        } else {    // current node subtree is not BST -> largest BST not in left => largest BST in right
            largestBSTRoot = rp.largestBSTRoot;
            largestBSTSize = rp.largestBSTSize;
        }

        // return BSTPair values for current node subtree to node's parent
        return new BSTPair(isNodeSubtreeBST, min, max, largestBSTRoot, largestBSTSize);
    }
}
