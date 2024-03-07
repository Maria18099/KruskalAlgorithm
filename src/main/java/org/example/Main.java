package org.example;

import java.util.*;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class Graph {
    int V, E;//V-кількість вершин, E- кількість ребер
    Edge[] edges;//масив ребер графа

    Graph(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
        for (int i = 0; i < e; ++i)
            edges[i] = new Edge();
    }
    //знайти батькіський елемент для вузла і
    int find(int[] parent, int i) {//i=0
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }
    //додає в масив parent зв`язок між вузлами
    void union(int[] parent, int x, int y) {//x=0, y=1
        int xset = find(parent, x);//3
        int yset = find(parent, y);//1
        parent[xset] = yset;
    }

    void KruskalMST() {
        Edge[] result = new Edge[V];
        for (int i = 0; i < V; ++i)
            result[i] = new Edge();

        Arrays.sort(edges);

        int[] parent = new int[V];

        Arrays.fill(parent, -1);

        int i = 0;//для перебору елементів масиву відсортованих ребер
        int e = 0;
        while (e < V - 1) {
            Edge next_edge = edges[i];
            i++;
            int x = find(parent, next_edge.source);//x=3
            int y = find(parent, next_edge.destination);//y=1

            if (x != y) {
                result[e++] = next_edge;
                union(parent, x, y);
            }
        }
        System.out.println("Мінімальне остовне дерево:");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].source + " - " + result[i].destination + ": " + result[i].weight);
    }
}
public class Main {
    public static void main(String[] args) {
        int V = 4;
        int E = 5;

        Graph graph = new Graph(V, E);

        // Додаємо ребра та їх вагу
        graph.edges[0].source = 0;
        graph.edges[0].destination = 1;
        graph.edges[0].weight = 10;

        graph.edges[1].source = 0;
        graph.edges[1].destination = 2;
        graph.edges[1].weight = 6;

        graph.edges[2].source = 0;
        graph.edges[2].destination = 3;
        graph.edges[2].weight = 5;

        graph.edges[3].source = 1;
        graph.edges[3].destination = 3;
        graph.edges[3].weight = 15;

        graph.edges[4].source = 2;
        graph.edges[4].destination = 3;
        graph.edges[4].weight = 4;

        graph.KruskalMST();
    }
}