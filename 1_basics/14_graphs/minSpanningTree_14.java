// Given a graph and a src vtx. vtx -> computers and edges -> length of LAN wire required to connect them.
// Find the min length of wire required to connect all computers over a network.
// Print the o/p in terms of which all PCs need to be connected and the length of wire b/w them.

// INPUT
// 7
// 8
// 0 1 10
// 1 2 10
// 2 3 10
// 0 3 40
// 3 4 2
// 4 5 3
// 5 6 3
// 4 6 8
// 0 -> src

// OUTPUT
// [1-0@10]
// [2-1@10]
// [3-2@10]
// [4-3@2]
// [5-4@3]
// [6-5@3]

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class minSpanningTree_14 {
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

        minSpanningTree(graph, src);    // finds min length of wire required to connect all PCs(vtxs)
    }

    static class Pair implements Comparable<Pair> {
        int vtx;    // edge b/w vtx and vtxParent
        int vtxParent;
        int wireLengthFromVtxToParent;  // weight of that edge

        Pair(int vtx, int vtxParent, int wireLengthFromVtxToParent) {
            this.vtx = vtx;
            this.vtxParent = vtxParent;
            this.wireLengthFromVtxToParent = wireLengthFromVtxToParent;
        }

        public int compareTo(Pair other) {  // min-priority order
            return this.wireLengthFromVtxToParent - other.wireLengthFromVtxToParent;
        }

        public String toString() {
            return "[ " + vtx + "-" + vtxParent + "@" + wireLengthFromVtxToParent + " ]";
        }
    }

    // Use PRIM'S ALGORITHM to find MIN SPANNING TREE
    // Similar to DIJKSTRA'S Algo 
    //              - instead of psf, we track parent vtx
    //              - instead of wsf, we track curr edge's weight
    public static void minSpanningTree(ArrayList<Edge>[] graph, int src) {
        boolean[] visited = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, -1, 0)); // src has no parent vtx, so -1. No edge, so weight: 0

        while(pq.size() > 0) {
            Pair p = pq.remove();   // highest min priority object

            if(visited[p.vtx])  // already visited -> move to next as cycles are ignored in spanning tree
                continue;

            visited[p.vtx] = true;  // mark curr object as visited
            if(p.vtxParent != -1)   // no need to print src vtx object as no parent and edge present for it
                System.out.println(p);  // print all edges indicating min spanning tree

            for(Edge e: graph[p.vtx]) { // traverse unvisited nbrs of curr object
                if(!visited[e.nbr]) // update nbr as vtx, curr object vtx as parent and weight of that edge
                    pq.add(new Pair(e.nbr, p.vtx, e.weight));   // this is the difference from Dijkstra's
            }
        }
    }
}
