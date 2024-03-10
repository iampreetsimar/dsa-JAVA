// 1. Complete traversals function for a BT. 
// The function is expected to print tree in preorder, inorder and postorder.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null 

// Output
// preorder : 50 25 12 37 30 75 62 70 87
// inorder : 12 25 30 37 50 62 70 75 87
// postorder: 12 30 37 25 70 62 87 75 50

import java.util.Stack;

public class traversals_BT_4 {
    public static void main(String[] args) {
        Integer input[] = { 50,
                            25, 12, null, null, 37, 30, null, null, null,
                            75, 62, null, 70, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        traversals(root);
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
                idx++;
                if(input[idx] != null) {
                    top.node.left = new Node(input[idx]);
                    Pair lp = new Pair(top.node.left, 1);
                    s.push(lp);
                }
                top.state++;
            } else if(top.state == 2) {
                idx++;
                if(input[idx] != null) {
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

    public static void traversals(Node root) {
        preOrder(root); 
        System.out.println(" : Preorder");
        inOrder(root); 
        System.out.println(" : Inorder");
        postOrder(root); 
        System.out.println(" : Postorder");
    }

    public static void preOrder(Node node) {
        if(node == null)
            return;

        System.out.print(node.data + " ");  // on reaching and before left child
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(Node node) {
        if(node == null)
            return;

        inOrder(node.left);
        System.out.print(node.data + " ");  // b/w left and right child
        inOrder(node.right);
    }

    public static void postOrder(Node node) {
        if(node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");  // after right child and before leaving
    }
}
