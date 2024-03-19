// Given a graph, find and print if graph is cyclic or not.
// A graph is cyclic, i.e., contains a cycle, if any of its components is cyclic.
// Cyclic means can reach from multiple paths

// Application of Get Connected Components + BFS Traversal

// INPUT
// 7
// 6
// 0 1 10
// 1 2 10
// 2 3 10
// 3 4 10
// 4 5 10
// 5 6 10

// OUTPUT
// false

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class isGraphCyclic_10 {
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

        System.out.println(isGraphCyclic(graph)); 
    }

    public static boolean isGraphCyclic(ArrayList<Edge>[] graph) { // GCC concept
        boolean[] visited = new boolean[graph.length];

        for(int vtx = 0; vtx < graph.length; vtx++) {   // check if curr vtx is unvisited
            if(!visited[vtx] && isComponentCyclic(graph, vtx, visited)) {   // and comp from curr vtx is cyclic
                return true;
            }
        }
        return false;   // no cycle found in any of the graph components
    }

    public static boolean isComponentCyclic(ArrayList<Edge>[] graph, int src, boolean[] visited) {
        ArrayDeque<Integer> q = new ArrayDeque<>();     // use BFS traversal to find a cycle
        q.add(src);    

        while(q.size() > 0) {
            int vtx = q.remove();

            if(visited[vtx]) // indicates vtx has been marked visited earlier from a different path
                return true;    // cycle formed at vtx -> component is cyclic -> graph is cyclic

            visited[vtx] = true;    
            for(Edge e: graph[vtx]) {
                if(!visited[e.nbr])
                    q.add(e.nbr);
            }
        }
        return false;   // component is not cyclic but bfs traversal complete
    }
}
