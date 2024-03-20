// Given a graph and a src vtx, do an iterative dfs traversal and print which vtx is reached via which path
// starting from src.
// Iterative DFS should mimic "Reverse Preorder" , i.e, nbr with highest value should be visited first
// and should be printed on the way down in recursion.

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

// INPUT
// 2 @ 2
// 3 @ 23
// 4 @ 234
// 6 @ 2346
// 5 @ 23465
// 0 @ 230
// 1 @ 2301

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class dfsIterative_15 {
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

        dfsIterative(graph, src); 
    }

    static class Pair {
        int vtx;
        String pathFromSrcToVtx;

        Pair(int vtx, String pathFromSrcToVtx) {
            this.vtx = vtx;
            this.pathFromSrcToVtx = pathFromSrcToVtx;
        }

        public String toString() {
            return vtx + "@" + pathFromSrcToVtx;
        }
    }

    // Similar to BFS traversal but use stack instead of queue
    // traverses like REVERSE EULER PATH of RECURSIVE DFS
    // Need: instead of using call stack(memory limit) in recursion, use a stack object in heap(no memory limit)
    public static void dfsIterative(ArrayList<Edge>[] graph, int src) {
        boolean[] visited = new boolean[graph.length];
        Stack<Pair> s = new Stack<>();
        s.push(new Pair(src, src + ""));

        while(s.size() > 0) {
            Pair p = s.pop();

            if(visited[p.vtx])
                continue;

            visited[p.vtx] = true;
            System.out.println(p);

            for(Edge e: graph[p.vtx]) {
                if(!visited[e.nbr])
                    s.push(new Pair(e.nbr, p.pathFromSrcToVtx + e.nbr));
            }
        }
    }
}
