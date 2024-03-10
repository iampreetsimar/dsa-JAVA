// Sample Input
// 5
// 5 3 8 6 7

// Sample Output
// -1 -1 3 3 6

import java.util.Scanner;
import java.util.Stack;


// NEXT SMALLER ELEMENT TO THE LEFT
public class nsetl_10 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scn.nextInt();
        }

        int[] nseFront = solveFromFront(arr);
        int[] nseBack = solveFromBack(arr);
        display(nseFront);
        display(nseBack);
    }

    public static void display(int[] arr){
        StringBuilder sb = new StringBuilder();

        for(int val: arr){
            sb.append(val + " ");
        }
        System.out.println(sb);
    }

    public static int[] solveFromFront(int[] arr) {
        int[] nse = new int[arr.length];
        Stack<Integer> s = new Stack<>();

        // there is no element to left of first element
        nse[0] = -1;
        s.push(arr[0]);

        for(int i = 1; i < arr.length; i++) {
            // pop items which are larger than current element
            while(s.size() > 0 && arr[i] <= s.peek()) {
                s.pop();
            }

            if(s.isEmpty())     // stack empty -> no NGETL(current element)
                nse[i] = -1;
            else    
                nse[i] = s.peek();

            s.push(arr[i]);
        }

        return nse;
    }

    public static int[] solveFromBack(int[] arr) {
        int[] nse = new int[arr.length];
        Stack<Integer> s = new Stack<>();

        // push last element idx to stack
        s.push(arr.length - 1);

        for(int i = arr.length - 2; i >= 0; i--) {
            
            // store and pop
            // current element < item whose idx on top of stack
            while(s.size() > 0 && arr[i] < arr[s.peek()]) {
                // store nse(item idx on top stack) = current element
                // pop item idx on top of stack
                nse[s.peek()] = arr[i];
                s.pop();
            }

            // push current element idx
            s.push(i);
        }

        // remaining items in stack -> no NSETL
        // pop
        while(s.size() > 0) {
            nse[s.peek()] = -1;
            s.pop();
        }

        return nse;
    }
}
