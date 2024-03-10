// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing the prices of a share on n days.
// 3. You are required to find the stock span for n days.
// 4. Stock span is defined as the number of days passed between the current day and the first day 
// before today when price was higher than today.

// e.g.
// for the array [2 5 9 3 1 12 6 8 7]
// span for 2 is 1
// span for 5 is 2
// span for 9 is 3
// span for 3 is 1
// span for 1 is 1
// span for 12 is 6
// span for 6 is 1
// span for 8 is 2
// span for 7 is 1

// Input Format
// Input is managed for you

// Output Format
// Output is managed for you

// Constraints
// 0 <= n < 10^5
// -10^9 <= a[i] <= 10^9

import java.util.*;

public class stockSpan_11 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = scn.nextInt();
        }

        int[] span = solve(a);
        display(span);
    }

    public static void display(int[] a){
        StringBuilder sb = new StringBuilder();

        for(int val: a){
            sb.append(val + " ");
        }
        System.out.println(sb);
    }

    public static int[] solve(int[] arr){
        Stack<Integer> s = new Stack<>();

        // span array will contain idx of NGELs first
        int[] span = new int[arr.length];

        // no NGEL(first element)
        span[0] = -1;

        // push idx
        s.push(0);

        // APPROACH 1 for NGEL - FRONT TRAVERSAL
        // storing idx instead of actual values of NGELs
        for(int i = 1; i < arr.length; i++) {
            while(s.size() > 0 && arr[i] >= arr[s.peek()]) {
                s.pop();
            }

            if(s.isEmpty())
                span[i] = -1;
            else    
                span[i] = s.peek();

            s.push(i);
        }

        // span of element at idx i = idx i - idx(NGEL)
        for(int i = 0; i < arr.length; i++) {
            span[i] = i - span[i];
        }
        
        return span;
    }
}
