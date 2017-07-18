package com.javferna.packtpub.book.mastering.test.common;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class CommonPhaserTask implements Runnable {
	
	private Phaser phaser;
	
	public CommonPhaserTask (Phaser phaser) {
		this.phaser=phaser;
	}
	
	@Override
	public void run() {
		
		long duration=(long)(Math.random()*10);
		System.out.printf("%s-%s: Working %d seconds\n",new Date(),Thread.currentThread().getName(),duration);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		phaser.arriveAndAwaitAdvance();
		
		duration=(long)(Math.random()*10);
		System.out.printf("%s-%s: Working %d seconds\n",new Date(),Thread.currentThread().getName(),duration);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		phaser.arriveAndDeregister();
	}

}
