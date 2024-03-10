// 1. Complete replaceWithSumOfLarger function for a given BST. 
// The function is expected to replace a node's value with sum of all nodes greater than it.

public class replaceSumOfLarger_BST_5 {
    public static void main(String[] args) {
        int input[] = { 12, 25, 37, 50, 62, 75, 87 };

        Node root = constructBST(input);
        System.out.println("BST root: " + root.data);
        displayBST(root);
        replaceWithSumOfLarger(root);
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

    static int sumOfLarger = 0;     // static variable -> stores sum of nodes

    // Initial value of node: root
    public static void replaceWithSumOfLarger(Node node) {
        if(node == null)    // BASE CASE
            return; // null node is reached -> just return

        // inorder traversal - Left Node Right -> traverses in ascending order of nodes
        // using reverse inorder traversal - Right Node Left -> traverses in descending order of nodes
        replaceWithSumOfLarger(node.right);

        int OGValueOfNode = node.data;  // store current node's data
        node.data = sumOfLarger; // replace node's data with sum(contains sum of nodes which are larger than node)
        sumOfLarger += OGValueOfNode;   // add current node' data to sum

        replaceWithSumOfLarger(node.left);
    }
}
