// ALTERNATIVE APPROACHES of TargetSumPair

import java.util.ArrayList;
import java.util.Stack;

public class targetSumPair_Alternatives_BST_9 {
    public static void main(String[] args) {
        int input[] = { 12, 25, 30, 37, 40, 50, 60, 62, 70, 75, 87 };

        Node root = constructBST(input);
        System.out.println("BST root: " + root.data);
        displayBST(root);

        // SIZE, SUM, MAX, MIN, FIND
        // System.out.println("BST Size: " + size(root));
        // System.out.println("BST Sum: " + sum(root));
        // System.out.println("BST Max: " + max(root));
        // System.out.println("BST Min: " + min(root));
        // System.out.println("BST Find 74: " + find(root, 74));
        // System.out.println("BST Find 37: " + find(root, 37));

        // ADD NODE
        // root = addNode(root, 49);
        // displayBST(root);

        // REMOVE NODE
        // root = removeNode(root, 30);
        // displayBST(root);

        // REPLACE WITH SUM OF LARGER
        // rwsol(root);
        // displayBST(root);

        // LOWEST COMMON ANCESTOR
        // System.out.println(lca(root, 12, 62));  // lca : 50
        // System.out.println(lca(root, 50, 37));  // lca : 50
        // System.out.println(lca(root, 62, 87));  // lca : 75

        // PRINT IN RANGE
        // printInRange(root, 12, 65);

        // TARGET SUM PAIR
        targetSumPair(root, root, 100);  // pairs: 25 75; 30 70; 40 60
        targetSumPairUsingArrayList(root, 100);  // pairs: 25 75; 30 70; 40 60
        iterativeTargetSumPair(root, 100);  // pairs: 25 75; 30 70; 40 60
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    // input is sorted
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

    public static int sum(Node node) {
        if(node == null)
            return 0;
        return sum(node.left) + sum(node.right) + node.data;
    }

    public static int size(Node node) { 
        if(node == null)
            return 0;
        return size(node.left) + size(node.right) + 1;
    }

    public static int min(Node node) {
        if(node.left == null)
            return node.data;
        return min(node.left);
    }

    public static int max(Node node) {
        if(node.right == null)
            return node.data;
        return max(node.right);
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

    public static Node addNode(Node node, int data) {
        if(node == null)
            return new Node(data);
        if(data < node.data)
            node.left = addNode(node.left, data);
        else if(data > node.data)
            node.right = addNode(node.right, data);
        return node;
    }

    public static Node removeNode(Node node, int data) {
        if(node == null)
            return null;

        if(data > node.data)
            node.right = removeNode(node.right, data);
        else if(data < node.data)
            node.left = removeNode(node.left, data);
        else {
            if(node.left != null && node.right != null) {
                int leftMax = max(node.left);
                node.left = removeNode(node.left, leftMax);
                node.data = leftMax;
                return node;
            } else if(node.left != null) {
                Node left = node.left;
                node.left = null;
                return left;
            } else if(node.right != null) {
                Node right = node.right;
                node.right = null;
                return right;
            } else {
                return null;
            }
        }
        return node;
    }

    static int sumOfLarger = 0;
    public static void rwsol(Node node) {
        if(node == null)
            return;
        rwsol(node.right);
        int OGVal = node.data;
        node.data = sumOfLarger;
        sumOfLarger += OGVal;
        rwsol(node.left);
    }

    public static int lca(Node node, int d1, int d2) {
        if(d1 < node.data && d2 < node.data)
            return lca(node.left, d1, d2);
        else if(d1 > node.data && d2 > node.data)
            return lca(node.right, d1, d2);
        else
            return node.data;
    }

    public static void printInRange(Node node, int d1, int d2) {
        if(node == null)
            return;

        if(d1 < node.data && d2 < node.data)
            printInRange(node.left, d1, d2);
        else if(d1 > node.data && d2 > node.data)
            printInRange(node.right, d1, d2);
        else {
            printInRange(node.left, d1, d2);
            System.out.println(node.data);
            printInRange(node.right, d1, d2);
        }
    }
    
    public static void targetSumPair(Node root, Node node, int target) {
        if(node == null)
            return;

        targetSumPair(root, node.left, target);
        int compliment = target - node.data;
        if(compliment > node.data && find(root, compliment))
            System.out.println(node.data + " " + compliment);
        targetSumPair(root, node.right, target);
    }

    // TC: O(N)
    public static void inorderTraversal(Node node, ArrayList<Integer> inorderBSTList) {
        if(node == null)
            return;
        inorderTraversal(node.left, inorderBSTList);
        inorderBSTList.add(node.data);
        inorderTraversal(node.right, inorderBSTList);
    }

    // SC: O(N) for arraylist, TC: O(N)
    public static void targetSumPairUsingArrayList(Node root, int target) {
        ArrayList<Integer> inorderBSTList = new ArrayList<>();
        inorderTraversal(root, inorderBSTList); // traverses BST and adds nodes to AL in inorder(increasing order)
        int low = 0;  
        int high = inorderBSTList.size() - 1;
        while(low < high) {   // Traversing from front and back of arrayList -> TC: O(N)
            int leftItem = inorderBSTList.get(low);
            int rightItem = inorderBSTList.get(high);
            if(leftItem + rightItem < target)   // pair sum < target -> move front pointer forward
                low++;
            else if(leftItem + rightItem > target)  // pair sum > target -> move back pointer backward
                high--;
            else {  // pair sum == target -> print pair -> move both pointers to next value
                System.out.println(leftItem + " " + rightItem);
                low++;
                high--;
            }
        }
    }

    public static class ITPair {
        Node node;
        int state;

        ITPair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    // Use Combination of ArrayList Approach with ITERATIVE INORDER TRAVERSAL with PAIR<Node, State>
    // NORMAL INORDER -> Left Node Right -> nodes in increasing order
    // REVERSE INORDER -> Right Node Left -> nodes in decreasing order
    // TC: O(N), SC: O(H)
    public static void iterativeTargetSumPair(Node root, int target) {
        Stack<ITPair> ls = new Stack<>();
        Stack<ITPair> rs = new Stack<>();
        ls.add(new ITPair(root, 0));
        rs.add(new ITPair(root, 0));
       
        Node left = getNextFromIterativeNormalInorder(ls);
        Node right = getNextFromIterativeReverseInorder(rs);

        while(left.data < right.data) {  // TC: O(N)
            if(left.data + right.data < target)
                left = getNextFromIterativeNormalInorder(ls);
            else if(left.data + right.data > target)
                right = getNextFromIterativeReverseInorder(rs);
            else {
                System.out.println(left.data + " " + right.data);
                left = getNextFromIterativeNormalInorder(ls);
                right = getNextFromIterativeReverseInorder(rs);
            }
        }
    }

    // returns node in NORMAL INORDER ITERATIVE TRAVERSAL -> imitates recursive stack
    // state 0: preorder -> move to left child
    // state 1: inorder -> move to right child -> return inorder node
    // state 3: remove from stack
    // Max stack size will be height of BST, SC:O(H) - height
    public static Node getNextFromIterativeNormalInorder(Stack<ITPair> ls) {
        while(ls.size() > 0) {
            ITPair top = ls.peek();
            if(top.state == 0) {
                if(top.node.left != null) {
                    ls.add(new ITPair(top.node.left, 0));
                }
                top.state++;
            } else if(top.state == 1) {
                if(top.node.right != null) {
                    ls.add(new ITPair(top.node.right, 0));
                }
                top.state++;
                return top.node;
            } else {
                ls.pop();
            }
        }
        return null;
    }

    // returns node in REVERSE INORDER TRAVERSAL -> imitates recursive stack 
    // state 0: preorder -> move to right child
    // state 1: inorder -> move to left child -> return inorder node
    // state 3: remove from stack
    // Max stack size will be height of BST, SC:O(H) - height
    public static Node getNextFromIterativeReverseInorder(Stack<ITPair> rs) {
        while(rs.size() > 0) {
            ITPair top = rs.peek();
            if(top.state == 0) {
                if(top.node.right != null) {
                    rs.add(new ITPair(top.node.right, 0));
                }
                top.state++;
            } else if(top.state == 1) {
                if(top.node.left != null) {
                    rs.add(new ITPair(top.node.left, 0));
                }
                top.state++;
                return top.node;
            } else {
                rs.pop();
            }
        }
        return null;
    }
}
