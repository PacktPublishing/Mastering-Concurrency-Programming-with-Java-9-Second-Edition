package com.javferna.packtpub.mastering.kmeans.serial;

import java.util.ArrayList;
import java.util.Random;

import com.javferna.packtpub.mastering.kmeans.common.data.DistanceMeasurer;

public class SerialKMeans {

	public static DocumentCluster[] calculate(Document[] documents,
			int clusterCount, int vocSize, int seed) {
		DocumentCluster[] clusters = new DocumentCluster[clusterCount];

		Random random = new Random(seed);
		for (int i = 0; i < clusterCount; i++) {
			clusters[i] = new DocumentCluster(vocSize, new ArrayList<>());
			clusters[i].initialize(random);
		}

		boolean change = true;

		int numSteps = 0;
		while (change) {
			change = assignment(clusters, documents);
			update(clusters);
			numSteps++;
		}
		System.out.println("Number of steps: "+numSteps);
		return clusters;
	}

	private static boolean assignment(DocumentCluster[] clusters,
			Document[] documents) {


		for (DocumentCluster cluster : clusters) {
			cluster.clearClusters();
		}

		int numChanges = 0;
		for (Document document : documents) {
			double distance = Double.MAX_VALUE;
			DocumentCluster selectedCluster = null;
			for (DocumentCluster cluster : clusters) {
				double curDistance = DistanceMeasurer.euclideanDistance(
						document.getData(), cluster.getCentroid());
				if (curDistance < distance) {
					distance = curDistance;
					selectedCluster = cluster;
				}
			}
			selectedCluster.addDocument(document);
			boolean result = document.setCluster(selectedCluster);
			if (result) {
				numChanges++;
			}
		}
		System.out.println("Number of Changes: " + numChanges);
		return numChanges > 0;
	}

	private static void update(DocumentCluster[] clusters) {
		for (DocumentCluster cluster : clusters) {
			cluster.calculateCentroid();
		}

	}

}
