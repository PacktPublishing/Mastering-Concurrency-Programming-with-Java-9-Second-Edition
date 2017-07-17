package com.javferna.packtpub.mastering.mergeSort.concurrent;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

import com.javferna.packtpub.mastering.mergeSort.common.AmazonMetaData;
import com.javferna.packtpub.mastering.mergeSort.common.AmazonMetaDataLoader;

public class ConcurrentMetaData {

	public static void main(String[] args) {

		for (int j = 0; j < 10; j++) {
			Path path = Paths.get("data", "amazon-meta.csv");

			AmazonMetaData data[] = AmazonMetaDataLoader.load(path);
			AmazonMetaData data2[] = Arrays.copyOf(data, data.length);// copy(data);

			Date start, end;

			start = new Date();
			Arrays.parallelSort(data);
			end = new Date();
			System.out.println("Execution Time Java Arrays.parallelSort(): " + (end.getTime() - start.getTime()));

			System.out.println(data[0].getTitle());
			System.out.println(data2[0].getTitle());
			ConcurrentMergeSort mySorter = new ConcurrentMergeSort();
			start = new Date();
			mySorter.mergeSort(data2, 0, data2.length);
			end = new Date();

			System.out.println("Execution Time Java ConcurrentMergeSort: " + (end.getTime() - start.getTime()));

			for (int i = 0; i < data.length; i++) {
				if (data[i].compareTo(data2[i]) != 0) {
					System.err.println("There's a difference is position " + i);
					System.exit(-1);
				}
			}

			System.out.println("Both arrays are equal");
		}
	}
}
