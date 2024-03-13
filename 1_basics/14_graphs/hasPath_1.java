// Given a graph, a src and a dest vertex, find if a path exists b/w src and dest vertex.
// Print true if path exists, otherwise print false.

// Input
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

import java.io.*;
import java.util.ArrayList;

public class hasPath_1 {
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
        System.out.println(hasPath(graph, src, dest, new boolean[graph.length]));
    }

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
        if(src == dest)         // BASE CASE
            return true;        // path exists

        visited[src] = true;        // mark current src as visited

        for(Edge e: graph[src]) {   // loop over current src' nbrs
            if(visited[e.nbr] == false &&   // get unvisited nbrs
               hasPath(graph, e.nbr, dest, visited))  // if path exists from unvisited nbr to dest
                return true;        // => path exists from current src to dest -> return true
        }
        
        return false;   // all nbrs checked -> no path found from current src to dest -> return false
    }
}
