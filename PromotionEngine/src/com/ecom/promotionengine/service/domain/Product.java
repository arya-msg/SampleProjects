package com.ecom.promotionengine.service.domain;

import java.io.Serializable;

public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6819465321638515133L;
	
	private char skuId;
	private double price;
	public char getSkuId() {
		return skuId;
	}
	public void setSkuId(char skuId) {
		this.skuId = skuId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

}
