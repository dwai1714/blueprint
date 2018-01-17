package com.corvesta.keyspring.blueprint.service;

import java.util.List;

import com.corvesta.keyspring.blueprint.model.postgres.Item;

public interface ItemService {
	
	Item save(Item item);
	List<Item> getAll();
	List<Item> getAllByDescription(String description);
	Item findItemByProductId(Long productId);

}
