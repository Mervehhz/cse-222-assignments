import java.util.*;
@SuppressWarnings("unchecked")
public class MyGraph extends AbstractGraph implements DynamicGraph{

    /** An array of Lists to contain the edges that
     originate with each vertex.
     */
    private List<Edge>[] edges;
    /**
     * A list of vertexes
     */
    private List<Vertex> vertexes = new ArrayList<>();
    /**
     * An arraylist of ids (index)
     */
    private ArrayList<Integer>  ids = new ArrayList<>();
    /**
     * Number of vertexes
     */
    private int vertexNum;
    private Random rand = new Random();

    /**
     * Default constructor; sets number of vertices to 0 and directed to false by calling super constructor.
     */
    public MyGraph(){
        super();
    }

    /** Construct a graph with the specified number of vertices and directionality.
     @param numV The number of vertices
     @param directed The directionality flag
     */
    public MyGraph(int numV, boolean directed) {
        super(numV, directed);
        vertexNum = numV;
        edges = new List[numV];
        for (int i = 0; i < numV; i++) {
            edges[i] = new LinkedList<>();
        }
    }

    /**
     * Returns list of the vertexes
     * @return list of the vertexes
     */
    public List<Vertex> getVertexes(){
        return vertexes;
    }

    /** Determine whether an edge exists.
     @param source The source vertex
     @param dest The destination vertex
     @return true if there is an edge from source to dest
     */
    public boolean isEdge(Vertex source, Vertex dest) {

        int index = source.getIndex();
        for(Edge edge : edges[index])
            if (Objects.equals(edge, new Edge(source, dest)))
                return true;
        return false;
    }

    /** Insert a new edge into the graph.
     @param edge The new edge
     */
    public void insert(Edge edge) {
        edges[edge.getSource().getIndex()].add(edge);
        if (!isDirected()) {
            edges[edge.getDest().getIndex()].add(new Edge(edge.getDest(), edge.getSource(),
                    edge.getWeight()));
        }
    }

    /**
     * Returns iterator
     * @param source The source vertex
     * @return An Iterator<Edge> object that can be used to iterate
     * through the edges adjacent to a given vertex.
     */
    public Iterator<Edge> edgeIterator(Vertex source) {
        return edges[source.getIndex()].iterator();
    }

    /** Get the edge between two vertices.
     @param source The source
     @param dest The destination
     @return the edge between these two vertices
     or null if an edge does not exist.
     */
    public Edge getEdge(Vertex source, Vertex dest) {
        Edge target = new Edge(source, dest, Double.POSITIVE_INFINITY);
        for (Edge edge : edges[source.getIndex()]) {
            if (edge.equals(target))
                return edge; // Desired edge found, return it.
        }
        // Assert: All edges for source checked.
        return null; // Desired edge not found.
    }

    /**
     * Generate a new vertex by given parameters.
     * @param label The label of vertex
     * @param weight The weight of vertex
     * @return generated new vertex
     */
    public Vertex newVertex (String label, double weight){
        return new Vertex(label, weight, uniqueID());
    }

    /**
     * Add the given vertex to the graph.
     * @param new_vertex vertex that will add
     * @return True if adding is successful, false if adding is unsuccessful
     */
    public boolean addVertex (Vertex new_vertex){
        vertexes.add(new_vertex);
        ids.add(new_vertex.getIndex(), new_vertex.getIndex());
        return true;
    }

    /**
     * Add an edge between the given two vertices in the graph.
     * @param vertexID1 Vertex id of source vertex
     * @param vertexID2 Vertex id of destination vertex
     * @param weight Weight of edge
     * @return True if adding is successful, false if adding is unsuccessful
     */
    public boolean addEdge (int vertexID1, int vertexID2, double weight){

        Vertex temp0 = findVertexAccordingToID(vertexID1);
        Vertex temp1 = findVertexAccordingToID(vertexID2);

        if(temp0 != null && temp1 != null)
            insert(new Edge(temp0, temp1, weight));
        return true;
    }

    /**
     * Remove the edge between the given two vertices.
     * @param vertexID1 Vertex id of source vertex
     * @param vertexID2 Vertex id of destination vertex
     * @return True if removing is successful, false if removing is unsuccessful
     */
    public boolean removeEdge (int vertexID1, int vertexID2){

        Vertex temp0 = findVertexAccordingToID(vertexID1);
        Vertex temp1 = findVertexAccordingToID(vertexID2);

        if(temp0 != null && temp1 != null) {
            edges[vertexID1].remove(new Edge(temp0, temp1));
            if (!isDirected()) edges[vertexID2].remove(new Edge(temp1, temp0));
            return true;
        }
        return false;
    }

    /**
     * Remove the vertex from the graph with respect to the given vertex id.
     * @param vertexID Vertex id
     * @return True if removing is successful, false if removing is unsuccessful
     */
    public boolean removeVertex (int vertexID){

        Vertex temp0 = findVertexAccordingToID(vertexID);

        if(temp0 != null){
            removeEdge(temp0);
            shift(vertexID);
            vertexes.remove(temp0);
            ids.remove(vertexID);
            vertexNum--;
            return true;
        }
        return false;
    }

    /**
     * Removes all edges connected to given vertex as parameter
     * @param temp Destination vertex
     */
    private void removeEdge(Vertex temp){

        for(Vertex v : vertexes){
            if(isEdge(v, temp)) removeEdge(v.getIndex(), temp.getIndex());
        }
    }

    /**
     * Shifts array toward up from last index to index of removed vertex
     * @param vertexID ID of the removed vertex
     */
    private void shift(int vertexID){

        for(int i=vertexID;i<vertexNum-1;i++){
            edges[i] = edges[i+1];
        }
    }

    /**
     * Remove the vertices that have the given label from the graph.
     * @param label Vertex label
     * @return True if removing is successful, false if removing is unsuccessful
     */
    public boolean removeVertex (String label){

        for(int i=0;i<vertexes.size();i++) {
            Vertex v = vertexes.get(i);
            if (v.getLabel().compareTo(label) == 0) {
                removeEdge(v);
                shift(v.getIndex());
                vertexes.remove(v);
                ids.remove(v.getIndex());
                vertexNum--;
            }
        }
        return true;
    }

    /**
     * Filter the vertices by the given user-defined
     * property and returns a subgraph of the graph.
     * @param key key of user defined property
     * @param filter value of user defined property
     * @return Subgraph of filtering vertex
     */
    public MyGraph filterVertices (String key, String filter){

        MyGraph subGraph = new MyGraph();

        for(Vertex v : vertexes) {
            Set entrySet = v.getUdp().entrySet();
            Iterator it = entrySet.iterator();

            while(it.hasNext()){
                Map.Entry me = (Map.Entry)it.next();
                if(me.getKey().equals(key) && me.getValue().equals(filter)){
                    subGraph.addVertex(v);
                    subGraph.vertexNum += 1;
                }
            }
        }
        subGraph.edges = new List[subGraph.vertexNum];
        for (int i = 0; i < subGraph.vertexNum; i++) {
            subGraph.edges[i] = new LinkedList<>();
        }
        for (Vertex temp : subGraph.getVertexes()) {
            for (Edge edge : edges[temp.getIndex()])
                subGraph.edges[temp.getIndex()].add(edge);
        }
        return subGraph;
    }

    /**
     * Generate the adjacency matrix representation of the graph and returns
     * the matrix.
     * @return 2D array as matrix representation of the graph
     */
    public double[][] exportMatrix(){

        double[][] matrix = new double[vertexes.size()][vertexes.size()];

        for(List<Edge> el : edges){
            for(Edge e : el) {
                matrix[e.getSource().getIndex()][e.getDest().getIndex()] = e.getWeight();
                if(!isDirected())
                    matrix[e.getDest().getIndex()][e.getSource().getIndex()] = e.getWeight();
            }
        }
        return matrix;
    }

    /**
     * Print the graph in adjacency list format
     */
    public void printGraph(){

        for(int i=0;i<vertexNum;i++){
            List<Edge> el = edges[i];
            System.out.print("(" + vertexes.get(i).getLabel() + ")->> ");
            int k=0;
            for(Edge e : el) {
                System.out.print("[" + e.getDest().getLabel() + "|" + e.getWeight() + "]");
                k++;
                if(k<el.size()) System.out.print("->");
            }
            System.out.print("\n");
        }
    }

    /**
     * Generates unique id for each vertex by using random library
     * @return Unique id
     */
    private int uniqueID(){

        boolean flag;
        int r;
        do {
            flag = false;
            r = Math.abs(rand.nextInt() % vertexNum);
            for (int o : ids)
                if (o == r) flag = true;
        }while(flag);

        ids.add(r,r);
        return r;
    }

    /**
     * Finds vertex according to index number
     * @param id ID of the vertex
     * @return Vertex if search is successful, null if search is not successful
     */
    private Vertex findVertexAccordingToID(int id){

        for (Vertex v : vertexes)
            if (v.getIndex() == id)
                return v;
        return null;
    }
}