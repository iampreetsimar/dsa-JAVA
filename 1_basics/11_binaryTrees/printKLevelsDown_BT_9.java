// 1. Complete printKLevelsDown function. 
// The function is expected to print in separates lines all the nodes which are k levels deep. 
// Use preorder for printing.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null 
// 3 -> k

// Output
// 30
// 70

import java.util.Stack;

public class printKLevelsDown_BT_9 {
    public static void main(String[] args) {
        Integer input[] = { 50,
                            25, 12, null, null, 37, 30, null, null, null,
                            75, 62, null, 70, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        printKLevelsDown(root, 3);
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

    // Initial value of node : root
    // Invalid k, k < 0 can be passed
    // Assuming root level is 0
    public static void printKLevelsDown(Node node, int k) {
        if(node == null || k < 0)   // BASE CASE
            return;       // if null node is reached and k > 0 -> no node printed as tree's height < k.                        

        // printing in preorder
        if(k == 0) {    // reached level k -> print nodes at level k
            System.out.println(node.data);
            return;
        }

        // recursive calls to left and right
        printKLevelsDown(node.left, k - 1);     // left is 1 level down from current node -> decrement k
        printKLevelsDown(node.right, k - 1);    // right is 1 level down from current node -> decrement k
    }
}
