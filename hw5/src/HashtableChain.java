@SuppressWarnings("unchecked")
/** Hash table implementation using chaining. */
public class HashtableChain<K extends Comparable<K>, V extends Comparable<V>> implements KWHashMap<K, V>{

    /** Contains key‐value pairs for a hash table. */
    public static class Entry<K, V> implements Comparable<HashtableChain.Entry<K,V>> {

        /** The key */
        private final K key;
        /** The value */
        private V value;

        /** Creates a new key‐value pair.
         @param key The key
         @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /** Retrieves the key.
         @return The key
         */
        public K getKey() {
            return key;
        }

        /** Retrieves the value.
         @return The value
         */
        public V getValue() {
            return value;
        }

        /** Sets the value.
         @param val The new value
         @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        public int compareTo(HashtableChain.Entry<K,V> entry){

            //if(this.equals(entry)) return 0;
            return 0;
        }
    }

    /** The table */
    private BinarySearchTree<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The capacity */
    private static final int CAPACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;
    /** The size*/
    private int size=0;

    // Constructor
    public HashtableChain() {
        table = new BinarySearchTree[CAPACITY];
    }

    public HashtableChain(int capacity) {
        table = new BinarySearchTree[capacity];
    }

    /** Method get for class HashtableChain.
     @param key The key being sought
     @return The value associated with this key if found;
     otherwise, null
     */
    @Override
    public V get(Object key) {

        int index = key.hashCode() % table.length;
        if (index < 0) index += table.length;
        if (table[index] == null) return null; // key is not in the table.

        // Search the list at table[index] to find the key.
        Entry<K, V> temp = table[index].find(new Entry<K,V>((K)key, null));
        if (temp != null && temp.getKey().equals(key))
            return temp.getValue();

        // assert: key is not in the table.
        return null;
    }

    /** Method put for class HashtableChain.
     @post This key‐value pair is inserted in the
     table and numKeys is incremented. If the key is already
     in the table, its value is changed to the argument
     value and numKeys is not changed.
     @param key The key of item being inserted
     @param value The value for this key
     @return The old value associated with this key if
     found; otherwise, null
     */
    @Override
    public V put(K key, V value) {

        int index = key.hashCode() % table.length;
        if (index < 0) index += table.length;
        if (table[index] == null)
            table[index] = new BinarySearchTree<>(); // Create a new linked list at table[index].

        // Search the list at table[index] to find the key.
        Entry<K, V> temp = table[index].find(new Entry<>(key, value));
        if (temp != null && temp.getKey().equals(key)) { // If the search is successful, replace the old value.
            V oldVal = temp.getValue(); // Replace value for this key.
            temp.setValue(value);
            return oldVal;
        }

        // assert: key is not in the table, add new item.
        table[index].add(new Entry<>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        size++;

        return null;
    }

    /**
     * check for table whether empty or not
     * @return true if table is empty
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Removes element that pointing to key
     * @param key key that will removed
     * @return returns value of that key
     */
    public V remove(Object key){

        int index = key.hashCode() % table.length;
        if(index < 0) index += table.length;
        if(table[index] == null) return null;

        Entry<K, V> temp = table[index].find(new Entry<K,V>((K)key, null));

        if(temp != null){
            table[index].delete(temp);
            numKeys--;

            if(table[index].size() == 0) table[index] = null;
            return temp.getValue();
        }
        return null;
    }

    /**
     * @return returns size of hash table
     */
    public int size(){
        return size;
    }

    /** Expands table size when loadFactor exceeds LOAD_THRESHOLD
     @post The size of the table is doubled and is an odd integer.
     Each nondeleted entry from the original table is
     reinserted into the expanded table.
     The value of numKeys is reset to the number of items
     actually inserted; numDeletes is reset to 0.
     */
    private void rehash() {

        // Save a reference to oldTable.
        BinarySearchTree<Entry<K, V>>[] oldTable = table;
        // Double capacity of this table.
        table = new BinarySearchTree[2 * oldTable.length + 1];
        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        for (int i = 0; i < oldTable.length; i++)
            if ((oldTable[i] != null))
                put(oldTable[i].getData().getKey(), oldTable[i].getData().getValue());
    }
}