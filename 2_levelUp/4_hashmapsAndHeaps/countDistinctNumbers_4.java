// Given an array of integers and a number k, find the count of distinct numbers in all windows of size k.

// INPUT 
// 2 5 5 6 3 2 3 2 4 5 2 2 2 2 5 6
// 4

// OUTPUT 
// 3 3 4 3 2 3 4 3 3 2 1 2 3

import java.io.*;
import java.util.*;

public class countDistinctNumbers_4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int[] arr = new int[parts.length];
        for(int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        int k = Integer.parseInt(br.readLine());
        System.out.println(countDistinctNumbers(arr, k));
    }

    // use sliding window approach and track freq of a number
    // map.size() gives the count of distinct numbers in a window
    public static List<Integer> countDistinctNumbers(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        int n = arr.length;
        int i = 0;

        for(int j = 0; j < n; j++) {
            freq.put(arr[j], freq.getOrDefault(arr[j], 0) + 1);

            if(j - i + 1 > k) { // window size > k -> slide window forward after decrementing count of num at window start
                freq.put(arr[i], freq.get(arr[i]) - 1);  
                if(freq.get(arr[i]) == 0) freq.remove(arr[i]);  // remove num if freq becomes 0
                i++;
            }

            if(j - i + 1 == k) {    // window size == k -> get count of distinct nums by checking size of map
                result.add(freq.size());
            }
        }

        return result;
    }

}
