package com.javferna.packtpub.book.mastering.test.common;

import java.util.Date;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class CommonForkTask extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2313055686828957266L;
	private int start;
	private int end;

	public CommonForkTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if ((end - start) > 100) {
			int mid=start+((end-start)/2);
			CommonForkTask task1=new CommonForkTask(start, mid);
			CommonForkTask task2=new CommonForkTask(mid, end);
			
			task1.fork();
			task2.fork();
			
			task1.join();
			task2.join();
		} else {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s-%s: Working %d seconds\n", new Date(), Thread
					.currentThread().getName(), duration);
			try {
				TimeUnit.SECONDS.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
