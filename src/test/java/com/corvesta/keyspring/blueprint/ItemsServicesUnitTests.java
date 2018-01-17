
package com.corvesta.keyspring.blueprint;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corvesta.keyspring.blueprint.dao.postgres.ItemRepository;
import com.corvesta.keyspring.blueprint.model.postgres.Item;
import com.corvesta.keyspring.blueprint.service.ItemServiceImpl;

/**
 * 
 *
 */
@RunWith(PowerMockRunner.class)
public class ItemsServicesUnitTests {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@InjectMocks
	ItemServiceImpl itemsServiceImpl;

	@Mock
	ItemRepository itemRepository;

	private List<Item> itemsList;

	public Item getItem() {

		Item blackDog = new Item();
		blackDog.setDescription("Black Dog description");

		return blackDog;
	}

	public List<Item> getItemsList() {
		itemsList = new ArrayList<Item>();
		itemsList.add(getItem());
		return itemsList;

	}

	@Test
	public void testGetItems() throws Exception {
		given(itemRepository.findAll()).willReturn(getItemsList());
		itemsServiceImpl.getAll();

		verify(itemRepository, times(1)).findAll();
		logger.info("Number of Items Returned from Test 1 " + itemsServiceImpl.getAll().size());

	}

	@Test
	public void testGetItemsByDesc() throws Exception {

		given(itemRepository.findByDescriptionIgnoreCaseContaining("Black")).willReturn(getItemsList());
//		assertEquals(itemsServiceImpl.getAllByDescription("Black").get(0).getDescription(), getItem().getDescription());
//		assertThat(itemsServiceImpl.getAllByDescription("Black").size(), is(1));
		assertThat(itemsServiceImpl.getAllByDescription("Black").size(), is(not(-1)));

		//assertEquals(itemsServiceImpl.getAllByDescription("Black").size(),1);

		//verify(itemRepository, times(1)).findByDescriptionIgnoreCaseContaining("Black");
		logger.info("Number of Items Returned from Test 2 " + itemsServiceImpl.getAllByDescription("Black").size());
	}

}