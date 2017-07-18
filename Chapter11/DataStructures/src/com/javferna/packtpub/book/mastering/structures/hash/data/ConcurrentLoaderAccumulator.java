package com.javferna.packtpub.book.mastering.structures.hash.data;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.BiConsumer;

public class ConcurrentLoaderAccumulator implements
		BiConsumer<ConcurrentLinkedDeque<Product>, Path> {

	@Override
	public void accept(ConcurrentLinkedDeque<Product> list, Path path) {

		Product product=ProductLoader.load(path);
		list.add(product);
		
	}

}
