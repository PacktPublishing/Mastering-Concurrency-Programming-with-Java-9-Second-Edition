package com.javferna.packtpub.book.mastering.synchronization.completable;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.javferna.packtpub.book.mastering.synchronization.data.Product;

public class SearchTask implements Function<List<Product>, List<Product>> {

	private String query;
	
	public SearchTask(String query) {
		this.query=query;
	}

	@Override
	public List<Product> apply(List<Product> products) {
		System.out.println(new Date()+": CompletableTask: start");
		List<Product> ret = products.stream()
				.filter(product -> product.getTitle().toLowerCase().contains(query))
				.collect(Collectors.toList());
		System.out.println(new Date()+": CompletableTask: end: "+ret.size());
		return ret;
	}

}
