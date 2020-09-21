package com.ecom.promotionengine.service.domain;

import java.io.Serializable;

public class Promotion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6709017563932013140L;

	private String type;
	
	private String description;
	
	public Promotion(String type, String description) {
		super();
		this.type = type;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Promotion [type=" + type + ", description=" + description + "]";
	}

	

}
