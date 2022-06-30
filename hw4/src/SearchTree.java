/**
 * Interface that will implemented by class later
 */
public interface SearchTree {

    /**
     * Inserts item where it belongs in the tree.
     * @param item item that added
     * @return Returns true if item is inserted; false if it isn’t (already in tree)
     */
    boolean add(int item);

    /**
     * checks item whether in tree or not
     * @param target check for existing target
     * @return Returns true if target is found in the tree
     */
    boolean contains(int target);

    /**
     * search for target
     * @param target finds target
     * @return Returns a reference to the data in the node that is equal to target . If no such node is found, returns -1
     */
    int find(int target);

    /**
     * Removes target (if found) from tree
     * @param target deleted element
     * @return returns removed element; otherwise, returns -1
     */
    public int delete(int target);

    /**
     * Removes target (if found) from tree
     * @param target removed element
     * @return if removes element successfully returns true ; otherwise, returns false
     */
    public boolean remove(int target);
}