// 1. Complete size, sum, max and height function. 
// size - expected to find size of BST, i.e., number of nodes in BST.
// sum - expected to find total sum of all nodes of BST
// max - expected to find max element in BST.
// min - expected to find max element in BST.

public class sizeSumMaxMin_BST_2 {
    public static void main(String[] args) {
        int input[] = { 12, 25, 37, 50, 62, 75, 87 };

        Node root = constructBST(input);
        System.out.println("BST root: " + root.data);
        displayBST(root);
        System.out.println("BST Size: " + size(root));
        System.out.println("BST Sum: " + sum(root));
        System.out.println("BST Max: " + max(root));
        System.out.println("BST Min: " + min(root));
        System.out.println("BST Find 74: " + find(root, 74));
        System.out.println("BST Find 37: " + find(root, 37));
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

    // STRUCTURE DEPENDENT - same approach in Binary Tree
    public static int size(Node node) {
        if(node == null)
            return 0;
        return size(node.left) + size(node.right) + 1;
    }

    // STRUCTURE DEPENDENT - same approach in Binary Tree
    public static int sum(Node node) {
        if(node == null)
            return 0;
        return sum(node.left) + sum(node.right) + node.data;
    }

    // DATA DEPENDENT - optimal approach in BST - O(logN)
    // max element in rightmost node of BST
    public static int max(Node node) {
        if(node.right == null)  // BASE CASE
            return node.data;   // return rightmost node
        
        return max(node.right); // recursive call to only right child
    }

    // DATA DEPENDENT - optimal approach in BST - O(logN)
    // min element in leftmost node of BST
    public static int min(Node node) {
        if(node.left == null)   // BASE CASE
            return node.data;   // return leftmost node
        
        return min(node.left); // recursive call to only left child
    }

    // DATA DEPENDENT - optimal approach in BST ~ O(logN)
    // goes straight to data node - only 1 comparison at each level of BST -> total: (1 + logN) levels
    public static boolean find(Node node, int data) {
        if(node == null)    // BASE CASE
            return false;   // null node -> data not present -> return false

        if(data > node.data)    // data in right subtree -> right recursive call
            return find(node.right, data);  
        else if(data < node.data)   // data in left subtree -> left recursive call
            return find(node.left, data);
        else    // data found at current node -> return true
            return true;
    }
}
