package com.javferna.packtpub.book.mastering.structures.hash.java8;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import com.javferna.packtpub.book.mastering.structures.hash.data.ConcurrentLoaderAccumulator;
import com.javferna.packtpub.book.mastering.structures.hash.data.ExtendedProduct;
import com.javferna.packtpub.book.mastering.structures.hash.data.Product;
import com.javferna.packtpub.book.mastering.structures.hash.data.Review;

class ConcurrentHashMainAmazon {

	public static void main(String[] args) {
		
		Path file = Paths.get("data\\category");
		
		ConcurrentLinkedDeque<Product> productList;
		try {
			productList = Files
					.walk(file, FileVisitOption.FOLLOW_LINKS)
					.parallel()
					.filter(f -> f.toString().endsWith(".txt"))
					.collect(ConcurrentLinkedDeque<Product>::new,
							new ConcurrentLoaderAccumulator(),
							ConcurrentLinkedDeque::addAll);

			ConcurrentHashMap<String, List<ExtendedProduct>> productsByBuyer=(ConcurrentHashMap<String, List<ExtendedProduct>>)productList
					.parallelStream()
					.map(p -> {
						List<ExtendedProduct> products = new ArrayList<ExtendedProduct>();
						List<Review> reviews = p.getReviews();
						reviews.forEach(r -> {
							ExtendedProduct newProduct = new ExtendedProduct(p, r.getUser(), r.getValue());
							products.add(newProduct);
						});
						return products;
					})
					.flatMap(Collection::stream)
					.parallel()
					.collect(Collectors.groupingByConcurrent( p -> p.getBuyer()));
			
			//Number of products per buyer
			productsByBuyer.forEach( (id, list) -> System.out.println(id+": "+list.size()));
			
			//Average rating - forEach
			productsByBuyer.forEach( (id, list) -> {
					double average=list.stream().mapToDouble(item -> item.getValue()).average().getAsDouble();
					System.out.println(id+": "+average);
				});
			
			//Searching - search
			ExtendedProduct firstProduct=productsByBuyer.search(100,
				(id, products) -> { 
					for (ExtendedProduct product: products) {
						if (product.getTitle().toLowerCase().contains("java")) {
							return product;
						}
					}
				return null;
			});
			
			if (firstProduct!=null) {
				System.out.println(firstProduct.getBuyer()+":"+firstProduct.getTitle());
			}
			
			// Transformer
			BiFunction<String, List<ExtendedProduct>, List<ExtendedProduct>> transformer = (key, value) -> value.stream().filter(product -> product.getValue() == 1).collect(Collectors.toList());
			
			// Reducer
			BinaryOperator<List<ExtendedProduct>> reducer = (list1, list2) -> {
			    list1.addAll(list2);
			    return list1;
			};
			
			// Reduce - reduce
			List<ExtendedProduct> badReviews=productsByBuyer.reduce(10, transformer, reducer);
			badReviews.forEach(product -> {
				System.out.println(product.getTitle()+":"+product.getBuyer()+":"+product.getValue());
			});
			
			// Compute method
			ConcurrentHashMap<String, LongAdder> counter=new ConcurrentHashMap<>();
			
			badReviews.forEach(product -> {
				counter.computeIfAbsent(product.getTitle(), title -> new LongAdder()).increment();
			});
			
			counter.forEach((title, count) -> {
				System.out.println(title+":"+count);
			});
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
