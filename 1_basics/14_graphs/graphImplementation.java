// IMPLEMENT a graph using adjacency list

//         40       2
//     0--------3--------4
//     |        |        | \
//  10 |      10|       3|  \8
//     |        |        |   \
//     |        |        |    \
//     1--------2        5-----6
//         10               3 

import java.util.ArrayList;

public class graphImplementation {
    static class Edge {
        int source;
        int nbr;
        int weight;

        Edge(int source, int nbr, int weight) {
            this.source = source;
            this.nbr = nbr;
            this.weight = weight;
        }

        public String toString() {  // prints edge object
            return source + " -> " + nbr + " @ " + weight;
        }
    }
    
    public static void main(String[] args) {
        int vertices = 7;       // vertices from 0 -> 6

        // bad practice to use array here, instead can use a ArrayList<ArrayList<Edge>>
        ArrayList<Edge>[] graph = new ArrayList[vertices];    // creates an array of vertices with null AL

        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();       // initialized an AL at each vertex
        }

        // vertex 0
        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 3, 40));

        // vertex 1
        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 2, 10));

        // vertex 2
        graph[2].add(new Edge(2, 1, 10));
        graph[2].add(new Edge(2, 3, 10));

        // vertex 3
        graph[3].add(new Edge(3, 0, 40));
        graph[3].add(new Edge(3, 2, 10));
        graph[3].add(new Edge(3, 4, 2));

        // vertex 4
        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 3));
        graph[4].add(new Edge(4, 6, 8));

        // vertex 5 
        graph[5].add(new Edge(5, 4, 3));
        graph[5].add(new Edge(5, 6, 3));

        // vertex 6
        graph[6].add(new Edge(6, 4, 8));
        graph[6].add(new Edge(6, 5, 3));


        // display graph
        for(int i = 0; i < graph.length; i++) {
            System.out.println(i + ": " + graph[i]);
        }
    }
}