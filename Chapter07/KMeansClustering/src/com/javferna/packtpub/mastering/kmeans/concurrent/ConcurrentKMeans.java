package com.javferna.packtpub.mastering.kmeans.concurrent;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

import com.javferna.packtpub.mastering.kmeans.serial.Document;
import com.javferna.packtpub.mastering.kmeans.serial.DocumentCluster;

public class ConcurrentKMeans {

	public static DocumentCluster[] calculate(
			Document[] documents, int numberClusters, int vocSize,
			int seed, int maxSize) {
		DocumentCluster[] clusters = new DocumentCluster[numberClusters];

		Random random = new Random(seed);
		for (int i = 0; i < numberClusters; i++) {
			clusters[i] = new DocumentCluster(vocSize, new ConcurrentLinkedQueue<>());
			clusters[i].initialize(random);
		}

		boolean change = true;

		ForkJoinPool pool = new ForkJoinPool();
		int numSteps = 0;
		while (change) {
			change = assignment(clusters, documents, maxSize, pool);
			update(clusters, maxSize, pool);
			numSteps++;
		}
		pool.shutdown();
		System.out.println("Number of steps: "+numSteps);
		return clusters;
	}

	private static boolean assignment(DocumentCluster[] clusters,
			Document[] documents, int maxSize, ForkJoinPool pool) {


		for (DocumentCluster cluster : clusters) {
			cluster.clearClusters();
		}

		AtomicInteger numChanges = new AtomicInteger(0);
		AssignmentTask task = new AssignmentTask(clusters, documents, 0,
				documents.length, numChanges, maxSize);


		pool.execute(task);

		task.join();


		System.out.println("Number of Changes: " + numChanges);

		return numChanges.get() > 0;
	}

	private static void update(DocumentCluster[] clusters, int maxSize, ForkJoinPool pool) {
		UpdateTask task = new UpdateTask(clusters, 0, clusters.length, maxSize);


		pool.execute(task);

		task.join();


	}

}
