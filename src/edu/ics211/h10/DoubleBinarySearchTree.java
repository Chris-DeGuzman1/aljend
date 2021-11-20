package edu.ics211.h10;

/**
 * @author Chris DeGuzman
 * @summary h10
 *          DoubleBinarySearchTree class which creates either an empty binary tree when the
 *          parameter-less constructor is called or takes an array of doubles as a parameter
 *          and creates a binary tree out of those values.
 */

public class DoubleBinarySearchTree {
    private Node rootNode;

    // constructor, creates an empty binary search tree
    public DoubleBinarySearchTree() {
        rootNode = null;
    }

    // constructor, creates a binary search tree containing all the given values

    public DoubleBinarySearchTree(double[] initialValues) {
        boolean done = false;
        int n = initialValues.length;
        int max = 0;
        int pow = 0;
        int index=0;
        int j = 0;
        // calculates the power of base 2 where size<=max
        for (int i = 0; i <= n; i++) {
            if (n <= getPow(2, i)) {
                max = (int) getPow(2,i);
                break;
            }
            pow++;
        }
        System.out.println(max);
        int count=0;
        int pnum = max-1;
        while(!done){
            double num = getPow(2, pnum);
            double den = getPow(2, max);
            double factor = num/den;
            index = Math.toIntExact(Math.round(n * factor));
            add(initialValues[index]);

            if(count>0) {
                for (int i = 0; i < count; i++) {
                    index += (int) Math.toIntExact(Math.round(n*2*factor));
                    if(index>=n){
                        add(initialValues[n-1]);
                        break;
                    }
                    add(initialValues[index]);
                }
            }
            if(pnum < 0){
                done = true;
            }
            pnum--;
            count+=getPow(2, j);
            j++;
        }
        add(initialValues[10]);
    }
    private double getPow(int base, int power){
        int i = base;
        if(power ==0){
            return 1;
        }else if(power ==1){
            return base;
        }else{
            for(int j = 1; j<power; j++){
                i*=base;
            }
        }
        return i;
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

    // main method for testing
    /*
    public static void main(String[] args) {

    }
*/
    public static void main(String[] args){

        double[] original = {7, 15, 22, 22, 30, 31, 35, 50, 62, 70, 87};
        DoubleBinarySearchTree myTree = new DoubleBinarySearchTree(original);
        System.out.println("Depth: " + myTree.depth());
        System.out.println("Size: " + myTree.size());
        double[] sorted = myTree.all();


        for (double v : sorted) {
            System.out.print(v + " ");
        }

/*        double[] original = {50, 30, 70, 15, 7, 62, 22, 35, 87, 22, 31};
        DoubleBinarySearchTree myTree = new DoubleBinarySearchTree(original);
        System.out.println("Depth: " + myTree.depth());
        System.out.println("Size: " + myTree.size());
        double[] sorted = myTree.all();


        for (double v : sorted) {
            System.out.print(v + " ");
        }*/

    }
}
