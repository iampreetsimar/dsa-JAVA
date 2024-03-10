// 1. Given BinaryTree class, complete size, sum, max and height function. 
// size - expected to find size of BT, i.e., number of nodes in BT.
// max - expected to find max element in BT.
// sum - expected to find total sum of all nodes of BT
// height - expected to find height of the BT in terms of edges

// Input 
// 10, 20, 40, null, null, 50, 80, null, null, null, 30, 60, null, 90, null, null, 70, null, null 

// Output
// 9 -> size
// 90 -> max
// 3 -> height
// 450 -> sum

import java.util.Stack;

public class sizeSumMaxHeight_BT_3 {
    public static void main(String[] args) {
        Integer input[] = { 10,
                            20, 40, null, null, 50, 80, null, null, null,
                            30, 60, null, 90, null, null, 70, null, null };

        BinaryTree bt = new BinaryTree(input);
        System.out.println("Tree root: " + bt.root.data);
        bt.displayTree(bt.root);
        System.out.println("Size : " + bt.size(bt.root));
        System.out.println("Max : " + bt.max(bt.root));
        System.out.println("Sum : " + bt.sum(bt.root));
        System.out.println("Height : " + bt.height(bt.root));
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

    public static class BinaryTree {
        Node root;

        BinaryTree(Integer[] input) {
            constructTree(input);
        }

        void constructTree(Integer[] input) {
            root = new Node(input[0]);
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
        }
        
        void displayTree(Node node) {
            if(node == null)    
            return;

            String res = (node.left == null) ? "." : node.left.data + "";   
            res += " <- " + node.data + " -> "; 
            res += (node.right == null) ? "." : node.right.data + "";  
            System.out.println(res);  

            displayTree(node.left);
            displayTree(node.right);
        }

        // initial value of node : root
        int size(Node node) {
            if(node == null)    // BASE CASE
                return 0;

            return size(node.left) + size(node.right) + 1;
        }

        // initial value of node : root
        int max(Node node) {
            if(node == null)    // BASE CASE -> return -infinity since we're finding max
                return Integer.MIN_VALUE;      // identity of max is -infinity -> max(x,-infinity) = x
            
            return Math.max(node.data, Math.max(max(node.left), max(node.right)));
        }

        // initial value of node : root
        int sum(Node node) {
            if(node == null)    // BASE CASE
                return 0;

            return node.data + sum(node.left) + sum(node.right);
        }

        // initial value of node : root
        int height(Node node) {
            if(node == null)    // BASE CASE
                return -1;  // return -1 for height in terms of edges, 0 for height in terms of nodes

            return 1 + Math.max(height(node.left), height(node.right));
        }
    }
}
