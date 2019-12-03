package com.huupham.models;

public class RangePrice {

	private int minPrice;
	private int maxPrice;

	public RangePrice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RangePrice(int minPrice, int maxPrice) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

}
