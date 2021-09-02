package com.postcode.au.api.service;

import java.util.List;

import com.postcode.au.api.entity.PostCodeRecord;
import com.postcode.au.api.repository.PostCodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCodeServicesImplementation implements PostCodeServices {

	@Autowired
	private PostCodeRepository postcodeRepository;

	@Override
	public List<PostCodeRecord> searchByPostCode(String postCode) {

		return postcodeRepository.findByPostCodeOrderByPostCodeAsc(postCode);
	}

	@Override
	public List<PostCodeRecord> searchBysuburb(String suburbName) {

		return postcodeRepository.findBySuburbOrderBySuburbAsc(suburbName);
	}

	@Override
	public PostCodeRecord saveNewRecord(PostCodeRecord record) {

		return postcodeRepository.save(record);
	}

	@Override
	public List<PostCodeRecord> searchAll() {
		return (List<PostCodeRecord>) postcodeRepository.findAll();
	}

}
