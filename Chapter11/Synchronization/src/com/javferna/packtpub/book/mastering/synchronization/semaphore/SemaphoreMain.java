package com.javferna.packtpub.book.mastering.synchronization.semaphore;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SemaphoreMain {

	public static void main(String[] args) {

		Semaphore semaphore=new Semaphore(2);
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		
		for (int i=0; i<10; i++) {
			executor.execute(new SemaphoreTask(semaphore));
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
