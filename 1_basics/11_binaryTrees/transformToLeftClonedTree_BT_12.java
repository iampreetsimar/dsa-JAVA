// 1. Complete transformToLeftClonedTree function. 
// The function is expected to tranform current tree to a left cloned tree. Each node gets a clone node of itself
// as its left child. Actual left child of OG node becomes left child of cloned node.

// Input 
// 1, 2, 4, null, null, 5, null, null, 3, 6, null, null, 7, null, null 

// Output
// Left cloned tree

import java.util.Stack;

public class transformToLeftClonedTree_BT_12 {
    public static void main(String[] args) {
        Integer input[] = { 1,
                            2, 4, null, null, 5, null, null,
                            3, 6, null, null, 7, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        transformToLeftClonedTree(root);
        System.out.println("Left Cloned Tree : ");
        displayTree(root);
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
    
    // Initial value of node : root
    public static void transformToLeftClonedTree(Node node) {
        if(node == null)    // BASE CASE
            return;

        // recursive calls to left and right 
        // -> faith that left and right subtree are transformed to left cloned tree
        transformToLeftClonedTree(node.left);
        transformToLeftClonedTree(node.right);

        // postorder
        // create new node which is a clone of current node
        Node cloneNode = new Node(node.data);
        cloneNode.left = node.left;     // add current node's left to cloned node's left
        node.left = cloneNode;      // cloned node becomes current node's left

        // node subtree is transformed to left cloned tree
    }
}
