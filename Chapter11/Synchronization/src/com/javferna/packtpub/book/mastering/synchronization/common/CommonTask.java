package com.javferna.packtpub.book.mastering.synchronization.common;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CommonTask {
	
	public static void doTask() {
		
		long duration = ThreadLocalRandom.current().nextLong(10);
		System.out.printf("%s-%s: Working %d seconds\n",new Date(),Thread.currentThread().getName(),duration);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
