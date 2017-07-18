package com.javferna.packtpub.mastering.knn.data;

/**
 * This class stores the distance between an example of the train data
 * and the example the algorithm wants to classify
 * @author author
 *
 */
public class Distance implements Comparable<Distance> {

	/**
	 * Index of the train instance
	 */
	private int index;
	/**
	 * Distance between the train instance and the example
	 */
	private double distance;
	
	
	/**
	 * Method that compare two Distance objects
	 * @param other: The other object to calculate the distance
	 * @return The distance between this instance and the other instance
	 */
	@Override
	public int compareTo(Distance other) {
		if (this.distance < other.getDistance()) {
			return -1;
		} else if (this.distance > other.getDistance()) {
			return 1;
		}
		return 0;
	}

	/**
	 * Method that returns the index
	 * @return The index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Method that sets the index
	 * @param index The index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Method that returns the distance
	 * @return The distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Method that sets the distance
	 * @param distance The distance
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	
	
}
