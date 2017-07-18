package com.javferna.packtpub.book.mastering.test.thread;

import java.util.concurrent.TimeUnit;

import com.javferna.packtpub.book.mastering.test.common.CommonTask;

public class MainThread {

	public static void main(String[] args) {

		Thread thread = new Thread(new CommonTask());

		thread.start();

		for (int i = 0; i < 10; i++) {
			System.out.println("**********************");
			System.out.println("Id: " + thread.getId());
			System.out.println("Name: " + thread.getName());
			System.out.println("Priority: " + thread.getPriority());
			System.out.println("Status: " + thread.getState());
			System.out.println("Stack Trace");
			for(StackTraceElement ste : thread.getStackTrace()) {
				  System.out.println(ste);
			}
			System.out.println("**********************\n");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
