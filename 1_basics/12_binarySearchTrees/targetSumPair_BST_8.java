// 1. Complete targetSumPair function for a given BST. 
// Given a target value, the function is expected to print the pair of nodes which add up to the given target value.
// All pairs should print the smaller value first and avoid duplicacies.
// All pairs should print in increasing order.

public class targetSumPair_BST_8 {
    public static void main(String[] args) {
        int input[] = { 12, 25, 30, 37, 40, 50, 60, 62, 70, 75, 87 };

        Node root = constructBST(input);
        System.out.println("BST root: " + root.data);
        displayBST(root);
        targetSumPair(root, root, 100);  // pairs: 25 75; 30 70; 40 60
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static void displayBST(Node node) {
        if(node == null)
            return;

        String res = (node.left == null) ? "." : node.left.data + "";
        res += " <- " + node.data + " -> ";
        res += (node.right == null) ? "." : node.right.data + "";
        System.out.println(res);

        displayBST(node.left);
        displayBST(node.right);
    }

    public static Node constructBST(int[] input) {
        return constructBST(input, 0, input.length - 1);
    }

    public static Node constructBST(int[] input, int low, int high) {
        if(low > high)   
            return null;   

        int mid = (low + high) / 2;
        Node node = new Node(input[mid]); 
        node.left = constructBST(input, low, mid - 1);
        node.right = constructBST(input, mid + 1, high);
        return node;   
    }

    public static boolean find(Node node, int data) {
        if(node == null)
            return false;

        if(data > node.data)
            return find(node.right, data);
        else if(data < node.data)
            return find(node.left, data);
        else
            return true;
    }

    // Initial value of node: root -> will change value in recursive call
    // root -> will remain same to find compliment of node's data in BST
    // Printing in Increasing order -> INORDER traversal
    // TC : O(N*h) => O(NlogN) where h is height of BST
    public static void targetSumPair(Node root, Node node, int target) {
        if(node == null)    // BASE CASE : null node reached -> just return
            return;

        targetSumPair(root, node.left, target); // print target sum pairs from left subtree

        int currentNodeCompliment = target - node.data; // get compliment of node.data

        // to print pairs where 1st item < 2nd item -> compliment > node'data
        // to find compliment in BST -> find(root, compliment) -> O(logN)
        if(currentNodeCompliment > node.data && find(root, currentNodeCompliment)) {
            System.out.println(node.data + " " + currentNodeCompliment);
        }

        targetSumPair(root, node.right, target);    // print target sum pairs from right subtree
    }
}
