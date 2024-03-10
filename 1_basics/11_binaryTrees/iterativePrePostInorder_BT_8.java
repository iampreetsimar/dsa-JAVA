// 1. Complete iterativePrePostInorderTraversal function. 
// The function does not use recursion and is expected to print preorder, postorder and inorder of the tree.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null 

// Output
// 50 25 12 37 30 75 62 70 87 : preorder
// 12 25 30 37 50 62 70 75 87 : inorder
// 12 30 37 25 70 62 87 75 50 : postorder

import java.util.Stack;

public class iterativePrePostInorder_BT_8 {
    public static void main(String[] args) {
        Integer input[] = { 50,
                            25, 12, null, null, 37, 30, null, null, null,
                            75, 62, null, 70, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        iterativePrePostInorderTraversal(root);
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

    // Similar to iterative Pre and Post order in Generic Tree
    // state 1 : represents first visit in preorder and adding left child of node to stack
    // state 2 : represents coming back from left child to node -> inorder node & adding right child of node to stack
    // state 3 : represents last visit of node -> postorder -> pop from stack
    public static void iterativePrePostInorderTraversal(Node root) {
        Stack<Pair> s = new Stack<>();
        Pair rootPair = new Pair(root, 1);
        s.push(rootPair);
        String preorder = ""; String postorder = ""; String inorder = "";
        while(s.size() > 0) {   // imitate recursive stack
            Pair top = s.peek();
            if(top.state == 1) {    // preorder, state++, move to left child
                preorder += top.node.data + " ";    // top's first visit -> preorder
                top.state++;    // setup for inorder visit + adding right child
                if(top.node.left != null) { // move to left child by adding to stack if not null
                    Pair lp = new Pair(top.node.left, 1);
                    s.push(lp);
                }
            } else if(top.state == 2) {     // inorder, state++, move to right child
                inorder += top.node.data + " "; // top's second visit -> inorder before adding right child
                top.state++;    // setup for moving back to parent -> postorder
                if(top.node.right != null) {    // move to right child by adding to stack if not null
                    Pair rp = new Pair(top.node.right, 1);
                    s.push(rp);
                }
            } else {    // state is 3 -> postorder, pop from stack
                postorder += top.node.data + " ";   // top's last visit -> postorder
                s.pop();    // pop from stack
            }
        }
        System.out.println(preorder + " : preorder");
        System.out.println(inorder + " : inorder");
        System.out.println(postorder + " : postorder");
    }
}
