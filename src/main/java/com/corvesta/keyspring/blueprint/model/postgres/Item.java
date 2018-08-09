package com.corvesta.keyspring.blueprint.model.postgres;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "T_ITEM")
@Data

public class Item implements Serializable{
	public Item(String description, ObjectNode urls) {
		super();
		this.description = description;
	}

	public Item() {
		super();
	}

	// Although table name is Item and id is product_id we tried to be consistent in
	// our entity definition
	// These will be your Postgres Tables

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true, nullable = false)
	Long itemtId;
	String description;
	


	
	

}
