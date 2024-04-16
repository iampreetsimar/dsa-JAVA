// Given a string str of digits(will never start from 0). You are required to encode str using following rules:
// a -> 1
// b -> 2
// c -> 3
// ......
// ......
// z -> 26

// You are required to calculate and print the count of encodings for string str.

// For 123, there are 3 encodings: abc, aw and lc.
// For 993, there is 1 encoding: iic.
// For 013, this is invalid input. A string starting with 0 is not passed as input.
// For 103, there is 1 encoding: jc.
// For 303, there are 0 encoding. Such a string maybe passed and ouput is 0.

import java.io.*;

public class countEncodings_15 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(countEncodings(str));
        System.out.println(countEncodingsTab(str));
    }

    public static int countEncodings(String str) {
        if(str.length() == 0) {  // BASE CASE: all chars read
            return 1;   // encoding possible for curr path of choices -> return 1 as count
        }

        if(str.charAt(0) == '0') // curr char is '0' -> no encoding possible
            return 0;  // return 0 as count

        // valid char -> can be encoded -> move to next char -> return count from next char till string end
        // count will be same for curr char as well
        int countEncodings = countEncodings(str.substring(1));
        
        if(str.length() >= 2) { // if more 1 char present in string
            // combine curr char with next char to get a double digit -> check dd <= 26
            // if true -> valid dd -> move to next char after dd -> return count from char next to dd till end
            // count will be same for dd and will be added to count formed using single digit
            int doubleDigit = Integer.parseInt(str.substring(0, 2));
            if(doubleDigit <= 26)
                countEncodings += countEncodings(str.substring(2));
        }       
        
        // total encodings possible from curr char till str end(taken both as single or double digit)
        return countEncodings;  
    }

    // use dp[] of size str.length
    // dp[idx]: count of encodings possible for str from index: 0 to idx
    // move from easiest problem(idx:0) to hardest problem(idx:str.length - 1)
    public static int countEncodingsTab(String str) {
        int[] dp = new int[str.length()];
        dp[0] = 1;  // char at idx:0 is always valid -> can be encoded as single digit -> count: 1
        for(int idx = 1; idx < dp.length; idx++) {
            char chIdx = str.charAt(idx);   // curr char idx
            char chIdxMinus1 = str.charAt(idx - 1); // prev char at idx - 1
            
            if(chIdxMinus1 == '0' && chIdx == '0') {    // both chars are 0
                dp[idx] = 0;    // cannot be encoded as single or double digits -> count: 0
            } else if(chIdxMinus1 == '0' && chIdx != '0') { // chars: 0i, i:1-9
                // only curr char can be encoded when taken as single digit
                // count will same as count at prev idx
                dp[idx] = dp[idx - 1];  // when taken as dd -> it becomes invalid -> cannot be encoded
            } else if(chIdxMinus1 != '0' && chIdx == '0') { // chars: i0, i:1-9
                // when take as single digit -> becomes invalid -> cannot be encoded
                // can only be encoded when taken as dd but dd <= 26
                // if prev char is 1 or 2 -> can be encoded with same count as count at idx - 2
                // if idx - 2 < 0, curr idx is 1 -> valid dd can be present if dd <= 26 -> count:1
                if(chIdxMinus1 == '1' || chIdxMinus1 == '2')
                    dp[idx] = (idx - 2) >= 0 ? dp[idx - 2] : 1;
            } else {    // chars: ij, i,j:1-9
                // curr char can be taken as single digit -> can be encoded
                dp[idx] = dp[idx - 1];  // count will be same count at prev idx

                // if taken as dd -> dd <= 26 -> dd can be encoded
                // count is same as count at idx - 2
                // if idx - 2 < 0, curr idx is 1 -> valid dd can be present if dd <= 26 -> count:1
                // double digit count will be added to count from single digit to get total count at idx
                if(Integer.parseInt(str.substring(idx - 1, idx + 1)) <= 26)
                    dp[idx] += (idx - 2) >= 0 ? dp[idx - 2] : 1;
            }
        }
        return dp[str.length() - 1];    // total encodings possible for entire str
    }
}
