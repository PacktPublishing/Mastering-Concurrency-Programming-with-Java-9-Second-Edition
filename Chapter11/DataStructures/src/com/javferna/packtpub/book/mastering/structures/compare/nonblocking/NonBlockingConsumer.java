package com.javferna.packtpub.book.mastering.structures.compare.nonblocking;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.javferna.packtpub.book.mastering.structures.compare.Item;

public class NonBlockingConsumer implements Runnable {

	private ConcurrentLinkedQueue<Item> queue;
	
	public NonBlockingConsumer (ConcurrentLinkedQueue<Item> queue) {
		this.queue=queue;
	}
	@Override
	public void run() {
		System.out.println("Consumer start: "+Thread.currentThread().getName());
		for (int i=0; i<1000000; i++) {
				Item item=queue.poll();
				System.out.println(item.getThreadName()+":"+item.getId()+":"+item.getDate());
		}
		System.out.println("Consumer end: "+Thread.currentThread().getName());
	}

}
