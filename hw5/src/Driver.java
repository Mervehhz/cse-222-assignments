import java.util.Random;

@SuppressWarnings("unchecked")
public class Driver{

    private static int sizeKey = 0;

    public static void main(String[] args) {

        final int SMALL = 100;
        final int MEDIUM = 1000;
        final int LARGE = 10000;
        final int SIZE = 100;

        int keyArray0 [] = new int[SIZE];

        HashtableChain chainSmall = new HashtableChain<>(SMALL);
        HashtableChain chainMedium = new HashtableChain<>(MEDIUM);
        HashtableChain chainLarge = new HashtableChain<>(LARGE);

        HashTableCoalesced<Object, Object> coalescedSmall = new HashTableCoalesced<>(SMALL);
        HashTableCoalesced<Object, Object> coalescedMedium = new HashTableCoalesced<>(MEDIUM);
        HashTableCoalesced<Object, Object> coalescedLarge = new HashTableCoalesced<>(LARGE);



        Random rand = new Random();

        System.out.println("--------------------------Hash Table Chain----------------------------\n");

        System.out.println("Loading datasets small size table");
        long start = System.nanoTime();

        for(int i=0;i<SIZE;i++)
            chainSmall.put(generateUniqueKey(LARGE, keyArray0), rand.nextInt(LARGE));

        long end = System.nanoTime();

        System.out.println("\nelapsed Time in seconds while adding 100 elements to small(100) sized hash table: "+ (double)(end-start)/1000000000 + "\n\n");



        System.out.println("Loading datasets medium size table");
        start = System.nanoTime();

        sizeKey = 0;
        for(int i=0;i<SIZE;i++)
            chainMedium.put(generateUniqueKey(LARGE, keyArray0), rand.nextInt(LARGE));

        end = System.nanoTime();

        System.out.println("\nelapsed Time in seconds while adding 100 elements to medium(1000) sized hash table: "+ (double)(end-start)/1000000000 + "\n\n");



        System.out.println("Loading datasets large size table");
        start = System.nanoTime();

        sizeKey = 0;
        for(int i=0;i<SIZE;i++)
            chainLarge.put(generateUniqueKey(LARGE, keyArray0), rand.nextInt(LARGE));

        end = System.nanoTime();

        System.out.println("\nelapsed Time in seconds while adding 100 elements to large(10000) sized hash table: "+ (double)(end-start)/1000000000 + "\n\n");

        sizeKey = 0;

        System.out.println("\n--------------------------Hash Table Coalesced----------------------------\n");

        System.out.println("Loading datasets small size table");
        start = System.nanoTime();

        for(int i=0;i<SIZE;i++)
            coalescedSmall.put(generateUniqueKey(LARGE, keyArray0), rand.nextInt(LARGE));

        end = System.nanoTime();

        System.out.println("\nelapsed Time in seconds while adding 100 elements to small(100) sized hash table: "+ (double)(end-start)/1000000000 + "\n\n");



        System.out.println("Loading datasets medium size table");
        start = System.nanoTime();

        sizeKey = 0;
        for(int i=0;i<SIZE;i++)
            coalescedMedium.put(generateUniqueKey(LARGE, keyArray0), rand.nextInt(LARGE));

        end = System.nanoTime();

        System.out.println("\nelapsed Time in seconds while adding 100 elements to medium(1000) sized hash table: "+ (double)(end-start)/1000000000 + "\n\n");



        System.out.println("Loading datasets large size table");
        start = System.nanoTime();

        sizeKey = 0;
        for(int i=0;i<SIZE;i++)
            coalescedLarge.put(generateUniqueKey(LARGE, keyArray0), rand.nextInt(LARGE));

        end = System.nanoTime();

        System.out.println("\nelapsed Time in seconds while adding 100 elements to large(10000) sized hash table: "+ (double)(end-start)/1000000000 + "\n\n");

        System.out.println("\n------------------------Printing small sized coalesced hash table-------------------------------\n");
        coalescedSmall.print();




        HashTableCoalesced<Object, Object> coalesced = new HashTableCoalesced<>();
        HashtableChain chainObj = new HashtableChain<>();

        System.out.println("\nPutting elements to hash table chain...\n");
        chainObj.put(5, 13);
        chainObj.put(6,16);
        chainObj.put(34,56);
        chainObj.put(2354,3);
        chainObj.put(5345,16);
        chainObj.put(33,56);
        chainObj.put(254,3);
        chainObj.put(2545,3);

        System.out.println("Getting an existing element from hash table chain: " + chainObj.get(5));

        System.out.println("Size of chaining hash table: " + chainObj.size() + "\n");

        System.out.println("isEmpty chaining hash table: " + chainObj.isEmpty() + "\n");

        System.out.println("Removing an existing element from chaining hash table: " + chainObj.remove(6));

        System.out.println("Getting element from chain hash table after removing: " + chainObj.get(6));


        System.out.println("\nPutting elements to hash table chain...\n");
        coalesced.put(5,13);
        coalesced.put(6,16);
        coalesced.put(34,56);
        coalesced.put(2345,3);
        coalesced.put(5345,16);
        coalesced.put(1,13);
        coalesced.put(2,160);
        coalesced.put(3,563);
        coalesced.put(4,354);
        coalesced.put(8,134);
        coalesced.put(9,161);
        coalesced.put(10,556);
        coalesced.put(11,39);
        coalesced.put(12,6);
        coalesced.put(1024,5516);
        coalesced.put(12321,394);
        coalesced.put(14552,1806);

        System.out.println("------BEFORE EDITING COALESCED HASH TABLE----------\n");
        coalesced.print();

        System.out.println("Getting an existing element from hash table coalesced: " + coalesced.get(8));

        System.out.println("Size of coalesced hash table " + coalesced.size() + "\n");

        System.out.println("isEmpty coalesced hash table: " + coalesced.isEmpty() + "\n");

        System.out.println("Removing an existing element from coalesced hash table " + coalesced.remove(6));

        System.out.println("Getting element from coalesced hash table after removing " + coalesced.get(6));

        System.out.println("------AFTER EDITING COALESCED HASH TABLE----------\n");
        coalesced.print();


        /** Test cases */

        System.out.println("\n-----------TEST CASES-------------");

        System.out.println("\nGetting a nonexisting(7) element from hash table chain: " + chainObj.get(7));

        System.out.println("Removing a nonexisting(7) element from chaining hash table: " + chainObj.remove(7));

        System.out.println("Getting a nonexisting(1071) element from hash table coalesced: " + coalesced.get(1071));

        System.out.println("Removing a nonexisting(1071) element from hash table coalesced: " + coalesced.remove(1071));

    }

    public static int generateUniqueKey(int bound, int [] keyArray){

        Random rand = new Random();
        int key;

        do {
            key = rand.nextInt(bound);
        }
        while (!isUnique(key, keyArray));

        keyArray[sizeKey] = key;
        sizeKey++;

        return key;
    }

    private static boolean isUnique(int key, int [] keyArray){

        for(int i=0;i<keyArray.length;i++)
            if(keyArray[i] == key)
                return false;
        return true;
    }
}