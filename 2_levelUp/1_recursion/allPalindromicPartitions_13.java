// Given a string of length n, partition the given string in such a way that each partition
// is a palindrome.

// NOTE: No permutations/arrangements!

// INPUT
// pep
// abaaba

// OUTPUT
// (p)(e)(p)
// (pep)

// (a)(b)(a)(a)(b)(a)
// (a)(b)(a)(aba)
// (a)(b)(aa)(b)(a)
// (a)(baab)(a)
// (aba)(a)(b)(a)
// (aba)(aba)
// (abaaba)

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class allPalindromicPartitions_13 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        solve(str, "");
    }

    // at each level: find all substrings starting from char at 0th idx
    // 0th idx because we don't want any permutations
    // check if any substring is a palindrome, if it is -- create a partition from it
    // rest of the remaining string is used at next level
    // since string and asf are changed in the recursive call, during backtracking we don't need to
    // update anything as only the parameters are changed and not actual values
    public static void solve(String str, String asf) {
        if(str.length() == 0) {     // BASE CASE
            System.out.println(asf);
            return;
        }

        for(int i = 0; i < str.length(); i++) { // find all substrings starting from 0th char
            String prefix = str.substring(0, i + 1);
            if(isPalindrome(prefix)) {  // check if substring is a palindrome or not
                solve(str.substring(i + 1), asf + "(" + prefix + ")");  // if yes, create partition using it
            }
        }
    }

    // iterative method to check a palindrome using two pointers - from front and back
    public static boolean isPalindrome(String str) {    
        if(str.length() == 1) return true;
        int li = 0, ri = str.length() - 1;
        while(li < ri) {
            if(str.charAt(li) != str.charAt(ri))
                return false;
            li++; ri--;
        }
        return true;
    }
}
