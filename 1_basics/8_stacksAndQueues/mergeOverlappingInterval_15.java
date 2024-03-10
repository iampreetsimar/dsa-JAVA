// 1. You are given a number n, representing the number of time-intervals.
// 2. In the next n lines, you are given a pair of space separated numbers.
// 3. The pair of numbers represent the start time and end time of a meeting 
// (first number is start time and second number is end time)
// 4. You are required to merge the meetings and print the merged meetings output in increasing order of start time.

// E.g. Let us say there are 6 meetings
// 1 8
// 5 12
// 14 19
// 22 28
// 25 27
// 27 30

// Then the output of merged meetings will belongs
// 1 12
// 14 19
// 22 30

// Note -> The given input may not be sorted by start-time.

// Input Format
// Input is managed for you 

// Output Format
// Print a merged meeting start time and end time separated by a space in a line
// .. print all merged meetings one in each line.

// Constraints
// 1 <= n <= 10^4
// 0 <= ith start time < 100
// ith start time < ith end time <= 100

// Sample Input
// 6
// 22 28
// 1 8
// 25 27
// 14 19
// 27 30
// 5 12

// Sample Output
// 1 12
// 14 19
// 22 30

import java.io.*;
import java.util.*;;

public class mergeOverlappingInterval_15 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            arr[j][0] = Integer.parseInt(line.split(" ")[0]);
            arr[j][1] = Integer.parseInt(line.split(" ")[1]);
        }

        mergeOverlappingIntervals(arr);
    }

    // merge overlapping intervals and print in increasing order of start time
    public static void mergeOverlappingIntervals(int[][] arr) {
        // make interval objects and store in an array
        Interval[] intervalPairs = new Interval[arr.length];
        for(int i = 0; i < arr.length; i++) {
            intervalPairs[i] = new Interval(arr[i][0], arr[i][1]);
        }

        // sort intervals
        Arrays.sort(intervalPairs);

        Stack<Interval> s = new Stack<>();

        for(int i = 0; i < intervalPairs.length; i++) {
            if(i == 0) {
                // push first interval
                s.push(intervalPairs[i]);
            } else {
                Interval top = s.peek();

                if(intervalPairs[i].startTime <= top.endTime) {
                    // overlapping condition true

                    // updation condition
                    top.endTime = Math.max(top.endTime, intervalPairs[i].endTime);
                    
                } else {
                    // overlapping condition false
                    // push interval to stack
                    s.push(intervalPairs[i]);
                }
            }
        }

        // s contains merged intervals
        // reverse by using another stack
        Stack<Interval> revS = new Stack<>();
        while(s.size() > 0) {
            revS.push(s.pop());
        }

        // print merged interval in increasing order of start time
        while(revS.size() > 0) {
            Interval mergedPair = revS.pop();
            System.out.println(mergedPair.startTime + " " + mergedPair.endTime);
        }
    }

    public static class Interval implements Comparable<Interval> {
        int startTime;
        int endTime;

        Interval(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        // Need to implement compareTo() to use Comparable interface
        public int compareTo(Interval other) {
            // returns :
            // +ve value -> this > other
            // 0 -> this == other
            // -ve value -> this < other

            // if start time is equal, sort on end time

            if(this.startTime != other.startTime) 
                return this.startTime - other.startTime;
            else    
                return this.endTime - other.endTime;
        }
    }
}
