// Given a graph and a src vtx where vtxs represent cities and edges represent distance in kms.
// Find shortest path to each city from the src city in terms of distance.

// INPUT
// 7
// 8
// 0 1 10
// 1 2 10
// 2 3 10
// 0 3 10
// 3 4 10
// 4 5 10
// 5 6 10
// 4 6 10
// 0   -> src

// OUTPUT
// 0 via 0@0
// 1 via 01@10
// 2 via 012@20
// 3 via 0123@30
// 4 via 01234@32
// 5 via 012345@35
// 6 via 0123456@38

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class dijkstraAlgo_13 {
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

        shortestPathByDistance(graph, src);
    }

    static class Pair implements Comparable<Pair> {
        int vtx;
        String pathFromSrcToVtx;
        int weightFromSrcToVtx;

        Pair(int vtx, String pathFromSrcToVtx, int weightFromSrcToVtx) {
            this.vtx = vtx;
            this.pathFromSrcToVtx = pathFromSrcToVtx;
            this.weightFromSrcToVtx = weightFromSrcToVtx;
        }

        public int compareTo(Pair other) {  // for min-priority pq
            return this.weightFromSrcToVtx - other.weightFromSrcToVtx;
        }

        public String toString() {
            return this.vtx + " via " + this.pathFromSrcToVtx + "@" + this.weightFromSrcToVtx;
        }
    }

    // use DIJKSTRA'S ALGO: use BFS traversal using priority queue
    // keeps track of min weight so far starting from src
    public static void shortestPathByDistance(ArrayList<Edge>[] graph, int src) {
        boolean[] visited = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, src + "", 0));

        while(pq.size() > 0) {
            Pair p = pq.remove();   // highest min-priority object

            if(visited[p.vtx])  // if already visited, move to next object
                continue;

            visited[p.vtx] = true;  // mark curr object as visited
            System.out.println(p);  

            for(Edge e: graph[p.vtx]) { // add all unvisited nbrs of curr object vtx
                if(!visited[e.nbr]) {   // add curr object's vtx to psf and curr object's edge's wt to wsf
                    pq.add(new Pair(e.nbr, p.pathFromSrcToVtx + e.nbr, p.weightFromSrcToVtx + e.weight));
                }
            }
        }
    }
}
