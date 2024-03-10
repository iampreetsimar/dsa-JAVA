// 1. Complete printInRange function for a given BST. 
// The function is expected to print all nodes b/w d1 and d2(both inclusive) in increasing order.

public class printInRange_BST_7 {
    public static void main(String[] args) {
        int input[] = { 12, 25, 30, 37, 40, 50, 60, 62, 70, 75, 87 };

        Node root = constructBST(input);
        System.out.println("BST root: " + root.data);
        displayBST(root);
        printInRange(root, 12, 65);
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

    // Since printing needed in increasing order, we'll use INORDER traversal
    // Use BST property: all nodes' data in left subtree < node.data < all nodes' data in right subtree
    // Initial value of node: root
    public static void printInRange(Node node, int d1, int d2) {
        if(node == null)    // BASE CASE
            return;     // null node reached -> just return

        if(d1 < node.data && d2 < node.data)    // current node is greater than range -> only traverse left subtree
            printInRange(node.left, d1, d2);
        else if(d1 > node.data && d2 > node.data) // current node is smaller than range -> only traverse right subtree
            printInRange(node.right, d1, d2);
        else {      // current node in range -> tranverse in INORDER 
            printInRange(node.left, d1, d2);    // left subtree will print nodes from left in inorder
            System.out.println(node.data);      // print node in INORDER
            printInRange(node.right, d1, d2);   // right subtree will print nodes from right in inorder
        }
    }
}
