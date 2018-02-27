package com.corvesta.keyspring.blueprint;

import com.corvesta.keyspring.blueprint.controller.ItemController;
import com.corvesta.keyspring.blueprint.model.postgres.Item;
import com.corvesta.keyspring.blueprint.service.ItemServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { ItemController.class }, secure = false)
public class ItemIntegrationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	ItemServiceImpl itemService;

	@Before
	public void setup() {
		List<Item> someEntitys = new ArrayList<>();
		Item item = new Item();
		item.setItemtId(new Long(1));
		item.setDescription("Test Item");
		someEntitys.add(item);
		when(itemService.getAll()).thenReturn(someEntitys);
	}
	@Test
	public void getItems() throws Exception {
		mvc.perform(get("/api/items"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].description", is("Test Item")));
	}

	@Test
	public void postItems() throws Exception {
		Item item = new Item();
		item.setDescription("Test Item from Post");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(item);

		mvc.perform(post("/api/items")
				.contentType(APPLICATION_JSON_UTF8)
				.content(requestJson))
				.andExpect(status().isOk());
	}

}
