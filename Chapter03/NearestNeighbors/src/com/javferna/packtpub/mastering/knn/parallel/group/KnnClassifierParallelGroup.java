package com.javferna.packtpub.mastering.knn.parallel.group;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.javferna.packtpub.mastering.knn.data.Distance;
import com.javferna.packtpub.mastering.knn.data.Sample;

/**
 * Coarse-grained concurrent version of the Knn algorithm
 * @author author
 *
 */
public class KnnClassifierParallelGroup {

	/**
	 * Train data
	 */
	private List<? extends Sample> dataSet;
	
	/**
	 * K parameter
	 */
	private int k;
	
	/**
	 * Executor to execute the concurrent tasks
	 */
	private ThreadPoolExecutor executor;
	
	/**
	 * Number of threads to configure the executor
	 */
	private int numThreads;
	
	/**
	 * Check to indicate if we use the serial or the parallel sorting
	 */
	private boolean parallelSort;
	
	/**
	 * Constructor of the class. Initialize the internal data
	 * @param dataSet Train data set
	 * @param k K parameter
	 * @param factor Factor of increment of the number of cores
	 * @param parallelSort Check to indicate if we use the serial or the parallel sorting
	 */
	public KnnClassifierParallelGroup(List<? extends Sample> dataSet, int k, int factor, boolean parallelSort) {
		this.dataSet=dataSet;
		this.k=k;
		numThreads=factor*(Runtime.getRuntime().availableProcessors());
		executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
		this.parallelSort=parallelSort;
	}
	
	/**
	 * Method that classify an example
	 * @param example Example to classify
	 * @return Class or tag of the example
	 * @throws Exception Exception if something goes wrong
	 */
	public String classify (Sample example) throws Exception {
		
		Distance[] distances=new Distance[dataSet.size()];
		CountDownLatch endControler=new CountDownLatch(numThreads);
		
		int length=dataSet.size()/numThreads;
		int startIndex=0, endIndex=length;
		
		for (int i=0; i <numThreads; i++) {
			GroupDistanceTask task=new GroupDistanceTask(distances, startIndex, endIndex, dataSet, example, endControler);
			startIndex=endIndex;
			if (i<numThreads-2) {
				endIndex=endIndex+length; 
			} else {
				endIndex=dataSet.size();
			}
			executor.execute(task);
			
		}
		endControler.await();

		if (parallelSort) {
			Arrays.parallelSort(distances);
		} else {
			Arrays.sort(distances);
		}
		
		Map<String, Integer> results = new HashMap<>();
		for (int i = 0; i < k; i++) {
		  Sample localExample = dataSet.get(distances[i].getIndex());
		  String tag = localExample.getTag();
		  results.merge(tag, 1, (a, b) -> a+b);
		}
		
		return Collections.max(results.entrySet(),
			    Map.Entry.comparingByValue()).getKey();
	}
	
	/**
	 * Method that finish the execution of the executor
	 */
	public void destroy() {
		executor.shutdown();
	}
	
}
