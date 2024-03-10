// 1. Complete pathToLeafFromRoot function. 
// The function is expected to print all paths from root to leaves which have sum of nodes in range 
// from low to high(both inclusive).
// Elements in path should be separated by a space.
// Each path should be in a separate line.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, 40, null, null, 75, 62, 60, null, null, 70, null, null, 87, null, null 
// 150 -> low
// 250 -> high

// Output
// 50 25 37 40
// 50 75 62 60
// 50 75 87

import java.util.Stack;

public class pathSumToLeafFromRoot_BT_11 {
    public static void main(String[] args) {
        Integer input[] = { 50,
                            25, 12, null, null, 37, 30, null, null, 40, null, null,
                            75, 62, 60, null, null, 70, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        pathToLeafFromRoot(root, "", 0, 150, 250);
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
    // Initial value of path at root node : "" - empty string
    // Initial value of sum at root node : 0
    public static void pathToLeafFromRoot(Node node, String path, int sum, int low, int high) {
        if(node == null)
            return;

        if(node.left == null && node.right == null) {   // reached leaf node
            sum += node.data;   // since leaf data is not added in sum yet -> add leaf data to sum
            if(sum >= low && sum <= high) {     // check if sum is in range
                System.out.println(path + node.data);   // print path and include leaf node in path
            }
            return;     // return - doesn't matter if sum in range or not
        }

        // recursive call to left, include current node in path and sum
        pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, low, high);

        // recursive call to right, include current node in path and sum
        pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, low, high);
    }
}
