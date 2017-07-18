package com.javferna.packtpub.mastering.knn.data;

/**
 * Abstract class that defines the basic elements of an example to the knn algoritm
 * @author author
 *
 */
public abstract class Sample {

	/**
	 * Method that returns the tag or class of the example
	 * @return The tag or class of the examples
	 */
	public abstract String getTag();
	
	/**
	 * Method that return the values of the attributes of the example as an array of doubles
	 * @return The values of the attributes of the example
	 */
	public abstract double[] getExample();
}
