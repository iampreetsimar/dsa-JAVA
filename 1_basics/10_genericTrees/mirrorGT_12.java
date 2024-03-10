// 1. Given GenericTree class, complete mirror function. 
// The function is expected to create a mirror image of the tree. 

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null 

// Output
// OG Tree :
// 10 -> 20 30 40 .
// 20 -> 50 60 .
// 50 -> .
// 60 -> .
// 30 -> 70 80 90 .
// 70 -> .
// 80 -> 110 120 .
// 110 -> .
// 120 -> .
// 90 -> .
// 40 -> 100 .
// 100 -> .

// Mirror Tree :
// 10 -> 40 30 20 .
// 40 -> 100 .
// 100 -> .
// 30 -> 90 80 70 .
// 90 -> .
// 80 -> 120 110 .
// 120 -> .
// 110 -> .
// 70 -> .
// 20 -> 60 50 .
// 60 -> .
// 50 -> .


import java.util.ArrayDeque;
import java.util.ArrayList;
// import java.util.Collections;
import java.util.Queue;
import java.util.Stack;

public class mirrorGT_12 {
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
        System.out.println("Height : " + tree.height(tree.root));
        tree.levelOrder(tree.root);
        tree.levelOrderLineWise(tree.root);
        tree.levelOrderLineWiseZZ(tree.root);
        System.out.println(tree.find(tree.root, 120));
        System.out.println(tree.find(tree.root, 95));
        tree.display(tree.root);    // Tree before mirror
        tree.mirror(tree.root);
        tree.display(tree.root);    // Tree after mirror
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
            for(int  i = 0; i < input.length; i++) {
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

        void levelOrderLineWiseZZ(Node node) {
            Stack<Node> mainS = new Stack<>();
            Stack<Node> childS = new Stack<>();
            int level = 0;
            mainS.push(node);

            while(mainS.size() > 0) {
                Node top = mainS.pop();
                System.out.print(top.data + " ");

                if(level % 2 == 0) {
                    for(Node child : top.children) {
                        childS.push(child);
                    }
                } else {
                    for(int i = top.children.size() - 1; i >= 0; i--) {
                        childS.push(top.children.get(i));
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

        boolean find(Node node, int key) {
            if(key == node.data)
                return true;

            for(Node child : node.children) {
                if(find(child, key))
                    return true;
            }
            return false;
        }

        void mirror(Node node) {
            // recursive call to all children of node
            for(Node child : node.children) {
                mirror(child);
            }

            // POST ORDER

            // APPROACH 1 : using Collections
            // Collections.reverse(node.children);

            // APPROACH 2 : using queue
            // q to reverse order of children of node
            Queue<Node> q = new ArrayDeque<>();

            // read and add node children to q in reverse order -> q is FIFO
            for(int i = node.children.size() - 1; i >= 0; i--) {
                Node child = node.children.get(i);
                q.add(child);
                node.children.remove(i);
            }

            while(q.size() > 0) {
                // add front node from q to children of node
                node.children.add(q.remove());
            }

            // node children reversed
        }
    }
}
