// Given a DAG(directed acyclic graph), vtxs -> tasks and edges -> dependencies b/w tasks.
// Find and print the order in which tasks can be done. The task that should be done last should be printed first.
// The task which should be done first should be printed last. This is TOPOLOGICAL SORT.

// TOPOLOGICAL SORT - permutation of vtxs for a DAG if for all directed edges 'uv', u appears before v in graph.

// INPUT
// 7
// 7
// 0 1 10
// 1 2 10
// 2 3 10
// 0 3 10
// 4 5 10
// 5 6 10
// 4 6 10

// OUTPUT (order of compilation - reverse of topological order)
// 4
// 5
// 6
// 0
// 1
// 2
// 3

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class topologicalSort_16 {
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

            graph[v1].add(new Edge(v1, v2, wt));  // directed graph
        }

        topologicalSort(graph); 
    }

    public static void topologicalSort(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        Stack<Integer> s = new Stack<>();

        for(int vtx = 0; vtx < graph.length; vtx++) {   // checks all comps using gcc()
            if(!visited[vtx])
                gcc(graph, vtx, visited, s);
        }

        while(s.size() > 0) {   // tasks stored in stack in reverse order of order of compilation
            System.out.println(s.pop());
        }
    }

    // Has Path Concept as we need to traverse only once to get the order
    // task(vtx) having no outgoing edge means no dependence on any other task
    // Stack(LIFO) adds the task which has no dependence first and removes the same last
    // Queue(FIFO) adds the task which has no dependence first and remove the same first
    // if we use Queue instead of stack, we get tasks based on ORDER of COMPILATION
    public static void gcc(ArrayList<Edge>[] graph, int src, boolean[] visited, Stack<Integer> s) {
        visited[src] = true;    
        for(Edge e: graph[src]) {
            if(!visited[e.nbr])
                gcc(graph, e.nbr, visited, s);
        }

        s.push(src);    // add task to stack in postorder while going down the recursive stack
    }
}
