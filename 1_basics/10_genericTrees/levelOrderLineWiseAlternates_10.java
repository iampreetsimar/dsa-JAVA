// 1. Given GenericTree class, complete levelOrderLineWise alternate approaches. 
// The function is expected to visit every node in level order fashion.
// All nodes on same level should be separated by a space.
// Different level nodes on separate lines.

// Input 
// 10 20 null 30 50 null 60 null null 40 null null

// Output
// 10
// 20 30 40
// 50 60

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class levelOrderLineWiseAlternates_10 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, null,
                            30, 50, null, 60, 70, null, null, null,
                            40, 80, null, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        tree.levelOrderLineWise(tree.root);
        tree.levelOrderLineWiseDelimiterApproach(tree.root);
        tree.levelOrderLineWiseCountApproach(tree.root);
        tree.levelOrderLineWisePairApproach(tree.root);
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

        GenericTree() {
            root = null;
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

        void levelOrderLineWise(Node node) {
            // mainQ represents nodes at level i
            Queue<Node> mainQ = new ArrayDeque<>();
    
             // childQ represents nodes at leve i + 1
            Queue<Node> childQ = new ArrayDeque<>();
    
            // add root node to mainQ
            mainQ.add(node);
    
            while(mainQ.size() > 0) {
                // 1. remove from front of mainQ
                Node f = mainQ.remove();
    
                // 2. print node removed from front
                System.out.print(f.data + " ");
    
                // 3. add children of node from front of mainQ to back of childQ 
                for(Node child : f.children) {
                    childQ.add(child);
                }
    
                if(mainQ.size() == 0) {
                    // marks that mainQ is empty -> nodes at level i has been read
                    // move to nodes at level i + 1 
                    // -> make mainQ as childQ and childQ becomes empty to repeat the process
                    Queue<Node> temp = mainQ;
                    mainQ = childQ;
                    childQ = temp;
    
                    // add new line before reading nodes at level i + 1
                    System.out.println();
                }
            }
        }
    
        // Initial value of node - tree.root
        // Use single queue similar levelOrder approach but add a delimiter node to indicate level change
        void levelOrderLineWiseDelimiterApproach(Node node) {
            Queue<Node> q = new ArrayDeque<>();
            q.add(node);
            q.add(new Node(-1));    // cannot add null to arrayDeque - instead adding -1 node as delimiter

            while(q.size() > 0) {
                Node f = q.remove();

                if(f.data == -1) {
                    if(q.size() > 0) {
                        // add delimiter node back only if q has some child items otherwise q.size always > 0
                        System.out.println(".");    // level change - add new line
                        q.add(f);       // add delimiter node to back to q indicating current level stop
                    }
                } else {
                    System.out.print(f.data + " ");
                    for(Node child : f.children) {
                        q.add(child);
                    }
                }
            }
            System.out.println(".");    // to add new line after removing last item from queue
        }
    
        // Initial value of node - tree.root
        // Use single queue and loop over number of nodes times in current level
        void levelOrderLineWiseCountApproach(Node node) {
            Queue<Node> q = new ArrayDeque<>();
            q.add(node);

            while(q.size() > 0) {
                int countChildrenInCurrentLevel = q.size();

                // this loop runs same number of times as number of nodes in current level
                // 1. remove
                // 2. print
                // 3. add children
                for(int i = 0; i < countChildrenInCurrentLevel; i++) {
                    Node f = q.remove();
                    System.out.print(f.data + " ");

                    for(Node child : f.children) {
                        q.add(child);
                    }
                }

                // When loop stops, current level is completed 
                // indicate next level by adding new line -> q only has next level nodes now
                System.out.println(".");
            }
        }

        private static class Pair {
            Node node;
            int level;

            Pair(Node node, int level) {
                this.node = node;
                this.level = level;
            }
        }
    
        // Initial value of node - tree.root
        // Use single queue along with Pair's level property to indicate new level
        void levelOrderLineWisePairApproach(Node node) {
            Queue<Pair> q = new ArrayDeque<>();
            Pair p = new Pair(node, 0);
            q.add(p);
            int currlevel = 0;
            while(q.size() > 0) {
                Pair f = q.remove();
                if(f.level > currlevel) {
                    // node removed is of next level
                    System.out.println(".");    // indicate new level by adding new line
                    currlevel = f.level;        // update current level to new level
                }

                System.out.print(f.node.data + " ");    // even if level changed - print and add still needs to happen
                for(Node child : f.node.children) {
                    // add child nodes of removed node as pair with incremented level that the removed node
                    q.add(new Pair(child, f.level + 1));
                }
            }
            System.out.println(".");     // to add new line after removing last item from queue
        }
    }
}
