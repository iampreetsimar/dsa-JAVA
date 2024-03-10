// 1. Given GenericTree class, complete levelOrderLineWiseZZ function. 
// The function is expected to visit every node in level order fashion but in a zig zag manner.
// 1st level visited from left to right, 2nd level from right to left and so on.
// All nodes on same level should be separated by a space.
// Different level nodes on separate lines.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null 

// Output
// 10
// 40 30 20
// 50 60 70 80 90 100
// 120 110

import java.util.ArrayList;
import java.util.Stack;

public class levelOrderLineWiseZigZag_9 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);
        tree.levelOrderLineWiseZZ(tree.root);
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

        void levelOrderLineWiseZZ(Node node) {
            Stack<Node> mainS = new Stack<>();  // store items at level i
            Stack<Node> childS = new Stack<>(); // store items at level i + 1(children) -> order depends on level
            int level = 0;      // initial level
            mainS.push(node);       // push root to mainS

            while(mainS.size() > 0) {
                Node top = mainS.pop();     // 1. remove from mainS
                System.out.print(top.data + " ");   // 2. print top node

                // 3. add top children to childS
                if(level % 2 == 0) {
                    // current level - even -> nodes at next level to be printed in right to left
                    for(Node child : top.children) {    // add to childS in left to right order due to LIFO
                        childS.push(child);
                    }
                } else {
                    // current level - odd -> nodes at next level to be printed in left to right
                    // add to childS in right to left order due to LIFO
                    for(int i = top.children.size() - 1; i >= 0; i--) {
                        childS.push(top.children.get(i));
                    } 
                }

                if(mainS.size() == 0) {
                    // current level nodes reading complete
                    // swap mainS and childS -> increment level -> print new line
                    Stack<Node> temp = mainS;
                    mainS = childS;
                    childS = temp;
                    level++;
                    System.out.println();
                }
            }
        }
    }
}
