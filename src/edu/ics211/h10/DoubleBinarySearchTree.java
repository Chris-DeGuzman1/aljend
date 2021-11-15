package edu.ics211.h10;

public class DoubleBinarySearchTree {
    private TreeNode root=null;


    // constructor, creates an empty binary search tree
    public DoubleBinarySearchTree() {
        this.root = null;
    }

    // constructor, creates a binary search tree containing all the given values
    public DoubleBinarySearchTree(double[] initialValues) {
        for(int i = 0; i<initialValues.length; i++){
            add(initialValues[i]);
        }
    }

    // add the given value to the binary search tree, or return false if the value is already in the tree
    public boolean add(double value) {
        try{
            addHelper(this.root, value);
            System.out.println("added "+value);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    private TreeNode addHelper(TreeNode root, double value) throws Exception {
        // checks if value already exists in tree
        if(!has(value)){
            throw new Exception();
        }
        // checks if this node is null
        if(root == null){
            return root = new TreeNode(value);
        }
        // the program will then determine if the value belongs on the left or the right side of this node
        else if(value<root.item){
            return root.left = addHelper(root.left,value);
        }else {
            return root.right = addHelper(root.right, value);
        }
    }

    // return true if the given value is in the binary search tree, false otherwise
    public boolean has(double value) {
        return true;
    }
    private boolean hasHelper(){
        return false;
    }

    // returns true if node has children
    private boolean hasChild(TreeNode root){
        if (root.left == null|| root.right == null){
            return false;
        }else{
            return true;
        }
    }

    // optional method to return true if the binary search tree has at least one value in the range first..last, false otherwise (and returns false if first > last)
    public boolean hasInRange(double first, double last) {
        return false;
    }

    // returns 0 for an empty three, 1 for a tree with exactly one value, and the depth of the tree (2..) for larger trees
    public int depth() {
    return depthHelper(this.root);
    }

    // if the node being passed in isn't null, and has no children, it will return 1
    // so if the tree has exactly 1 value, it'll return 1
    private int depthHelper(TreeNode root) {
        if(root == null) {
            return 0;
        }else if(hasChild(root)){
            // if there are child nodes, the program will return the larger depth +1
            int lDepth = depthHelper(root.left);
            int rDepth = depthHelper(root.right);
            if(lDepth>rDepth){
                return lDepth+1;
            }else {
                return rDepth+1;
            }
        }else{
            return 1;
        }
    }
    private int size(TreeNode root) {
        if(root == null) {
            return 0;
        }else if(hasChild(root)){
            int lSize = size(root.left);
            int rSize = size(root.right);
            return lSize+rSize+1;
        }else{
            return 1;
        }
    }

    // returns an array containing all the values in sorted order
    public double[] all() {
        double[] sorted = new double[size(this.root)];
        //sorted[0] = getSmallest(this.root);
        for(int i = 0; i<sorted.length; i++){
            sorted[i] = allHelper(this.root);
        }
        return sorted;
    }
    public double allHelper(TreeNode root){
        if(root != null){
            allHelper(root.left);
            allHelper(root.right);
        }
        return root.item;
    }

    // the smaller number should be on the left. If there is no left node, then the current node should be the smallest number in the tree
    public double getSmallest(TreeNode root){
        if(!hasChild(root)){
            return root.item;
        }else{
            return getSmallest(root.left);
        }
    }

    private static class TreeNode<Double>{
        private double item;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(double value){
            item = value;
            left = null;
            right = null;
        }
    }
    public static void main(String[] args){
        double[] original = {50, 30, 70, 15, 7, 62, 22, 35, 87, 22, 31};
        DoubleBinarySearchTree myTree = new DoubleBinarySearchTree(original);
        double[] sorted = myTree.all();
        for (double v : sorted) {
            System.out.print(v + " ");
        }
        System.out.println("\nDepth: " + myTree.depth());
    }
}
