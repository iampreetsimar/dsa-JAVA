// Given a graph, find and print all connected components of the graph.

// INPUT
// 7   -> # of vtx
// 5   -> # of edges
// 0 1 10
// 2 3 10
// 4 5 10
// 5 6 10
// 4 6 10

// OUTPUT
// [[0, 1], [2, 3], [4, 5, 6]]

import java.io.*;
import java.util.ArrayList;

public class getConnectedComponents_4 {
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

        System.out.println(gcc(graph));     // returns list<connected components>
    }

    public static ArrayList<ArrayList<Integer>> gcc(ArrayList<Edge>[] graph) {
        ArrayList<ArrayList<Integer>> allConnectedComponents = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];

        for(int vtx = 0; vtx < graph.length; vtx++) {   // chance to find component from each unvisited vtx
            if(!visited[vtx]) {     
                ArrayList<Integer> connectedComponent = new ArrayList<>();
                gcc(graph, vtx, visited, connectedComponent);   // adds all vtxs in curr component
                allConnectedComponents.add(connectedComponent);  // add curr component to list of comps
            }
        }

        return allConnectedComponents;
    }

    public static void gcc(ArrayList<Edge>[] graph, int src, boolean[] visited, 
                           ArrayList<Integer> connectedComponent) {
        // similar concept to HAS PATH -> no need to find all paths, just a single path 
        visited[src] = true;       // and mark those vtxs as visited
        connectedComponent.add(src);    // add current src vtx to curr component list
        for(Edge e: graph[src]) {
            if(!visited[e.nbr]) {
                gcc(graph, e.nbr, visited, connectedComponent);
            }
        }
    }
}
