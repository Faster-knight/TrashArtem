package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.demo.controllers.ApiRootController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DemoApplicationTests {
	@InjectMocks
	private ApiRootController apiRootController;
	@Mock
	private ApiRootService apiRootService;
	@Test
	public void testCheck() {
		// Пример модульного тестирования
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HashMap<String, Object> body = new HashMap<>();
		body.put("data", "Application work");
		ResponseEntity<HashMap<String, Object>> expectedResponse = new ResponseEntity<>(body, headers, HttpStatus.OK);
		ResponseEntity<HashMap<String, Object>> actualResponse = apiRootController.check();
		assertEquals(expectedResponse, actualResponse);
	}
}
