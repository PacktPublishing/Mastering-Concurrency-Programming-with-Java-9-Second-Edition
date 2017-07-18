package com.javferna.packtpub.mastering.irsystem.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryResult {
	
	private Map<String, Document> results;
	
	public QueryResult(Map<String, Document> results) {
		this.results=results;
	}

	public void append(Token token) {
		results.computeIfAbsent(token.getFile(), s -> new Document(s)).addTfxidf(token.getTfxidf());
	}

	public Map<String, Document> getResults() {
		return results;
	}

	public List<Document> getAsList() {
		return new ArrayList<>(results.values());
	}
	
	

}
