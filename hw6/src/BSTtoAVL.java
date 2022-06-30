@SuppressWarnings("unchecked")
public class BSTtoAVL<E extends Comparable<E>> extends BinarySearchTree<E>{

    /**
     * Takes a binary search tree (BST) as a parameter
     * and returns the AVL tree obtained by rearranging the BST. The method should convert the
     * BST into an AVL tree by using rotation operations
     * @param bst binary search tree to convert avl tree
     * @return returns binary search tree by rotating to avl
     */
    public BinarySearchTree<E> rotateBSTtoAVL(BinarySearchTree<E> bst){

        if(bst != null) {
            int leftHeight = findHeight(bst.root.left);
            int rightHeight = findHeight(bst.root.right);

            while (Math.abs(leftHeight - rightHeight) > 1) {
                if (leftHeight < rightHeight)
                    bst.root = rotateLeft(bst.root);
                else if (leftHeight > rightHeight)
                    bst.root = rotateRight(bst.root);

                leftHeight = findHeight(bst.root.left);
                rightHeight = findHeight(bst.root.right);
            }
        }
        else
            System.out.println("\nThere is no tree to convert to avl tree...\n");
        return bst;
    }

    /** Method to perform a right rotation.
     @pre root is the root of a binary search tree.
     @post root.right is the root of a binary search tree,
     root.right.right is raised one level,
     root.right.left does not change levels,
     root.left is lowered one level,
     the new root is returned.
     @param root The root of the binary tree to be rotated
     @return The new root of the rotated tree
     */
    protected Node<E> rotateRight(Node<E> root) {
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }

    /** Method to perform a left rotation.
     @param root The root of the binary tree to be rotated
     @return The new root of the rotated tree
     */
    protected Node<E> rotateLeft(Node<E> root) {
        Node temp = root.right;
        Node temp1 = temp.left;
        temp.left = root;
        root.right = temp1;
        return temp;
    }

    /**
     * Finds height of each node
     * @param root node to find the height
     * @return returns height
     */
    protected int findHeight(Node<E> root){

        // Base Case
        if (root == null) return -1;

        // Store the maximum height of
        // the left and right subtree
        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Given a binary tree, print its nodes in inorder
     * @param node root node
     */
    public void printInorder(BinaryTree.Node<E> node) {
        if (node == null) return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.data + " ");

        /* now recur on right child */
        printInorder(node.right);
    }
}