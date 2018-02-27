package com.corvesta.keyspring.blueprint.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.corvesta.keyspring.blueprint.dao.postgres.ItemJdbcRepository;
import com.corvesta.keyspring.blueprint.dao.postgres.ItemRepository;
import com.corvesta.keyspring.blueprint.model.postgres.Item;

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



	@Override
	public Item findItemByProductId(Long productId) {
		logger.debug("Getting one Product with Id service is invoked");

		return itemJdbcRepository.findItemByProductId(productId);
	}

}
