// 1. Given GenericTree class, complete maxSubtreeSum function. 
// The function is expected to find node and its subtree sum for which the subtree sum is maximum in the tree.
// Subtree sum includes sum of descendents plus node itself
// Use "Travel and Change" strategy.

// Input 
// 10, 20, -50, null, -60, null, null, 30, -70, null, 80, -110, null, 120, null, null, 90, null, null,
// 40, -100, null, null, null -> tree input

// Output
// 30@140 -> 30 is node and 140 is subtreeSum(max) for node 30

import java.util.ArrayList;
import java.util.Stack;

public class nodeWithMaxSubtreeSum_25 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, -50, null, -60, null, null,
                            30, -70, null, 80, -110, null, 120, null, null, 90, null, null,
                            40, -100, null, null,
                            null }; 

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        tree.returnSubtreeSumAndFindMaxSubtreeSum(tree.root);
        System.out.println(tree.maxSubtreeSumNode + " @ " + tree.maxSubtreeSum); 
    }

    public static class Node {
        int data;
        ArrayList<Node> children;

        Node() {
            children = new ArrayList<>();
        }

        Node(int val) {
            this();
            data = val;
        }
    }

    public static class GenericTree {
        Node root;
        int maxSubtreeSum;
        int maxSubtreeSumNode;

        GenericTree() {
            root = null;
            maxSubtreeSum = Integer.MIN_VALUE;
            maxSubtreeSumNode = 0;
        }

        GenericTree(Integer[] input) {
            this();
            constructTree(input);
        }

        void constructTree(Integer[] input) {
            Stack<Node> s = new Stack<>();
            for(int i = 0; i < input.length; i++) {
                if(input[i] == null) {
                    s.pop();
                } else {
                    Node node = new Node(input[i]);
                    if(s.size() > 0) {
                        s.peek().children.add(node);
                    } else {
                        root = node;
                    }
                    s.push(node);
                }
            }
        }

        void display(Node node) {
            System.out.print(node.data + " -> ");
            for(Node child : node.children) {
                System.out.print(child.data + " ");
            }
            System.out.println(".");

            for(Node child : node.children) {
                display(child);
            }
        }

        // Use Travel and Change Approach for maxSubtreeSum & maxSubtreeSumNode
        // Initial node value : tree.root
        // Initial value of maxSubtreeSum : Integer.MIN_VALUE(since we are finding max)
        // Initial value of maxSubtreeSumNode : 0
        // need to add returntype int to get sum of node subtree
        int returnSubtreeSumAndFindMaxSubtreeSum(Node node) {
            // initial sum value -> subtree includes node as well
            int sum = node.data;

            // recursive call to node's children
            for(Node child : node.children) {
                // add up sum from children
                sum += returnSubtreeSumAndFindMaxSubtreeSum(child);
            }

            // if added sum > maxSubtreeSum -> update maxSubtreeSum and maxSubtreeSumNode
            if(sum > maxSubtreeSum) {
                maxSubtreeSum = sum;
                maxSubtreeSumNode = node.data;
            }

            // return added sum to node's parent
            return sum;
        }
    }
}
