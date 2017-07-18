package com.javferna.packtpub.book.mastering.structures.hash.data;


public class ExtendedProduct extends Product {

	private String buyer;
	private short value;

	public ExtendedProduct(Product source, String buyer, short value) {
		this.setId(source.getId());
		this.setAsin(source.getAsin());
		this.setTitle(source.getTitle());
		this.setGroup(source.getGroup());
		this.setSalesrank(source.getSalesrank());
		this.setSimilar(source.getSimilar());
		this.setCategories(source.getCategories());
		this.setReviews(source.getReviews());
		this.buyer=buyer;
		this.value=value;
	}
	
	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}
	
}
