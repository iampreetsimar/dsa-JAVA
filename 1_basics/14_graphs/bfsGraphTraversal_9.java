// Given a graph and a src vtx.
// do a bfs traversal and print which vtx is reached via which path, starting from src

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
// 2 -> src

// OUTPUT
// 2 @ 2
// 1 @ 21
// 3 @ 23
// 0 @ 210
// 4 @ 234
// 5 @ 2345
// 6 @ 2346

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class bfsGraphTraversal_9 {
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

        bfsTraversal(graph, src); 
    }

    static class Pair {
        int vtx;
        String pathFromSrcToVtx;

        Pair(int vtx, String pathFromSrcToVtx) {
            this.vtx = vtx;
            this.pathFromSrcToVtx = pathFromSrcToVtx;
        }
    }

    public static void bfsTraversal(ArrayList<Edge>[] graph, int src) {
        ArrayDeque<Pair> q = new ArrayDeque<>();    // ensures vtxs are read in level order from src
        boolean[] visited = new boolean[graph.length];
        q.add(new Pair(src, src + ""));

        while(q.size() > 0) {       // order: r m* w a*
            Pair p = q.remove();

            if(visited[p.vtx]) {    // indicates p.vtx is already visited via another path
                continue;  // here, ignoring visited vtxs  // also shows a cycle is being formed at p.vtx
            }

            visited[p.vtx] = true;  // mark p.vtx as visited
            System.out.println(p.vtx + "@" + p.pathFromSrcToVtx);

            for(Edge e: graph[p.vtx]) {     // traverse through unvisited nbrs of p.vtx
                if(!visited[e.nbr]) {
                    q.add(new Pair(e.nbr, p.pathFromSrcToVtx + e.nbr));
                }
            }
        }
    }
}
