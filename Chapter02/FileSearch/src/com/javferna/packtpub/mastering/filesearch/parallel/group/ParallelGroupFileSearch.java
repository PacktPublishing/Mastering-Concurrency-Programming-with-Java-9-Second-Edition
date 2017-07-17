package com.javferna.packtpub.mastering.filesearch.parallel.group;

import java.io.File;
import java.lang.Thread.State;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.javferna.packtpub.mastering.filesearch.utils.Result;

public class ParallelGroupFileSearch {

	public static void searchFiles(File file, String fileName, Result parallelResult) {

		ConcurrentLinkedQueue<File> directories = new ConcurrentLinkedQueue<>();
		File[] contents = file.listFiles();

		for (File content : contents) {
			if (content.isDirectory()) {
				directories.add(content);
			}
		}

		int numThreads = Runtime.getRuntime().availableProcessors();
		Thread[] threads = new Thread[numThreads];
		ParallelGroupFileTask[] tasks = new ParallelGroupFileTask[numThreads];

		for (int i = 0; i < numThreads; i++) {
			tasks[i] = new ParallelGroupFileTask(fileName, parallelResult, directories);
			threads[i] = new Thread(tasks[i]);
			threads[i].start();
		}

		boolean finish = false;
		int numFinished = 0;

		while (!finish) {
			numFinished = 0;
			for (int i = 0; i < threads.length; i++) {
				if (threads[i].getState() == State.TERMINATED) {
					numFinished++;
					if (tasks[i].getFound()) {
						finish = true;
					}
				}
			}
			if (numFinished == threads.length) {
				finish = true;
			}
		}

		// Interrupt the remaining threads.
		if (numFinished != threads.length) {
			for (Thread thread : threads) {
				thread.interrupt();
			}
		}

	}
}
