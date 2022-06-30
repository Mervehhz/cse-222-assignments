import java.util.Arrays;
import java.util.Random;

/**
 *@param <E> The type of data stored. Must be a Comparable
 */
@SuppressWarnings("unchecked")
public class CustomSkipList<E extends Comparable<E>> {
    /**
     * Head of the skip-list
     */
    public SLNode<E> head;
    /**
     * Size of the skip list
     */
    private int size;
    /**
     * The maximum level of the skip-list
     */
    private int maxLevel;
    /**
     * Smallest power of 2 that is greater than the current skip-list size
     */
    private int maxCap;
    /**
     * Natural log of 2
     */
    static final double LOG2 = Math.log(2.0);
    /**
     * Minimum possible integer value for the head
     */
    static final int MIN = Integer.MIN_VALUE;
    /**
     * Random number generator
     */
    private Random rand = new Random();

    //Constructor

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public CustomSkipList(){
        size = 0;
        maxLevel = 2;
        maxCap = computeMaxCap(maxLevel);
        head = new SLNode(maxLevel, MIN);
    }

    /**
     * Search for an item in the list
     * @param target The item being sought
     * @return An SLNode array which references the predecessors of the target at each level.
     */
    private SLNode<E>[] search(E target){
        SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];
        SLNode<E> current = head;
        for(int i = current.links.length - 1; i >= 0; i--){
            while(current.links[i] != null
                    && current.links[i].data.compareTo(target) < 0){
                current = current.links[i];
            }
            pred[i] = current;
        }
        return pred;
    }

    /**
     * Find an object in the skip-list
     * @param target The item being sought
     * @return A reference to the object in the skip-list that matches
     * 		   the target. If not found, null is returned
     */
    public E find(E target){
        SLNode<E>[] pred = search(target);
        if(pred[0].links != null &&
                pred[0].links[0].data.compareTo(target) == 0){
            return pred[0].links[0].data;
        } else {
            return null;
        }
    }

    /**
     * Find an object node in the skip-list
     * @param target The item being sought
     * @return A reference node to the object in the skip-list that matches
     * 		   the target. If not found, null is returned
     */
    private SLNode<E> findNode(E target){
        SLNode<E>[] pred = search(target);
        if(pred[0].links != null &&
                pred[0].links[0].data.compareTo(target) == 0){
            return pred[0];
        } else {
            return null;
        }
    }

    /**
     * Inserts the given item
     * @param item The item to add
     * @return true as the item is added
     */
    public boolean add(E item){
        size++;
        SLNode<E>[] pred = search(item);
        if(size % 10 == 0){
            maxLevel++;
            maxCap = computeMaxCap(maxLevel);
            head.links = Arrays.copyOf(head.links, maxLevel);
            pred = Arrays.copyOf(pred, maxLevel);
            pred[maxLevel - 1] = head;
        }
        SLNode<E> newNode = new SLNode<E>(logRandom(), item);
        for(int i = 0; i < newNode.links.length; i++){
            newNode.links[i] = pred[i].links[i];
            pred[i].links[i] = newNode;
        }
        /*SLNode<E> itr = head;
        while(itr.links[0] != null){
            if(itr.links.length > 1)
                increaseLevel(itr.data);
            itr = itr.links[0];
        }*/
        return true;
    }

    /**
     * The tall items (contained in the more than
     * one level) are appended to a one-level upper list when a new level is added to the skip list.
     * @param item item that will increased the level
     * @return returns true
     */
    private boolean increaseLevel(E item){

        int i;
        SLNode<E> temp = findNode(item);
        SLNode<E> [] temp2 = new SLNode[temp.links.length+1];
        for(i=0;i<temp.links.length;i++)
            temp2[i] = temp.links[i];
        temp2[i] = helper(temp);
        temp.links = temp2;
        return true;
    }

    private SLNode<E> helper(SLNode<E> node){

        SLNode<E> itr = head, returned = null;
        while(itr.links[0] != null && itr != node){
            if(itr.links.length >= node.links.length)
                returned = itr.links[0];
            itr = itr.links[0];
        }
        return returned;
    }

    /**
     * Method to generate a logarithmic distributed integer between 1 and maxLevel.
     *  I.E. 1/2 of the values are 1, 1/4 are 2, etc.
     * @return a random logarithmic distributed int between 1 and maxLevel
     */
    private int logRandom(){
        int r = rand.nextInt(maxCap);
        int k = (int) (Math.log(r + 1) / LOG2);
        if(k > maxLevel - 1)
            k = maxLevel - 1;
        return maxLevel - k;
    }

    /**
     * Recompute the max cap
     * @param level will be computed
     * @return computed capacity
     */
    private int computeMaxCap(int level){
        return (int) Math.pow(2, level) - 1;
    }

    /**
     *
     * @return returns skip list as string
     */
    @SuppressWarnings("rawtypes")
    public String toString(){
        if(size == 0) return "Empty";
        StringBuilder sc = new StringBuilder();
        SLNode itr = head;
        sc.append("Head: " + maxLevel);
        int lineMaker = 0;
        while(itr.links[0] != null){
            itr = itr.links[0];
            sc.append(" --> " + itr.toString());
            lineMaker++;
            if(lineMaker == 10){
                sc.append("\n");
                lineMaker = 0;
            }
        }
        return sc.toString();
    }

    /**
     * Static class to contain data and links
     * @author Koffman & Wolfgang
     *
     * @param <E> The type of data stored. Must be a Comparable
     */
    static class SLNode<E>{
        SLNode<E>[] links;
        E data;

        /**
         * Create a node of level m
         * @param m The level of the node
         * @param data The data to be stored
         */
        @SuppressWarnings("unchecked")
        public SLNode(int m, E data){
            links = (SLNode<E>[]) new SLNode[m];
            this.data = data;
        }

        public String toString(){
            return (data.toString() + " |" + links.length + "|");
        }
    }

    /**
     *
     * @return the size
     */
    public int size() {return size;}
}