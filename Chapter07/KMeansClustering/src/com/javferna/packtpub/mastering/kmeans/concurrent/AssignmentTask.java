package com.javferna.packtpub.mastering.kmeans.concurrent;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

import com.javferna.packtpub.mastering.kmeans.common.data.DistanceMeasurer;
import com.javferna.packtpub.mastering.kmeans.serial.Document;
import com.javferna.packtpub.mastering.kmeans.serial.DocumentCluster;

public class AssignmentTask extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8709589012589400116L;

	private DocumentCluster[] clusters;

	private Document[] documents;

	private int start, end;

	private AtomicInteger numChanges;

	private int maxSize;

	public AssignmentTask(DocumentCluster[] clusters,
			Document[] documents, int start, int end,
			AtomicInteger numChanges, int maxSize) {
		this.clusters = clusters;
		this.documents = documents;
		this.start = start;
		this.end = end;
		this.numChanges = numChanges;
		this.maxSize = maxSize;
	}

	@Override
	protected void compute() {
		if (end - start <= maxSize) {
			for (int i = start; i < end; i++) {
				Document document = documents[i];
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
					numChanges.incrementAndGet();
				}

			}
		} else {
			int mid = (start + end) / 2;
			AssignmentTask task1 = new AssignmentTask(clusters, documents, start,
					mid, numChanges, maxSize);
			AssignmentTask task2 = new AssignmentTask(clusters, documents, mid,
					end, numChanges, maxSize);

			invokeAll(task1, task2);
		}
	}

	public DocumentCluster[] getClusters() {
		return clusters;
	}

	public void setClusters(DocumentCluster[] clusters) {
		this.clusters = clusters;
	}

	public Document[] getDocuments() {
		return documents;
	}

	public void setDocuments(Document[] documents) {
		this.documents = documents;
	}

}
