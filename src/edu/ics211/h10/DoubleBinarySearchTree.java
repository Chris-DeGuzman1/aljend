package edu.ics211.h10;

public class DoubleBinarySearchTree {
    private Node rootNode;

    // constructor, creates an empty binary search tree
    public DoubleBinarySearchTree() {
        rootNode = null;
    }

    // constructor, creates a binary search tree containing all the given values
    public DoubleBinarySearchTree(double[] initialValues) {
        int size = initialValues.length;
        int max;
        int n = 0;
        // loop is empty because I just want the max that it calculates
        for(max=0; (2^max)<=size; max++){
            n++;
        }
        System.out.println(n);

        for (double i : initialValues) {
            add(i);
        }
    }


    // add the given value to the binary search tree, or return false if the value
    // is already in the tree
    public boolean add(double value) {
        try {
            rootNode = addHelper(rootNode, value);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private Node addHelper(Node root, double value) throws Exception {
        if (rootNode == null) {
            return rootNode = new Node(value);
        }
        // checks if value already exists in tree
        if (!has(value)) {
            throw new Exception("Value " + value + " already exists in the tree");
        } else if (root == null) {
            // recursively calls itself until it finds the spot where the value should be a
            // leaf
            return new Node(value);
        } else if (value < root.item) {
            root.left = addHelper(root.left, value);
        } else if (value > root.item) {
            root.right = addHelper(root.right, value);
        }
        return root;
    }

    // return true if the given value is in the binary search tree, false otherwise
    public boolean has(double value) {
        return (hasHelper(rootNode, value));
    }

    private boolean hasHelper(Node root, double value) {
        if (root.item == value) {
            return false;
        } else if (hasChild(root) && value < root.item) {
            return hasHelper(root.left, value);
        } else if (hasChild(root) && value > root.item) {
            return hasHelper(root.right, value);
        }
        return true;
    }

    // returns true if node has children
    private boolean hasChild(Node root) {
        return root.left != null && root.right != null;
    }

    // returns 0 for an empty three, 1 for a tree with exactly one value, and the
    // depth of the tree (2..) for larger trees
    public int depth() {
        return depthHelper(rootNode);
    }

    // if the node being passed in isn't null, and has no children, it will return 1
    // so if the tree has exactly 1 value, it'll return 1
    private int depthHelper(Node root) {
        if (root == null) {
            return 0;
        } else if (hasChild(root)) {
            // if there are child nodes, the program will return the larger depth +1
            int lDepth = depthHelper(root.left);
            int rDepth = depthHelper(root.right);
            if (lDepth > rDepth) {
                return lDepth + 1;
            } else {
                return rDepth + 1;
            }
        } else {
            return 1;
        }
    }

    public int size() {
        return sizeHelper(rootNode);
    }

    private int sizeHelper(Node root) {
        if (root == null) {
            return 0;
        } else {
            return sizeHelper(root.left) + sizeHelper(root.right) + 1;
        }
    }

    // returns an array containing all the values in sorted order
    public double[] all() {
        double[] sorted = new double[this.size()];
        allHelper(rootNode, sorted, 0);
        return sorted;
    }

    private int allHelper(Node root, double[] values, int index) {
        if (root.left != null) {
            index = allHelper(root.left, values, index);
        }
        values[index++] = root.item;
        if (root.right != null) {
            index = allHelper(root.right, values, index);
        }
        return index;
    }

    // adapted from hw5
    private static class Node {
        private final double item;
        private Node left;
        private Node right;

        private Node(double value) {
            item = value;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        double[] original = {50, 30, 70, 15, 7, 62, 22, 35, 87, 22, 31};
        DoubleBinarySearchTree myTree = new DoubleBinarySearchTree(original);
        System.out.println("Depth: " + myTree.depth());
        System.out.println("Size: " + myTree.size());
        double[] sorted = myTree.all();


        for (double v : sorted) {
            System.out.print(v + " ");
        }
    }
}
