import java.util.AbstractList;
import java.util.List;


/** Class to represent a linked list with a link from each node to the next
 * node. SingleLinkedList does not implement the List interface.
 **/
public class LDLinkedList<E> extends AbstractList<E> implements List<E>{

    /** Reference to list head. */
    private Node<E> head = null;
    /** The number of items in the list */
    private int size = 0;
    /** iterator over the list */
    private int iter = -1;

    /** A Node is the building block for a single‐linked list. */
    private static class Node<E> {

        // Data Fields
        /** The reference to the data. */
        private E data;
        /** The reference to the next node. */
        private Node<E> next;

        // Constructors
        /** Creates a new node with a null next field.
         @param dataItem The data stored
         */
        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }
        /** Creates a new node that references another node.
         @param dataItem The data stored
         @param nodeRef The node referenced by new node
         */
        private Node(E dataItem, Node<E> nodeRef) {
            data = dataItem;
            next = nodeRef;
        }
    }

    /** Add an item to the front of the list.
     @param item The item to be added
     */
    public void addFirst(E item) {
        head = new Node<>(item, head);
        size++;
    }

    /** Add a node after a given node
     @param node The node preceding the new item
     @param item The item to insert
     */
    private void addAfter(Node<E> node, E item) {
        node.next = new Node<>(item, node.next);
        size++;
    }

    /** Insert the specified item at index
     @param index The position where item is to be inserted
     @param element The item to be inserted
     @throws IndexOutOfBoundsException if index is out of range
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            addFirst(element);
        } else {
            Node<E> node = getNode(index-1);
            addAfter(node, element);
        }
    }

    /** Append item to the end of the list
     @param e The item to be appended
     @return true (as specified by the Collection interface)
     */
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    /** Find the node at a specified position
     @param index The position of the node sought
     @return The node at index or null if it does not exist
     */
    private Node<E> getNode(int index) {
        Node<E> node = head;
        for (int i = 0; i < index && node != null; i++)
            node = node.next;

        return node;
    }

    /** Get the data at index
     @param index The position of the data to return
     @return The data at index
     @throws IndexOutOfBoundsException if index is out of range
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    /** Store a reference to anEntry in the element at position index.
     @param index The position of the item to change
     @param element The new data
     @return The data previously at index
     @throws IndexOutOfBoundsException if index is out of range
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = element;
        return result;
    }

    /** Remove the node after a given node
     @param node The node before the one to be removed
     @return The data from the removed node, or null
     if there is no node to remove
     */

    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if (temp != null) {
            node.next = temp.next;
            size--;
            return temp.data;
        }
        else
            return null;
    }

    /** Remove the first node from the list
     @return The removed node's data or null if the list is empty
     */
    public E removeFirst() {
        Node<E> temp = head;
        if (head != null) {
            head = head.next;
        }
        // Return data at old head or null if list is empty
        if (temp != null) {
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /**
     * Removes an element according to object as parameter
     * @param o o is the object that will delete
     * @return returns true if there is no exception
     * */

    public boolean remove(Object o){

        // indexOf method of AbstractList class
        int index = indexOf(o);

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        if (index == 0)
            removeFirst();

        else {
            Node<E> node = getNode(index-1);
            removeAfter(node);
        }
        return true;
    }

    /**
     * checks linkedList is empty
     * @return whether linkedlist is empty or not
     * */

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * @return returns size of the list
     * */

    public int size(){
        return size;
    }

    /** public boolean contains(Object o){


    }*/
}