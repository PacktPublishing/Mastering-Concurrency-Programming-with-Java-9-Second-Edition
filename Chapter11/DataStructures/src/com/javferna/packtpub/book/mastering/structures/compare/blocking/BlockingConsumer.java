package com.javferna.packtpub.book.mastering.structures.compare.blocking;

import java.util.concurrent.LinkedBlockingQueue;

import com.javferna.packtpub.book.mastering.structures.compare.Item;

public class BlockingConsumer implements Runnable {

	private LinkedBlockingQueue<Item> queue;
	
	public BlockingConsumer (LinkedBlockingQueue<Item> queue) {
		this.queue=queue;
	}
	@Override
	public void run() {
		System.out.println("Consumer start: "+Thread.currentThread().getName());
		for (int i=0; i<1000000; i++) {
			try {
				Item item=queue.take();
				System.out.println(item.getThreadName()+":"+item.getId()+":"+item.getDate());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Consumer end: "+Thread.currentThread().getName());
	}

}
