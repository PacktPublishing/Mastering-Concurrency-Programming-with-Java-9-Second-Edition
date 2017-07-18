package com.javferna.packtpub.book.mastering.searchindex.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.javferna.packtpub.book.mastering.searchindex.common.IntermediateResult;
import com.javferna.packtpub.book.mastering.searchindex.common.IntermediateResultIndex;

public class FileLoader {

	private int wordIndex;
	
	public FileLoader() {
		wordIndex=0;
	}
	
	public InvertedIndex load(String path) {
		InvertedIndex invertedIndex = new InvertedIndex();
		Path file = Paths.get(path);
		List<IntermediateResultIndex> results = new ArrayList<>();
		HashMap<Integer, String> vocabulary = new HashMap<Integer, String>();
		
		
		try (InputStream in = Files.newInputStream(file);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String tokens[] = line.split(",");
				String word = tokens[0].split(":")[0];
				int index=getIndexFromWord(word,vocabulary);
				for (int i = 1; i < tokens.length; i++) {
					String fileInfo[] = tokens[i].split(":");

					IntermediateResultIndex result = new IntermediateResultIndex();
					result.setWord(index);
					result.setFile(convertToInteger(fileInfo[0]));
					result.setTfxidf(Double.valueOf(fileInfo[1]));
					results.add(result);
					
					if (i==100) {
						break;
					}
				}
				
			}
		} catch (IOException x) {
			x.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		invertedIndex.setIndex(results);
		invertedIndex.setVocabulary(vocabulary);
		return invertedIndex;
	}

	private int getIndexFromWord(String word, HashMap<Integer, String> vocabulary) {
		int index=wordIndex;
		vocabulary.put(wordIndex++, word);
		return index;
	}

	private int convertToInteger(String file) {
		int index=file.indexOf('.');
		int value=Integer.valueOf(file.substring(0,index));
		return value;
	}
}
