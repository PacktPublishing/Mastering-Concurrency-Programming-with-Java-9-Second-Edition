package com.javferna.packtpub.mastering.filesearch.parallel.group;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.javferna.packtpub.mastering.filesearch.utils.Result;

public class ParallelGroupFileTask implements Runnable {

	private final String fileName;
	private final ConcurrentLinkedQueue<File> directories;
	private final Result parallelResult;
	private boolean found;

	public ParallelGroupFileTask(String fileName, Result parallelResult, ConcurrentLinkedQueue<File> directories) {
		this.fileName = fileName;
		this.parallelResult = parallelResult;
		this.directories = directories;
		this.found = false;
	}

	@Override
	public void run() {
		while (directories.size() > 0) {
			File file = directories.poll();
			try {
				processDirectory(file, fileName, parallelResult);
				if (found) {
					System.out.printf("%s has found the file%n", Thread.currentThread().getName());
					System.out.printf("Parallel Search: Path: %s%n", parallelResult.getPath());
					return;
				}
			} catch (InterruptedException e) {
				System.out.printf("%s has been interrupted%n", Thread.currentThread().getName());
			}
		}
	}

	private void processDirectory(File file, String fileName, Result parallelResult) throws InterruptedException {
		File[] contents;
		contents = file.listFiles();

		if ((contents == null) || (contents.length == 0)) {
			return;
		}

		for (File content : contents) {
			if (content.isDirectory()) {
				processDirectory(content, fileName, parallelResult);
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}
				if (found) {
					return;
				}
			} else {
				processFile(content, fileName, parallelResult);
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}
				if (found) {
					return;
				}
			}
		}
	}

	private void processFile(File content, String fileName, Result parallelResult) {
		if (content.getName().equals(fileName)) {
			parallelResult.setPath(content.getAbsolutePath());
			this.found = true;
		}
	}

	public boolean getFound() {
		return found;
	}

}
