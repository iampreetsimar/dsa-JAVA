// 1. Complete isBST function. 
// The function is expected to check if given BT is a BST or not. 
// A BT is BST if every node of the BT has a value greater than all nodes on its left side and smaller value than
// all nodes on its right side.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null - input 1
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 49, null, null, 87, null, null - input 2

// Output
// true -> is a BST(input 1)
// false -> not a BST(input 2)

import java.util.Stack;

public class isBST_BT_19 {
    public static void main(String[] args) {
        Integer input[] = { 50, 
                            25, 12, null, null, 37, 30, null, null, null, 
                            75, 62, null, 49, null, null, 87, null, null};

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        System.out.println(isBST(root).isBST);
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

        BSTPair(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    // Use Pair class approach - return 3 values: isNodeSubtreeBST, min and max of node subtree
    // Initial value of node : root
    public static BSTPair isBST(Node node) {
        if(node == null)  {  // BASE CASE 
            // - return true for null subtree, identity of min is +infinity and max is -infinity
            return new BSTPair(true, Integer.MAX_VALUE,  Integer.MIN_VALUE);
        }

        // recursive calls to left and right childs
        BSTPair lp = isBST(node.left);
        BSTPair rp = isBST(node.right);

        boolean isNodeBST = node.data >= lp.max && node.data <= rp.min; // check if current node itself is BST or not
        boolean isNodeSubtreeBST = lp.isBST && rp.isBST && isNodeBST; // check if current node's subtree is BST or not

        // calculate entire node subtree's min - maybe needed by node's parent
        int nodeSubtreeMin = Math.min(node.data, Math.min(lp.min, rp.min));
        // calculate entire node subtree's max - maybe needed by node's parent
        int nodeSubtreeMax = Math.max(node.data, Math.max(lp.max, rp.max));

        // return current node's entire subtree is BST or not, entire subtree's min and max
        return new BSTPair(isNodeSubtreeBST, nodeSubtreeMin, nodeSubtreeMax);
    }
}
