package com.javferna.packtpub.book.mastering.test.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.javferna.packtpub.book.mastering.test.common.CommonTask;

public class MainExecutor {

	public static void main(String[] args) {

		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		
		for (int i=0; i<10; i++) {
			executor.execute(new CommonTask());
		}

		for (int i=0; i<10; i++) {
			System.out.println("*******************************************");
			System.out.println("Active Count: "+executor.getActiveCount());
			System.out.println("Completed Task Count: "+executor.getCompletedTaskCount());
			System.out.println("Core Pool Size: "+executor.getCorePoolSize());
			System.out.println("Largest Pool Size: "+executor.getLargestPoolSize());
			System.out.println("Maximum Pool Size: "+executor.getMaximumPoolSize());
			System.out.println("Pool Size: "+executor.getPoolSize());
			System.out.println("Task Count: "+executor.getTaskCount());
			System.out.println("Terminated: "+executor.isTerminated());
			System.out.println("Is Terminating: "+executor.isTerminating());
			System.out.println("*******************************************");
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
		
		System.out.println("*******************************************");
		System.out.println("Active Count: "+executor.getActiveCount());
		System.out.println("Completed Task Count: "+executor.getCompletedTaskCount());
		System.out.println("Core Pool Size: "+executor.getCorePoolSize());
		System.out.println("Largest Pool Size: "+executor.getLargestPoolSize());
		System.out.println("Maximum Pool Size: "+executor.getMaximumPoolSize());
		System.out.println("Pool Size: "+executor.getPoolSize());
		System.out.println("Task Count: "+executor.getTaskCount());
		System.out.println("Terminated: "+executor.isTerminated());
		System.out.println("Is Terminating: "+executor.isTerminating());
		System.out.println("*******************************************");

	}

}
