package com.javferna.packtpub.book.mastering.synchronization.lock;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LockMain {

	public static void main(String[] args) {

		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		
		for (int i=0; i<10; i++) {
			executor.execute(new LockTask("Task "+i));
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		

	}

}
