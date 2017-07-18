package com.javferna.packtpub.mastering.irsystem.serial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.javferna.packtpub.mastering.irsystem.common.Token;

public class SerialFileLoader {

	public SerialInvertedIndex load(Path path) throws IOException {
		SerialInvertedIndex invertedIndex = new SerialInvertedIndex();
		List<Token> results=new ArrayList<>();
		
		try (Stream<String> fileStream = Files.lines(path)) {
			fileStream
			.flatMap(SerialSearch::limitedMapper)
			.forEach(results::add);
		}
		
		invertedIndex.setIndex(results);
		return invertedIndex;
	}
}
