// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing the height of bars in a bar chart.
// 3. You are required to find and print the area of largest rectangle in the histogram.

// e.g.
// for the array [6 2 5 4 5 1 6] -> 12
// Input Format

// Input is managed for you
// Output Format

// A number representing area of largest rectangle in histogram

// Constraints
// 0 <= n < 20
// 0 <= a[i] <= 10
// Sample Input
// 7
// 6 2 5 4 5 1 6

// Sample Output
// 12

import java.util.*;

public class largestAreaHistogram_12 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scn.nextInt();
        }

        System.out.println(solve(arr));
    }

    public static int solve(int[] arr) {
        // Left Boundary - Next Smaller Element to Left
        int[] lb = nsel(arr);

        // Right Boundary - Next Smaller Element to Right
        int[] rb = nser(arr);

        int maxArea = 0;
        for(int i = 0; i < arr.length; i++) {
            int width = rb[i] - lb[i] - 1;
            int area = arr[i] * width;
            if(area > maxArea)
                maxArea = area;
        }

        return maxArea;
    }

    public static int[] nsel(int[] arr) {
        // FRONT TRAVERSAL - store indexes
        Stack<Integer> s = new Stack<>();
        int[] nse = new int[arr.length];

        nse[0] = -1;
        s.push(0);

        for(int i = 1; i < arr.length; i++) {
            while(s.size() > 0 && arr[i] <= arr[s.peek()]) {
                s.pop();
            }

            // leftmost boundary : -1
            if(s.isEmpty()) nse[i] = -1;
            else nse[i] = s.peek();

            s.push(i);
        }

        return nse;
    }

    public static int[] nser(int[] arr) {
        // BACK TRAVERSAL - store indexes
        Stack<Integer> s = new Stack<>();
        int[] nse = new int[arr.length];

        nse[arr.length - 1] = arr.length;
        s.push(arr.length - 1);

        for(int i = arr.length - 2; i >= 0; i--) {
            while(s.size() > 0 && arr[i] <= arr[s.peek()]) {
                s.pop();
            }

            // rightmost boundary : arr.length instead of -1
            if(s.isEmpty()) nse[i] = arr.length;
            else nse[i] = s.peek();     

            s.push(i);
        }

        return nse;
    }
}
