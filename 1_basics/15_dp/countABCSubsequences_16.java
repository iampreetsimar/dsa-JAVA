// Given a string, print count of subsequences of the nature a+ b+ c+.
// Regular exp: a+b+c+ means one or more a's, then one or more b's and then one or more c's.
// Eg:
// str: abb'c -> count: 3 => abc, ab'c, abb'c.
// str: abca'b'c' -> count: 7 => abc, abc', abb'c', aa'b'c', abcc', ab'c', a'b'c'.

// INPUT
// abcabc

// OUTPUT
// 7

import java.io.*;

public class countABCSubsequences_16 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(countABCSubsequenceTab(str));
    }

    // countA: count of a+ subseq. for str from index:0 to idx
    // countAB: count of a+b+ subseq. for str from index: 0 to idx
    // countABC: count of a+b+c+ subseq. for str from index:0 to idx
    public static int countABCSubsequenceTab(String str) {
        int countA = 0, countAB = 0, countABC = 0;

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);    // char at idx:i

            if(ch == 'a') {  // char: a 
                // 2 choices to include/exclude from already present a+ subseq
                // + 1 choice creating starting point for new subseq using curr char
                countA = (2 * countA) + 1;  
            } else if(ch == 'b') {  // char: b
                // 2 choices to incl/excl from already present a+b+ subseq
                // + 1 choice to be added to a+ subseq
                countAB = (2 * countAB) + countA;
            } else if(ch == 'c') {  // char: c
                // 2 choices to incl/excl from already present a+b+c+ subseq
                // + 1 choice to be added to a+b+ subseq
                countABC = (2 * countABC) + countAB;
            }
        }

        return countABC;    // total count of a+b+c+ subseq. for entire str
    }
}
