// Given a graph, a src and a dest vertex.
// Also given two number, criteria and k.
// Find and print the value of:
        // - smallest path by weight and its weight
        // - largest path by weight and its weight
        // - ceil(just larger) path of criteria by weight and its weight
        // - floor(just smaller) path of criteria by weight and its weight
        // - kth largest path by weight and its weight

// INPUT
// 7   -> # of vtx
// 8   -> # of edges
// 0 1 10
// 1 2 10
// 2 3 10
// 0 3 40
// 3 4 2
// 4 5 3
// 5 6 3
// 4 6 8
// 0   -> src
// 6   -> dest
// 3   -> k
// 40  -> criteria

// OUTPUT
// 0123456@38  -> smallest path
// 0346@50     -> largest path
// 03456@48    -> ceil path
// 0123456@38  -> floor path
// 012346@40   -> kth largest path

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class multisolver_3 {
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

        int k = Integer.parseInt(br.readLine());
        int criteria = Integer.parseInt(br.readLine());
        
        multisolver(graph, src, dest, new boolean[graph.length], k, criteria, src + "", 0);

        System.out.println(sPath + " @ " + sPathWt);
        System.out.println(lPath + " @ " + lPathWt);
        System.out.println(ceilPath + " @ " + ceilPathWt);
        System.out.println(floorPath + " @ " + floorPathWt);
        System.out.println(pq.peek().psf + " @ " + pq.peek().wsf);
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

    static class Pair implements Comparable<Pair> {
        String psf;
        int wsf;

        Pair(String psf, int wsf) {
            this.psf = psf;
            this.wsf = wsf;
        }

        public int compareTo(Pair other) {  // gives ascending order for Pair type objects
            return this.wsf - other.wsf;
        }
    }
    
    static String sPath, lPath, ceilPath, floorPath;
    static Integer sPathWt = Integer.MAX_VALUE;     // identity of min: +infinity
    static Integer lPathWt = Integer.MIN_VALUE;     // identity if max: -infinity
    static Integer ceilPathWt = Integer.MAX_VALUE;  // smallest of largest -> identity of min: +infinity
    static Integer floorPathWt = Integer.MIN_VALUE; // largest of smallest -> identity of max: -infinity
    static PriorityQueue<Pair> pq = new PriorityQueue<>();  // to get kth largest

    // Initial psf: "src" | Initial wsf: 0 | Initial visited: new boolean[graph.length]
    // Use TRAVEL & CHANGE APPROACH along with ALL PATHS concept
    public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited,  
                                   int k, int criteria, String psf, int wsf) {
        if(src == dest) {       // BASE CASE -> dest vtx and its edge wt present in psf & wsf
            if(wsf < sPathWt) {     // found smaller path wt than min path wt
                sPath = psf; sPathWt = wsf;
            }
            if(wsf > lPathWt) {     // found largest path wt than max path wt
                lPath = psf; lPathWt = wsf;
            }
            if(wsf > criteria && wsf < ceilPathWt) {    // larger than criteria but smaller than ceil
                ceilPathWt = wsf; ceilPath = psf;
            }
            if(wsf < criteria && wsf > floorPathWt) {   // smaller than criteria but larger than floor
                floorPathWt = wsf; floorPath = psf;
            }
            if(pq.size() < k) {     // add pair object<psf,wsf> to pq till k objects
                pq.add(new Pair(psf, wsf));
            } else {                // remove a weaker pair object from pq peek and add a stronger one
                if(wsf > pq.peek().wsf) {
                    pq.remove(); pq.add(new Pair(psf, wsf));
                }       // when all paths are traversed, only k largest objects remain
            }           // pq peek shows kth largest object
            return;
        }
        visited[src] = true;    // same concept as ALL PATHS to traverse all paths
        for(Edge e: graph[src]) {
            if(!visited[e.nbr]) {  // also add current edge's(curr src -> its nbr) weight to wsf
                multisolver(graph, e.nbr, dest, visited, k, criteria, psf + e.nbr, wsf + e.weight);
            }
        }
        visited[src] = false;
    }
}
