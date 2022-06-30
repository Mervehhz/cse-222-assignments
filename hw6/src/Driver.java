@SuppressWarnings("unchecked")
public class Driver {

    private static int ITEM_NUM = 10;

    public static void main(String[] args) {

        BinaryTree<Integer> bt = new BinaryTree<>();
        Comparable [] items = {7, 9, 4, 3, 2, 5, 6, 8, 1, 10};
        BTtoBST bTtoBST = new BTtoBST();
        BSTtoAVL bSTtoAVL = new BSTtoAVL();
        BinarySearchTree bst = new BinarySearchTree();
        CustomSkipList csl = new CustomSkipList();
        CustomSkipList csl2 = new CustomSkipList();

        bt.root = new BinaryTree.Node<>(0);
        bt.root.left = new BinaryTree.Node<>(0);
        bt.root.right = new BinaryTree.Node<>(0);
        bt.root.left.left = new BinaryTree.Node<>(0);
        bt.root.left.right = new BinaryTree.Node<>(0);
        bt.root.left.left.left = new BinaryTree.Node<>(0);
        bt.root.right.left = new BinaryTree.Node<>(0);
        bt.root.right.left.left = new BinaryTree.Node<>(0);
        bt.root.right.right = new BinaryTree.Node<>(0);
        bt.root.right.right.right = new BinaryTree.Node<>(0);

        System.out.println("CONVERTING BINARY TREE TO BINARY SEARCH TREE\n");
        System.out.println("---------------Array before converting from binary tree to binary search tree----------------\n");
        for(int i=0;i<items.length;i++) System.out.print(items[i] + " ");
        System.out.println("\n");
        bTtoBST.generateBST(bt, items);

        bst.root = new BinaryTree.Node<>(21);
        bst.root.left = new BinaryTree.Node<>(15);
        bst.root.right = new BinaryTree.Node<>(33);
        bst.root.left.left = new BinaryTree.Node<>(14);
        bst.root.left.right = new BinaryTree.Node<>(17);
        bst.root.left.left.left = new BinaryTree.Node<>(9);
        bst.root.left.left.left.left = new BinaryTree.Node<>(7);
        bst.root.left.left.left.left.left = new BinaryTree.Node<>(5);
        bst.root.left.left.left.left.left.left = new BinaryTree.Node<>(3);
        bst.root.right.left = new BinaryTree.Node<>(30);
        bst.root.right.right = new BinaryTree.Node<>(34);

        System.out.println("CONVERTING BINARY SEARCH TREE TO AVL TREE\n");
        System.out.println("---------------Before converting from binary search tree to avl tree----------------\n");
        System.out.println(bst.toString());
        bst = bSTtoAVL.rotateBSTtoAVL(bst);
        System.out.println("---------------After converting from binary search tree to avl tree----------------\n");
        System.out.println(bst.toString());

        System.out.println("IMPLEMENTING CUSTOM SKIP LIST\n");
        System.out.println("-----------------------Adding 24 elements to skipList----------------------\n");
        csl.add(21);
        csl.add(15);
        csl.add(33);
        csl.add(14);
        csl.add(435);
        csl.add(2);
        csl.add(5);
        csl.add(7);
        csl.add(6);
        csl.add(1);
        csl.add(67);
        csl.add(121);
        csl.add(213);
        csl.add(115);
        csl.add(116);
        csl.add(147);
        csl.add(9);
        csl.add(28);
        csl.add(35);
        csl.add(79);
        csl.add(63);
        csl.add(11);
        csl.add(68);
        csl.add(1271);

        System.out.println(csl.toString());

        System.out.println("-----------------------Adding 13 elements to skipList----------------------\n");

        csl2.add(21);
        csl2.add(15);
        csl2.add(33);
        csl2.add(14);
        csl2.add(435);
        csl2.add(2);
        csl2.add(5);
        csl2.add(7);
        csl2.add(6);
        csl2.add(1);
        csl2.add(67);
        csl2.add(121);
        csl2.add(213);

        System.out.println(csl2.toString());
    }
}