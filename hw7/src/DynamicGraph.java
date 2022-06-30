public interface DynamicGraph extends Graph{

    /**
     * Generate a new vertex by given parameters.
     * @param label The label of vertex
     * @param weight The weight of vertex
     * @return generated new vertex
     */
    Vertex newVertex (String label, double weight);

    /**
     * Add the given vertex to the graph.
     * @param new_vertex vertex that will add
     * @return True if adding is successful, false if adding is unsuccessful
     */
    boolean addVertex (Vertex new_vertex);

    /**
     * Add an edge between the given two vertices in the graph.
     * @param vertexID1 Vertex id of source vertex
     * @param vertexID2 Vertex id of destination vertex
     * @param weight Weight of edge
     * @return True if adding is successful, false if adding is unsuccessful
     */
    boolean addEdge (int vertexID1, int vertexID2, double weight);

    /**
     * Remove the edge between the given two vertices.
     * @param vertexID1 Vertex id of source vertex
     * @param vertexID2 Vertex id of destination vertex
     * @return True if removing is successful, false if removing is unsuccessful
     */
    boolean removeEdge (int vertexID1, int vertexID2);

    /**
     * Remove the vertex from the graph with respect to the given vertex id.
     * @param vertexID Vertex id
     * @return True if removing is successful, false if removing is unsuccessful
     */
    boolean removeVertex (int vertexID);

    /**
     * Remove the vertices that have the given label from the graph.
     * @param label Vertex label
     * @return True if removing is successful, false if removing is unsuccessful
     */
    boolean removeVertex (String label);

    /**
     * Filter the vertices by the given user-defined
     * property and returns a subgraph of the graph.
     * @param key Key of the user defined property
     * @param filter Value of the user defined property
     */
    MyGraph filterVertices (String key, String filter);

    /**
     * Generate the adjacency matrix representation of the graph and returns
     * the matrix.
     * @return 2D array as matrix representation of the graph
     */
    double[][] exportMatrix();

    /**
     * Print the graph in adjacency list format
     */
    void printGraph();
}