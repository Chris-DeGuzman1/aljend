package edu.ics211.h05;

import java.util.Comparator;

//@author Chris
//@author Rumesh
public class DoubleComparator implements Comparator<Double> {

	@Override
	public int compare(Double o1, Double o2) {
		final double epsilon = 0.01;
		int val;
		double delta = o2 - o1;
		if (Math.abs(delta) < epsilon) {
			val = 0;
			return val;
		} else if (o1 < o2) {
			val = -1;
			return val;
		} else if (o1 > o2) {
			val = 1;
			return val;
		} else {
			return 0;
		}

	}
}
