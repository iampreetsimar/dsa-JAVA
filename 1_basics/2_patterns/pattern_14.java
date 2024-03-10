// 1. You are given a number n.
// 2. You've to write code to print it's multiplication table up to 10 in format given below.

// Input Format
// A number n

// Output Format
// n * 1 = n
// n * 2 = 2n
// n * 3 = 3n
// ....
// n * 10 = 10n

import java.util.*;

public class pattern_14 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        for(int row = 1; row <= 10; row++){
            System.out.println(n + " * " + row + " = " + n*row);
        }
    }
}