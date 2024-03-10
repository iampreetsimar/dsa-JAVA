// 1. Complete addNodeToBST function. 
// add - expected to add a new node with given data as a leaf in BST and return root(remains same).
// BST property should be taken into account.

public class addNode_BST_3 {
    public static void main(String[] args) {
        int input[] = { 12, 25, 37, 50, 62, 75, 87 };

        Node root = constructBST(input);
        System.out.println("BST root: " + root.data);
        displayBST(root);
        root = addNodeToBST(root, 30);
        root = addNodeToBST(root, 40);
        displayBST(root);
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

    // Similar to find() - we reach null node where new node is to be added at leaf
    public static Node addNodeToBST(Node node, int data) {
        if(node == null) {  // BASE CASE : reached where new node is to be added
            return new Node(data);  // return node with given data
        }

        if(data > node.data) {  // move to right subtree
            node.right = addNodeToBST(node.right, data); // link right subtree to node's right - may contain new node
        } else if(data < node.data) {   // move to left subtree
            node.left = addNodeToBST(node.left, data); // link left subtree to node's left - may contain new node
        }

        return node;    // return node - left/right subtree changed with added new node at leaf
    }
}
