package com.javferna.packtpub.mastering.irsystem.serial;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;



public class SerialMain {

	public static void main(String[] args) throws IOException {


		String[] query1 = { "james", "bond" };
		String[] query2 = { "gone", "with", "the", "wind" };
		String[] query3 = { "rocky" };
		StringBuffer bufferResults = new StringBuffer();

		Date start, end;

		bufferResults.append("Version 1, query 1, concurrent\n");
		start = new Date();
		SerialSearch.basicSearch(query1);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 1, query 2, concurrent\n");
		start = new Date();
		SerialSearch.basicSearch(query2);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 1, query 3, concurrent\n");
		start = new Date();
		SerialSearch.basicSearch(query3);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 2, query 1, concurrent\n");
		start = new Date();
		SerialSearch.reducedSearch(query1);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 2, query 2, concurrent\n");
		start = new Date();
		SerialSearch.reducedSearch(query2);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 2, query 3, concurrent\n");
		start = new Date();
		SerialSearch.reducedSearch(query3);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 3, query 1, concurrent\n");
		start = new Date();
		SerialSearch.htmlSearch(query1, "query1_concurrent");
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 3, query 2, concurrent\n");
		start = new Date();
		SerialSearch.htmlSearch(query2, "query2_concurrent");
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 3, query 3, concurrent\n");
		start = new Date();
		SerialSearch.htmlSearch(query3, "query3_concurrent");
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		
		SerialInvertedIndex invertedIndex = new SerialInvertedIndex();
		SerialFileLoader loader = new SerialFileLoader();
		invertedIndex = loader.load(Paths.get("index","invertedIndex.txt"));
	
		bufferResults.append("Version 4, query 1, concurrent\n");
		start = new Date();
		SerialSearch.preloadSearch(query1, invertedIndex);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 4, query 2, concurrent\n");
		start = new Date();
		SerialSearch.preloadSearch(query2, invertedIndex);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 4, query 3, concurrent\n");
		start = new Date();
		SerialSearch.preloadSearch(query3, invertedIndex);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");
		
		ForkJoinPool pool = new ForkJoinPool();

		
		bufferResults.append("Version 5, query 1, concurrent\n");
		start = new Date();
		SerialSearch.executorSearch(query1, invertedIndex, pool);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 5, query 2, concurrent\n");
		start = new Date();
		SerialSearch.executorSearch(query2, invertedIndex, pool);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Version 5, query 3, concurrent\n");
		start = new Date();
		SerialSearch.executorSearch(query3, invertedIndex, pool);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");
	
		bufferResults.append("Inverted Index Data: Words in File version 1\n");
		start = new Date();
		SerialData.getWordsInFile1("27759897.txt", invertedIndex);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		bufferResults.append("Inverted Index Data: Words in File version 2\n");
		start = new Date();
		SerialData.getWordsInFile2("27759897.txt", invertedIndex);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");
		
		bufferResults.append("Inverted Index Data: Average Tfxidf in File \n");
		start = new Date();
		SerialData.getAverageTfxidf("27759897.txt",invertedIndex);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");		

		bufferResults.append("Inverted Index Data: Max Tfxidf\n");
		start = new Date();
		SerialData.maxTfxidf(invertedIndex);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");
		
		bufferResults.append("Inverted Index Data: Min Tfxidf\n");
		start = new Date();
		SerialData.minTfxidf(invertedIndex);
		end = new Date();
		bufferResults.append("Execution Time: "
				+ (end.getTime() - start.getTime()) + "\n");

		System.out.println("***************************");
		System.out.println(bufferResults.toString());
		System.out.println("***************************");

	}
}
