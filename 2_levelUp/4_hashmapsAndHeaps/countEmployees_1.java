// Given a number N and 2*N number of strings that contains mapping of the employee and his manager.
// An employee directly reports to only one manager. All managers are employees but the reverse is not true. 
// An employee reporting to himself is the CEO of the company. Find the number of employees under each manager
// in the hierarchy not just their direct reports. 

// INPUT
// 6
// A C
// B C 
// C F 
// D E 
// E F 
// F F 

// OUTPUT
// A 0
// B 0
// C 2
// D 0
// E 1
// F 5

import java.io.*;
import java.util.*;

public class countEmployees_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<String, String> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            map.put(parts[0], parts[1]);
        }

        findCount(map);
    }

    public static void printCounts(Map<String, Integer> counts) {
        for(String emp : counts.keySet()) {
            System.out.println(emp + " : " + counts.get(emp));
        }
    }

    public static void findCount(Map<String, String> map) {
        StringBuilder ceo = new StringBuilder();
        Map<String, HashSet<String>> hierarchy = new HashMap<>();

        for(String emp : map.keySet()) {
            String manager = map.get(emp);

            if(emp.equals(manager)) {
                ceo.append(emp);
                continue;
            }

            if(hierarchy.containsKey(manager)) {
                HashSet<String> emps = hierarchy.get(manager);
                emps.add(emp);
            } else {
                HashSet<String> emps = new HashSet<>();
                emps.add(emp);
                hierarchy.put(manager, emps);
            }
        }

        Map<String, Integer> counts = new HashMap<>();
        getCounts(hierarchy, ceo.toString(), counts);
        printCounts(counts);
    }

    public static int getCounts(Map<String, HashSet<String>> hierarchy, String manager, Map<String, Integer> counts) {
        if(!hierarchy.containsKey(manager)) {
            counts.put(manager, 0);
            return 1;
        }

        int count = 0;
        for(String emp : hierarchy.get(manager)) {
            count += getCounts(hierarchy, emp, counts);
        }

        counts.put(manager, count);
        return count + 1;
    }
}