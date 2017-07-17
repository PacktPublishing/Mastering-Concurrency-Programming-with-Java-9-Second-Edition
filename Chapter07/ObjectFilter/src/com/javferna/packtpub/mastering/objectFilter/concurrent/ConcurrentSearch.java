package com.javferna.packtpub.mastering.objectFilter.concurrent;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import com.javferna.packtpub.mastering.objectFilter.common.data.CensusData;
import com.javferna.packtpub.mastering.objectFilter.common.data.FilterData;

public class ConcurrentSearch {

	public static CensusData findAny(CensusData[] data, List<FilterData> filters, int size) {
		
		System.out.println(data);
		System.out.println(filters);
		System.out.println(size);

		TaskManager manager = new TaskManager();
		IndividualTask task = new IndividualTask(data, 0, data.length, manager, size, filters);
		ForkJoinPool.commonPool().execute(task);
		try {
			CensusData result = task.join();

			if (result != null) {
				System.out.println("Find First Result: " + result.getCitizenship());
			}

			return result;
		} catch (Exception e) {
			System.err.println("findFirst has finished with an error: " + task.getException().getMessage());
		}
		return null;
	}

	public static List<CensusData> findAll(CensusData[] data, List<FilterData> filters, int size) {
		List<CensusData> results;

		TaskManager manager = new TaskManager();
		ListTask task = new ListTask(data, 0, data.length, manager, size, filters);
		ForkJoinPool.commonPool().execute(task);

		try {
			results = task.join();
			return results;
		} catch (Exception e) {
			System.err.println("findFirst has finished with an error: " + task.getException().getMessage());
		}
		return null;

	}

}
