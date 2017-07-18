package com.javferna.packtpub.book.mastering.synchronization.semaphore;

import java.util.concurrent.Semaphore;

import com.javferna.packtpub.book.mastering.synchronization.common.CommonTask;

public class SemaphoreTask implements Runnable{

	private Semaphore semaphore;
	
	public SemaphoreTask(Semaphore semaphore) {
		this.semaphore=semaphore;
	}
	
	@Override
	public void run() {

		try {
			semaphore.acquire();
			CommonTask.doTask();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}
