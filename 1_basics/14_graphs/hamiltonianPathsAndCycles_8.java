// Given a graph and a src vtx, find and print all hamiltonian paths and cycles starting from src.
// Cycles end with "*" and paths end with ".".
// HAMILTONIAN PATH - visits all vtxs without visiting any vtx twice
// HAMILTONIAN CYCLE - Hamiltonian path becomes a cycle if there is an edge b/w first(src) and last vtx.
// Print in lexicographically increasing order.

// INPUT
// 7   -> vtxs
// 9   -> edges
// 0 1
// 1 2 
// 2 3
// 0 3
// 3 4
// 4 5
// 5 6
// 4 6
// 2 5
// 0   -> src

// OUTPUT
// 0123456.
// 0123465.
// 0125643*
// 0346521*

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class hamiltonianPathsAndCycles_8 {
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
        int vertices = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[vertices]; 
        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>(); 
        }
        int edges = Integer.parseInt(br.readLine());
        for(int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);

            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));    // not present in directed graph
        }
        int src = Integer.parseInt(br.readLine());

        printHamiltonianPathsAndCycles(graph, src, new HashSet<Integer>(), src + "", src); 
    }

    // Same concept as All Paths
    // Instead of visited array, use HashSet to visited vtxs so to get # of visited vtxs in BASE CASE
    // Also, srcOrig as parameter to compare nbr of last vtx to srcOrig to know HAMILTONIAN CYCLE
    public static void printHamiltonianPathsAndCycles(ArrayList<Edge>[] graph, int src, 
                                                      HashSet<Integer> visited, String psf, int srcOrig) {
        if(visited.size() == graph.length - 1) {    // BASE CASE : size of hashSet == graph.length - 1
            boolean cycleFlag = false;
            for(Edge e: graph[src]) {   // to check if last vtx has srcOrig as nbr
                if(e.nbr == srcOrig) {  
                    cycleFlag = true;
                    break;
                }
            }

            if(cycleFlag)   // if true, path is Hamiltonian Cycle, else Hamiltonian Path
                System.out.println(psf + '*');
            else        
            System.out.println(psf + '.');

            return;
        }

        visited.add(src);       // Same concept as All Paths - add curr src vtx to visited hashSet
        for(Edge e: graph[src]) {   // visit to all unvisited nbrs of curr src vtx and update psf
            if(!visited.contains(e.nbr)) {
                printHamiltonianPathsAndCycles(graph, e.nbr, visited, psf + e.nbr, srcOrig);
            }
        }
        visited.remove(src);    // remove curr src vtx from hashSet to check other possible paths                          
    }
}
