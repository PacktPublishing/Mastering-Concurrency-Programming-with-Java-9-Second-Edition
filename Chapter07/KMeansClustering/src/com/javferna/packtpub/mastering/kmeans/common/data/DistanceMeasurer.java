package com.javferna.packtpub.mastering.kmeans.common.data;

public class DistanceMeasurer {

	public static double euclideanDistance(Word[] words, double[] centroid) {
		double distance = 0;

		int wordIndex = 0;
		for (int i = 0; i < centroid.length; i++) {
			if ((wordIndex < words.length)
					&& (words[wordIndex].getIndex() == i)) {
				distance += Math.pow(
						(words[wordIndex].getTfidf() - centroid[i]), 2);
				wordIndex++;
			} else {
				distance += centroid[i] * centroid[i];
			}
		}

		return Math.sqrt(distance);
	}

}
