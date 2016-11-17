import java.io.*;
import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;
    protected int compsFound = 0;
    protected int compsNotFound = 0;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }
    
    /**
     * Adds to the compsFound counter that tracks how many comparisons have been made for words found.
     */
    public void addCompsFound() {
        compsFound++;
    }
    
    /**
     * Adds to the compsNotFound counter which tracks how many comparisons have been made for words not found.
     */
    public void addCompsNotFound(){
        compsNotFound++;
    }
    
    /**
     * Retrieves an integer and adds it to the compsNotFound counter. 
     * Count must be of int type.
     * @param count 
     */
    public void addIntCompsNotFound(int count) {
       compsNotFound = compsNotFound + count;
    }
    
    /**
     * Retrieves an integer and adds it to the compsFound counter.
     * count must be of int type.
     * @param count 
     */
    public void addIntCompsFound(int count) {
        compsFound = compsFound + count;
    }
    
    /**
     * Retrieves compsFound counter.
     * @return compsFound.
     */
    public int getCompsFound() {
        return compsFound;
    }
    /**
     * Retrieves compsNotFound counter.
     * @return compsNotFound
     */
    public int getCompsNotFound() {
        return compsNotFound;
    }

    /**
     * Returns true if the element is in the tree
     */
    public boolean search(E e) {
        int countCompsNotFound = 0;
        int countCompsFound = 0;
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                countCompsNotFound++;
                countCompsFound++;
                current = current.left;
                
            } else if (e.compareTo(current.element) > 0) {
                countCompsNotFound++;
                countCompsFound++;
                current = current.right;
            } else // element matches current.element
            {
                countCompsNotFound = 0;
                addIntCompsFound(countCompsFound);
                return true; // Element is found
            }
        }
        addIntCompsNotFound(countCompsNotFound);
        return false;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * Inner class tree node
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Returns an ArrayList containing elements in the path from the root
     * leading to the specified element, returns an empty ArrayList if no such
     * element exists.
     * 
     * Parameter must be of generic type.
     * @param e
     */
    public ArrayList<E> path(E e) {
        ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                list.add(current.element);
                current = current.left;
            } 
            else if (e.compareTo(current.element) > 0) {
                list.add(current.element);
                current = current.right;
            } 
            else {              
               list.add(current.element);
               break;
            }
        }
        if (list.contains(e)) {
            return list;
        } 
        else {
           list.clear();
           return list;
        }
        // Return an array of elements
    } // path


    /**
     * Returns the number of leaf nodes in this tree, returns 0 if tree is empty.
     * The base structure of code for the 'while' loop was taken from this web page:
     * http://www.java2blog.com/2014/07/binary-tree-preorder-traversal-in-java.html
     * 
     * Some of the code concepts in this link are ones we have not heavily covered, such as the Stack object.
     * So, I took to the API to figure it out. 
     * For example, the 'push' method places that item onto the top of that stack, in this case 'nodes'.
     * 
     * The !nodes.isEmpty() essentially checks to make sure the Stack object is empty. If it isn't,
     * the rest of the code is executed. 
     * 
     * @return count
     */
    public int getNumberOfLeaves() {
        int count = 0;
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode<E> current = root;

        while (!nodes.isEmpty() || current != null) {
            if (current != null) {
                if (current.left == null && current.right == null) {
                    count++;
                } // if
                nodes.push(current);
                current = current.left;
            } else {
                TreeNode node = nodes.pop();
                current = node.right;
            }
        }
        return count;
    }

    /**
     * Returns an ArrayList containing all elements in preorder of the specified element�s left sub-tree, returns an empty ArrayList if no  such element exists.
     * This method first checks to make sure that the element exists. If so, the final else statement is executed.
     * I used the same concepts in the link given above for the code in the final 'else' statement. 
     * 
     * Each node to the 'left' of the specified element is ran through. If a node is a leaf, that node is taken out of the stack. If a node isn't a leaf, which
     * ever side contains another node is placed on top of the stack. If both sides are nodes, both are placed - right-side first - onto the stack. Then, the sides
     * of those nodes are checked.
     * 
     * While the stack object is not empty, 
     * Parameter must be of generic type.
     * @param e
     * @return list
     */
    public ArrayList<E> leftSubTree(E e) {
        ArrayList<E> list = new java.util.ArrayList<>();

        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else { // element matches current.element
                current = current.left;
                if (current == null) {
                    return list;
                }

                Stack<TreeNode> stack = new Stack<TreeNode>();
                stack.push(current);

                while (!stack.empty()) {

                    TreeNode n = stack.pop();

                    E temp = (E) n.element;
                    list.add(temp);

                    if (n.right != null) {
                        stack.push(n.right);
                    }

                    if (n.left != null) {
                        stack.push(n.left);
                    }
                }
            }
        }
        return list;
    } // leftsubTree

   
    /**
     * Returns an ArrayList containing all elements in preorder of the specified element�s right sub-tree, returns an empty ArrayList if no  such element exists.
     * Works the exact way as leftSubTree except the right side of the specified element is analyzed.
     * 
     * Parameter must be of generic type.
     * @param e
     * @return list
     */
    public ArrayList<E> rightSubTree(E e) {
        ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                current = current.right;
                if (current == null) {
                    return list;
                }

                Stack<TreeNode> stack = new Stack<TreeNode>();
                stack.push(current);

                while (!stack.isEmpty()) {
                    TreeNode n = stack.pop();

                    E temp = (E) n.element;
                    list.add(temp);

                    if (n.right != null) {
                        stack.push(n.right);
                    }

                    if (n.left != null) {
                        stack.push(n.left);
                    }
                }
            }
        }
        return list;
    }

    /**
     * creates four ArrayLists - two for each tree, one for each side of each tree - to store the values returned by the leftSubTree and rightSubTree methods.
     * The method checks to make sure the two trees aren't null. If so, 'true' is returned. If the root of both trees is equal and both lists of each tree are 
     * equal 'true' is returned. Otherwise, 'false' is returned.
     * 
     * Parameters must be a BinarySearchTree object.
     * @param tree1
     * @param tree2
     * @return boolean
     */
    public boolean sameTree(BinarySearchTree tree1, BinarySearchTree tree2) {
        ArrayList<E> tree1subLeftList = tree1.leftSubTree(tree1.root.element);
        ArrayList<E> tree1subRightList = tree1.rightSubTree(tree1.root.element);
        ArrayList<E> tree2subLeftList = tree2.leftSubTree(tree2.root.element);
        ArrayList<E> tree2subRightList = tree2.rightSubTree(tree2.root.element);
        
        if (tree1 == null && tree2 == null) {
            return true;
        } else if (tree1.root.element == tree2.root.element && tree1subLeftList.equals(tree2subLeftList) && tree1subRightList.equals(tree2subRightList)) {
            return true;
        } else {
            return false;
        }
    }

    /* Returns the inorder predecessor of the specified element, returns null if tree is empty or element 'e' is not in the tree. */
    //public E inorderPredecessor(E e){
    //left for you to implement in Lab 7
    // }
    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else if (e.compareTo(parent.element) < 0) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
        // Store the elements in a list

        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
}
