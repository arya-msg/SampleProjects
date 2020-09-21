package com.ecom.promotionengine.service.domain;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3205007495437507242L;

	private List<Character> skuList;

	/**
	 * @return the skuList
	 */
	public List<Character> getSkuList() {
		return skuList;
	}

	/**
	 * @param skuList the skuList to set
	 */
	public void setSkuList(List<Character> skuList) {
		this.skuList = skuList;
	}

	@Override
	public String toString() {
		return "Cart [skuList=" + skuList + "]";
	}
	
}
