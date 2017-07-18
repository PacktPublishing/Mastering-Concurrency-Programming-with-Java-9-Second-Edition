package com.javferna.packtpub.book.mastering.synchronization.lock;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import com.javferna.packtpub.book.mastering.synchronization.common.CommonTask;

public class LockTask implements Runnable {

	private static ReentrantLock lock = new ReentrantLock();
	private String name;

	public LockTask(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			lock.lock();
			System.out.println("Task: " + name + "; Date: " + new Date() + ": Running the task");
			CommonTask.doTask();
			System.out.println("Task: " + name + "; Date: " + new Date() + ": The execution has finished");
		} finally {
			lock.unlock();
		}
	}

}
