// A valid IP address consists of exactly four integers separated by single dots. 
// Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

// For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, 
// but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.

// Given a string s containing only digits, return all possible valid IP addresses that can be formed 
// by inserting dots into s. You are not allowed to reorder or remove any digits in s. 
// You may return the valid IP addresses in any order.

// CONSTRAINTS
// 1 <= s.length <= 20
// s consists of digits only.

// INPUT
// "25525511135"
// "0000"
// "101023"

// OUTPUT
// ["255.255.11.135","255.255.111.35"]
// ["0.0.0.0"]
// ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class restoreIPAddresses_46 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        List<String> res = new ArrayList<>();
        solve(s, 0, 0, "", res);
        System.out.println(res);
    }

    // use level-options appraoch: -> char idx on levels | -> each char has 3 choices as options
    // choose len:1, len:2 and len:3 as partitions from curr char idx
    public static void solve(String s, int idx, int partitions, String asf, List<String> res) {
        if(idx == s.length()) {     // BASE CASE
            if(partitions == 4) {   // 4 partitions made
                res.add(asf.substring(0, asf.length() - 1)); // add curr IP add. to list without last dot
            }
            return;
        }

        solve(s, idx + 1, partitions + 1, asf + s.charAt(idx) + ".", res);  // choose curr char as partition of len:1

        if(idx + 2 <= s.length() && isValidPartition(s.substring(idx, idx + 2)))  // choose curr char + next char as partition if valid
            solve(s, idx + 2, partitions + 1, asf + s.substring(idx, idx + 2) + ".", res);

        if(idx + 3 <= s.length() && isValidPartition(s.substring(idx, idx + 3))) // choose curr char + next 2 chars as partition if valid
            solve(s, idx + 3, partitions + 1, asf + s.substring(idx, idx + 3) + ".", res);
    }

    public static boolean isValidPartition(String s) {
        if(s.length() > 1 && s.charAt(0) == '0')  // len:2 and len:3 partitions must not have any leading 0
            return false;
            
        if(s.length() > 2)  // len:3 partition must be  <= 255
            return Integer.parseInt(s) <= 255;

        return true;
    }
}
