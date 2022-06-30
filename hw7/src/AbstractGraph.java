import java.util.*;

/** Abstract base class for graphs. A graph is a set of vertices and
 a set of edges. Vertices are represented by integers
 from 0 to n ‐ 1. Edges are ordered pairs of vertices.
 */
public abstract class AbstractGraph implements Graph{

    /** The number of vertices */
    private int numV;

    /** Flag to indicate whether this is a directed graph */
    private boolean directed;

    /** Construct a graph with the specified number of vertices and the directed
     flag. If the directed flag is true, this is a directed graph.
     @param numV The number of vertices
     @param directed The directed flag
     */
    public AbstractGraph(int numV, boolean directed) {
        this.numV = numV;
        this.directed = directed;
    }

    /**
     * Default constructor; sets number of vertices to 0 and directed to false.
     */
    public AbstractGraph(){
        numV = 0;
        directed = false;
    }

    /** Return the number of vertices.
     @return The number of vertices
     */
    public int getNumV() {
        return numV;
    }

    /** Return whether this is a directed graph.
     @return true if this is a directed graph
     */
    public boolean isDirected() {
        return directed;
    }

    /** Load the edges of a graph from the data in an input file. The file
     should contain a series of lines, each line with two or
     three data values. The first is the source, the second is
     the destination, and the optional third is the weight.
     @param scan The Scanner connected to the data file
     */
    public void loadEdgesFromFile(Scanner scan) {


    }

    /** Factory method to create a graph and load the data from an input
     file. The first line of the input file should contain the number
     of vertices. The remaining lines should contain the edge data as
     described under loadEdgesFromFile.
     @param scan The Scanner connected to the data file
     @param isDirected true if this is a directed graph,
     false otherwise
     */
    public static Graph createGraph(Scanner scan, boolean isDirected) {
        int numV = scan.nextInt();
        AbstractGraph returnValue;
        returnValue = new MyGraph(numV, isDirected);
        returnValue.loadEdgesFromFile(scan);
        return returnValue;
    }
}