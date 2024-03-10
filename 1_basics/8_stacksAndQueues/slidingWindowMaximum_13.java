// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing the elements of array a.
// 3. You are given a number k, representing the size of window.
// 4. You are required to find and print the maximum element in every window of size k.

// e.g.
// for the array [2 9 3 8 1 7 12 6 14 4 32 0 7 19 8 12 6] and k = 4, 
// the answer is [9 9 8 12 12 14 14 32 32 32 32 19 19 19]

// Input Format
// Input is managed for you

// Output Format
// Maximum of each window in separate line

// Constraints
// 0 <= n < 100000
// -10^9 <= a[i] <= 10^9
// 0 < k < n

// Sample Input
// 17
// 2 9 3 8 1 7 12 6 14 4 32 0 7 19 8 12 6
// 4

// Sample Output
// 9
// 9
// 8
// 12
// 12
// 14
// 14
// 32
// 32
// 32
// 32
// 19
// 19
// 19

import java.util.*;

public class slidingWindowMaximum_13 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scn.nextInt();
        }

        int k = scn.nextInt();

        solve(arr, k);
    }

    // k - size of window
    public static void solve(int[] arr, int k) {
        // nge contains indexes of nger
        int[] nge = nger(arr);

        int j = 0;

        // n - k gives starting point of last window
        for(int i = 0; i <= arr.length - k; i++) {
            if(j < i)   
                j = i;      // since j travels nge -> posssibility it gets left behind i

            while(nge[j] < i + k) {
                // j moves to next nge inside the window
                j = nge[j];
            }

            // max of each window at j now
            System.out.println(arr[j]);
        }
    }

    public static int[] nger(int[] arr) {
        int[] nge = new int[arr.length];
        Stack<Integer> s = new Stack<>();

        // BACK TRAVERSAL

        // stack and nge stores indexes
        // righmost nse : outside array -> arr.length
        nge[arr.length - 1] = arr.length;
        s.push(arr.length - 1);

        for(int i = arr.length - 2; i >= 0; i--) {
            while(s.size() > 0 && arr[i] >= arr[s.peek()]) {
                s.pop();
            }

            if(s.isEmpty()) nge[i] = arr.length;
            else nge[i] = s.peek();

            s.push(i);
        }

        return nge;
    }
}
