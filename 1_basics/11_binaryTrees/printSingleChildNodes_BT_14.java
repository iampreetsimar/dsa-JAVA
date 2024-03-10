// 1. Complete printSingleChildNodes function. 
// The function is expected to print in separates lines all the nodes which are an only child of a parent.
// Use preorder for traversal.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, 73, null, null, 87, null, null 

// Output
// 30
// 70
// 73

import java.util.Stack;

public class printSingleChildNodes_BT_14 {
    public static void main(String[] args) {
        Integer input[] = { 50, 
                            25, 12, null, null, 37, 30, null, null, null, 
                            75, 62, null, 70, null, 73, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        printSingleChildNodes(root);
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
    public static void printSingleChildNodes(Node node) {
        if(node == null)    // BASE CASE
            return;

        if(node.left != null && node.right == null) {   // current node has only left child
            System.out.println(node.left.data);     // print left child data
        } else if(node.right != null && node.left == null) {    // current node has only right child
            System.out.println(node.right.data);    // print right child data
        }       
        
        // recursive calls to both left and right
        printSingleChildNodes(node.left);       
        printSingleChildNodes(node.right);
    }
}
