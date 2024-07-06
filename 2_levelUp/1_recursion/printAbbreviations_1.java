// Given a word, generate all abbreviations of that word.

// INPUT
// pep

// OUTPUT
// pep
// pe1
// p1p
// p2
// 1ep
// 1e1
// 2p
// 3

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class printAbbreviations_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        solution(str, "", 0, 0);
    }

    // use string subsequences approach
    // when char chooses to be present:
    //          -> add count(if > 0) and char to ans -> set count to 0 now
    // when char chooses to be absent:
    //          -> increment count by 1
    public static void solution(String str, String asf, int count, int idx) {
        if(idx == str.length()) {
            asf += (count > 0) ? "" + count : "";
            System.out.println(asf);
            return;
        }

        String temp = (count > 0) ? "" + count + str.charAt(idx) : "" + str.charAt(idx);

        solution(str, asf + temp, 0, idx + 1);     // pick
        solution(str, asf, count + 1, idx + 1);     // not pick
    }
}
