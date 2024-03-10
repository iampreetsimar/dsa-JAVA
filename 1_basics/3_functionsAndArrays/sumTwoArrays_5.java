// 1. You are given a number n1, representing the size of array a1.
// 2. You are given n1 numbers, representing elements of array a1.
// 3. You are given a number n2, representing the size of array a2.
// 4. You are given n2 numbers, representing elements of array a2.
// 5. The two arrays represent digits of two numbers.
// 6. You are required to add the numbers represented by two arrays and print the
// arrays.

// Input Format
// A number n1
// n1 number of elements line separated
// A number n2
// n2 number of elements line separated

// Output Format
// A number representing sum of two numbers, represented by two arrays.

// Constraints
// 1 <= n1, n2 <= 100
// 0 <= a1[i], a2[i] < 10

// Sample Input
// 5

// 3
// 1
// 0
// 7
// 5

// 6

// 1
// 1
// 1
// 1
// 1
// 1

// Sample Output
// 1
// 4
// 2
// 1
// 8
// 6

import java.util.*;

public class sumTwoArrays_5 {
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

        int[] res = sumTwoArray(arr1, arr2);
        for(int i = 0; i < res.length; i++){
            if(i == 0 && res[i] == 0) continue;
            System.out.println(res[i]);
        }
    }

    public static int[] sumTwoArray(int[] a, int[] b) {
        int[] res = new int[a.length > b.length ? a.length + 1 : b.length + 1];
        int i = a.length - 1;
        int j = b.length - 1;
        int k = res.length - 1;
        int carry = 0;

        while(k >= 0) {
            int sum = 0;

            if(i >= 0)
                sum += a[i];

            if(j >= 0)
                sum += b[j];

            sum += carry;
            res[k] = sum % 10;
            carry = sum / 10;

            i--; j--; k--;
        }

        return res;
    }
}
