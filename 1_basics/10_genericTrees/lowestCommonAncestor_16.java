// 1. Given GenericTree class, complete lca function. 
// The function is expected to return the lowest common ancestor of two data values passed to it.

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null 
// 120 -> data
// 80 -> data

// Output
// 80 -> lowest common ancestor of 120 and 80

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class lowestCommonAncestor_16 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null };

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);
        tree.display(tree.root);  
        // System.out.println("Size : " + tree.size(tree.root));
        // System.out.println("Max : " + tree.max(tree.root));
        // System.out.println("Height : " + tree.height(tree.root));

        // tree.levelOrder(tree.root);
        // tree.levelOrderLineWise(tree.root);
        // tree.levelOrderLineWiseZZ(tree.root);

        // find
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

        // // linearize
        // tree.display(tree.root);    // Before linearize
        // tree.linearize(tree.root);
        // // tree.linearizeEfficient(tree.root);
        // tree.display(tree.root);    // After linearize

        // Node to root path
        // int data = 120;
        // ArrayList<Integer> nodeToRootPath = tree.nodeToRootPath(tree.root, data);
        // System.out.println("Node to root path for data " + data +  " -- " + nodeToRootPath);

        // lowest common ancestor
        int data1 = 120;
        int data2 = 60;
        int lca = tree.lca(tree.root, data1, data2);
        if(lca != -1)
            System.out.println("Lowest Common Ancestor of " + data1 + " and " + data2 + " - " + lca);
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
                    for(int i = top.children.size() - 1; i>= 0; i--) {
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
                Node secondLastChild = node.children.get(node.children.size() - 1);
                Node secondLastChildTail = getTailNode(secondLastChild);
                secondLastChildTail.children.add(lastChild);
            }
        }

        Node linearizeEfficient(Node node) {
            if(node.children.size() == 0) 
                return node;

            Node lastChildTail = linearizeEfficient(node.children.get(node.children.size() - 1));
            while(node.children.size() > 1) {
                Node lastChild = node.children.remove(node.children.size() - 1);
                Node secondLastChildTail = linearizeEfficient(node.children.get(node.children.size() - 1));
                secondLastChildTail.children.add(lastChild);
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
                ArrayList<Integer> pathTillChild = nodeToRootPath(child, data);
                if(pathTillChild.size() > 0) {
                    pathTillChild.add(node.data);
                    return pathTillChild;
                }
            }

            return new ArrayList<Integer>();
        }

        int lca(Node node, int data1, int data2) {
            // return node to root path for data1
            ArrayList<Integer> pathToRootForData1 = nodeToRootPath(node, data1);

            // return node to root path for data2
            ArrayList<Integer> pathToRootForData2 = nodeToRootPath(node, data2);

            int i = pathToRootForData1.size() - 1;
            int j = pathToRootForData2.size() - 1;

            if(i < 0 && j < 0) {
                // data1 and data2 not in tree -> no LCA
                System.out.println(data1 + " and " + data2 + " not found in tree. No LCA.");
                return -1;
            } else if(i < 0) {
                // data 1 not in tree -> no LCA
                System.out.println(data1 + " not found in tree. No LCA.");
                return -1;
            } else if(j < 0){
                // data 2 not in tree -> no LCA
                System.out.println(data2 + " not found in tree. No LCA.");
                return -1;
            }
                
            // traverse both paths from back -> as paths can have different sizes
            // back traversing will start from root node
            // during traversing, if data is equal in both paths -> continue traversing
            // -> else if either of both paths traversing complete or if different data found
            // -> LCA for data1 and data2 is the data prior
            while(i >= 0 && j >= 0 && pathToRootForData1.get(i) == pathToRootForData2.get(j)) {
                i--; j--;
            }

            // return data prior in either data1 path or data2 path
            return pathToRootForData1.get(i + 1);
        }
    }
}
