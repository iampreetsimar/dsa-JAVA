// Given a graph, find and print if the graph is connected.
// A graph is connected if there is a path from every vtx to every other.

// Application of GetConnectedComponents -> if there is more than one connected component, graph is not connected 

// INPUT
// 7
// 5
// 0 1 10
// 2 3 10
// 4 5 10
// 5 6 10
// 4 6 10

// OUTPUT
// false

import java.io.*;
import java.util.ArrayList;

public class isGraphConnected_5 {
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

        System.out.println(isGraphConnected(graph));
    }

    public static boolean isGraphConnected(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        int count = 0;  //no need to store AL<AL>, instead will keep count of times, we traverse into a component
        for(int vtx = 0; vtx < graph.length; vtx++) {
            if(!visited[vtx]) {
                dfsTraversal(graph, vtx, visited);
                count++;    
            }

            if(count > 1)   // if we traverse more than once, graph is not connected
                return false;
        }
        return true;    // only one traversal -> one component -> graph is connected
    }

    public static void dfsTraversal(ArrayList<Edge>[] graph, int src, boolean[] visited) {
        visited[src] = true;        // HAS PATH concept using dfs traversal
        for(Edge e: graph[src]) {
            if(!visited[e.nbr]) {
                dfsTraversal(graph, e.nbr, visited);
            }
        }
    }
}
