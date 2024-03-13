// Given a graph, a src and a dest vertex, find all path which exists b/w src and dest vertex.
// Print all those paths. Print in lexicographical order.

// INPUT
// 7 -> # of vertices
// 8 -> # of edges
// Edges
// 0 1 10
// 1 2 10
// 2 3 10
// 0 3 10
// 3 4 10
// 4 5 10
// 5 6 10
// 4 6 10
// 0   -> src
// 6   -> dest

// OUTPUT
// 0123456
// 012346
// 03456
// 0346

import java.io.*;
import java.util.ArrayList;

public class allPaths_2 {
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
            int wt = Integer.parseInt(parts[2]);

            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));    // not present in directed graph
        }
        int src = Integer.parseInt(br.readLine());
        int dest = Integer.parseInt(br.readLine());
        
        allPaths(graph, src, dest, new boolean[graph.length], src + "");
    }

    static class Edge {
        int src;
        int nbr;
        int weight;

        Edge(int src, int nbr, int weight) {
            this.src = src;
            this.nbr = nbr;
            this.weight = weight;
        } 
    }

    // Initial value of visited : new boolean[graph.length] - all entries as false
    // Initial value of psf : "src"  
    public static void allPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf) {
        if(src == dest) {       // BASE CASE  - psf includes dest vtx
            System.out.println(psf);
            return;
        }

        visited[src] = true;    // mark current src as visited

        for(Edge e: graph[src]) {   // loop over current src's AL
            if(!visited[e.nbr]) {   // recursive call to unvisited nbrs of current src to prevent cycles
                allPaths(graph, e.nbr, dest, visited, psf + e.nbr);     // add nbr to psf
            }
        }

        // unmark current src as unvisited so that other possible paths can be found using current src
        visited[src] = false;
    }   
}
