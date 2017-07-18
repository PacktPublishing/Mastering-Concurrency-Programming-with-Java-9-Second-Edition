package com.javferna.packtpub.book.mastering.structures.collection.java8;

import java.util.Spliterator;

import com.javferna.packtpub.book.mastering.structures.hash.data.Product;

public class SpliteratorTask implements Runnable {

	private Spliterator<Product> spliterator;
	
	public SpliteratorTask (Spliterator<Product> spliterator) {
		this.spliterator=spliterator;
	}
	
	@Override
	public void run() {
		int counter=0;
		while (spliterator.tryAdvance(product -> {
			product.setTitle(product.getTitle().toLowerCase());
		})) {
			counter++;
		};
		System.out.println(Thread.currentThread().getName()+":"+counter);
	}

}
