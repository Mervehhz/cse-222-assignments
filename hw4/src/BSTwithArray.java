import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * class that implements SearchTree interface and implement binary search tree with array
 */
public class BSTwithArray implements SearchTree{

    private int [] BST;
    private int length;

    /**
     * initializes data fields
     */
    public BSTwithArray(){
        BST = new int[100];
        length = 0;
        Arrays.fill(BST, -1);
    }

    /**
     * stores item at index
     * @param index index that will stores item
     * @param item item that will stored
     */
    public BSTwithArray(int index, int item){
        BST[index] = item;
    }

    /**
     * Inserts item recursively where it belongs in the tree
     * @param item item that added
     * @param index index belong to added item
     * @return Returns true if item is inserted; false if it isn’t (already in tree)
     */
    private boolean add(int item, int index){

        if(BST[index] == -1){
            BST[index] = item;
            return true;
        }
        else if(item < BST[index])
            add(item, 2*index);
        else
            add(item, 2*index+1);

        length++;
        return true;
    }

    /**
     * Inserts item where it belongs in the tree.
     * @param item item that added
     * @return Returns true if item is inserted; false if it isn’t (already in tree)
     */
    public boolean add(int item){

        /* root always stored at index 1*/
        add(item, 1);
        return true;
    }

    /**
     * checks item whether in tree or not
     * @param target check for existing target
     * @return Returns true if target is found in the tree
     */
    public boolean contains(int target){

        for(int i=0;i<length;i++)
            if(BST[i] == target)
                return true;
        return false;
    }

    /**
     * search for target
     * @param target finds target
     * @return Returns a reference to the data in the node that is equal to target . If no such node is found, returns -1
     */
    public int find(int target){

        for(int i=0;i<length;i++)
            if(BST[i] == target)
                return i;
        return -1;
    }

    /**
     * Removes target (if found) from tree
     * @param target deleted element
     * @return returns removed element; otherwise, returns -1
     */
    public int delete(int target){

        if(contains(target)){
            for(int i=0;i<length;i++)
                if(BST[i] == target) {
                    refreshTree(i);
                    return i;
                }
        }
        else
            throw new NoSuchElementException();
        return -1;
    }

    /**
     * Removes target (if found) from tree
     * @param target removed element
     * @return if removes element successfully returns true ; otherwise, returns false
     */
    public boolean remove(int target){

        if(delete(target) == -1)
            return false;
        return true;
    }

    /**
     * if an item is deleted, then tree must be refresh
     * @param index index of deleted item
     */
    private void refreshTree(int index){

        if(BST[2*index] != -1){
            BST[index] = BST[2*index];
            BST[2*index] = -1;
        }
        else if(BST[2*index+1] != -1){
            BST[index] = BST[2*index+1];
            BST[2*index+1] = -1;
        }
        else
            BST[index] = -1;
    }

    /**
     * @return returns tree as string
     */
    @Override
    public String toString(){

        StringBuilder str = new StringBuilder();

        for (int j=0;j<length+1;j++)
            str.append(BST[j]).append(" ");
        return str.toString();
    }
}