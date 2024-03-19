// Given a graph, find and print if graph is bipartite or not.
// A graph is bipartite, if its possible to split its vtxs into two sets of mutually exclusive and exhaustive
// vtxs such that all edges are across the two sets.

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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class isGraphBipartite_11 {
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

        System.out.println(isGraphBipartite(graph)); 
    }

    static class Pair {
        int vtx;
        int levelFromSrcToVtx;

        Pair(int vtx, int levelFromSrcToVtx) {
            this.vtx = vtx;
            this.levelFromSrcToVtx = levelFromSrcToVtx;
        }
    }

    public static boolean isGraphBipartite(ArrayList<Edge>[] graph) {   // GCC Concept
        int[] visited = new int[graph.length];  // stores level of vtx at first visit
        Arrays.fill(visited, -1);   // -1 shows vtx is unvisited at first
        for(int vtx = 0; vtx < graph.length; vtx++) {   // to check all components
            if(visited[vtx] == -1 && // if vtx is univisited && a comp is NON-BIPARTITE
               isCompBipartite(graph, vtx, visited) == false)
                return false;   // -> graph becomes NON-BIPARTITE
        }
        return true;    // all comps BIPARTITE -> graph is BIPARTITE
    } 

    // isGraphCyclic Concept
    // Pair<vtx, level> : level here is the curr level of vtx from src and is 1 greater than its parent vtx
    public static boolean isCompBipartite(ArrayList<Edge>[] graph, int src, int[] visited) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, 0));

        while(q.size() > 0) {
            Pair p = q.remove();

            if(visited[p.vtx] != -1) {  // if vtx is revisited -> compare first visit level with curr level
                if(p.levelFromSrcToVtx != visited[p.vtx])   // -> diff level -> odd cycle
                    return false;   // -> comp NON-BIPARTITE -> return false
            } else {
                visited[p.vtx] = p.levelFromSrcToVtx; // -> first visit for vtx -> set visited[vtx] as curr level
            }

            for(Edge e: graph[p.vtx]) { // check for vtx's unvisited nbrs -> add them to queue for level order reading
                if(visited[e.nbr] == -1) {
                    q.add(new Pair(e.nbr, p.levelFromSrcToVtx + 1));    // increase nbr level by 1 more than curr vtx
                }
            }
        }
        return true; // level order complete -> comp is ACYCLIC/EVEN CYCLIC -> comp BIPARTITE -> return true
    }
}
