// Given a graph representing people and their connectivity.
// Given a src person who got infected and time t.
// Find how many total people will get infected till time t, if the infection spreads to nbrs of infected person
// in 1 unit of time.

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
// 6   -> src
// 3   -> time t

// OUTPUT
// 4

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class spreadOfInfection_12 {
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
        int time = Integer.parseInt(br.readLine());

        System.out.println(countInfected(graph, src, time)); 
    }

    static class Pair {
        int vtx;
        int timePassedFromStart;

        Pair(int vtx, int timePassedFromStart) {
            this.vtx = vtx;
            this.timePassedFromStart = timePassedFromStart;
        }
    }

    public static int countInfected(ArrayList<Edge>[] graph, int src, int time) {
        int count = 0;
        boolean[] visited = new boolean[graph.length];
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, 1));    // src vtx will be infected at t:1
        while(q.size() > 0) {
            Pair p = q.remove();
            if(visited[p.vtx]) {    // if vtx already infected(visited), move to next vtx
                continue;
            }
            visited[p.vtx] = true;  // mark curr vtx as infected
            count++;    // increase count of infected
            
            // move to uninfected nbrs of curr vtx only if nbrs' time passed(curr vtx.timePassed + 1) <= time limit
            if(p.timePassedFromStart + 1 <= time) { 
                for(Edge e: graph[p.vtx]) {
                    if(!visited[e.nbr]) {
                        // nbrs timePassed will be incremented by 1 from curr vtx's timePassed
                        q.add(new Pair(e.nbr, p.timePassedFromStart + 1)); 
                    }
                }
            }
        }
        return count;   // return total infected persons till time t
    }
}
