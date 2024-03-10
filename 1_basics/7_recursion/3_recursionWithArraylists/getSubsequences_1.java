// 1. You are given a string str.
// 2. Complete the body of getSS function - without changing signature - to calculate all subsequences of str.
// Use sample input and output to take idea about subsequences.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is.
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Input Format
// A string str

// Output Format
// Contents of the arraylist containing subsequences as shown in sample output

// Constraints
// 0 <= str.length <= 20

// Sample Input
// abc

// Sample Output
// [, c, b, bc, a, ac, ab, abc]

import java.util.*;

public class getSubsequences_1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        System.out.println(gss(str));
    }

    public static ArrayList<String> gss(String str) {
        if(str.length() == 0) {
            // BASE CASE
            // return an arraylist with empty string element
            // subsequence of empty string is an empty string
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = str.charAt(0);
        String ros = str.substring(1);

        ArrayList<String> rres = gss(ros);

        ArrayList<String> finalRes = new ArrayList<>();

        // add rres in finalRes
        for(String rstr : rres) {
            finalRes.add(rstr);
        }

        // append ch in rres and add in finalRes
        for(String rstr : rres) {
            finalRes.add(ch + rstr);
        }

        return finalRes;
    }
}
