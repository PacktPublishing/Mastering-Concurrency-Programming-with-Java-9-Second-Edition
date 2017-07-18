package com.javferna.packtpub.book.mastering.structures.hash.java8;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.javferna.packtpub.book.mastering.structures.hash.data.BasicProduct;
import com.javferna.packtpub.book.mastering.structures.hash.data.BasicProductLoader;
import com.javferna.packtpub.book.mastering.structures.hash.data.BasicReview;

class ConcurrentHashMainBasicAmazon {

	public static void main(String[] args) {

		Path path=Paths.get("data\\amazon\\1995.txt");
		ConcurrentHashMap<BasicProduct, ConcurrentLinkedDeque<BasicReview>> products1995=BasicProductLoader.load(path);
		showData(products1995);
		
		path=Paths.get("data\\amazon\\1996.txt");
		ConcurrentHashMap<BasicProduct, ConcurrentLinkedDeque<BasicReview>> products1996=BasicProductLoader.load(path);
		System.out.println(products1996.size());
		showData(products1996);
		
		products1996.forEach(10,(product, reviews) -> {
			products1995.merge(product, reviews, (reviews1, reviews2) -> {
				System.out.println("Merge for: "+product.getAsin());
				reviews1.addAll(reviews2);
				return reviews1;
			});
		});
		showData(products1995);

	}

	private static void showData(ConcurrentHashMap<BasicProduct, ConcurrentLinkedDeque<BasicReview>> products) {
		System.out.println(products.size());
		BasicProduct basicProduct = new BasicProduct();
		
		basicProduct.setAsin("0743519787");
		ConcurrentLinkedDeque<BasicReview> listReviews=products.get(basicProduct);
		System.out.println(listReviews.size());
		listReviews.forEach(review -> System.out.print(review.getUser()+":"+review.getValue()+";"));
		System.out.println();
		
	}

}
