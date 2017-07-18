package com.javferna.packtpub.mastering.knn.main;

import java.util.Date;
import java.util.List;

import com.javferna.packtpub.mastering.knn.data.BankMarketing;
import com.javferna.packtpub.mastering.knn.loader.BankMarketingLoader;
import com.javferna.packtpub.mastering.knn.parallel.group.KnnClassifierParallelGroup;

/**
 * Main class that launches the tests using the coarse-grained concurrent
 * version and serial sorting
 * 
 * @author author
 *
 */
public class ParallelGroupMainSort {

	public static void main(String[] args) {

		BankMarketingLoader loader = new BankMarketingLoader();
		List<BankMarketing> train = loader.load("data\\bank.data");
		System.out.println("Train: " + train.size());
		List<BankMarketing> test = loader.load("data\\bank.test");
		System.out.println("Test: " + test.size());
		double currentTime = 0.0d;
		int success = 0, mistakes = 0;

		int k = 10;
		if (args.length==1) {
			k = Integer.parseInt(args[0]);
		}
		
		success = 0;
		mistakes = 0;
		KnnClassifierParallelGroup classifier = new KnnClassifierParallelGroup(train, k, 1, true);
		try {
			Date start, end;
			start = new Date();
			for (BankMarketing example : test) {
				String tag = classifier.classify(example);
				if (tag.equals(example.getTag())) {
					success++;
				} else {
					mistakes++;
				}
			}
			end = new Date();

			currentTime = end.getTime() - start.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		classifier.destroy();

		System.out.println("******************************************");
		System.out.println("Parallel Classifier Group - K: " + k + " - Factor 1 - Parallel Sort: true");
		System.out.println("Success: " + success);
		System.out.println("Mistakes: " + mistakes);
		System.out.println("Execution Time: " + (currentTime / 1000) + " seconds.");
		System.out.println("******************************************");

	}

}
