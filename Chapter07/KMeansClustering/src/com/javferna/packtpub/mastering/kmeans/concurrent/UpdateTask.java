package com.javferna.packtpub.mastering.kmeans.concurrent;

import java.util.concurrent.RecursiveAction;

import com.javferna.packtpub.mastering.kmeans.serial.DocumentCluster;

public class UpdateTask extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1005287726225369344L;

	private DocumentCluster[] clusters;

	private int start, end;

	private int maxSize;

	public UpdateTask(DocumentCluster[] clusters, int start, int end,
			int maxSize) {
		this.clusters = clusters;
		this.start = start;
		this.end = end;
		this.maxSize = maxSize;
	}

	@Override
	protected void compute() {
		if (end - start <= maxSize) {
			for (int i = start; i < end; i++) {
				DocumentCluster cluster = clusters[i];
				cluster.calculateCentroid();
			}
		} else {
			int mid = (start + end) / 2;
			UpdateTask task1 = new UpdateTask(clusters, start, mid, maxSize);
			UpdateTask task2 = new UpdateTask(clusters, mid, end, maxSize);

			invokeAll(task1, task2);
		}

	}

}
