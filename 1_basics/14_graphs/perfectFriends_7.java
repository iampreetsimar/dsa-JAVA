// Given a number n(# of students), each student having id from 0 to n - 1.
// Given a number k representing # of clubs.
// In next k lines, two numbers are given representing ids of students belonging to same club.
// Find in how many ways can we select a PAIR of students such that both students are from different clubs.

// Application of Get Connected Components
// n -> # of vtxs
// k -> # of edges

// INPUT
// 7
// 5
// 0 1
// 2 3
// 4 5
// 5 6
// 4 6

// OUTPUT
// 16

import java.io.*;
import java.util.ArrayList;

public class perfectFriends_7 {
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[n]; 
        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>(); 
        }
        int k = Integer.parseInt(br.readLine());
        for(int i = 0; i < k; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);

            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));    // not present in directed graph
        }

        System.out.println(countPairCombinations(graph));  
    }

    public static ArrayList<ArrayList<Integer>> gcc(ArrayList<Edge>[] graph) {
        ArrayList<ArrayList<Integer>> allComps = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++) {
            if(!visited[i]) {
                ArrayList<Integer> comp = new ArrayList<>();
                gcc(graph, i, visited, comp);
                allComps.add(comp);
            }
        }
        return allComps;
    }

    public static void gcc(ArrayList<Edge>[] graph, int src, boolean[] visited, ArrayList<Integer> comp) {
        visited[src] = true;
        comp.add(src);
        for(Edge e: graph[src]) {
            if(!visited[e.nbr]) {
                gcc(graph, e.nbr, visited, comp);
            }
        }
    }

    // Use Get Connected Components to fetch total connected clubs
    // Each vtx in a connected component represent a student id in that connected club
    public static int countPairCombinations(ArrayList<Edge>[] graph) {
        int count = 0;
        ArrayList<ArrayList<Integer>> allComps = gcc(graph);    // returns all connected clubs
        for(int i = 0; i < allComps.size(); i++) {         
            for(int j = i + 1; j < allComps.size(); j++) {
                int ithCompSize = allComps.get(i).size();
                int jthCompSize = allComps.get(j).size();
                count += (ithCompSize * jthCompSize);  // computes total combinations to choose pair from two clubs
            }
        }
        return count;   // return total combinations
    }
}
