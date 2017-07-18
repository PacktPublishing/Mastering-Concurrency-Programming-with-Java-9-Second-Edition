package com.javferna.packtpub.book.mastering.searchindex.data;

import java.util.HashMap;
import java.util.List;

import com.javferna.packtpub.book.mastering.searchindex.common.IntermediateResultIndex;

public class InvertedIndex {

	private IntermediateResultIndex index[];
	private HashMap<Integer, String> vocabulary;

	public void setIndex(List<IntermediateResultIndex> index) {
		this.index = new IntermediateResultIndex[index.size()];
		this.index = index.toArray(this.index);
	}

	public void setVocabulary(HashMap<Integer, String> vocabulary) {
		this.vocabulary = vocabulary;
	}

	public IntermediateResultIndex[] getIndex() {
		return index;
	}

	public HashMap<Integer, String> getVocabulary() {
		return vocabulary;
	}

}
