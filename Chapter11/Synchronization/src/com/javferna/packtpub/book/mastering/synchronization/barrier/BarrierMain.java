package com.javferna.packtpub.book.mastering.synchronization.barrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BarrierMain {

	public static void main(String[] args) {
		CyclicBarrier barrier=new CyclicBarrier(10,new FinishBarrierTask());

		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();

		for (int i=0; i<10; i++) {
			executor.execute(new BarrierTask(barrier));
		}

		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
	}

}
