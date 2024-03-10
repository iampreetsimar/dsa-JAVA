// Sample Input
// 5
// 5 3 8 6 7

// Sample Output
// 3 -1 6 -1 -1 

import java.util.Scanner;
import java.util.Stack;

// NEXT SMALLER ELEMENT TO THE RIGHT
public class nsetr_8 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scn.nextInt();
        }

        int[] nseRevTrav = solveRevTrav(arr);
        int[] nseFrontTrav = solveFrontTrav(arr);
        display(nseRevTrav);
        display(nseFrontTrav);
    }

    public static void display(int[] arr){
        StringBuilder sb = new StringBuilder();

        for(int val: arr){
            sb.append(val + " ");
        }
        System.out.println(sb);
    }

    public static int[] solveRevTrav(int[] arr) {
        int[] nse = new int[arr.length];
        Stack<Integer> s = new Stack<>();

        nse[nse.length - 1] = -1;
        s.push(arr[arr.length - 1]);

        for(int i = arr.length - 2; i >= 0; i--) {
            while(!s.isEmpty() && arr[i] <= s.peek()) {
                // pop larger elements from stack
                s.pop();
            }

            if(s.isEmpty()) nse[i] = -1;
            else nse[i] = s.peek();

            s.push(arr[i]);
        }

        return nse;
    }

    public static int[] solveFrontTrav(int[] arr) {
        int[] nse = new int[arr.length];
        Stack<Integer> s = new Stack<>();

        s.push(0);

        for(int i = 0; i < arr.length; i++) {
            while(s.size() > 0 && arr[i] < arr[s.peek()]) {
                // storing nse of larger elements and popping from stack
                nse[s.peek()] = arr[i];
                s.pop();
            }

            s.push(i);
        }

        while(s.size() > 0) {
            nse[s.peek()] = -1;
            s.pop();
        }

        return nse;
    }
}
