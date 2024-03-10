// 1. Complete levelOrder function for a BT. 
// The function is expected to print tree in level order from left to right.
// Nodes in a level should be printed together and nodes in different levels should be on different lines.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null 

// Output
// 50
// 25 75
// 12 37 62 87
// 30 70

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class levelOrderTraversal_BT_5 {
    public static void main(String[] args) {
        Integer input[] = { 50,
                            25, 12, null, null, 37, 30, null, null, null,
                            75, 62, null, 70, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        levelOrder(root);
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

    // Counting approach(used in Generic Trees - Alternative Approaches)
    // nodes at level i + 1 are added after nodes at level i
    public static void levelOrder(Node root) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(root); // add root to q -> for level 0 -> only 1 node present

        while(q.size() > 0) {
            int nodesCountAtCurrLevel = q.size();

            // steps - 1. remove, 2. print, 3. add left and right to q
            // 3 steps will happen same number of times as the number of nodes present at that level
            for(int i = 0; i < nodesCountAtCurrLevel; i++) {
                Node f = q.remove();        // remove
                System.out.print(f.data + " ");     // print

                if(f.left != null)  // add left only if it is not null
                    q.add(f.left);

                if(f.right != null) // add right only if it is not null
                    q.add(f.right);
            }

            System.out.println(".");    // indicates current level is finished - add new line for next level
        }
    }
}
