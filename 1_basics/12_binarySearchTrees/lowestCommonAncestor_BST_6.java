// 1. Complete lca function for a given BST. 
// The function is expected to find the lowest common ancestor of d1 and d2 - both present in BST.

public class lowestCommonAncestor_BST_6 {
    public static void main(String[] args) {
        int input[] = { 12, 25, 37, 50, 62, 75, 87 };

        Node root = constructBST(input);
        System.out.println("BST root: " + root.data);
        displayBST(root);
        System.out.println(lca(root, 12, 62));  // lca : 50
        System.out.println(lca(root, 50, 37));  // lca : 50
        System.out.println(lca(root, 62, 87));  // lca : 75
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

    // Valid d1 and d2 - present in BST
    // Initial value of node: root
    // Use BST property: all nodes' data in left subtree < node.data < all nodes' data in right subtree
    public static int lca(Node node, int d1, int d2) {
        if(d1 < node.data && d2 < node.data)    // both d1 and d2 in left subtree  
            return lca(node.left, d1, d2);
        else if(d1 > node.data && d2 > node.data)   // both d1 and d2 in right subtree
            return lca(node.right, d1, d2);
        else    // either one in left subtree and one in right subtree -> current node is merging point -> lca is node 
            return node.data;   // or one is current node itself -> lca is node
    }
}
