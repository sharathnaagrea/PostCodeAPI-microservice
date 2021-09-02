package com.postcode.au.api.service;

import java.util.List;

import com.postcode.au.api.entity.APIStatus;
import com.postcode.au.api.entity.PostCodeRecord;

public interface PostCodeServices {
	public static final APIStatus API_SUCCESS_STATUS = new APIStatus("API-200", "Success");

	public List<PostCodeRecord> searchByPostCode(String postCode);

	public List<PostCodeRecord> searchBysuburb(String suburbName);

	public PostCodeRecord saveNewRecord(PostCodeRecord record);

	public List<PostCodeRecord> searchAll();
}
