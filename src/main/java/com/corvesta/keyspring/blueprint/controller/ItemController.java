package com.corvesta.keyspring.blueprint.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.corvesta.keyspring.blueprint.model.postgres.Item;
import com.corvesta.keyspring.blueprint.service.ItemServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/items")

public class ItemController {

	@Autowired
	private ItemServiceImpl itemService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(httpMethod = "POST", value = "The API for saving a product ")

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Item saveProduct(@RequestBody Item product) {

		logger.debug("Invoking saveProduct Call in Controller");
		Item savedProduct = itemService.save(product);
		return savedProduct;

	}

	@PreAuthorize("hasAuthority('ROLE_READ')")
	@RequestMapping(method = RequestMethod.GET)
	public List<Item> list() {
		logger.debug("Invoking getAll Call in Controller");
		return itemService.getAll();
	}

	@RequestMapping(value = "desc/{desc}", method = RequestMethod.GET)
	public List<Item> get(@PathVariable String desc) {
		return itemService.getAllByDescription(desc);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getItemById(@PathVariable Long id) {
			return new ResponseEntity<Item>(itemService.findItemByProductId(id), HttpStatus.OK);
	}

}
