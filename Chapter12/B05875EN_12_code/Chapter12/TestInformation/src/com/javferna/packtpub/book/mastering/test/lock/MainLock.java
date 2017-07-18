package com.javferna.packtpub.book.mastering.test.lock;

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.javferna.packtpub.book.mastering.test.common.CommonLockTask;

public class MainLock {

	public static void main(String[] args) {

		MyLock lock = new MyLock();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			executor.execute(new CommonLockTask(lock));
		}

		for (int i = 0; i < 100; i++) {
			System.out.println("************************\n");
			System.out.println("Owner : " + lock.getOwnerName());
			System.out.println("Queued Threads: " + lock.hasQueuedThreads());
			if (lock.hasQueuedThreads()) {
				System.out.println("Queue Length: " + lock.getQueueLength());
				System.out.println("Queued Threads: ");
				Collection<Thread> lockedThreads = lock.getThreads();
				for (Thread lockedThread : lockedThreads) {
					System.out.println(lockedThread.getName());
				}
			}
			System.out.println("Fairness: " + lock.isFair());
			System.out.println("Locked: " + lock.isLocked());
			System.out.println("Holds: "+lock.getHoldCount());
			System.out.println("************************\n");

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		executor.shutdown();
		
		System.out.println("************************\n");
		System.out.println("Owner : " + lock.getOwnerName());
		System.out.println("Queued Threads: " + lock.hasQueuedThreads());
		if (lock.hasQueuedThreads()) {
			System.out.println("Queue Length: " + lock.getQueueLength());
			System.out.println("Queued Threads: ");
			Collection<Thread> lockedThreads = lock.getThreads();
			for (Thread lockedThread : lockedThreads) {
				System.out.println(lockedThread.getName());
			}
		}
		System.out.println("Fairness: " + lock.isFair());
		System.out.println("Locked: " + lock.isLocked());
		System.out.println("Holds: "+lock.getHoldCount());		
		System.out.println("************************\n");		

	}

}
