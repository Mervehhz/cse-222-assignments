public class Edge {

    /**
     * The destination vertex for an edge
     */
    private Vertex dest;

    /**
     * The source vertex for an edge
     */
    private Vertex source;

    /**
     * The weight
     */
    private double weight;

    /**
     * Constructs an Edge from source to dest . Sets the weight to 1.0
     * @param source The source vertex
     * @param dest The destination vertex
     */
    public Edge(Vertex source, Vertex dest){
        this.source = source;
        this.dest = dest;
        weight = 1.0;
    }

    /**
     * Constructs an Edge from source to dest . Sets the weight to w
     * @param source The source vertex
     * @param dest The destination vertex
     * @param w The weight
     */
    public Edge(Vertex source, Vertex dest, double w){
        this.source = source;
        this.dest = dest;
        this.weight = w;
    }

    /**
     * Compares two edges for equality. Edges are equal if their source
     * and destination vertices are the same. The weight is not considered
     * @param o Object that will compare
     * @return Returns true if objects are equal, false if objects are not equal
     */
    public boolean equals(Object o){
        return ((Edge) o).source == this.source && ((Edge) o).dest == this.dest;
    }

    /**
     * Returns the destination vertex
     * @return The destination vertex
     */
    public Vertex getDest(){
        return dest;
    }

    /**
     * Returns the source vertex
     * @return source vertex
     */
    public Vertex getSource(){
        return source;
    }

    /**
     * Returns the weight
     * @return the weight
     */
    public double getWeight(){
        return weight;
    }

    /**
     * Returns the hash code for an edge. The hash code depends only
     * on the source and destination
     * @return The hash code
     */
    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + source.hashCode();
        hash = 31 * hash + dest.hashCode();
        return hash;
    }

    /**
     * Returns a string representation of the edge
     * @return a string representation of the edge
     */
    @Override
    public String toString(){

        StringBuilder str = new StringBuilder();
        str.append("source-> " + this.source.getLabel() + "   destination-> " + this.dest.getLabel() + "   weight-> " + this.weight);

        return str.toString();
    }
}