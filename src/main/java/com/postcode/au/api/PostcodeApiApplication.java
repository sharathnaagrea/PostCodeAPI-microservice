package com.postcode.au.api;

import com.postcode.au.api.repository.PostCodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.postcode.au.api.entity.PostCodeRecord;

@SpringBootApplication
public class PostcodeApiApplication implements CommandLineRunner {

	@Autowired
	private PostCodeRepository postCodeRepository;

	private static final Logger logger = LoggerFactory.getLogger(PostcodeApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PostcodeApiApplication.class, args);
	}

	/**
	 * Adding Default Postcode and suburb when starting the Micro Services
	 * 
	 */
	@Override
	public void run(String... strings) throws Exception {
		List<PostCodeRecord> records = new ArrayList<>();
		records.add(new PostCodeRecord("3000", "MELBOURNE", "VIC"));
		records.add(new PostCodeRecord("3182", "ST KILDA WEST", "VIC"));
		records.add(new PostCodeRecord("3024", "WYNDHAM VALE", "VIC"));

		postCodeRepository.saveAll(records);

		logger.info("Looking to load default australian post code...");
		for (PostCodeRecord record : postCodeRepository.findAll()) {
			logger.info(record.getPostCode());
		}
	}

}
