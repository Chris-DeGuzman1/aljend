package edu.ics211.h10;

public class DoubleBinarySearchTree {
	private Node rootNode;

	// constructor, creates an empty binary search tree
	public DoubleBinarySearchTree() {
		rootNode = null;
	}

	// constructor, creates a binary search tree containing all the given values
	public DoubleBinarySearchTree(double[] initialValues) {
		// rootNode = null;
		for (int i = 0; i < initialValues.length; i++) {
			add(initialValues[i]);
		}
		System.out.println(rootNode.item);
		System.out.println(rootNode.left.item);
		System.out.println(rootNode.right.item);
	}

	// add the given value to the binary search tree, or return false if the value
	// is already in the tree
	public boolean add(double value) {
		if(rootNode == null) {
			rootNode = new Node(value);
		}
		try {
			addHelper(rootNode, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private Node addHelper(Node root, double value) throws Exception {
		Node temp = new Node(value);
		Node current;
		// checks if value already exists in tree
		if (!has(value)) {
			throw new Exception();
		}
		// creates new node if current node is null
		if (root == null) {
			System.out.println("added " + value);
			return root = temp;
		}else {
		// traversal
		current = nextNode(root, value);
		return current = addHelper(current, value);
		}
	}

	private Node nextNode(Node root, double value) {
		if (root.item < value) {
			return root.left;
		}else {
			return root.right;
		}
	}

	// return true if the given value is in the binary search tree, false otherwise
	public boolean has(double value) {
		return true;
	}

	private boolean hasHelper() {
		return false;
	}

	// returns true if node has children
	private boolean hasChild(Node root) {
		if (root.left == null || root.right == null) {
			return false;
		} else {
			return true;
		}
	}

	// optional method to return true if the binary search tree has at least one
	// value in the range first..last, false otherwise (and returns false if first >
	// last)
	public boolean hasInRange(double first, double last) {
		return false;
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

	private int size(Node root) {
		if (root == null) {
			return 0;
		} else if (hasChild(root)) {
			int lSize = size(root.left);
			int rSize = size(root.right);
			return lSize + rSize + 1;
		} else {
			return 1;
		}
	}

	// returns an array containing all the values in sorted order
	public double[] all() {
		double[] sorted = new double[size(rootNode)];
		// sorted[0] = getSmallest(this.root);
		for (int i = 0; i < sorted.length; i++) {
			// System.out.println(i);
			sorted[i] = allHelper(rootNode);
		}
		return sorted;
	}

	public double allHelper(Node root) {
		if (root != null) {
			allHelper(root.left);
			allHelper(root.right);
		}
		return (double) root.item;
	}

	// the smaller number should be on the left. If there is no left node, then the
	// current node should be the smallest number in the tree
	public double getSmallest(Node root) {
		if (!hasChild(root)) {
			return (double) root.item;
		} else {
			return getSmallest(root.left);
		}
	}

	// adapted from hw5
	private static class Node {
		private double item;
		private Node left;
		private Node right;

		private Node(double value) {
			item = value;
			left = null;
			right = null;
		}
	}

	public static void main(String[] args) {
		double[] original = { 50, 30, 70, 15, 7, 62, 22, 35, 87, 22, 31 };
		DoubleBinarySearchTree myTree = new DoubleBinarySearchTree(original);
		System.out.println("\nDepth: " + myTree.depth());
		double[] sorted = myTree.all();
		// for (double v : sorted) {
		// System.out.print(v + " ");
		// }
	}
}
