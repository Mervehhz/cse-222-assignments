import java.util.*;

public class Vertex {

    /**
     * The id of the vertex
     */
    private int index;

    /**
     * The label of the vertex
     */
    private String label;

    /**
     * The weight of the vertex
     */
    private double weight;

    /**
     * User-defined additional properties
     */
    private Map<String, String> udp = new HashMap<>();

    /**
     * Constructs vertex that have these properties: label, weight and id
     * @param label The label of the vertex
     * @param weight The weight of the vertex
     * @param id The id of the  vertex
     */
    public Vertex(String label, double weight, int id){
        this.index = id;
        this.label = label;
        this.weight = weight;
    }

    /**
     * The vertices may have user-defined additional properties, vertex class should be general.
     * @param args Properties
     */
    public void userDefinedProperty(String... args){

        for(int i=0;i<args.length;i+=2)
            udp.put(args[i], args[i+1]);
    }

    /**
     * Returns user-defined properties in map data structure
     * @return user-defined properties as map data structure
     */
    public Map<String, String> getUdp(){
        return udp;
    }

    /**
     * Sets index
     * @param index index of the vertex
     */
    public void setIndex(int index){
        this.index = index;
    }

    /**
     * Returns index
     * @return index of the vertex
     */
    public int getIndex(){
        return index;
    }

    /**
     * Sets label
     * @param label label of the vertex
     */
    public void setLabel(String label){
        this.label = label;
    }

    /**
     * Returns label
     * @return label of the vertex
     */
    public String getLabel(){
        return label;
    }

    /**
     * Sets weight
     * @param weight weight of the vertex
     */
    public void setWeight(double weight){
        this.weight = weight;
    }

    /**
     * Returns weight
     * @return weight of the vertex
     */
    public double getWeight(){
        return weight;
    }

    /**
     * Prints user defined property
     */
    public void printUDP(){

        Set entrySet = udp.entrySet();
        Iterator it = entrySet.iterator();

        while(it.hasNext()){
            Map.Entry me = (Map.Entry)it.next();
            System.out.println("Key is: "+me.getKey() + " value is: "+me.getValue());
        }
    }
}