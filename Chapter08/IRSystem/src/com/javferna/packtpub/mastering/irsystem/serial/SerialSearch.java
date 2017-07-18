package com.javferna.packtpub.mastering.irsystem.serial;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

import com.javferna.packtpub.mastering.irsystem.common.ContentMapper;
import com.javferna.packtpub.mastering.irsystem.common.QueryResult;
import com.javferna.packtpub.mastering.irsystem.common.Token;
import com.javferna.packtpub.mastering.irsystem.common.Utils;

public class SerialSearch {

	public static void basicSearch(String query[]) throws IOException {

		Path path = Paths.get("index", "invertedIndex.txt");
		HashSet<String> set = new HashSet<>(Arrays.asList(query));
		QueryResult results = new QueryResult(new HashMap<>());

		try (Stream<String> invertedIndex = Files.lines(path)) {

			invertedIndex
				.filter(line -> set.contains(Utils.getWord(line)))
				.flatMap(SerialSearch::basicMapper)
				.forEach(results::append);

			results
				.getAsList()
				.stream()
				.sorted()
				.limit(100)
				.forEach(System.out::println);

			System.out.println("Basic Search Ok");
		}

	}

	public static void reducedSearch(String query[]) throws IOException {
		Path path = Paths.get("index", "invertedIndex.txt");
		HashSet<String> set = new HashSet<>(Arrays.asList(query));
		QueryResult results = new QueryResult(new HashMap<>());

		// Version 2
		try (Stream<String> invertedIndex = Files.lines(path)) {

			invertedIndex
				.filter(line -> set.contains(Utils.getWord(line)))
				.flatMap(SerialSearch::limitedMapper)
				.forEach(results::append);

			results
				.getAsList()
				.stream()
				.sorted()
				.limit(100)
				.forEach(System.out::println);

			System.out.println("Reduced Search Ok ");
		}

	}

	public static void htmlSearch(String query[], String fileName) throws IOException {
		Path path = Paths.get("index", "invertedIndex.txt");
		HashSet<String> set = new HashSet<>(Arrays.asList(query));
		QueryResult results = new QueryResult(new HashMap<>());

		// Version 3
		try (Stream<String> invertedIndex = Files.lines(path)) {

			invertedIndex
				.filter(line -> set.contains(Utils.getWord(line)))
				.flatMap(SerialSearch::limitedMapper)
				.forEach(results::append);

			path = Paths.get("output", fileName + "_results.html");
			try (BufferedWriter fileWriter = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {

				fileWriter.write("<HTML>");
				fileWriter.write("<HEAD>");
				fileWriter.write("<TITLE>");
				fileWriter.write("Search Results with Streams");
				fileWriter.write("</TITLE>");
				fileWriter.write("</HEAD>");
				fileWriter.write("<BODY>");
				fileWriter.newLine();

				results.getAsList()
					.stream()
					.sorted()
					.limit(100)
					.map(new ContentMapper(query)).forEach(l -> {
						try {
							fileWriter.write(l);
							fileWriter.newLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
				});

				fileWriter.write("</BODY>");
				fileWriter.write("</HTML>");

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Html Search Ok ");
		}

	}

	public static void preloadSearch(String[] query, SerialInvertedIndex invertedIndex) {

		HashSet<String> set = new HashSet<>(Arrays.asList(query));
		QueryResult results = new QueryResult(new HashMap<>());

		// Version 4
		invertedIndex.getIndex()
			.stream()
			.filter(token -> set.contains(token.getWord()))
			.forEach(results::append);

		results
			.getAsList()
			.stream()
			.sorted()
			.limit(100)
			.forEach(System.out::println);

		System.out.println("Preload Search Ok.");
	}

	public static void executorSearch(String[] query, SerialInvertedIndex invertedIndex, ForkJoinPool pool) {
		HashSet<String> set = new HashSet<>(Arrays.asList(query));
		QueryResult results = new QueryResult(new ConcurrentHashMap<>());

		pool.submit(() -> {
			invertedIndex.getIndex()
				.stream()
				.filter(token -> set.contains(token.getWord()))
				.forEach(results::append);

			results
				.getAsList()
				.stream()
				.sorted()
				.limit(100)
				.forEach(System.out::println);
		}).join();

		System.out.println("Executor Search Ok.");
	
	}

	public static Stream<Token> basicMapper(String input) {
		Stream.Builder<Token> stream = Stream.builder();
		String word = Utils.getWord(input);

		Arrays.stream(input.split(",")).skip(1).forEach(token -> stream.add(new Token(word, token)));

		return stream.build();
	}

	public static Stream<Token> limitedMapper(String input) {
		Stream.Builder<Token> stream = Stream.builder();
		String word = Utils.getWord(input);

		Arrays.stream(input.split(","))
			.skip(1)
			.limit(100)
			.forEach(token -> {
				stream.add(new Token(word, token));
			});

		return stream.build();
	}
}
