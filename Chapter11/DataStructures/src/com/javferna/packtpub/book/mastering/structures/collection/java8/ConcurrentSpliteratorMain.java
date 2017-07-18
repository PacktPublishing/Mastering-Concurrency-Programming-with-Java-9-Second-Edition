package com.javferna.packtpub.book.mastering.structures.collection.java8;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Spliterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.javferna.packtpub.book.mastering.structures.hash.data.Product;
import com.javferna.packtpub.book.mastering.structures.hash.data.ProductLoader;

public class ConcurrentSpliteratorMain {

	public static void main(String[] args) {
		Path file = Paths.get("data\\category");

		ArrayList<Product> productList;
		try {
			productList = Files.walk(file, FileVisitOption.FOLLOW_LINKS).parallel()
					.filter(f -> f.toString().endsWith(".txt")).collect(ArrayList<Product>::new,
							(list,path) -> list.add(ProductLoader.load(path)), ArrayList::addAll);
			

			Spliterator<Product> split1=productList.spliterator();
			
			System.out.println(split1.hasCharacteristics(Spliterator.CONCURRENT));
			System.out.println(split1.hasCharacteristics(Spliterator.SUBSIZED));
			System.out.println(split1.estimateSize());
			
			Spliterator<Product> split2=split1.trySplit();
			
			System.out.println(split1.estimateSize());
			System.out.println(split2.estimateSize());

			ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
			executor.execute(new SpliteratorTask(split1));
			executor.execute(new SpliteratorTask(split2));
			executor.shutdown();
			
			executor.awaitTermination(1, TimeUnit.DAYS);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
