// 1. You are given a number n1, representing the size of array a1.
// 2. You are given n1 numbers, representing elements of array a1.
// 3. You are given a number n2, representing the size of array a2.
// 4. You are given n2 numbers, representing elements of array a2.
// 5. The two arrays represent digits of two numbers.
// 6. You are required to find the difference of two numbers represented by two arrays and print the arrays. a2 - a1

// Assumption - number represented by a2 is greater.

// Input Format
// A number n1
// n1 number of elements line separated
// A number n2
// n2 number of elements line separated

// Output Format
// A number representing difference of two numbers (a2 - a1), represented by two arrays.

// Constraints
// 1 <= n1, n2 <= 100
// 0 <= a1[i], a2[i] < 10
// number reresented by a1 is smaller than number represented by a2

// Sample Input
// 3

// 2
// 6
// 7

// 4

// 1
// 0
// 0
// 0

// Sample Output
// 7
// 3
// 3

import java.util.*;

public class differenceTwoArrays_6 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n1 = scn.nextInt();
        int[] arr1 = new int[n1];
        for(int i = 0; i < n1; i++) {
            arr1[i] = scn.nextInt();
        }

        int n2 = scn.nextInt();
        int[] arr2 = new int[n2];
        for(int i = 0; i < n2; i++) {
            arr2[i] = scn.nextInt();
        }

        int[] res = differenceTwoArray(arr1, arr2);
        for(int i = 0; i < res.length; i++){
            if(i == 0 && res[i] == 0) continue;
            System.out.println(res[i]);
        }
    }

    public static int[] differenceTwoArray(int[] a, int[] b) {
        int i = b.length - 1;
        int j = a.length - 1;
        int[] res = new int[b.length];
        int k = res.length - 1;
        int carry = 0;

        while(k >= 0) {
            int diff = 0;
            int aVal = j >= 0 ? a[j] : 0;

            if(carry + b[i] >= aVal) {
                diff = carry + b[i] - aVal;
                carry = 0;
            } else {
                diff = 10 + carry + b[i] - aVal;
                carry = -1;
            }

            res[k] = diff;
            i--; j--; k--;
        }

        return res;
    }
}
