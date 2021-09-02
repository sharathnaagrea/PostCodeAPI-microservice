package com.postcode.au.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.postcode.au.api.model.AuthenticationRequest;
import com.postcode.au.api.model.AuthenticationResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostcodeApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private HttpEntity<?> requEntity;

	@BeforeEach
	void setUp() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",
				"Bearer  eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjMwNTkzODk2LCJpYXQiOjE2MzA1NzU4OTZ9.Rl900xmztaVXrW7XkF_ovMcgtdrA3_cu9fcW6CD4_oDokc5oxKlNMNxFphtaczbvgPhoss0Ikj32tJV71nFSHA");
		requEntity = new HttpEntity<>(headers);
	}

	@Test
	public void welcomePageTest() {
		ResponseEntity<String> response = this.restTemplate.exchange("/welcome", HttpMethod.GET, requEntity,
				String.class);
		assertThat(response.toString()).contains("Welcome to Post Code API");
	}

	@Test
	public void resourceNotFoundPathTest() {
		ResponseEntity<String> response = this.restTemplate.exchange("/api/v1/test", HttpMethod.GET, requEntity,
				String.class);
		assertThat(response.toString()).contains("Not Found");
	}

	@Test
	public void searchByPostCodeAPITest() {
		ResponseEntity<String> response = this.restTemplate.exchange("/api/v1/postcode/3000", HttpMethod.GET,
				requEntity, String.class);
		// Check suburb for postcode 3000
		assertThat(response.toString()).contains("MELBOURNE");
	}

	@Test
	public void searchBysuburbAPITest() {
		ResponseEntity<String> response = this.restTemplate.exchange("/api/v1/suburb/MELBOURNE", HttpMethod.GET,
				requEntity, String.class);
		// test if Melbourne Post Code is 3000
		assertThat(response.toString()).contains("3000");
	}

	@Test
	public void forbiddenPostCodeAPITest() {
		String body = this.restTemplate.getForObject("/api/v1/postcode/3000", String.class);
		assertThat(body).contains("Forbidden");
	}

}
