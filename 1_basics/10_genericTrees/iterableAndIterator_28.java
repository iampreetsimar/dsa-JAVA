// 1. Given GenericTree class, print given GT in preorder. 
// The p/g is expected to implement a for loop similar to enhanced for loop of JAVA 
// which does the preorder traversal of GT and print it.
// eg for(val : tree) { } -> this should traverse tree in preorder
// Use ITERABLE and ITERATOR

// Input 
// 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
// 40, 100, null, null, null -> tree input

// Output
// 10 20 50 60 30 70 80 110 120 90 40 100 -> preorder

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class iterableAndIterator_28 {
    public static void main(String[] args) {
        Integer input[] = { 10, 
                            20, 50, null, 60, null, null,
                            30, 70, null, 80, 110, null, 120, null, null, 90, null, null,
                            40, 100, null, null,
                            null }; 

        GenericTree tree = new GenericTree(input);
        System.out.println("Tree root : " + tree.root.data);

        // Syntactical sugar of tree loop
        for(Integer val : tree) {
            System.out.print(val + " ");
        }   // prints 10 20 50 60 30 70 80 110 120 90 40 100
        System.out.println();

        // Actual work behind the scenes for tree loop
        // gti - ref of type Iterator<Integer> and instance of type GTPreorderIterator class
        Iterator<Integer> gti = tree.iterator();
        while(gti.hasNext()) {
            System.out.print(gti.next() + " ");
        }   // prints 10 20 50 60 30 70 80 110 120 90 40 100
        System.out.println();
    }

    private static class Node {
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

    // Since we need to iterate over GenericTree object
    // implement Iterable<Integer> interface, Integer because we want to print node.data
    public static class GenericTree implements Iterable<Integer> {
        Node root;

        GenericTree() {
            root = null;
        }

        GenericTree(Integer[] input) {
            this();
            constructTree(input);
        }

        // need to implement iterator function due to Iterable interface
        public Iterator<Integer> iterator() {  
            // return ref of obj of type Iterator<Integer> which is initialized by instance of GTPreorderIterator class
            // Actual implementation of loop in GTPreorderIterator class
            // since we traverse from root -> need to pass tree root to GTPreorderIterator class
            Iterator<Integer> obj = new GTPreorderIterator(root);
            return obj;
        }

        void constructTree(Integer[]input) {
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
    }

    // for iterativePreAndPostOrder logic
    private static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    // need to add actual implementation in Iterator interface
    public static class GTPreorderIterator implements Iterator<Integer> {
        Integer nVal;      // pointer to value in loop
        Stack<Pair> s;      // iterativePreAndPostOrder logic

        public GTPreorderIterator(Node root) {  // tree root is passed to implement loop on tree
            s = new Stack<>();  // iterativePreAndPostOrder logic
            s.push(new Pair(root, -1));

            // next called once so that nVal is pointing to tree root for the first time instead of null
            next();       // when loop starts, first value will be tree root
        }

        // need to implement due to Iterator interface
        // returns boolean value based on if next value in loop(tree traversal) is null or not
        public boolean hasNext() {
            if(nVal == null)
                return false;
            
            return true;
        }

        // need to implement due to Iterator interface
        // return current value in loop(tree traversal) and points to next value for next iteration 
        public Integer next() {
            // nVal points to the current value needed
            Integer currentVal = nVal;

            // point nVal to next value in traversal for next iteration
            // point nVal to null before each iteration incase if there are no more elements left in stack
            // nVal will be null for next value
            nVal = null;        
            while(s.size() > 0) {       // iterativePreAndPostOrder logic
                Pair top = s.peek();
                if(top.state == -1) {    // instead of printing in preorder
                    nVal = top.node.data;    // store preorder value to nVal
                    top.state++;

                    // also break after each preorder value has been pointed for next iteration
                    break;      // to return current value in loop and print
                } else if(top.state == top.node.children.size()) {
                    s.pop();
                } else {
                    Pair child = new Pair(top.node.children.get(top.state), -1);
                    top.state++;  s.push(child);
                }
            }

            return currentVal;  // return current value from nVal
        }
    }
}
