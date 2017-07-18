package com.javferna.packtpub.book.mastering.synchronization.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProductLoader {

	public static Product load(Path path) {
		try (InputStream in = Files.newInputStream(path);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in))) {
			Product product=new Product();
			String line=reader.readLine();
			product.setId(line.split(":")[1]);
			line=reader.readLine();
			product.setAsin(line.split(":")[1]);
			line=reader.readLine();
			product.setTitle(line.split(":")[1]);
			line=reader.readLine();
			product.setGroup(line.split(":")[1]);
			line=reader.readLine();
			product.setSalesrank(Long.valueOf(line.split(":")[1]));
			line=reader.readLine();
			product.setSimilar(line.split(":")[1]);
			line=reader.readLine();
			
			int numItems=Integer.valueOf(line.split(":")[1]);
			
			for (int i=0; i<numItems; i++) {
				line=reader.readLine();
				product.addCategory(line.split(":")[1]);
			}
			
			line=reader.readLine();
			numItems=Integer.valueOf(line.split(":")[1]);
			for (int i=0; i<numItems; i++) {
				line=reader.readLine();
				String tokens[]=line.split(":");
				Review review=new Review();
				review.setUser(tokens[1]);
				review.setValue(Short.valueOf(tokens[2]));
				product.addReview(review);
			}
			return product;

		} catch (IOException x) {
			x.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
