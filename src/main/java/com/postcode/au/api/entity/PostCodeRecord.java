package com.postcode.au.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUBURB_POSTCODE")
public class PostCodeRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	private String postCode;
	private String suburb;
	private String state;

	public PostCodeRecord() {
		super();
	}

	public PostCodeRecord(String postCode, String suburb, String state) {
		super();
		this.postCode = postCode;
		this.suburb = suburb;
		this.state = state;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
