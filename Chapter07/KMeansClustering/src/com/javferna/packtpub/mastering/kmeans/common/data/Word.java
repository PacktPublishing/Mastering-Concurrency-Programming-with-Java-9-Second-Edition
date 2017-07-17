package com.javferna.packtpub.mastering.kmeans.common.data;

public class Word implements Comparable<Word> {

	private int index;
	private double tfidf;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getTfidf() {
		return tfidf;
	}

	public void setTfidf(double tfidf) {
		this.tfidf = tfidf;
	}

	@Override
	public int compareTo(Word o) {
		if (this.getIndex() < o.getIndex()) {
			return -1;
		} else if (this.getIndex() > o.getIndex()) {
			return 1;
		}
		return 0;
	}

}
