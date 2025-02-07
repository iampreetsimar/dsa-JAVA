// Given an array of integers and a number k, check if the array can be divided into number of pairs
// such that the sum of the pair is divisible by k. 

// INPUT
// 4
// 9 7 5 3
// 6

// OUTPUT
// true

import java.io.*;

public class checkArrayPairsDivisibleByK_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] parts = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        int k = Integer.parseInt(br.readLine());
        System.out.println(checkArray(arr, k));
    }

    // can use hashmap instead of array for mapping remainder freq.
    public static boolean checkArray(int[] arr, int k) {
        int[] freq = new int[k];    // size k as rem can be in range [0,k)
        int n = arr.length;

        for(int i = 0; i < n; i++) {    // find freq of each rem
            freq[arr[i] % k]++;
        }

        // for a rem x, its pair rem (k - x) must have same freq to get pairs
        // if rem is 0 or (2 * rem == k), freq must be even to make pairs
        // (2 * rem == k) checks if k is even and rem is k/2, there must be even count of freq
        for(int i = 0; i < n; i++) {
            int rem = arr[i] % k;   

            if(rem == 0 || 2 * rem == k) {
                if(freq[rem] % 2 != 0) return false;
            } else {
                if(freq[rem] != freq[k - rem]) return false;
            }
        }

        return true;
    }
}
