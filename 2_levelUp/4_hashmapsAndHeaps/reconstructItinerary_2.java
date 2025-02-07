// Given a number N and 2*N number of strings that represents a list of N tickets(source and destination).
// Find the itinerary in order using the given list of tickets. 
// Assume the input list of tickets is not cyclic and there is one ticket from every city except the final destination. 

// INPUT
// 4
// Chennai Bangalore
// Bombay Delhi
// Goa Chennai
// Delhi Goa

// OUTPUT
// Bombay -> Delhi -> Goa -> Chennai -> Bangalore.

import java.io.*;
import java.util.*;

public class reconstructItinerary_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<String, String> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            map.put(parts[0], parts[1]);
        }

        constructItinerary(map);
    }

    public static void constructItinerary(Map<String, String> map) {
        Map<String, Boolean> origSrc = new HashMap<>(); // {place : true - place is orig src | false - place is not orig src}
        for(String src : map.keySet()) {
            String dest = map.get(src);
            origSrc.put(dest, false);   // dest can never be a orig src

            if(!origSrc.containsKey(src)) {   // src already present - don't change as it may/may not be a potential src anymore
                origSrc.put(src, true);
            }
        }

        StringBuilder src = new StringBuilder();
        for(String s : origSrc.keySet()) {  // find the one orig src
            if(origSrc.get(s)) {
                src.append(s);
                break;
            }
        }

        while(true) {   // start traversal from orig src and create itinerary using i/p map
            String s = src.toString();
            if(map.containsKey(s)) {
                System.out.print(s + " -> ");
                String dest = map.get(s);
                src.setLength(0);
                src.append(dest);
            } else {
                System.out.print(s + ".");
                break;
            }
        }
    }
}