// Given N dates in DDMMYYYY format, sort it in increasing order.
// NOTE: All dates b/w year 0 to year 2500.

// INPUT
// 5
// 12041996
// 20101996
// 05061997
// 12041989
// 11081987

// OUTPUT
// 11081987
// 12041989
// 12041996
// 20101996
// 05061997

import java.io.*;

public class sortDates_14 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] dates = new String[n];
        for(int i = 0; i < n; i++) {
            dates[i] = br.readLine();
        }

        sortDates(dates);
        display(dates);
    }

    // date in DDMMYYYY
    // least significant: days
    // most significant: years
    // days -> months -> years
    // use RADIX SORT concept to use countSort on days -> months -> years
    public static void sortDates(String[] dates) {
        countSort(dates, 1000000, 100, 32);   // sort days: days range: [1,31] -> 0 empty
        countSort(dates, 10000, 100, 13);   // sort months: months range:[1,12] -> 0 empty
        countSort(dates, 1, 10000, 2501); // sort years: years range:[0, 2500]
    }

    // div and mod helps in extracting digits for current iteration(days/months/years)
    // range represents the size of frequency array
    public static void countSort(String[] dates, int div, int mod, int range) {
        int[] freqToPrefixSumArr = new int[range];
        for(String date: dates) {
            // convert string date to int(base 10) date -> by default also base 10
            // since we can have 0* for days -> JAVA can interpret it as a base 8 number
            // but we want to let 0* be read as * so use base 10
            int dateInt = Integer.parseInt(date, 10);   
            freqToPrefixSumArr[(dateInt / div) % mod]++; // extract digits for curr iter and increment its freq.
        }

        for(int i = 1; i < freqToPrefixSumArr.length; i++) {    // converting to prefix sum array
            freqToPrefixSumArr[i] += freqToPrefixSumArr[i - 1];
        }

        String[] res = new String[dates.length];

        for(int i = dates.length - 1; i >= 0; i--) {    // traversing from back for stable sorting
            int dateInt = Integer.parseInt(dates[i], 10);
            int pos = freqToPrefixSumArr[(dateInt / div) % mod]--;
            int idx = pos - 1;
            res[idx] = dates[i];
        }

        for(int i = 0; i < dates.length; i++) {
            dates[i] = res[i];
        }
    }

    public static void display(String[] dates) {
        System.out.println("Sorted Dates: ");
        for(String date: dates) {
            System.out.println(date);
        }
    }
}
