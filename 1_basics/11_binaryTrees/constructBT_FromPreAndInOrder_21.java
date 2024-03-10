// Complete constructTree function.
// The function is expected to contruct a binary tree using given valid inputs in preorder and inorder.

public class constructBT_FromPreAndInOrder_21 {
    public static void main(String[] args) {
        Integer preorderInput[] = { 0, 1, 3, 7, 8, 4, 9, 10, 2, 5, 11, 6};
        Integer inorderInput[] = { 7, 3, 8, 1, 9, 4, 10, 0, 11, 5, 2, 6};

        Node root = constructTree(preorderInput, inorderInput, 0, preorderInput.length - 1, 
                                                                 0, inorderInput.length - 1);
        System.out.println("Tree root: " + root.data);
        displayTree(root);
        preorderTraversal(root); System.out.println(" : preorder");
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

    public static void preorderTraversal(Node node) {
        if(node == null)
            return;

        System.out.print(node.data + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    public static void inorderTraversal(Node node) {
        if(node == null)
            return;

        inorderTraversal(node.left);
        System.out.print(node.data + " ");
        inorderTraversal(node.right);
    }

    // preStartIdx - starting idx of preorder input array
    // preEndIdx - ending idx of preorder input array
    // inStartIdx - starting idx of inorder input array
    // inEndIdx - ending idx of inorder input array
    public static Node constructTree(Integer[] preorderInput, Integer[] inorderInput, int preStartIdx, 
                                                          int preEndIdx, int inStartIdx, int inEndIdx) {
        if(preStartIdx > preEndIdx || inStartIdx > inEndIdx)  // BASE CASE - out of bounds
            return null;        // return null node                                   
                                                    
        Node node = new Node(preorderInput[preStartIdx]);   // starting idx of pre is used to create node

        // find val of node created above in inorder input to get count of nodes in left subtree
        int idx = inStartIdx; // inorder input starting point
        while(node.data != inorderInput[idx]) { idx++; }     // loop till idx of node is found in irorder

        int nodeCountInLeft = idx - inStartIdx; // number of nodes before node in inorder -> node is not included

        // left node subtree returned from left recursive call links to left child of node
        // Left Subtree Call Range - 
        // preorder starting point: idx 1 next to current starting point -> preStartIdx + 1
        // preorder ending point: current starting point + count of nodes in left
        // inorder starting point: same as current starting point
        // inorder ending point: idx 1 before current idx where node found in inorder array
        node.left = constructTree(preorderInput, inorderInput, preStartIdx + 1, preStartIdx + nodeCountInLeft, 
                                 inStartIdx, idx - 1);
    
        // right node subtree returned from right recursive call links to right child of node
        // Right Subtree Call Range - 
        // preorder starting point: current starting point + count of nodes in left + 1
        // preorder ending point: same as current ending point
        // inorder starting point: idx 1 after current idx where node found in inorder array
        // inorder ending point: same as current ending point
        node.right = constructTree(preorderInput, inorderInput, preStartIdx + nodeCountInLeft + 1, 
                                  preEndIdx, idx + 1, inEndIdx);

        return node;    // node created and child subtrees are linked -> return node
    }
}
