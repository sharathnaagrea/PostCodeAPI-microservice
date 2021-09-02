package com.postcode.au.api.entity;

import java.util.List;

public class SearchResult {

	private APIStatus status;

	private List<PostCodeRecord> result;

	public APIStatus getStatus() {
		return status;
	}

	public void setStatus(APIStatus status) {
		this.status = status;
	}

	public List<PostCodeRecord> getResult() {
		return result;
	}

	public void setResult(List<PostCodeRecord> result) {
		this.result = result;
	}
}
