// 1. Complete find function for a BT. 
// The function is expected to find the given data in tree. If found, print true, else false.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null 
// 62 -> data
// 9 -> data

// Output
// true
// false

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class find_BT_6 {
    public static void main(String[] args) {
        Integer input[] = { 50,
                            25, 12, null, null, 37, 30, null, null, null,
                            75, 62, null, 70, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        System.out.println("Size : " + size(root));
        System.out.println("Sum : " + sum(root));
        System.out.println("Max : " + max(root));
        System.out.println("Height : " + height(root));
        levelOrder(root);
        System.out.println(find(root, 62));
        System.out.println(find(root, 9));
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

    public static int size(Node node) {
        if(node == null)    
            return 0;
        
        return 1 + size(node.left) + size(node.right);
    }

    public static int sum(Node node) {
        if(node == null)
            return 0;
        
        return node.data + sum(node.left) + sum(node.right);
    }

    public static int max(Node node) {
        if(node == null)
            return Integer.MIN_VALUE;

        return Math.max(node.data, Math.max(max(node.left), max(node.right)));
    }

    public static int height(Node node) {
        if(node == null)
            return -1;

        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static void levelOrder(Node root) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while(q.size() > 0) {
            int count = q.size();
            for(int i = 0; i < count; i++) {
                Node f = q.remove();
                System.out.print(f.data + " ");

                if(f.left != null)  q.add(f.left);
                if(f.right != null) q.add(f.right);
            }
            System.out.println(".");
        }
    }

    // initial value of node : root
    public static boolean find(Node node, int data) {
        if(node == null)    // BASE CASE
            return false;   // recursive call reaching to null -> return false

        if(node.data == data)   // data found 
            return true;    // control does not move further -> return true

        if(find(node.left, data))   // recursive call to left child -> if data found in left subtree
            return true;    // control does not move further -> return true

        if(find(node.right, data))  // recursive call to right child -> if data found in right subtree
            return true;    // control does not move further -> return true

        return false;   // data not found in node subtree -> return false in postorder
    }
}
