/**
 * 
 */
package edu.ics211.h04;

/**
 * @author esb
 *
 */
public class StringComparator implements java.util.Comparator<String> {
	
	public int compare(String s1, String s2) {
		return s1.compareTo(s2);
	}

	/**
	 * basic test for functionality
	 * @param args (ignored)
	 */
	public static void main(String[] args) {
		StringComparator c = new StringComparator();
		System.out.println("hello compared to world returns " + c.compare("hello", "world"));
		System.out.println("world compared to hello returns " + c.compare("world", "hello"));		
		System.out.println("world compared to world returns " + c.compare("world", "world"));		
	}

}