package com.corvesta.keyspring.blueprint.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.corvesta.keyspring.blueprint.dao.postgres.ItemJdbcRepository;
import com.corvesta.keyspring.blueprint.dao.postgres.ItemRepository;
import com.corvesta.keyspring.blueprint.model.postgres.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)

public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemJdbcRepository itemJdbcRepository; // Usage of JDBC Template


	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Transactional
	public Item save(Item item) {

		logger.debug("Item save service is invoked");

		Item savedProduct = itemRepository.save(item);
		try {
			sendProductMessage(savedProduct); // After saving to DB Item is sent also to the rabbit queue called blueprint-message-queue
		} catch (Exception e) {
			System.out.println("Am I coming here");

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Item save service is sucess");
		return savedProduct;
	}

	public List<Item> getAll() {

		logger.debug("Getting All Products  service is invoked");
		List<Item> products = itemRepository.findAll();

		return products;
	}

	/*
	 * This method is to show how to use the JPA repository to get results with just
	 * naming convention and no query
	 */

	public List<Item> getAllByDescription(String description) {

		logger.debug("Getting All Products with Description service is invoked");

		List<Item> items = itemRepository.findByDescriptionIgnoreCaseContaining(description);

		return items;
	}

	public void sendProductMessage(Item item) throws Exception {
		Map<String, String> itemMap = new HashMap<>();
		ObjectMapper oMapper = new ObjectMapper();
		itemMap = oMapper.convertValue(item, Map.class); // Converting to Hashmap to put to queue
		String itemString = oMapper.writeValueAsString(item);
		logger.debug("Sending the index request through queue message >> " + item.getItemtId().toString());
		logger.debug("Item as String >> " + itemString);
		//rabbitTemplate.convertAndSend(RabbitConfig.BP_MESSAGE_QUEUE, itemMap);
//		producer.send(itemString);
//		consumer.processMessage(itemString);

	}

	@Override
	public Item findItemByProductId(Long productId) {
		logger.debug("Getting one Product with Id service is invoked");

		return itemJdbcRepository.findItemByProductId(productId);
	}

}
