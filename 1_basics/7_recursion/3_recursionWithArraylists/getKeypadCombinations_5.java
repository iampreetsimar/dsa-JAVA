// 1. You are given a string str. The string str will contains numbers only, where each number stands for a key pressed on a 
// mobile phone.
// 2. The following list is the key to characters map :
//     0 -> .;
//     1 -> abc
//     2 -> def
//     3 -> ghi
//     4 -> jkl
//     5 -> mno
//     6 -> pqrs
//     7 -> tu
//     8 -> vwx
//     9 -> yz

// 3. Complete the body of getKPC function - without changing signature - to get the list of all words that could be produced by the keys in str.
// Use sample input and output to take idea about output.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is. 
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Constraints
// 0 <= str.length <= 10
// str contains numbers only

// Sample Input
// 78

// Sample Output
// [tv, tw, tx, uv, uw, ux]

import java.util.*;

public class getKeypadCombinations_5 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        System.out.println(getKPC(str));
    }

    static String[] keypad = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};

    public static ArrayList<String> getKPC(String str) {
        if(str.length() == 0) {
            // BASE CASE
            // return an arraylist with empty string element
            // as if no key is pressed, there will be one word , i.e., A BLANK WORD

            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        int idx = ch - '0';         // Alternative : idx = Integer.parseInt(ch + "");
        String word = keypad[idx];

        String ros = str.substring(1);

        ArrayList<String> rres = getKPC(ros);   // returns all combinations of string ros

        ArrayList<String> allKpc = new ArrayList<>();

        // make combinations of word characters with each element of rres
        for(int i = 0; i < word.length(); i++) {
            for(String s : rres) {
                allKpc.add(word.charAt(i) + s);
            }
        }

        return allKpc;
    }
}
