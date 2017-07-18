package com.javferna.packtpub.book.mastering.structures.hash.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class BasicProductLoader {

	public static ConcurrentHashMap<BasicProduct, ConcurrentLinkedDeque<BasicReview>> load(Path path) {
		
		try (InputStream in = Files.newInputStream(path);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in))) {
			ConcurrentHashMap<BasicProduct, ConcurrentLinkedDeque<BasicReview>> products=new ConcurrentHashMap<>();
			
			String line;
			while  ((line=reader.readLine())!=null) {
				String tokens[]=line.split("::");
				BasicProduct product=new BasicProduct();
				product.setId(tokens[0]);
				product.setAsin(tokens[1]);
				product.setTitle(tokens[2]);

				String tokensReview[]=tokens[3].split(":");
				BasicReview review=new BasicReview();
				review.setDate(tokensReview[0]);
				review.setUser(tokensReview[1]);
				review.setValue(Short.valueOf(tokensReview[2]));
			
				ConcurrentLinkedDeque<BasicReview> reviews=products.get(product);

				if (reviews==null) {
					reviews=new ConcurrentLinkedDeque<BasicReview>();
					reviews.add(review);
					products.put(product, reviews);
				} else {
					reviews.add(review);
				}
				
			}
			return products;
			

		} catch (IOException x) {
			x.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
