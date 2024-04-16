// Given N: length of the road. Road has N plots on both of its side. There should not be consecutive buildings
// on either side of the road.
// Print the number of ways in w/c buildings can be validly built on both side of the road.

// INPUT
// 6

// OUTPUT
// 441

import java.io.*;

public class arrangeBuildings_14 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(totalWaysToArrange(n));
        System.out.println(totalWaysToArrangeOpt(n));
    }

    // SIMILAR TO COUNT BINARY STRINGS(0: building, 1: space)
    // recursive function gives total valid ways for one side
    // Square it to get valid ways to arrange for both sides
    public static long totalWaysToArrange(int n) {
        long waysToArrangeOnOneSide = totalWaysOnEitherSide(n, "");
        return waysToArrangeOnOneSide * waysToArrangeOnOneSide;
    }

    public static long totalWaysOnEitherSide(int n, String way) {
        if(n == 0)  // BASE CASE: at end of road
            return 1;   // since we only get valid ways, return 1 as count

        // if starting point of road or last plot was space -> next plot can be both space and building
        if(way.length() == 0 || way.charAt(way.length() - 1) == 's') {
            return totalWaysOnEitherSide(n - 1, way + "s") + 
                   totalWaysOnEitherSide(n - 1, way + "b");
        } else {    // last plot was building -> next plot can only be space
            return totalWaysOnEitherSide(n - 1, way + "s");
        }
    }

    // SIMILAR TO COUNT BINARY STRINGS(0: building, 1: space)
    // use constant space instead of dp[] same as Count Binary String Opt
    // wayEndingWithBuilding takes result from wayEndingWithSpace(1 step earlier value)
    // wayEndingWithSpace takes result from wayEndingWithSpace and wayEndingWithBuilding(1 step earlier value)
    public static long totalWaysToArrangeOpt(int n) {
        int wayEndWithSpace = 1;    // initial count for road len: 1 -> "S"
        int wayEndWithBuilding = 1; // initial count for road len: 1 -> "B"

        for(int i = 2; i <= n; i++) {
            // count of ways(endB) for road length:i == count of ways(endS) for road length:i - 1
            int currWayEndWithBuilding = wayEndWithSpace;

            // count of ways(endS) for road len:i == count of ways(endS + endB) for road len:i - 1
            int currWayEndWithSpace = wayEndWithBuilding + wayEndWithSpace;

            // count of ways for len:i  becomes count of ways for len:i - 1 for len:i + 1
            wayEndWithBuilding = currWayEndWithBuilding;
            wayEndWithSpace = currWayEndWithSpace;
        }

        // total ways on one side of road
        long waysToArrangeOnOneSide = wayEndWithBuilding + wayEndWithSpace;
        return waysToArrangeOnOneSide * waysToArrangeOnOneSide; // total ways on both sides of road
    }
}
