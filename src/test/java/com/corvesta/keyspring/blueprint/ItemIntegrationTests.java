package com.corvesta.keyspring.blueprint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.corvesta.keyspring.blueprint.dao.postgres.ItemRepository;
import com.corvesta.keyspring.blueprint.model.postgres.Item;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BlueprintApplication.class)
@AutoConfigureMockMvc
public class ItemIntegrationTests {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@org.springframework.boot.web.server.LocalServerPort
	private int port;

	private URL base;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ItemRepository repository;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private void createTestItem() {
		Item item = new Item();
		item.setDescription("Test Item");
		repository.save(item);
	}

	@Test
	public void givenItems_whenGetItems_thenStatus200() throws Exception {

		createTestItem();

		mvc.perform(get("/api/items").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].description", is("Test Item")));
	}

	@Test
	public void postTest() throws Exception {
		logger.debug("Testing attribute Controller method for adding Class");
		Item item = new Item();
		item.setDescription("Test Item from Post");

		String url = this.base.toString() + "api/items";

		ResponseEntity<Item> response = testRestTemplate.postForEntity(url, item, Item.class);
		logger.info("Post Status Code " + response.getStatusCode().toString());

		assertThat(response.getStatusCode().is2xxSuccessful());

	}
}
