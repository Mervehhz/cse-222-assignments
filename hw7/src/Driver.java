/**
 * Driver class
 */
public class Driver {

    public static void main(String[] args) {

        MyGraph graph = new MyGraph(10, false);

        Vertex vertex0 = new Vertex("A", 23, 0);
        Vertex vertex1 = new Vertex("B", 45, 1);
        Vertex vertex2 = new Vertex("C", 2, 2);
        Vertex vertex3 = new Vertex("D", 3, 3);
        Vertex vertex4 = new Vertex("E", 76, 4);
        Vertex vertex5 = new Vertex("F", 89, 5);
        Vertex vertex6 = new Vertex("G", 575, 6);
        Vertex vertex7 = new Vertex("H", 897, 7);
        Vertex vertex8 = new Vertex("I", 24, 8);
        Vertex vertex9 = new Vertex("J", 143, 9);

        System.out.println("Adding vertexes to graph...\n");

        graph.addVertex(vertex0);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);
        graph.addVertex(vertex6);
        graph.addVertex(vertex7);
        graph.addVertex(vertex8);
        graph.addVertex(vertex9);

        System.out.println("Adding edges between vertexes to graph...\n");

        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 11);
        graph.addEdge(1, 3, 12);
        graph.addEdge(1, 4, 13);
        graph.addEdge(2, 3, 14);
        graph.addEdge(3, 4, 15);
        graph.addEdge(4, 5, 16);
        graph.addEdge(3, 5, 17);
        graph.addEdge(5, 6, 18);
        graph.addEdge(6, 7, 19);
        graph.addEdge(7, 8, 20);
        graph.addEdge(8, 9, 21);
        graph.addEdge(9, 2, 22);

        System.out.println("------------------GRAPH----------------\n");

        graph.printGraph();

        System.out.println("\n\n------------------EDGES----------------\n");

        System.out.println(graph.getEdge(vertex0, vertex1).toString());
        System.out.println(graph.getEdge(vertex1, vertex2).toString());
        System.out.println(graph.getEdge(vertex1, vertex3).toString());
        System.out.println(graph.getEdge(vertex1, vertex4).toString());
        System.out.println(graph.getEdge(vertex2, vertex3).toString());
        System.out.println(graph.getEdge(vertex3, vertex4).toString());
        System.out.println(graph.getEdge(vertex4, vertex5).toString());
        System.out.println(graph.getEdge(vertex3, vertex5).toString());
        System.out.println(graph.getEdge(vertex5, vertex6).toString());
        System.out.println(graph.getEdge(vertex6, vertex7).toString());
        System.out.println(graph.getEdge(vertex7, vertex8).toString());
        System.out.println(graph.getEdge(vertex8, vertex9).toString());
        System.out.println(graph.getEdge(vertex9, vertex2).toString());

        System.out.println("\n\nExporting matrix...");
        double [][] matrix = graph.exportMatrix();

        for(int i=0;i<matrix.length;i++) {
            for (int k = 0; k < matrix[i].length; k++)
                System.out.print(matrix[i][k] + "   ");
            System.out.print("\n");
        }

        System.out.println("\n\n---------User defined property of vertex0-----------\n");
        vertex0.userDefinedProperty("color", "red", "shape", "circle", "size", "5", "boosting", "2.5");
        vertex0.printUDP();
        System.out.println("\n");
        vertex1.userDefinedProperty("color", "red", "shape", "square", "size", "7", "boosting", "3.5");
        vertex1.printUDP();
        System.out.println("\n");
        vertex2.userDefinedProperty("color", "green", "shape", "ellipse", "size", "9", "boosting", "4.5");
        vertex2.printUDP();

        System.out.println("\n\nVertices are filtering according to color(red)...");
        graph.filterVertices("color", "red").printGraph();

        /*System.out.println("\nGenerating new vertex...");
        Vertex new_vertex = graph.newVertex("X", 111);
        System.out.println("Label: " + new_vertex.getLabel() + "  Weight: " + new_vertex.getWeight() + "  Index: " + new_vertex.getIndex());*/

        System.out.println("\nRemoving edge between vertex 2(C) and 3(D)...");
        graph.removeEdge(2, 3);
        System.out.print("\n");
        graph.printGraph();

        System.out.println("\nRemoving vertex 4(E)...");
        graph.removeVertex(4);
        System.out.println("\n");
        graph.printGraph();

        System.out.println("\nRemoving label A...");
        graph.removeVertex("A");
        System.out.print("\n");
        graph.printGraph();
    }
}