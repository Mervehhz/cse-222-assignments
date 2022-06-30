public class Driver {

    public static void main(String[] args) {

        BSTwithArray bst = new BSTwithArray();

        bst.add(5);
        bst.add(15);
        bst.add(4);
        bst.add(25);
        bst.add(3);
        bst.add(13);

        System.out.println("Representation of tree as array:\n" + bst.toString());

        bst.delete(15);

        System.out.println("Representation of array after deleting 15:\n" + bst.toString());

        System.out.println("\nDoes tree contains 15: " + bst.contains(15));

        System.out.println("\nDoes tree contains 25: " + bst.contains(25));

        System.out.println("\nFind index of item 13: " + bst.find(13));

        bst.remove(4);

        System.out.println("\nRepresentation of array after removing 4:\n" + bst.toString());

        // test cases

        System.out.println("Representation of tree as array:\n" + bst.toString());

        System.out.println("\nDoes not tree contains 31: " + bst.contains(31));

        if(bst.find(70) == -1)
            System.out.println("\n70 is not in tree");

        System.out.println("\nDeleting an item that is not in tree: throws exception\n");
        bst.delete(33);
    }
}