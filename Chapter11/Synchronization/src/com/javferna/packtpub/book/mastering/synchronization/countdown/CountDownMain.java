package com.javferna.packtpub.book.mastering.synchronization.countdown;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CountDownMain {

	public static void main(String[] args) {
		
		CountDownLatch countDownLatch=new CountDownLatch(10);
		
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		
		System.out.println("Main: Launching tasks");
		for (int i=0; i<10; i++) {
			executor.execute(new CountDownTask(countDownLatch));
		}

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main: Tasks finished at "+new Date());
		
		executor.shutdown();
	}

}
