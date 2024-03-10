// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing elements of array a.
// 3. You are required to "next greater element on the right" for all elements of array
// 4. Input and output is handled for you.

// "Next greater element on the right" of an element x is defined as the first element to right of x 
// having value greater than x.
// Note -> If an element does not have any element on it's right side greater than it, 
// consider -1 as it's "next greater element on right"
// e.g.
// for the array [2 5 9 3 1 12 6 8 7]
// Next greater for 2 is 5
// Next greater for 5 is 9
// Next greater for 9 is 12
// Next greater for 3 is 12
// Next greater for 1 is 12
// Next greater for 12 is -1
// Next greater for 6 is 8
// Next greater for 8 is -1
// Next greater for 7 is -1

// Input Format
// Input is managed for you

// Output Format
// Output is managed for you

// Constraints
// 0 <= n < 10^5
// -10^9 <= a[i] <= 10^9

// Sample Input
// 5
// 5 3 8 -2 7

// Sample Output
// 8 8 -1 7 -1

import java.io.*;
import java.util.*;

// NEXT GREATER ELEMENT TO THE RIGHT
public class ngetr_7 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] nextGreaterElementToRightArr = solve(arr);
        int[] nextGreaterElementToRightArrAlternative = alternativeSolve(arr);
        display(nextGreaterElementToRightArr);
        display(nextGreaterElementToRightArrAlternative);
    }

    public static void display(int[] arr){
        StringBuilder sb = new StringBuilder();

        for(int val: arr){
            sb.append(val + "\n");
        }
        System.out.println(sb);
    }

    // TRAVERSAL - RIGHT TO LEFT
    public static int[] solve(int[] arr){
        Stack<Integer> s = new Stack<>();

        // result array of size arr
        int[] result = new int[arr.length];

        // no NGETR for last element
        // NGETR(last element) = -1
        result[result.length - 1] = -1;

        // push last element to stack
        s.push(arr[arr.length - 1]);

        // traverse from second last element
        for(int i = arr.length - 2; i >= 0; i--) {
            // 3 steps - 1.pop  2. store NGETR  3. push
            
            while(!s.isEmpty() && s.peek() <= arr[i]) {
                // pop items from stack if item <= element
                s.pop();
            }

            // if stack empty - no NGETR for current element
            // stack not empty - NGETR(element) = item on top of stack
            if(s.isEmpty())
                result[i] = -1;
            else
                result[i] = s.peek();

            // push current element
            s.push(arr[i]);
        }

        return result;
    }

    // TRAVERSAL - LEFT TO RIGHT
    public static int[] alternativeSolve(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> s = new Stack<>();

        // add first element idx in stack
        s.push(0);

        for(int i = 1; i < arr.length; i++) {
            while(!s.isEmpty() && arr[i] > arr[s.peek()]) {
                // current element > any item whose idx on stop of stack
                // store NGETR(arr[s.peek()]) -> current element
                result[s.peek()] = arr[i];

                // pop idx from stack
                s.pop();
            }

            // push current idx to stack
            s.push(i);
        }

        // if items remain in stack -> no NGETR for those items present
        // set NGETR for them to -1 and pop from stack
        while(!s.isEmpty()) {
            result[s.peek()] = -1;
            s.pop();
        }

        return result;
    }
}
