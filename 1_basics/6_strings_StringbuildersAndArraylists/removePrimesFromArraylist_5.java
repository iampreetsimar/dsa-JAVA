// 1. You are given an ArrayList of positive integers.
// 2. You have to remove prime numbers from the given ArrayList and return the updated ArrayList.

// Note -> The order of elements should remain same.

// Input Format
// A number N
// arr1
// arr2.. N numbers

// Output Format
// An Arraylist

// Constraints
// 1 <= N <= 10000

// Sample Input
// 4
// 3 12 13 15

// Sample Output
// [12, 15]

import java.util.*;

public class removePrimesFromArraylist_5 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        ArrayList<Integer> al = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            al.add(scn.nextInt());
        }

        removePrimes(al);
        System.out.println(al);
    }

    public static void removePrimes(ArrayList<Integer> al) {
        // traversing from back
        for(int i = al.size() - 1; i >= 0; i--) {
            if(isPrime(al.get(i)))
                al.remove(i);
        }
    }

    public static boolean isPrime(int num) {
        for(int i = 2; i * i <= num; i++) {
            if(num % i == 0)
                return false;   // if num is divisible, num is not prime
        }

        return true;
    }
}
