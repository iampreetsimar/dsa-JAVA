// Given N: count of items, N numbers: values of N items, N numbers: weights of N items, cap: capacity of bag you have.
// Calculate and print the maximum value that can be created in the bag without overflowing its capacity.

// NOTE: Items can be taken even partially but cannot be taken again and again, i.e, FINITE supply.

// INPUT
// 10
// 33 14 50 9 8 11 6 40 2 15
//  7  2  5 9 3  2 1 10 3  3
// 5

// OUTPUT
// 50.0

import java.io.*;
import java.util.Arrays;

public class fractionalKnapsack_12 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] values = new int[n];
        int[] weights = new int[n];

        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(row[i]);
        }
        row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(row[i]);
        }
        int cap = Integer.parseInt(br.readLine());

        System.out.println(fractionalKnapsack(values, weights, cap));
    }

    static class Pair implements Comparable<Pair> {
        int value;
        int weight;
        double valuePerUnitWt;

        Pair(int value, int weight, double valuePerUnitWt) {
            this.value = value;
            this.weight = weight;
            this.valuePerUnitWt = valuePerUnitWt;
        }

        public int compareTo(Pair other) {  // cannot use subtraction st as that returns a double value
            // ASCENDING ORDER
            // if(this.valuePerUnitWt > other.valuePerUnitWt)
            //     return 1;
            // else if(this.valuePerUnitWt < other.valuePerUnitWt)
            //     return -1;
            // else
            //     return 0;

            // DESCENDING ORDER
            if(this.valuePerUnitWt > other.valuePerUnitWt)
                return -1;
            else if(this.valuePerUnitWt < other.valuePerUnitWt)
                return 1;
            else
                return 0;
        }
    }

    // GREEDY APPROACH
    // somewhat similar to 01 knapsack: item can be taken only once(either in full or partial)
    // valuePerUnitWeight of an item: how valuable the item is per its unit weight
    // move from most to least valuable item and we'll choose the most valuable item first
    public static double fractionalKnapsack(int[] values,int[] weights, int cap) {
        Pair[] pairs = new Pair[values.length];
        for(int i = 0; i < pairs.length; i++) {
            pairs[i] = new Pair(values[i], weights[i], (values[i] * 1.0)/weights[i]);
        } 

        Arrays.sort(pairs); // sorted in descending order: most valuable item in front

        double maxVal = 0.0;
        int idx = 0;

        // traverse till bag cap and items are remaining 
        while(cap > 0 && idx < pairs.length) {
            if(pairs[idx].weight <= cap) {  // item's wt <= cap -> bag can include entire item
                maxVal += pairs[idx].value; // add item's value to maxVal
                cap -= pairs[idx].weight;   // decrease bag's cap by item's wt
                idx++;  // move to next item
            } else {    // item's wt > cap -> bag can include partial item
                // include bag's cap's worth of item's value
                maxVal += (cap * pairs[idx].valuePerUnitWt);    
                cap = 0;    // bag becomes full -> no need to move to next item
            }
        }

        return maxVal;  // max value than can be created
    }
}
