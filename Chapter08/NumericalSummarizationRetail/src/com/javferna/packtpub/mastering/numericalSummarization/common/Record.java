package com.javferna.packtpub.mastering.numericalSummarization.common;

public class Record {
	
	private String id;
	private String stockCode;
	private String description;
	private int quantity;
	private String date;
	private Double unitPrice;
	private String customer;
	private String country;
	
	public Record(String[] data) {
		this.id=data[0];
		this.stockCode=data[1];
		this.description=data[2];
		this.quantity=Integer.valueOf(data[3]);
		this.date=data[4];
		this.unitPrice=Double.valueOf(data[5]);
		this.customer=data[6];
		this.country=data[7];
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
