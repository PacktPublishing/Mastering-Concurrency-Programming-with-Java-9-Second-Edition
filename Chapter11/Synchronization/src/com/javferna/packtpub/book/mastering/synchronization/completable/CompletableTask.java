package com.javferna.packtpub.book.mastering.synchronization.completable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import com.javferna.packtpub.book.mastering.synchronization.data.Product;

public class CompletableTask implements Function<List<Product>, List<Product>> {

	private String query;
	
	public CompletableTask(String query) {
		this.query=query;
	}

	@Override
	public List<Product> apply(List<Product> products) {
		List<Product> ret=new ArrayList<>();
		System.out.println(new Date()+": CompletableTask: start");
		products.forEach(product -> {
			if (product.getTitle().toLowerCase().contains(query)) {
				ret.add(product);
			}
		});
		System.out.println(new Date()+": CompletableTask: end: "+ret.size());
		return ret;
	}

}
