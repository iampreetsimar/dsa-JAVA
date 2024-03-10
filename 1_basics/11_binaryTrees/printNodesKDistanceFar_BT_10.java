// 1. Complete printKDistanceFar function. 
// The function is expected to print in separates lines all the nodes which are k distance away in any direction
// from given node vale as input.

// Input 
// 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null 
// 37 -> data
// 2 -> k

// Output
// 12
// 50

import java.util.ArrayList;
import java.util.Stack;

public class printNodesKDistanceFar_BT_10 {
    public static void main(String[] args) {
        Integer input[] = { 50,
                            25, 12, null, null, 37, 30, null, null, null,
                            75, 62, null, 70, null, null, 87, null, null };

        Node root = constructTree(input);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        printKDistanceFar(root, 37, 2);
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

    // returns ArrayList<Node>
    public static ArrayList<Node> nodeToRootPath(Node node, int data) {
        if(node == null)
            return new ArrayList<Node>();

        if(node.data == data) {
            ArrayList<Node> bl = new ArrayList<>();
            bl.add(node);
            return bl;
        }

        ArrayList<Node> leftPath = nodeToRootPath(node.left, data);
        if(leftPath.size() > 0) {
            leftPath.add(node);
            return leftPath;
        }

        ArrayList<Node> rightPath = nodeToRootPath(node.right, data);
        if(rightPath.size() > 0) {
            rightPath.add(node);
            return rightPath;
        }

        return new ArrayList<Node>();
    }

    // Initial value of node : node passed from printKDistanceFar
    // add a blockerNode which when reached, just returns
    public static void printKLevelsDown(Node node, int k, Node blockerNode) {
        if(node == null || k < 0 || node == blockerNode)
            return;

        if(k == 0) {
            System.out.println(node.data);
            return;
        }

        printKLevelsDown(node.left, k - 1, blockerNode);
        printKLevelsDown(node.right, k - 1, blockerNode);
    }

    // Initial value of node : root
    // data : print nodes which are k distance from data
    // k : distance
    public static void printKDistanceFar(Node node, int data, int k) {
        // returns nodes from node(value : data) to root in path
        ArrayList<Node> path = nodeToRootPath(node, data);

        // Traversing path till we get a node which k distance far from data
        for(int i = 0; i < path.size() && i <= k; i++) {
            // node -> node at idx : 0 in path
            // k -> k - idx
            // blockerNode -> idx : 0 -> null , idx : 1 to < path.size() -> node at idx - 1
            printKLevelsDown(path.get(i), k - i, (i == 0) ? null : path.get(i - 1));
        }
    }
}
