package bfs.shortest.reach;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static class Graph {

        Vertex[] verticies;

        public class Vertex {

            List<Vertex> adj;
            int index;

            public Vertex(int index) {
                adj = new ArrayList<>();
                this.index = index;
            }
        }


        public Graph(int size) {
            verticies = new Vertex[size];
        }

        public void addEdge(int first, int second) {
            if (verticies[first] == null) {
                verticies[first] = new Vertex(first);
            }
            if (verticies[second] == null) {
                verticies[second] = new Vertex(second);
            }

            verticies[first].adj.add(verticies[second]);
            verticies[second].adj.add(verticies[first]);
        }

        public int[] shortestReach(int startId) { // 0 indexed
            int[] distances = new int[verticies.length];
            boolean[] visited = new boolean[verticies.length];

            for (int i = 0; i < distances.length; i++) {
                distances[i] = -1;
            }
            distances[startId] = 0;
            Queue<Vertex> q = new LinkedList<>();
            q.add(verticies[startId]);
            visited[startId] = true;
            while (!q.isEmpty()) {
                Vertex currentVertex = q.remove();
                currentVertex.adj.forEach(v -> {
                    if (!visited[v.index]) {
                        q.add(v);
                        visited[v.index] = true;
                        distances[v.index] = distances[currentVertex.index] + 6;
                    }
                });
            }

            return distances;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
