package com.rest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import com.rest.entity.Product;
import com.rest.repository.TestH2Repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductRestAppApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;

	@Autowired
	private TestH2Repository testH2Repository;

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/products");
	}

	@Test
	public void testAddProduct() {
		Product product = new Product("headset", 2999);
		Product response = restTemplate.postForObject(baseUrl, product, Product.class);
		assertEquals("headset", response.getName());
		assertEquals(1, testH2Repository.findAll().size());
	}

	@SuppressWarnings("unchecked")
	@Test
	@Sql(statements = "INSERT INTO PRODUCT (id,name, price) VALUES (4,'AC', 34000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM PRODUCT WHERE name='AC'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetProducts() {
		List<Product> products = restTemplate.getForObject(baseUrl, List.class);
		assertEquals(1, products.size());
		assertEquals(1, testH2Repository.findAll().size());
	}

	@Test
	@Sql(statements = "INSERT INTO PRODUCT (id,name, price) VALUES (2,'books', 1499)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeleteProduct() {
		int recordCount = testH2Repository.findAll().size();
		assertEquals(1, recordCount);
		restTemplate.delete(baseUrl + "/delete/{id}", 8);
		assertEquals(0, testH2Repository.findAll().size());

	}
}
