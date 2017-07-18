package com.javferna.packtpub.book.mastering.synchronization.data;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private String id;
	private String asin;
	private String title;
	private String group;
	private long salesrank;
	private String similar;
	private List<String> categories;
	private List<Review> reviews;

	
	
	public Product() {
		categories=new ArrayList<String>();
		reviews=new ArrayList<Review>();
	}
	
	public void addCategory(String category) {
		categories.add(category);
	}
	
	public void addReview(Review review) {
		reviews.add(review);
	}
	
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
	
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public long getSalesrank() {
		return salesrank;
	}
	
	public void setSalesrank(long salesrank) {
		this.salesrank = salesrank;
	}
	
	public String getSimilar() {
		return similar;
	}
	
	public void setSimilar(String similar) {
		this.similar = similar;
	}
	
	public List<String> getCategories() {
		return categories;
	}
	
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

}
