// Complete constructTree function.
// The function is expected to contruct a binary tree using given valid inputs in postorder and inorder.

public class constructBT_FromPostAndInOrder_22 {
    public static void main(String[] args) {
        int postorderInput[] = { 7, 8, 3, 9, 10, 4, 1, 11, 5, 6, 2, 0 };
        int inorderInput[] = { 7, 3, 8, 1, 9, 4, 10, 0, 11, 5, 2, 6 };

        Node root = constructTree(postorderInput, inorderInput, 0, postorderInput.length - 1, 
                                                                 0, inorderInput.length - 1);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        postorderTraversal(root); System.out.println(" : postorder");
        inorderTraversal(root); System.out.println(" : inorder");
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
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

    public static void postorderTraversal(Node node) {
        if(node == null)
            return;

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.data + " ");
    }

    public static void inorderTraversal(Node node) {
        if(node == null)
            return;

        inorderTraversal(node.left);
        System.out.print(node.data + " ");
        inorderTraversal(node.right);
    }

    // postStartIdx - starting idx of postorder input array
    // postEndIdx - ending idx of postorder input array
    // inStartIdx - starting idx of inorder input array
    // inEndIdx - ending idx of inorder input array
    public static Node constructTree(int[] postorderInput, int[] inorderInput, int postStartIdx, int postEndIdx, 
                                     int inStartIdx, int inEndIdx) {    
        if(postStartIdx > postEndIdx || inStartIdx > inEndIdx)  // BASE CASE - out of bounds
            return null;        // return null node 
            
        Node node = new Node(postorderInput[postEndIdx]);   // ending idx of post is used to create node

        // find val of node created above in inorder input to get count of nodes in left subtree
        int idx = inStartIdx;   // inorder input starting point
        while(node.data != inorderInput[idx]) { idx++; }    // loop till idx of node is found in irorder
        int nodeCountInLeft = idx - inStartIdx;  // number of nodes before node in inorder -> node is not included

        // left node subtree returned from left recursive call links to left child of node
        // Left Subtree Call Range - 
        // postorder starting point: same as current starting point
        // postorder ending point: current starting point + count of nodes in left subtree - 1 -> gives count nodes
        // inorder starting point: same as current starting point
        // inorder ending point: idx 1 before current idx where node found in inorder array
        node.left = constructTree(postorderInput, inorderInput, postStartIdx, postStartIdx + nodeCountInLeft - 1, 
                                  inStartIdx, idx - 1);

        // right node subtree returned from right recursive call links to right child of node
        // Right Subtree Call Range - 
        // postorder starting point: current starting point + count of nodes in left
        // postorder ending point: idx 1 before current ending idx as last idx used to create node -> postEndIdx - 1
        // inorder starting point: idx 1 after current idx where node found in inorder array
        // inorder ending point: same as current ending point                          
        node.right = constructTree(postorderInput, inorderInput, postStartIdx + nodeCountInLeft, postEndIdx - 1, 
                                   idx + 1, inEndIdx);

        return node;    // node created and child subtrees are linked -> return node
    }
}
