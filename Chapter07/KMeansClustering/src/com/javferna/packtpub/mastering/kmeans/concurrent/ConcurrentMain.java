package com.javferna.packtpub.mastering.kmeans.concurrent;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import com.javferna.packtpub.mastering.kmeans.common.data.VocabularyLoader;
import com.javferna.packtpub.mastering.kmeans.serial.Document;
import com.javferna.packtpub.mastering.kmeans.serial.DocumentCluster;
import com.javferna.packtpub.mastering.kmeans.serial.DocumentLoader;
import com.javferna.packtpub.mastering.kmeans.serial.SerialKMeans;

public class ConcurrentMain {

	public static void main(String[] args) throws IOException {
		
		Path pathVoc = Paths.get("data\\movies.words");
		Map<String, Integer> vocIndex=VocabularyLoader.load(pathVoc);
		System.out.println("Voc Size: "+vocIndex.size());
		
		Path pathDocs = Paths.get("data\\movies.data");
		Document[] documents=DocumentLoader.load(pathDocs, vocIndex);
		System.out.println("Document Size: "+documents.length);
		
		if (args.length != 3) {
			System.err.println("Please specify K, SEED, MIN_SIZE");
			return;
		}
		int K = Integer.valueOf(args[0]);
		int SEED = Integer.valueOf(args[1]);
		int MAX_SIZE = Integer.valueOf(args[2]);
	
		
		Date start, end;
		start = new Date();
		DocumentCluster[] clusters = ConcurrentKMeans.calculate(documents, K, vocIndex.size(), SEED, MAX_SIZE );
		end = new Date();
		System.out.println("K: " + K + "; SEED: " + SEED+"; MAX_SIZE: "+MAX_SIZE);
		System.out.println("Execution Time: " + (end.getTime() - start.getTime()));

		System.out.println(
				Arrays.stream(clusters).map(DocumentCluster::getDocumentCount).sorted(Comparator.reverseOrder())
						.map(Object::toString).collect(Collectors.joining(", ", "Cluster sizes: ", "")));

	}

}
