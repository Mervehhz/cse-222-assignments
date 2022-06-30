@SuppressWarnings("unchecked")
public class HashTableCoalesced<K, V> implements KWHashMap<K, V>{

    /** Contains key‐value pairs for a hash table. */
    private static class Entry<K, V> {

        /** The key */
        private K key;
        /** The value */
        private V value;
        /** Next index */
        private int next;

        /** Creates a new key‐value pair.
         @param key The key
         @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = -1;
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
    }

    // Data Fields
    private Entry<K, V>[] table;
    private static final int START_CAPACITY = 101;
    private final Entry<K, V> DELETED = new Entry<>(null, null);
    private int numKeys;
    private int numDeletes;
    private double LOAD_THRESHOLD = 0.75;
    private int size = 0;


    // Constructor
    public HashTableCoalesced() {
        table = new Entry[START_CAPACITY];
    }

    public HashTableCoalesced(int capacity) {
        table = new Entry[capacity];
    }

    /**
     * Generates index
     * @param key key for index
     * @return returns index
     */
    private int hash1(Object key){
        return key.hashCode() % table.length;
    }

    /**
     * Generates index if collision occurs
     * @param key for index
     * @return returns index
     */
    private int hash2(Object key){
        int PRIME = 7;
        return PRIME -(key.hashCode()% PRIME);
    }

    /** Finds either the target key or the first empty slot in the
     search chain using linear probing.
     @pre The table is not full.
     @param key The key of the target object
     @return The position of the target or the first empty slot if
     the target is not in the table.
     */
    private int find(Object key) {

        int index = hash1(key); // Calculate the starting index.
        if (index < 0) index += table.length; // Make it positive.

        for (int i=1;(table[index] != null) && (!key.equals(table[index].getKey()));i++) { // Increment index until an empty slot is reached or the key is found

            index = (hash1(key) + (i*hash2(key))) % table.length;
            if (index >= table.length)
                index = 0;

            table[(hash1(key) + ((i-1)*hash2(key))) % table.length].next = index;
        }
        return index;
    }

    /** Method get for class HashtableOpen.
     @param key The key being sought
     @return the value associated with this key if found;
     otherwise, null
     */
    @Override
    public V get(Object key) {

        int index = find(key); // Find the first table element that is empty or the table element that contains the key.

        if (table[index] != null) // If the search is successful, return the value.
            return table[index].getValue();
        else
            return null; // key not found.
    }

    /** Method put for class HashtableOpen.
     @post This key‐value pair is inserted in the
     table and numKeys is incremented. If the key is already
     in the table, its value is changed to the argument
     value and numKeys is not changed. If the LOAD_THRESHOLD
     is exceeded, the table is expanded.
     @param key The key of item being inserted
     @param value The value for this key
     @return Old value associated with this key if found;
     otherwise, null
     */
    @Override
    public V put(K key, V value) {

        int index = find(key); // Find the first table element that is empty or the table element that contains the key.

        if (table[index] == null) { // If an empty element was found, insert new entry.
            table[index] = new Entry<>(key, value);
            numKeys++;
            double loadFactor = (double) (numKeys + numDeletes) / table.length; // Check whether rehash is needed.
            if (loadFactor > LOAD_THRESHOLD)
                rehash();
            size++;
        }
        return null;
    }

    /**
     * Removes specific element from hash table
     * @param key key that will deleted
     * @return returns value by pointing key
     */
    public V remove(Object key){

        int index = find(key);

        if (table[index]==null) return null;

        V oldValue=table[index].getValue();

        if (table[index].next==-1) table[index]=null;
        else{
            int temp=0;
            while (table[index].next != -1) {
                table[index].key = table[table[index].next].key;
                if (table[index].next != -1) {
                    if (table[table[index].next].next == -1)
                        temp=index;
                    index=table[index].next;
                }
            }
            table[index]=null;
            table[temp].next=-1;
        }
        size--;
        return oldValue;
    }

    /**
     * Check the table is whether empty or not
     * @return returns true if table is empty
     */
    public boolean isEmpty(){
        return size == 0;
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

        Entry<K, V>[] oldTable = table; // Save a reference to oldTable.
        table = new Entry[2 * oldTable.length + 1]; // Double capacity of this table.
        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        numDeletes = 0;
        for (Entry<K, V> kvEntry : oldTable)
            if ((kvEntry != null) && (kvEntry != DELETED))
                put(kvEntry.getKey(), kvEntry.getValue()); // Insert entry in expanded table
    }

    /**
     * Prints hash table
     */
    public void print() {
        for (int i = 0; i < table.length; i++)
            if (table[i] != null)
                System.out.println("Index: "+i+" Hash: "+table[i].getKey().hashCode()%START_CAPACITY+" Key: "+table[i].key + " Next: " + table[i].next);
    }
}