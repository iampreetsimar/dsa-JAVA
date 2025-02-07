// Given an array of integers, find the count of all subarrays with 0 sum. 

// INPUT 
// 2 8 -3 -5 2 -4 6 1 2 1 -3 4

// OUTPUT
// 6

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class countSubarraysZeroSum_6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int[] arr = new int[parts.length];
        for(int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        System.out.println(countSubarrays(arr));
    }

    // similar to length of largest subarray with 0 sum
    // instead of first seen idx, keep track of freq of prefix sum
    // thr freq tells the count of how many subarrays with 0 sum can be found
    public static int countSubarrays(int[] arr) {
        int n = arr.length;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int cum_sum = 0;
        map.put(0, 1);  // base case for 0 prefix sum

        for(int i = 0; i < n; i++) {
            cum_sum += arr[i];

            if(map.containsKey(cum_sum))   // curr prefix sum already present -> add its freq to count
                count += map.get(cum_sum);  // curr prefix sum can make old freq number of subarrays with 0 sum

            map.put(cum_sum, map.getOrDefault(cum_sum, 0) + 1); // add/update freq of curr prefix sum
        }

        return count;
    }
}
