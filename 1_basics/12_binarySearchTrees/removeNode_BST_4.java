// 1. Complete removeNodeFromBST function. 
// removeNodeFromBST - expected to remove a node with given data from BST and return new root.
// BST property should be taken into account.

public class removeNode_BST_4 {
    public static void main(String[] args) {
        int input[] = { 12, 25, 37, 50, 62, 75, 87 };

        Node root = constructBST(input);
        System.out.println("BST root: " + root.data);
        displayBST(root);
        root = removeNodeFromBST(root, 50);
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

    public static int max(Node node) {
        if(node.right == null)
            return node.data;
        return max(node.right);
    }
    
    // Initial value of node : root
    // 3 cases arise :
    // 1. Node to be deleted is a leaf node -> directly remove it 
    // 2. Node to be deleted has 1 child(left/right) -> attach that child to that of node's parent
    // 3. Node to be deleted has 2 childs:
    //   - Replace node to be deleted with left subtree's max or right subtree's min(This preserves BST property)
    //   - Delete left subtree's max or right subtree's min whichever was used to replace node to avoid repeat occurence
    public static Node removeNodeFromBST(Node node, int data) {
        if(node == null)    // BASE CASE 
            return null;   // reaches null node when data not present in BST -> return null

        if(data > node.data) {    // traverse right to move to node to be deleted
            node.right = removeNodeFromBST(node.right, data);   // link right subtree with changes to node's right
        }
        else if(data < node.data) {   // traverse left to move to node to be deleted
            node.left = removeNodeFromBST(node.left, data);     // link left subtree with changes to node's left
        }
        else {  // reached node to be deleted
            if(node.left != null && node.right != null) {       // node has 2 childs
                int leftMax = max(node.left);   // get node's left subtree's max
                node.left = removeNodeFromBST(node.left, leftMax);  // remove left subtree's max
                node.data = leftMax;    // replace node's data with that of left subtree's max
                return node;    // return node

            } else if(node.left != null) {      // node has 1 left child
                Node left = node.left;  // attach node's left child to node's parent
                node.left = null;   // needed incase Garbage Collection is not there(in C++)
                return left;    // return child to attach where node was

            } else if(node.right != null) {     // node has 1 right child
                Node right = node.right;    // attach node's right child to node's parent
                node.right = null;  // needed incase Garbage Collection is not there(in C++)
                return right;   // return child to attach where node was

            } else {        // node has 0 child - is a leaf
                return null;    // return null -> null is attached to node's parent where node was
            }
        }

        return node; // need to return node even if no change on node level, there may be a change in node's subtree
    }
}
