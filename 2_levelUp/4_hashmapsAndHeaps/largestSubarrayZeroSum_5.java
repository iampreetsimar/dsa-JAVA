// Given an array of integers, find the length of the largest subarray with 0 sum. 

// INPUT 
// 2 8 -3 -5 2 -4 6 1 2 1 -3 4

// OUTPUT
// 8 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class largestSubarrayZeroSum_5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int[] arr = new int[parts.length];
        for(int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        System.out.println(largestSubarrayLength(arr));
    }

    public static int largestSubarrayLength(int[] arr) {
        int n = arr.length;
        int length = 0;
        Map<Integer, Integer> map = new HashMap<>();    // { cum_sum : first seen on idx }
        int cum_sum = 0;
        map.put(0, -1); // edge case to get length of subarray from starting idx:0

        for(int i = 0; i < n; i++) {
            cum_sum += arr[i];
            if(map.containsKey(cum_sum)) {  // since first seen idx is not included in subarray, len is (curr idx - first seen idx)
                length = Math.max(length, i - map.get(cum_sum));
            } else {
                map.put(cum_sum, i);    // add cum_sum with first seen idx
            }
        }

        return length;
    }
}
