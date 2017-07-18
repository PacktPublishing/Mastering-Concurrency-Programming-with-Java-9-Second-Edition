package com.javferna.packtpub.book.mastering.structures.hash.data;

public class BasicProduct implements Comparable<BasicProduct>{

	private String id;
	private String asin;
	private String title;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAsin() {
		return asin;
	}
	
	public void setAsin(String asin) {
		this.asin = asin;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int compareTo(BasicProduct other) {
		System.out.println(other.getAsin().compareTo(getAsin()));
		return other.getAsin().compareTo(getAsin());
	}

	@Override
	public boolean equals(Object object) {
		BasicProduct other=(BasicProduct)object;
		return asin.equals(other.getAsin());
	}

	@Override
	public int hashCode() {
		return asin.hashCode();
	}
	
	
	
	

}
