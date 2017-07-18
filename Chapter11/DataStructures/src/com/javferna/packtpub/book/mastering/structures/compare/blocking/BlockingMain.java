package com.javferna.packtpub.book.mastering.structures.compare.blocking;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.javferna.packtpub.book.mastering.structures.compare.Item;

public class BlockingMain {

	public static void main(String[] args) {

		// int numTasks=Integer.valueOf(args[0]);

		int numTasks = 4;

		LinkedBlockingQueue<Item> queue = new LinkedBlockingQueue<Item>();

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newCachedThreadPool();

		Date start, end;
		start=new Date();
		for (int i = 0; i < numTasks; i++) {
			BlockingProducer producer = new BlockingProducer(queue);
			executor.execute(producer);
		}

		for (int i = 0; i < numTasks; i++) {
			BlockingConsumer consumer = new BlockingConsumer(queue);
			executor.execute(consumer);
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1,TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		end=new Date();
		System.out.println("Execution Time: "+(end.getTime()-start.getTime()));
	}

}
