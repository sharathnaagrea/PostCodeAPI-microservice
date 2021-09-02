package com.postcode.au.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.postcode.au.api.entity.PostCodeRecord;
import com.postcode.au.api.repository.PostCodeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostCodeRepositoryTest {
	@Autowired
	private PostCodeRepository postcodeRepo;

	/**
	 * By defualt we adding Melbourne suburb recored when starting spring context
	 */
	@Test
	public void testFindByPostCode() {
		List<PostCodeRecord> testList = this.postcodeRepo.findByPostCodeOrderByPostCodeAsc("3000");
		assertThat(testList.size() == 1);
		assertThat(testList.get(0).getPostCode().equals("3000"));
		assertThat(testList.get(0).getState().equals("VIC"));
		assertThat(testList.get(0).getSuburb().equals("MELBOURNE"));

	}

	@Test
	public void testFindBysuburb() {
		List<PostCodeRecord> testList = this.postcodeRepo.findBySuburbOrderBySuburbAsc("MELBOURNE");
		assertThat(testList.size() == 1);
		assertThat(testList.get(0).getPostCode().equals("3000"));
		assertThat(testList.get(0).getState().equals("VIC"));
		assertThat(testList.get(0).getSuburb().equals("MELBOURNE"));

	}
}
