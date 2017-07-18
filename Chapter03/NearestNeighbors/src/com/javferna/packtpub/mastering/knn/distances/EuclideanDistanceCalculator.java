package com.javferna.packtpub.mastering.knn.distances;

import com.javferna.packtpub.mastering.knn.data.Sample;

/**
 * This class calculates the distance between two examples using the euclidean formula
 * @author author
 *
 */
public class EuclideanDistanceCalculator {
	
	/**
	 * Method that calculates the euclidean distance between two examples
	 * @param example1 One of the examples
	 * @param example2 The other example
	 * @return The euclidean distance between the two examples
	 * @throws Exception Exception if something goes wrong
	 */
	public static double calculate (Sample example1, Sample example2) {
		double ret=0.0d;
		
		double[] data1=example1.getExample();
		double[] data2=example2.getExample();
		
		if (data1.length!=data2.length) {
			throw new IllegalArgumentException ("Vector doesn't have the same length");
		}
		
		for (int i=0; i<data1.length; i++) {
			ret+=Math.pow(data1[i]-data2[i], 2);
		}
		return Math.sqrt(ret);

	}

}
