// Complete constructBST function.
// The function is expected to contruct a BST using given sorted input array.

public class constructor_BST_1 {
    public static void main(String[] args) {
        int input[] = { 12, 25, 37, 50, 62, 75, 87 };

        Node root = constructBST(input);
        System.out.println("BST root: " + root.data);
        displayBST(root);
    }

    // structure same as Binary Tree
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    // same as Binary Tree
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

    // Use BINARY SEARCH ALGORITHM - input is sorted
    public static Node constructBST(int[] input, int low, int high) {
        if(low > high)      // BASE CASE
            return null;    // all input elements read in range -> return null node

        int mid = (low + high) / 2;

        Node node = new Node(input[mid]);   // element at mid becomes node

        // returns left subtree and links to node's left
        node.left = constructBST(input, low, mid - 1);

        // return right subtree and links to node's right
        node.right = constructBST(input, mid + 1, high);

        return node;    // node is created and its subtrees are linked to node -> return node to parent
    }
}
