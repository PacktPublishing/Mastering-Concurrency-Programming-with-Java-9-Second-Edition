package com.javferna.packtpub.book.mastering.synchronization.completable;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.javferna.packtpub.book.mastering.synchronization.data.Product;

public class CompletableMain {

	public static void main(String[] args) {
		Path file = Paths.get("data","category");


		System.out.println(new Date() + ": Main: Loading products after three seconds....");
		LoadTask loadTask = new LoadTask(file);
		CompletableFuture<List<Product>> loadFuture = CompletableFuture
				.supplyAsync(loadTask,CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS));

		System.out.println(new Date() + ": Main: Then apply for search....");

		CompletableFuture<List<Product>> completableSearch = loadFuture
				.thenApplyAsync(new SearchTask("love"));

		CompletableFuture<Void> completableWrite = completableSearch
				.thenAcceptAsync(new WriteTask());
		
		completableWrite.exceptionally(ex -> {
			System.out.println(new Date() + ": Main: Exception "
					+ ex.getMessage());
			return null;
		});

		System.out.println(new Date() + ": Main: Then apply for users....");

		CompletableFuture<List<String>> completableUsers = loadFuture
				.thenApplyAsync(resultList -> {

					System.out.println(new Date()
							+ ": Main: Completable users: start");
					List<String> users = resultList.stream()
							.flatMap(p -> p.getReviews().stream())
							.map(review -> review.getUser())
							.distinct()
							.collect(Collectors.toList());
					System.out.println(new Date()
							+ ": Main: Completable users: end");
					return users;

				});

		System.out.println(new Date()
				+ ": Main: Then apply for best rated product....");

		CompletableFuture<Product> completableProduct = loadFuture
				.thenApplyAsync(resultList -> {
					Product maxProduct = null;
					double maxScore = 0.0;

					System.out.println(new Date()
							+ ": Main: Completable product: start");
					for (Product product : resultList) {
						if (!product.getReviews().isEmpty()) {
							double score = product.getReviews().stream()
									.mapToDouble(review -> review.getValue())
									.average().getAsDouble();
							if (score > maxScore) {
								maxProduct = product;
								maxScore = score;
							}
						}
					}
					System.out.println(new Date()
							+ ": Main: Completable product: end");
					return maxProduct;
				});

		System.out.println(new Date()
				+ ": Main: Then apply for best selling product....");
		CompletableFuture<Product> completableBestSellingProduct = loadFuture
				.thenApplyAsync(resultList -> {
					System.out.println(new Date()
							+ ": Main: Completable best selling: start");
					Product bestProduct = resultList.stream().min(Comparator.comparingLong(Product::getSalesrank)).orElse(null);
					System.out.println(new Date()
							+ ": Main: Completable best selling: end");
					return bestProduct;

				});

		CompletableFuture<String> completableProductResult = completableBestSellingProduct
				.thenCombineAsync(
						completableProduct,
						(bestSellingProduct, bestRatedProduct) -> {
							System.out
									.println(new Date()
											+ ": Main: Completable product result: start");
							String ret = "The best selling product is "
									+ bestSellingProduct.getTitle() + "\n";
							ret += "The best rated product is "
									+ bestRatedProduct.getTitle();
							System.out
									.println(new Date()
											+ ": Main: Completable product result: end");
							return ret;
						});
		

		System.out.println(new Date() + ": Main: Waiting for results");
		
		completableProductResult.completeOnTimeout("TimeOut", 1, TimeUnit.SECONDS);

		
		CompletableFuture<Void> finalCompletableFuture = CompletableFuture
				.allOf(completableProductResult, completableUsers,
						completableWrite);
		finalCompletableFuture.join();
		
		try {
			System.out.println("Number of loaded products: "
					+ loadFuture.get().size());
			System.out.println("Number of found products: "
					+ completableSearch.get().size());
			System.out.println("Number of users: "
					+ completableUsers.get().size());
			System.out.println("Best rated product: "
					+ completableProduct.get().getTitle());
			System.out.println("Best selling product: "
					+ completableBestSellingProduct.get().getTitle());
			System.out.println("Product result: "+completableProductResult.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println(new Date() + ": Main: end");

	}

}
