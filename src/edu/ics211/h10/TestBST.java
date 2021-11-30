package edu.ics211.h10;

import java.util.Random;

public class TestBST {
	public static void main(String[] args) {
		Random rand = new Random();
		DoubleBinarySearchTree firstTree = new DoubleBinarySearchTree();
		// using int because generating a random double seems to generates a value between 0 and 1 and my program always rounds so only 1 value is added
		int lastValue = 2;
		boolean done = false;
		int addedCount = 0;
		long timeAfterHas = 0;
		long timeBeforeHas = 0;
		while (!done) {
			lastValue = rand.nextInt();
			firstTree.add(lastValue);
			timeBeforeHas = System.currentTimeMillis();
			firstTree.has(lastValue);
			timeAfterHas = System.currentTimeMillis();
			addedCount++;
			if ((timeAfterHas - timeBeforeHas >= 3) || (addedCount >= 1000000)) {
				done = true;
			}
		}
		System.out.println("Values added: " + addedCount + "\nTime taken: " + (timeAfterHas - timeBeforeHas));
		System.out.println("Tree size: " + firstTree.size() + "\nTree Depth: " + firstTree.depth());
		double[] sorted = firstTree.all();
		DoubleBinarySearchTree balancedTree = new DoubleBinarySearchTree(sorted);
		timeBeforeHas = System.currentTimeMillis();
		balancedTree.has(lastValue);
		timeAfterHas = System.currentTimeMillis();
		System.out.println("Balanced Tree size: " +  balancedTree.size() + "\nBalanced Tree depth: " + balancedTree.depth());
		System.out.println("Balanced Tree time taken: " + (timeAfterHas - timeBeforeHas));
	}

}
