// Sample Input
// 5
// 5 3 8 6 7

// Sample Output
// -1 5 -1 8 8

import java.util.Scanner;
import java.util.Stack;

// NEXT GREATER ELEMENT TO THE LEFT
public class ngetl_9 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scn.nextInt();
        }

        int[] ngeFront = solveFromFront(arr);
        int[] ngeBack = solveFromBack(arr);
        display(ngeFront);
        display(ngeBack);
    }

    public static void display(int[] arr){
        StringBuilder sb = new StringBuilder();

        for(int val: arr){
            sb.append(val + " ");
        }
        System.out.println(sb);
    }

    public static int[] solveFromFront(int[] arr) {
        int[] nge = new int[arr.length];
        Stack<Integer> s = new Stack<>();

        // NGETL(first element) = -1
        nge[0] = -1;
        s.push(arr[0]);

        for(int i = 1; i < arr.length; i++) {
            // removing all items from stack which are smaller than current element
            while(s.size() > 0 && arr[i] >= s.peek()) {
                s.pop();
            }

            if(s.isEmpty())
                nge[i] = -1;    // stack empty -> no item greater for current element
            else    
                nge[i] = s.peek();   // NGETL(current element) is on top of stack

            // push current element
            s.push(arr[i]);
        }

        return nge;
    }

    public static int[] solveFromBack(int[] arr) {
        int[] nge = new int[arr.length];
        Stack<Integer> s = new Stack<>();

        // stack contains indexes 
        s.push(arr.length - 1);

        for(int i = arr.length - 2; i >= 0; i--) {
            // pop items whose value is less than current element
            while(s.size() > 0 && arr[i] > arr[s.peek()]) {
                // store nge(item whose idx on top) = current element
                // pop idx from stack
                nge[s.peek()] = arr[i];
                s.pop();
            }

            s.push(i);
        }

        // if stack not empty -> no NGETL for remaining items
        while(s.size() > 0) {
            nge[s.peek()] = -1;
            s.pop();
        }

        return nge;
    }
}
