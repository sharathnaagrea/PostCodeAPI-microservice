package com.postcode.au.api.repository;

import java.util.List;

import com.postcode.au.api.entity.PostCodeRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCodeRepository extends JpaRepository<PostCodeRecord, Long> {
	public List<PostCodeRecord> findByPostCodeOrderByPostCodeAsc(String postCode);

	public List<PostCodeRecord> findBySuburbOrderBySuburbAsc(String suburb);

	public List<PostCodeRecord> findByStateOrderByStateAsc(String state);

	public List<PostCodeRecord> findBySuburbAndStateOrderBySuburbAsc(String suburb, String state);

}
