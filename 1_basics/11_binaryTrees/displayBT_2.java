// Display constructed binary tree using given input in input array

// Output Format
// print (left child <- node -> right child)

// Output
// 20 <- 10 -> 30
// 40 <- 20 -> 50
// . <- 40 -> .
// 80 <- 50 -> .
// . <- 80 -> .
// 60 <- 30 -> 70
// . <- 60 -> 90
// . <- 90 -> .
// . <- 70 -> .

import java.util.Stack;

public class displayBT_2 {
    public static void main(String[] args) {
        Integer input[] = { 10,
                            20, 40, null, null, 50, 80, null, null, null,
                            30, 60, null, 90, null, null, 70, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
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

    // initial value of node : binary tree root
    public static void displayTree(Node node) {
        if(node == null)    // BASE CASE needed
            return;

        String res = (node.left == null) ? "." : node.left.data + "";   // added left node
        res += " <- " + node.data + " -> "; // added node
        res += (node.right == null) ? "." : node.right.data + "";   // added right node
        System.out.println(res);    // print res

        // recursive calls to left and right childs
        displayTree(node.left);
        displayTree(node.right);
    }
}
