// 1. Given GenericTree class, complete predecessorAndSuccessor function. 
// The function is expected to find the preorder predecessor and successor of a given valid element.
// Use "Travel and Change" strategy.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null -> tree input
// 120 -> element

// Output
// 110 -> preorder predecessor
// 90 -> preorder successor

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class predecessorSuccessorOfElement_22 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };   

        GenericTree tree = new GenericTree(input);

        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root); 
        System.out.println("Size : " + tree.size(tree.root));
        System.out.println("Max : " + tree.max(tree.root));
        System.out.println("Min : " + tree.min(tree.root));
        System.out.println("Height : " + tree.height(tree.root));

        // Level Order 
        // tree.levelOrder(tree.root);
        // tree.levelOrderLineWise(tree.root);
        // tree.levelOrderLineWiseDelimiterApproach(tree.root);
        // tree.levelOrderLineWiseCountApproach(tree.root);
        // tree.levelOrderLineWisePairApproach(tree.root);
        // tree.levelOrderLineWiseZZ(tree.root);
        
        // Find
        // System.out.println(tree.find(tree.root, 120));
        // System.out.println(tree.find(tree.root, 95));

        // mirror
        // tree.display(tree.root);    // Tree before mirror
        // tree.mirror(tree.root);
        // tree.display(tree.root);    // Tree after mirror

        // remove Leaves
        // tree.display(tree.root);    // Before removeLeaves
        // tree.removeLeaves(tree.root);
        // tree.display(tree.root);    // After removeLeaves

        // linearize
        // tree.display(tree.root);    // Before linearize
        // tree.linearize(tree.root);
        // // tree.linearizeEfficient(tree.root);
        // tree.display(tree.root);    // After linearize

        // Node to root path
        // int data = 120;
        // ArrayList<Integer> nodeToRootPath = tree.nodeToRootPath(tree.root, data);
        // System.out.println("Node to root path for data " + data +  " -- " + nodeToRootPath);

        // lowest common ancestor
        // int data1 = 120;
        // int data2 = 80;
        // int lca = tree.lca(tree.root, data1, data2);
        // System.out.println("Lowest Common Ancestor of " + data1 + " and " + data2 + " - " + lca);

        // distance b/w nodes in terms of edges
        // int d1 = 50;
        // int d2 = 120;
        // int distance = tree.distanceBetweenNodes(tree.root, d1, d2);
        // System.out.println("Distance b/w " + d1 + " and " + d2 + " - " + distance);

        // Are Trees Similar
        // Integer input1[] = { 10, 
        //                     20, 50, null, 60, null, null,
        //                     30, 70, null, 80, 110, null, 120, null, 130, null, null, 90, null, null,
        //                     40, 100, null, null,
        //                     null };
        // Integer input2[] = { 100, 
        //                     200, 500, null, 600, null, null,
        //                     300, 700, null, 800, 1100, null, 1200, null, null, 900, null, null,
        //                     400, 1000, null, null,
        //                     null };
        // GenericTree tree1 = new GenericTree(input1);
        // GenericTree tree2 = new GenericTree(input2);
        // System.out.println("Tree 1 root : " + tree1.root.data);
        // tree1.display(tree1.root); 
        // System.out.println("Tree 2 root : " + tree2.root.data);
        // tree2.display(tree2.root); 
        // // return true if tree1 and tree2 are similar in shape
        // System.out.println(tree1.areTreesSimilar(tree1.root, tree2.root));

        // Are Trees Mirror
        // Integer input1[] = { 10, 
        //                     20, 50, null, null,
        //                     30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
        //                     40, 100, null, 130, null, null,
        //                     null };
        // Integer input2[] = { 100, 
        //                     200, 500, null, 600, null, null,
        //                     300, 700, null, 800, 1100, null, 1200, null, null, 900, null, null,
        //                     400, 1000, null, null,
        //                     null };
        // GenericTree tree1 = new GenericTree(input1);
        // GenericTree tree2 = new GenericTree(input2);
        // System.out.println("Tree 1 root : " + tree1.root.data);
        // tree1.display(tree1.root); 
        // System.out.println("Tree 2 root : " + tree2.root.data);
        // tree2.display(tree2.root); 
        // // return true if tree1 and tree2 are mirrored in shape
        // System.out.println(tree1.areTreesMirror(tree1.root, tree2.root));

        // Is Tree Symmetric
        // Integer input[] = { 10, 
        //     20, 50, null, 60, null, null,
        //     30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
        //     40, 100, null, null,
        //     null };     // Non Symmetric input
        // Integer input[] = { 10, 
        //                     20, 50, null, 60, null, null,
        //                     30, 70, null, 80, null, 90, null, null,
        //                     40, 100, null, 110, null, null,
        //                     null };     // Symmetric input
        // GenericTree tree = new GenericTree(input);
        // System.out.println("Tree root : " + tree.root.data);
        // tree.display(tree.root); 
        // return true if tree is symmetric
        // System.out.println(tree.isTreeSymmetric(tree.root));
        // System.out.println(tree.isTreeSymmetricEfficient(tree.root, tree.root));

        // Multisolver
        // tree.multisolver(tree.root, 0);
        // System.out.println("Size using multisolver : " + tree.size);
        // System.out.println("Max using multisolver : " + tree.max);
        // System.out.println("Min using multisolver : " + tree.min);
        // System.out.println("Height using multisolver : " + tree.height);

        // Predecessor and Successor
        int data = 110;
        tree.predecessorAndSuccessor(tree.root, data);
        if(tree.predecessor == null) {
            System.out.println("Predecessor not found!!");
        } else {
            System.out.println("Predecessor of " + data + " : " + tree.predecessor.data);
        }

        if(tree.successor == null) {
            System.out.println("Successor not found!!");
        } else {
            System.out.println("Successor of " + data + " : " + tree.successor.data);
        }
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

        // For Multisolver Start
        int size;
        int min;
        int max;
        int height;
        // For Multisolver End

        // For PredecessorAndSuccessor Start
        Node predecessor;
        Node successor;
        int state;
        // For PredecessorAndSuccessor End

        GenericTree() {
            root = null;

            // For Multisolver Start
            size = 0;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            height = 0;
            // For Multisolver End

            // For PredecessorAndSuccessor Start
            predecessor = null;
            successor = null;
            state = 0;
            // For PredecessorAndSuccessor End
        }

        GenericTree(Integer[] input) {
            this();
            this.constructTree(input);
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

        int size(Node node) {
            int count = 0;
            for(Node child : node.children) {
                count += size(child);
            }
            return count + 1;
        }

        int max(Node node) {
            int max = node.data;
            for(Node child : node.children) {
                max = Math.max(max, max(child));
            }
            return max;
        }

        int height(Node node) {
            int height = -1;
            for(Node child : node.children) {
                height = Math.max(height, height(child));
            }
            return height + 1;
        }

        int min(Node node ){
            int min = node.data;
            for(Node child : node.children) {
                min = Math.min(min, min(child));
            }
            return min;
        }

        void levelOrder(Node node) {
            Queue<Node> q = new ArrayDeque<>();
            q.add(node);
            while(q.size() > 0) {
                Node f = q.remove();
                System.out.print(f.data + " ");

                for(Node child : f.children) {
                    q.add(child);
                }
            }
            System.out.println(".");
        }

        void levelOrderLineWise(Node node) {
            Queue<Node> mainQ = new ArrayDeque<>();
            Queue<Node> childQ = new ArrayDeque<>();
            mainQ.add(node);

            while(mainQ.size() > 0) {
                Node f = mainQ.remove();
                System.out.print(f.data + " ");

                for(Node child : f.children) {
                    childQ.add(child);
                }

                if(mainQ.size() == 0) {
                    mainQ = childQ;
                    childQ = new ArrayDeque<>();
                    System.out.println(".");
                }
            }
        }

        void levelOrderLineWiseDelimiterApproach(Node node) {
            Queue<Node> q = new ArrayDeque<>();
            q.add(node);
            q.add(new Node(-1));

            while(q.size() > 0) {
                Node f = q.remove();
                if(f.data == -1) {
                    System.out.println(".");
                    if(q.size() > 0) {
                        q.add(f);
                    }
                } else {
                    System.out.print(f.data + " ");
                    for(Node child : f.children) {
                        q.add(child);
                    }
                }
            }
        }

        void levelOrderLineWiseCountApproach(Node node) {
            Queue<Node> q = new ArrayDeque<>();
            q.add(node);

            while(q.size() > 0) {
                int nodesOnCurrLevel = q.size();

                for(int i = 0; i < nodesOnCurrLevel; i++) {
                    Node f = q.remove();
                    System.out.print(f.data + " ");
                    for(Node child : f.children) {
                        q.add(child);
                    }
                }
                System.out.println(".");
            }
        }

        private class Pair {
            Node node;
            int level;

            Pair(Node node, int level) {
                this.node = node;
                this.level = level;
            }
        }

        void levelOrderLineWisePairApproach(Node node) {
            Queue<Pair> q = new ArrayDeque<>();
            q.add(new Pair(node, 0));
            int level = 0;
            while(q.size() > 0) {
                Pair p = q.remove();
                if(p.level > level) {
                    level = p.level;
                    System.out.println(".");
                }

                System.out.print(p.node.data + " ");
                for(Node child : p.node.children) {
                    q.add(new Pair(child, p.level + 1));
                }
            }
            System.out.println(".");
        }

        void levelOrderLineWiseZZ(Node node) {
            Stack<Node> mainS = new Stack<>();
            Stack<Node> childS = new Stack<>();
            mainS.push(node);
            int level = 0;

            while(mainS.size() > 0) {
                Node top = mainS.pop();
                System.out.print(top.data + " ");
                if(level % 2 == 0) {
                    for(Node child : top.children) {
                        childS.push(child);
                    }
                } else {
                    for(int i = top.children.size() - 1; i >= 0; i--) {
                        Node child = top.children.get(i);
                        childS.push(child);
                    }
                }

                if(mainS.size() == 0) {
                    mainS = childS;
                    childS = new Stack<>();
                    level++;
                    System.out.println(".");
                }
            }
        }

        boolean find(Node node, int data) {
            if(data == node.data)
                return true;

            for(Node child : node.children) {
                if(find(child, data))
                    return true;
            }
            return false;
        }

        void mirror(Node node) {
            for(Node child : node.children) {
                mirror(child);
            }

            Queue<Node> q = new ArrayDeque<>();
            for(int i = node.children.size() - 1; i >= 0; i--) {
                q.add(node.children.remove(i));
            }

            while(q.size() > 0) {
                node.children.add(q.remove());
            }
        }

        void removeLeaves(Node node) {
            for(int i = node.children.size() - 1; i >= 0; i--) {
                Node child = node.children.get(i);
                if(child.children.size() == 0) {
                    node.children.remove(child);
                }
            }

            for(Node child : node.children) {
                removeLeaves(child);
            }
        }

        private Node getTailNode(Node node) {
            while(node.children.size() > 0) {
                node = node.children.get(0);
            }
            return node;
        }

        void linearize(Node node) {
            for(Node child : node.children) {
                linearize(child);
            }

            while(node.children.size() > 1) {
                Node lastChild = node.children.remove(node.children.size() - 1);
                Node slChild = node.children.get(node.children.size() - 1);
                Node slChildTail = getTailNode(slChild);
                slChildTail.children.add(lastChild);
            }
        }

        Node linearizeEfficient(Node node) {
            if(node.children.size() == 0)
                return node;

            Node lastChildTail = linearizeEfficient(node.children.get(node.children.size() - 1));
            while(node.children.size() > 1) {
                Node lastChild = node.children.remove(node.children.size() - 1);
                Node slChildTail = linearizeEfficient(node.children.get(node.children.size() - 1));
                slChildTail.children.add(lastChild);
            }
            return lastChildTail;
        }

        ArrayList<Integer> nodeToRootPath(Node node, int data) {
            if(data == node.data) {
                ArrayList<Integer> bl = new ArrayList<>();
                bl.add(node.data);
                return bl;
            }

            for(Node child : node.children) {
                ArrayList<Integer> path = nodeToRootPath(child, data);
                if(path.size() > 0) {
                    path.add(node.data);
                    return path;
                }
            }
            return new ArrayList<Integer>();
        }

        int lca(Node node, int d1, int d2) {
            ArrayList<Integer> p1 = nodeToRootPath(node, d1);
            ArrayList<Integer> p2 = nodeToRootPath(node, d2);

            int i = p1.size() - 1;
            int j = p2.size() - 1;

            while(i >= 0 && j >= 0 && p1.get(i) == p2.get(j)) {
                i--;
                j--;
            }

            i++;
            j++;

            return p1.get(i);
        }

        int distanceBetweenNodes(Node node, int d1, int d2) {
            ArrayList<Integer> p1 = nodeToRootPath(node, d1);
            ArrayList<Integer> p2 = nodeToRootPath(node, d2);

            int i = p1.size() - 1;
            int j = p2.size() - 1;

            while(i >= 0 && j >= 0 && p1.get(i) == p2.get(j)) {
                i--; j--;
            }
            i++; j++;
            return i + j;
        }

        boolean areTreesSimilar(Node node1, Node node2) {
            if(node1.children.size() != node2.children.size())
                return false;

            for(int i = 0; i < node1.children.size(); i++) {
                Node child1 = node1.children.get(i);
                Node child2 = node2.children.get(i);
                if(areTreesSimilar(child1, child2) == false)
                    return false;
            }
            return true;
        }

        boolean areTreesMirror(Node node1, Node node2) {
            if(node1.children.size() != node2.children.size())
                return false;

            int i = 0; 
            int j = node2.children.size() - 1;
            while(i < node1.children.size() && j >= 0) {
                Node child1 = node1.children.get(i);
                Node child2 = node2.children.get(j);

                if(areTreesMirror(child1, child2) == false)
                    return false;

                i++;
                j--;
            }
            return true;
        }

        boolean isTreeSymmetric(Node node) {
            return areTreesMirror(node, node);
        }

        boolean isTreeSymmetricEfficient(Node node1, Node node2) {
            if(node1.children.size() != node2.children.size())
                return false;

            int i = 0;
            int j = node2.children.size() - 1;
            while(i <= j) {
                Node child1 = node1.children.get(i);
                Node child2 = node2.children.get(j);

                if(isTreeSymmetricEfficient(child1, child2) == false)
                    return false;

                i++; j--;
            }
            return true;
        }

        void multisolver(Node node, int depth) {
            size++;
            min = Math.min(min, node.data);
            max = Math.max(max, node.data);
            height = Math.max(height, depth);

            for(Node child : node.children) {
                multisolver(child, depth + 1);
            }
        }

        // Travel and Change Approach
        // Initial value of node - tree.root
        // State - 0 : represents data not found yet(last node of state 0 is predecessor)
        // State - 1 : represents data found(last node of state 1 is successor)
        // State - 2 : represents successor onwards path
        void predecessorAndSuccessor(Node node, int data) {
            if(state == 0) {
                // data not found yet

                if(node.data == data) {
                    // data found -> update state
                    // predecessor would have been set in previous call
                    state = 1;
                } else {
                    // data not found yet
                    // add nodes as potential predecessor
                    predecessor = node;
                }
            } else if(state == 1) {
                // data has been found in previous call
                // set current node to successor
                // update state
                successor = node;
                state = 2;
            }
            
            // recursive calls to node's children
            for(Node child : node.children) {
                predecessorAndSuccessor(child, data);
            }
        }
    }
}
